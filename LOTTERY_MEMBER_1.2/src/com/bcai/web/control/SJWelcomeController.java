package com.bcai.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SJWelcomeController {
	
	@RequestMapping("/")
    public String welcome(){
        return "forward:/login";
    }
}
