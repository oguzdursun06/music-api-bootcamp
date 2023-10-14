package com.trendyol.musicapi.service;

import com.trendyol.musicapi.NotFoundException;
import com.trendyol.musicapi.domain.Playlist;
import com.trendyol.musicapi.domain.Song;
import com.trendyol.musicapi.dto.PlaylistCreatedDTO;
import com.trendyol.musicapi.dto.PlaylistUpdatedDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaylistService {

    Map<UUID, Playlist> playlistMap = new HashMap<>();

    public Playlist createPlaylist(PlaylistCreatedDTO playlistCreatedDTO) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistCreatedDTO.getName());
        playlistMap.put(playlist.getUuid(), playlist);
        return playlist;
    }

    public Playlist addSongToPlaylist(UUID playlistId, Song song) {
        Playlist playlist = playlistMap.get(playlistId);
        playlist.addSong(song);
        playlistMap.put(playlistId, playlist);
        return playlist;
    }

    public void deletePlaylist(UUID playlistId) {
        if(playlistMap.containsKey(playlistId)){
            playlistMap.remove(playlistId);
        }
    }

    public Playlist updatePlaylist(UUID playlistId, PlaylistUpdatedDTO playlistUpdatedDTO) {
        if(playlistMap.containsKey(playlistId)){
            Playlist playlist = playlistMap.get(playlistId);
            playlist.setName(playlistUpdatedDTO.getName());
            return playlist;
        }
        throw new NotFoundException("playlist not found");
    }

    public Playlist deleteSongFromPlaylist(UUID playlistId, UUID songId) {
        if(playlistMap.containsKey(playlistId)){
            Playlist playlist = playlistMap.get(playlistId);
            List<Song> songList = playlist.getSongList();
            Song song = songList
                    .stream()
                    .filter(s -> s.getId().equals(songId))
                    .findFirst()
                    .orElseThrow(() -> new NotFoundException("song not found"));
            songList.remove(song);
            return playlist;
        }
        throw new NotFoundException("playlist not found");
    }
}
