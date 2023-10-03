package Rockwell.CRUD.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import Rockwell.CRUD.models.Tank;

// Definição da interface do repositório que estende Neo4jRepository e trabalha com a entidade Tank e o tipo Long (para o ID)
public interface TankRepository extends Neo4jRepository<Tank, Long> {
    

   /**
     * Encontra um Tank pelo número.
     * @param number Número do Tank
     * @return Um Optional contendo o Tank se ele existir
     */
   Optional<Tank> findTankByNumber(int number);

   /**
     * Encontra uma lista de Tanks que estão conectados a um HUB específico.
     * @param name Nome do HUB
     * @return Lista de Tanks conectados ao HUB
     */
   @Query("MATCH (:HUB {name: $name})-[:CONNECTED_TO]->(tanks:Tank) RETURN tanks")
    List<Tank> findTanksByHubName(String name);


     /**
     * Cria uma conexão entre dois Tanks.
     * @param startTankNumber Número do primeiro Tank
     * @param endTankNumber Número do segundo Tank
     */
    @Query("MATCH (tank1:Tank {number: $startTankNumber}), (tank2:Tank {number: $endTankNumber}) MERGE (tank1)-[:CONNECTED_TO]->(tank2)")
    void createConnection(Integer startTankNumber, Integer endTankNumber);

   /**
     * Cria uma conexão entre um Tank e um EntradaESaida.
     * @param tankNumber Número do Tank
     * @param entradaESaidaName Nome do EntradaESaida
     */
    @Query("MATCH (tank:Tank {number: $tankNumber}), (entradaESaida:EntradaESaida {name: $entradaESaidaName}) MERGE (tank)-[:CONNECTED_TO]->(entradaESaida)")
    void connectTankToEntradaESaida(Integer tankNumber, String entradaESaidaName);


     /**
     * Deleta a conexão entre dois Tanks.
     * @param startTankNumber Número do primeiro Tank
     * @param endTankNumber Número do segundo Tank
     */
    @Query("MATCH (tank1:Tank {number: $startTankNumber})-[r:CONNECTED_TO]-(tank2:Tank {number: $endTankNumber}) DELETE r")
    void deleteConnection(Integer startTankNumber, Integer endTankNumber);

     /**
     * Deleta a conexão entre um Tank e um EntradaESaida.
     * @param tankNumber Número do Tank
     * @param entradaESaidaName Nome do EntradaESaida
     */
    @Query("MATCH (tank:Tank {number: $tankNumber})-[r:CONNECTED_TO]->(entradaESaida:EntradaESaida {name: $entradaESaidaName}) DELETE r")
    void deleteTankEntradaESaidaConnection(Integer tankNumber, String entradaESaidaName);
    
    /**
     * Cria uma conexão entre um Tank e um Hub.
     * @param tankNumber Número do Tank
     * @param hubName Nome do Hub
     */
    @Query("MATCH (t:Tank {number: $tankNumber}), (h:HUB {name: $hubName}) CREATE (t)-[:CONNECTED_TO]->(h)")
      void connectToHub(int tankNumber, String hubName);

     /**
     * Deleta a conexão entre um Tank e um Hub.
     * @param tankName Número do Tank
     * @param hubName Nome do Hub
     */
    @Query("MATCH (tank:Tank {number: $tankName})-[r:CONNECTED_TO]->(hub:HUB {name: $hubName}) " +
           "DELETE r")
    void deleteConnectionToHub(Integer tankName, String hubName);


    @Query("MATCH (t:Tank {number: $tankNumber}), (v:Valve {name: $valveName}) CREATE (t)-[:CONNECTED_TO]->(v)")
    void connectTankToValve(int tankNumber, String valveName);

    @Query("MATCH (t:Tank {number: $tankNumber})-[r:CONNECTED_TO]->(v:Valve {name: $valveName}) DELETE r")
    void deleteConnectionToValve(int tankNumber, String valveName);


    @Query("MATCH (t:Tank {number: $currentNumber}) SET t.number = $newNumber RETURN t")
    Tank updateNumber(long currentNumber, long newNumber);

    

}
