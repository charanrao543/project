package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Songs;
import com.example.demo.entity.Users;
import com.example.demo.service.SongsService;
import com.example.demo.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UsersController {
	
	@Autowired
	UsersService userv;
	
	@Autowired
	SongsService songserv;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) 
	{
		boolean userstatus = userv.emailExists(user.getEmail());
		
		if(userstatus == false) {
			userv.addUsers(user);
			return "registersuccess";
		}
		else {
			return "registerfail";
		}
		
	}
	
	
	@PostMapping("/login")
	public String validateUser(@RequestParam String email, @RequestParam String password , HttpSession session) {
		
		//invoking validateuser() in service
		if(userv.validateUser(email, password) == true) {
			session.setAttribute("email", email);
			// checking whether the user is admin or customer
			if(userv.getRole(email).equals("admin")) {
				return "adminhome";
			}
			else {
				return "customerhome";
			}
		}
		else {
			return "loginfail";
		}
		
	}
	
		@GetMapping("/logout")
		public String logout(HttpServletRequest request) {
			HttpSession session = request.getSession();
			session.invalidate();
		    return "login";
		}
	
		
	@GetMapping("/exploreSongs")
	public String exploreSongs(HttpSession session, Model model) {
		String email = (String) session.getAttribute("email");
		Users user = userv.getUser(email);
		
		boolean userStatus = user.isPremium();
		if(userStatus== true) {
			List<Songs> songslist = songserv.findAllSongs();
			model.addAttribute("songslist", songslist);
			return "displaysongs";
		}
		else {
			return "makepayment";
		}
		
	}
	
	
}
