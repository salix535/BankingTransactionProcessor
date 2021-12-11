package rs.ac.uns.ftn.bankingtransactionprocessor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String user(Principal principal) {
        return principal.getName();
    }
}
