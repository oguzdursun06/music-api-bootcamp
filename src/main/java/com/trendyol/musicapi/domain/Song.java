package com.trendyol.musicapi.domain;

import lombok.Data;

import java.util.UUID;

@Data
public class Song {

    private UUID id;

    private String name;

    public Song() {
        this.id = UUID.randomUUID();
    }
}
