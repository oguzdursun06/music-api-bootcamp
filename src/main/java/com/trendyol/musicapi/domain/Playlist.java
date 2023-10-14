package com.trendyol.musicapi.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Playlist {

    public Playlist() {
        this.uuid = UUID.randomUUID();
    }

    private List<Song> songList = new ArrayList<>();

    private UUID uuid;

    private String name;

    public boolean addSong(Song song){
        return this.songList.add(song);
    }

}
