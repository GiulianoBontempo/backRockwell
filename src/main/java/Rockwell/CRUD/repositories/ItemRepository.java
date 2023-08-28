package Rockwell.CRUD.repositories;
import java.util.Optional;

// Importações de classes necessárias
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.models.Item;

// Definição da interface do repositório que estende Neo4jRepository e trabalha com a entidade Item e o tipo Long (para o ID)
public interface ItemRepository extends Neo4jRepository<Item, Long> {

    // Método para encontrar um Item por nome e retornar uma instância opcional de HUB (talvez seja necessário verificar esse tipo de retorno)
    Optional<HUB> findItemByName(String name);

    // Consulta personalizada usando a anotação @Query para criar uma conexão entre dois itens com base em seus nomes
    @Query("MATCH (a:Item {name: $name1}), (b:Item {name: $name2}) CREATE (a)-[:RELATES_TO]->(b)")
    void createConnection(String name1, String name2);

    // Consulta personalizada usando a anotação @Query para deletar uma conexão entre dois itens por nome
    @Query("MATCH (a:Item {name: $name1})-[r:RELATES_TO]->(b:Item {name: $name2}) DELETE r")
    void deleteConnectionByName(String name1, String name2);

    @Query("MATCH (i:Item { id: $itemId }), (h:Hub { name: $hubName }) CREATE (i)-[:CONNECTED_TO]->(h)")
    void connectItemToHub(String itemId, String hubName);

}
