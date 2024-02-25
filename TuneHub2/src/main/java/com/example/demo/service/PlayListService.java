package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.PlayList;

public interface PlayListService {

	public void addPlayList(PlayList playlist);

	public List<PlayList> fetchPlaylist();
}
