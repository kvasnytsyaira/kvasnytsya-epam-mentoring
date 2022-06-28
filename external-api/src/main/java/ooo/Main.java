package ooo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static void main(String[] args) throws URISyntaxException {

//        TypeReference<List<Photo>> typeReference = new TypeReference<List<Photo>>() {
//        };

        RestTemplate restTemplate = new RestTemplate();
        Photos forObject = restTemplate.getForObject(
                new URI("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=15&api_key=DEMO_KEY"), Photos.class);


        Function<String, Long> function = link ->
                restTemplate.headForHeaders(
                        restTemplate.headForHeaders(link).getLocation())
                        .getContentLength();

        forObject.getPhotos().parallelStream()
                .map(Photo::getImgSrc)
                .max(Comparator.comparing(function))
                .ifPresent(link -> System.out.println(link + "\n size " + function));

    }
}
