package com.example.botond.judoapp_4.ctrl;

import com.example.botond.judoapp_4.domain.Belt;
import com.example.botond.judoapp_4.repo.BeltRepository;

import java.util.List;

public class BeltController {

    private BeltRepository beltRepository;

    public BeltController(){
        beltRepository=new BeltRepository();
    }

    public Belt getById(String id){
        return beltRepository.getById(id);
    }

    public Belt getByName(String name){
        return beltRepository.getByName(name);
    }

    public List<Belt> getBelts(){
        return beltRepository.getBelts();
    }

    public void addBelt(Belt belt){
        beltRepository.addBelt(belt);
    }

}
