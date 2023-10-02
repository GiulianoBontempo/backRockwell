package Rockwell.CRUD.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rockwell.CRUD.repositories.GeneralRepository;

@Service
public class GeneralService {

    @Autowired
    private GeneralRepository generalRepository;

    public void deleteAllNodes() {
        generalRepository.deleteAllNodes();
    }
}
