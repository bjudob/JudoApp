package com.example.botond.judoapp_4.ctrl;

import com.example.botond.judoapp_4.domain.Vocabulary;
import com.example.botond.judoapp_4.repo.VocabularyRepository;

import java.util.List;

public class VocabularyController {
    private VocabularyRepository repo;

    public VocabularyController() {
        repo=new VocabularyRepository();
    }

    public Vocabulary getByName(String name){
        return repo.getByName(name);
    }

    public List<Vocabulary> getVocabularies(){
        return repo.getVocabularies();
    }
}
