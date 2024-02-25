package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Songs;

public interface SongsService {
	public String addSongs(Songs songs);

	public List<Songs> findAllSongs();

	public boolean songExists(String name);

	public void updateSong(Songs song);
	
}
