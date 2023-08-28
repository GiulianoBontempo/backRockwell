package Rockwell.CRUD.repositories;

import Rockwell.CRUD.models.HUB;

import java.util.Optional;

// Importações de classes necessárias
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

// Definição da interface do repositório que estende Neo4jRepository e trabalha com a entidade HUB e o tipo Long (para o ID)
public interface HUBRepository extends Neo4jRepository<HUB, Long> {

    // Método para encontrar um HUB por nome e retornar uma instância opcional de HUB
    Optional<HUB> findHUBByName(String name);

    // Consulta personalizada para verificar se um HUB está conectado a um Tank específico
    @Query("MATCH (hub:HUB), (tank:Tank) WHERE hub.name = $hubName AND tank.number = $tankNumber " + 
        "RETURN EXISTS((hub)-[:CONNECTED_TO]->(tank))")
    Boolean checkIfConnectedToTank(String hubName, int tankNumber);

    // Consulta personalizada para criar uma conexão entre um HUB e um Tank
    @Query("MATCH (hub:HUB {name: $hubName}), (tank:Tank {number: $tankNumber}) " + 
            "CREATE (hub)-[:CONNECTED_TO]->(tank)")
    void createConnectionToTank(String hubName, int tankNumber);

    // Consulta personalizada para verificar se um HUB está conectado a um Item específico
    @Query("MATCH (hub:HUB), (item:Item) WHERE hub.name = $hubName AND item.name = $itemName " + 
        "RETURN EXISTS((hub)-[:CONNECTED_TO]->(item))")
    Boolean checkIfConnectedToItem(String hubName, String itemName);

    // Consulta personalizada para criar uma conexão entre um HUB e um Item
    @Query("MATCH (hub:HUB {name: $hubName}), (item:Item {name: $itemName}) " + 
            "CREATE (hub)-[:CONNECTED_TO]->(item)")
    void createConnectionToItem(String hubName, String itemName);

    // Consulta personalizada para verificar se um HUB está conectado a outro HUB
    @Query("MATCH (start_hub: HUB), (end_hub:HUB) WHERE start_hub.name = $startHubName AND end_hub.name = $endHubName " + 
        "RETURN EXISTS((start_hub)-[:CONNECTED_TO]->(end_hub))")
    Boolean checkIfConnectedToHub(String startHubName, String endHubName);

    // Consulta personalizada para criar uma conexão entre dois HUBs
    @Query("MATCH (start_hub:HUB {name: $startHubName}), (end_hub:HUB {name: $endHubName}) " + 
            "CREATE (start_hub)-[:CONNECTED_TO]->(end_hub)")
    void createConnectionToHub(String startHubName, String endHubName);

    @Query("MATCH (hub:HUB {name: $hubName})-[r:CONNECTED_TO]->(tank:Tank {number: $tankNumber}) " +
            "DELETE r")
    void deleteConnectionToTank(String hubName, int tankNumber);

    @Query("MATCH (hub:HUB {name: $hubName})-[r:CONNECTED_TO]->(item:Item {name: $itemName}) " +
            "DELETE r")
    void deleteConnectionToItem(String hubName, String itemName);

    @Query("MATCH (start_hub:HUB {name: $startHubName})-[r:CONNECTED_TO]->(end_hub:HUB {name: $endHubName}) " +
            "DELETE r")
    void deleteConnectionToHub(String startHubName, String endHubName);
    
}

