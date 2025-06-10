package lt.viko.eif.tpetrov.Pirmas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/{city}/{date}")
    public String serveIndex() {
        return "forward:/index.html";
    }

    @GetMapping("/")
    public String serveRoot() {
        return "forward:/index.html";
    }
}
