package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Songs;
import com.example.demo.service.SongsService;

@Controller
public class SongsController {
	
	@Autowired
	SongsService songserv;
	
	@PostMapping("/addsongs")
	public String addSongs(@ModelAttribute Songs songs) {
		boolean songStatus = songserv.songExists(songs.getName());
		if(songStatus == false) {
			songserv.addSongs(songs);
			return "songsuccess";
		}
		else {
			return "songfail";
		}
		
	}
	
	@GetMapping("/viewsongs")
	public String viewsongs(Model model) {
		List<Songs> songslist = songserv.findAllSongs();
		model.addAttribute("songslist", songslist);
		return "displaysongs";
		
	}
	
	
	@GetMapping("/customerviewsongs")
	public String customerviewsongs(Model model) {
		boolean primeCustomerStatus = true;
		if(primeCustomerStatus == true) {
			List<Songs> songslist = songserv.findAllSongs();
			model.addAttribute("songslist", songslist);
			return "displaysongs";
		}
		else {
			return "makepayment";
		}
	}
	
	
	
}
