package cz.itnetwork.webinsurance.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * HomeController is responsible for handling requests to the home page of the application.
 * It serves the index page when the root URL is accessed.
 */
@Controller
public class HomeController {

    /**
     * Renders the home page (index) of the application.
     * This method is mapped to the root URL ("/").
     *
     * @return the path to the index page template.
     */
    @GetMapping("/")
    public String renderIndex() {
        return "pages/home/index";
    }
}
