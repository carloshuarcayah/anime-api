package com.carlos.animeapi.service;

import com.carlos.animeapi.model.Anime;
import com.carlos.animeapi.repository.AnimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    @Autowired
    private AnimeRepository animeRepository;

    public List<Anime> listarTodo(){
        return animeRepository.findAll();
    }
}
