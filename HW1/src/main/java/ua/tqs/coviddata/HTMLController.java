package ua.tqs.coviddata;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HTMLController {
    @RequestMapping(value="/")
    public static String index() {
        return "index";
    }

    @RequestMapping(value="/history")
    public static String history(){
        return "history";
    }

    @RequestMapping(value="/statistics")
    public static String statistics() {
        return "statistics";
    }
}