package in.jtechy.ViewService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @RequestMapping("/greetFE")
    public String greet() {
        return "Hi Ganesh FE!";
    }

}