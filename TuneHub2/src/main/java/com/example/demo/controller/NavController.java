package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NavController {
	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";
	}
	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";
	}
	@GetMapping("/map-songs")
	public String songsMapping() {
		return "addsongs";
	}
	
	@GetMapping("/samplePayments")
	public String samplePayments(@RequestParam String param) {
		return "samplePayments";
	}
	
}
