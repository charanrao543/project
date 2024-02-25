package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.PlayList;
import com.example.demo.entity.Songs;
import com.example.demo.service.PlayListService;
import com.example.demo.service.SongsService;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class PlayListController {

	@Autowired
	PlayListService pserv;
	
	@Autowired
	SongsService sserv;
	
	@GetMapping("/createplaylist")
	public String createplaylist(Model model) {
		
		//Fetching the songs using song service
		List<Songs> songslist = sserv.findAllSongs();
		
		//Adding the songs in the model
		model.addAttribute("songslist" ,songslist);
		
		//sending createplaylist
		return "createplaylist";
		
	}
	@PostMapping("/addplaylist")
	public String addPlayList(@ModelAttribute PlayList playlist) {
		//System.out.println(playlist);
		//adding songs
		pserv.addPlayList(playlist);
		List<Songs> songList = playlist.getSongs();
		for (Songs song : songList) {
			song.getPlaylist().add(playlist);
			sserv.updateSong(song);
		}
		return "playlistsuccess";
	}
	
	@GetMapping("/viewplaylist")
	public String viewplaylist(Model model) {
		List<PlayList> plist= pserv.fetchPlaylist();
		model.addAttribute("plist", plist);
		return "viewPlaylist";
	}
	
	
	
}
