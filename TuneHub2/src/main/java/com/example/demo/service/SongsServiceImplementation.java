package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Songs;
import com.example.demo.repository.SongsRepository;

@Service
public class SongsServiceImplementation implements SongsService{
	
	@Autowired
	SongsRepository repo;

	@Override
	public String addSongs(Songs song) {
		repo.save(song);
		return "song is added";
	}

	@Override
	public List<Songs> findAllSongs() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public boolean songExists(String name) {
		Songs song = repo.findByName(name);
		if(song == null) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public void updateSong(Songs song) {
		repo.save(song);
		
	}
}
