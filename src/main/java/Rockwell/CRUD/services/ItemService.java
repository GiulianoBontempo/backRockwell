package Rockwell.CRUD.services;

// Importações das classes e anotações necessárias
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rockwell.CRUD.models.Item;
import Rockwell.CRUD.repositories.ItemRepository;
import Rockwell.CRUD.requests.CreateItemRequest;

// Anotação que marca a classe como um serviço gerenciado pelo Spring
@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    // Método para obter todos os itens
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    // Método para criar um novo item
    public Item createItem(CreateItemRequest request) {
        Item item = new Item();
        item.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        item.setName(request.getName());
        return itemRepository.save(item);
    }

    // Método para excluir todos os itens
    public void deleteAllItems() {
        itemRepository.deleteAll();
    }

    // Método para conectar itens por nome
    public void connectByName(String name1, String name2){
        itemRepository.createConnection(name1, name2);
    }

    // Método para deletar conexão entre itens por nome
    public void deleteConnection(String name1, String name2){
        itemRepository.deleteConnectionByName(name1, name2);
    }

    // Método para conectar um item a um HUB por nome
    public void connectItemToHub(String name1, String name2){
        itemRepository.connectItemToHub(name1, name2);
    }
}
