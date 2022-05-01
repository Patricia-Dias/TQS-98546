package ua.tqs.coviddata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HTMLController {
    
    private HTMLController(){}

    @GetMapping(value="/")
    public static String index() {
        return "index";
    }

    @GetMapping(value="/history")
    public static String history(){
        return "history";
    }

    @GetMapping(value="/statistics")
    public static String statistics() {
        return "statistics";
    }
}