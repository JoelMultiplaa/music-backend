package org.example.reeksamen.controller;


import lombok.RequiredArgsConstructor;
import org.example.reeksamen.Service.AlbumService;
import org.example.reeksamen.entity.Album;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAll() {
        return ResponseEntity.ok(albumService.findAllAlbums());
    }

    @PostMapping
    public ResponseEntity<Album> create(@RequestBody Album album) {
        return ResponseEntity.status(201).body(albumService.createAlbum(album));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Album> opt = albumService.findAlbumById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body("Album not found");
        return ResponseEntity.ok(opt.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Album album) {
        Album updated = albumService.updateAlbum(id, album);
        if (updated == null) return ResponseEntity.status(404).body("Album not found");
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!albumService.deleteAlbum(id)) {
            return ResponseEntity.status(404).body("Album not found");
        }
        return ResponseEntity.noContent().build();
    }
}