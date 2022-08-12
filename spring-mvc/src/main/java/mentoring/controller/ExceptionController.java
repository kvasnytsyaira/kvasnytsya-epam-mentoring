package mentoring.controller;

import mentoring.controller.exceptions.RecordNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(Logger.class);


    @ExceptionHandler(value = RecordNotFoundException.class)
    public String handleError(HttpServletRequest req, Exception exception, Model model) {
        logger.error(req.getRequestURL() + " " + "raised " + exception);
        model.addAttribute("ex", exception.getMessage());
        return "error";
    }
}
