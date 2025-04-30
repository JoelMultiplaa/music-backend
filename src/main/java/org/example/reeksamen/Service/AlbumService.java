package org.example.reeksamen.Service;

import lombok.RequiredArgsConstructor;
import org.example.reeksamen.entity.Album;
import org.example.reeksamen.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepo;

    public List<Album> findAllAlbums() {
        return albumRepo.findAll();
    }

    public Optional<Album> findAlbumById(Long id) {
        return albumRepo.findById(id);
    }

    public Album createAlbum(Album album) {
        return albumRepo.save(album);
    }

    public Album updateAlbum(Long id, Album updated) {
        Optional<Album> existingOpt = albumRepo.findById(id);
        if (existingOpt.isEmpty()) {
            return null;
        }
        Album existing = existingOpt.get();
        existing.setTitle(updated.getTitle());
        existing.setArtist(updated.getArtist());
        existing.setGenre(updated.getGenre());
        existing.setAvailable(updated.isAvailable());
        return albumRepo.save(existing);
    }

    public boolean deleteAlbum(Long id) {
        if (!albumRepo.existsById(id)) return false;
        albumRepo.deleteById(id);
        return true;
    }
}
