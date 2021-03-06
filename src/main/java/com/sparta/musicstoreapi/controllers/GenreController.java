package com.sparta.musicstoreapi.controllers;

import com.sparta.musicstoreapi.entities.Genre;
import com.sparta.musicstoreapi.entities.Token;
import com.sparta.musicstoreapi.repositories.GenreRepository;
import com.sparta.musicstoreapi.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/chinook")
public class GenreController {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping(value = "/genres", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }

    @GetMapping(value = "/genres/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<?> getGenreById(@PathVariable Integer id){
        Optional<Genre> genre = genreRepository.findById(id);
        if(genre.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Genre not found");
        else
            return ResponseEntity.ok(genre.get());
    }

    @PostMapping(value = "/genres/add/{token}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Genre> addNewGenre(@RequestBody Genre newGenre, @PathVariable String token){
        Optional<Token> tokenResult = tokenRepository.findByToken(token);
        if (tokenResult.isPresent()) {
            if (tokenResult.get().getPermissionLevel() >= 2) {
                genreRepository.save(newGenre);
                return ResponseEntity.ok(newGenre);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
