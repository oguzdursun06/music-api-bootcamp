package com.trendyol.musicapi.controller;

import com.trendyol.musicapi.domain.Playlist;
import com.trendyol.musicapi.domain.Song;
import com.trendyol.musicapi.dto.PlaylistCreatedDTO;
import com.trendyol.musicapi.dto.PlaylistUpdatedDTO;
import com.trendyol.musicapi.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @PostMapping("/playlist")
    public ResponseEntity<Playlist> createPlaylist(@RequestBody PlaylistCreatedDTO playlistCreatedDTO){
        Playlist playlist = playlistService.createPlaylist(playlistCreatedDTO);
        return ResponseEntity.created(URI.create("/playlist")).body(playlist);
    }

    @DeleteMapping("/playlist/{playlistId}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable UUID playlistId){
        playlistService.deletePlaylist(playlistId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/playlist/{playlistId}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable UUID playlistId, @RequestBody PlaylistUpdatedDTO playlistUpdatedDTO){
        Playlist playlist = playlistService.updatePlaylist(playlistId, playlistUpdatedDTO);
        return ResponseEntity.ok().body(playlist);
    }

    @PatchMapping("/playlist/{playlistId}/songs")
    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable UUID playlistId, @RequestBody Song song){
        Playlist playlist = playlistService.addSongToPlaylist(playlistId, song);
        return ResponseEntity.ok(playlist);
    }

    @PatchMapping("/playlist/{playlistId}/songs/{songId}")
    public ResponseEntity<Void> deleteSongFromPlaylist(@PathVariable UUID playlistId, @PathVariable UUID songId){
        playlistService.deleteSongFromPlaylist(playlistId, songId);
        return ResponseEntity.noContent().build();
    }

}
