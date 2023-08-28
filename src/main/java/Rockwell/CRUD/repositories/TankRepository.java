package Rockwell.CRUD.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.models.Tank;

// Definição da interface do repositório que estende Neo4jRepository e trabalha com a entidade Tank e o tipo Long (para o ID)
public interface TankRepository extends Neo4jRepository<Tank, Long> {
    
    // Método para encontrar um Tank por número e retornar uma instância opcional de Tank
    Optional<Tank> findTankByNumber(int number);

    // Consulta personalizada para encontrar uma lista de Tanks conectados a um HUB específico por nome do HUB
    @Query("MATCH (:HUB {name: $name})-[:CONNECTED_TO]->(tanks:Tank) RETURN tanks")
    List<Tank> findTanksByHubName(String name);
}
