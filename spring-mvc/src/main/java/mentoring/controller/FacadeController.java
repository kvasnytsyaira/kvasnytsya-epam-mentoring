package mentoring.controller;

import mentoring.model.User1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

@Controller
public class FacadeController {

    /**
     * Simply selects the home view to render by returning its name.
     */
    @GetMapping("/welcome")
    public void home(Locale locale, Model model, HttpServletResponse httpServletResponse) throws IOException {
        System.out.println("Home Page Requested, locale = " + locale);
//        httpServletResponse.setContentType(");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println("{\"Hello world from events!\"}");

    }

    @GetMapping("/")
    public String welcome(Locale locale, Model model, HttpServletResponse httpServletResponse) throws IOException {
//        System.out.println("Home Page Requested, locale = " + locale);
////        httpServletResponse.setContentType(");
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        PrintWriter writer = httpServletResponse.getWriter();
//        writer.println("{\"Hello world from events!\"}");
        return "welcome";
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String user(@Validated User1 user, Model model) {
        System.out.println("User Page Requested");
        model.addAttribute("userName", user.getUserName());
        return "user";
    }
}