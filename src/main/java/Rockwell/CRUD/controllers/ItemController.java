package Rockwell.CRUD.controllers;

// Importações das classes e anotações necessárias
import Rockwell.CRUD.services.ItemService;
import Rockwell.CRUD.models.Item;
import Rockwell.CRUD.requests.CreateItemRequest;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Anotação que marca a classe como um controlador REST
@RestController
@RequestMapping("/api/v1/Items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Endpoint para obter todos os itens
    @GetMapping("/")
    public Iterable<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // Endpoint para criar um novo item
    @PostMapping("/create")
    public Item createItem(@RequestBody CreateItemRequest newItemRequest) {
        return itemService.createItem(newItemRequest);  
    }

    // Endpoint para conectar dois itens por nome
    @PostMapping("/connectToItem")
    public ResponseEntity<String> connectByName(@RequestBody Map<String, String> conectarMap) {
        itemService.connectByName(conectarMap.get("name1"), conectarMap.get("name2"));
        return new ResponseEntity<>("Itens conectados!", HttpStatus.OK);
    }  

    // Endpoint para deletar todos os itens
    @DeleteMapping("/delete")
    public void deleteAllItems() {
        itemService.deleteAllItems();
    }

    // Endpoint para deletar uma conexão entre itens por nome
    @DeleteMapping("/deleteConnection")
    public ResponseEntity<String> deleteConnection(@RequestBody Map<String, String> deletarMap){
        itemService.deleteConnection(deletarMap.get("name1"), deletarMap.get("name2"));
        return new ResponseEntity<>("Conexão deletada", HttpStatus.OK);
    }

    // Endpoint para conectar um item a um HUB por nome
    @PostMapping("/connectToHUB")
    public ResponseEntity<String> conectarItemHub(@RequestBody Map<String, String> conectarMap){
        itemService.connectItemToHub(conectarMap.get("Item"), conectarMap.get("HUB"));
        return new ResponseEntity<>("Itens conectados", HttpStatus.OK);
    }
}
