package Rockwell.CRUD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rockwell.CRUD.services.GeneralService;

@RestController
@RequestMapping("/api/v1/general")
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @DeleteMapping("/deleteAll")
    @CrossOrigin(origins = "*", allowedHeaders = { "*" })
    public ResponseEntity<String> deleteAllNodes() {
        generalService.deleteAllNodes();
        return new ResponseEntity<>("Todos os nós e relacionamentos foram deletados", HttpStatus.OK);
    }
}
