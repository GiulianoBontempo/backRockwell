package Rockwell.CRUD.repositories;

import java.util.Optional;

import Rockwell.CRUD.models.Valve;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ValveRepository extends Neo4jRepository<Valve, Long>{
    Optional<Valve> findValveByName(String name);

        /**
     * Conecta uma Valve a um Tank.
     *
     * @param valveName Nome da Valve que será conectada.
     * @param tankNumber Número do Tank ao qual a Valve será conectada.
     */
    @Query("MATCH (valve:Valve { name: $valveName }), (tank:Tank { number: $tankNumber }) " +
    "MERGE (valve)-[:CONNECTED_TO]->(tank)")
    void connectToTank(String valveName, int tankNumber);
    
    /**
    * Deleta uma Valve com base no seu nome.
    *
    * @param valveName Nome da Valve a ser deletada.
    */
    @Query("MATCH (v:Valve {name: $valveName}) DETACH DELETE v")
    void deleteValve(String valveName);
    
    /**
    * Conecta uma Valve a uma EntradaESaida.
    *
    * @param valveName Nome da Valve que será conectada.
    * @param entradaESaidaName Nome da EntradaESaida à qual a Valve será conectada.
    */
    @Query("MATCH (valve:Valve { name: $valveName }), (entradaESaida:EntradaESaida { name: $entradaESaidaName }) " +
    "MERGE (valve)-[:CONNECTED_TO]->(entradaESaida)")
    void connectToEntradaESaida(String valveName, String entradaESaidaName);
    
    /**
    * Deleta a conexão entre uma Valve e um Tank.
    *
    * @param valveName Nome da Valve cuja conexão será deletada.
    * @param tankNumber Número do Tank do qual a Valve será desconectada.
    */
    @Query("MATCH (valve:Valve { name: $valveName })-[r:CONNECTED_TO]->(tank:Tank { number: $tankNumber }) " +
    "DELETE r")
    void deleteConnectionToTank(String valveName, int tankNumber);
    
    /**
    * Deleta a conexão entre uma Valve e uma EntradaESaida.
    *
    * @param valveName Nome da Valve cuja conexão será deletada.
    * @param entradaESaidaName Nome da EntradaESaida da qual a Valve será desconectada.
    */
    @Query("MATCH (valve:Valve { name: $valveName })-[r:CONNECTED_TO]->(entradaESaida:EntradaESaida { name: $entradaESaidaName }) DELETE r")
    void deleteConnectionToEntradaESaida(String valveName, String entradaESaidaName);
    
    /**
    * Conecta uma Valve a outra Valve.
    *
    * @param valveName Nome da primeira Valve que será conectada.
    * @param valveName2 Nome da segunda Valve à qual a primeira Valve será conectada.
    */
    @Query("MATCH (v1:Valve { name: $valveName }), (v2:Valve { name: $valveName2 }) " +
    "CREATE (v1)-[:CONNECTED_TO]->(v2)")
    void connectToValve(String valveName, String valveName2);
    
    /**
    * Deleta a conexão entre duas Valves.
    *
    * @param valveName Nome da primeira Valve cuja conexão será deletada.
    * @param valveName2 Nome da segunda Valve da qual a primeira Valve será desconectada.
    */
    @Query("MATCH (valve1:Valve { name: $valveName })-[r:CONNECTED_TO]->(valve2:Valve { name: $valveName2 }) " +
    "DELETE r")
    void deleteConnectionToValve(String valveName, String valveName2);
    
    /**
    * Deleta uma Valve com base no seu nome.
    *
    * @param valveName Nome da Valve a ser deletada.
    */
    @Query("MATCH (valve:Valve { name: $valveName }) DETACH DELETE valve")
    void deleteValveByName(String valveName);
    
    /**
    * Conecta uma Valve a um HUB.
    *
    * @param valveName Nome da Valve que será conectada.
    * @param hubName Nome do HUB ao qual a Valve será conectada.
    */
    @Query("MATCH (v:Valve {name: $valveName}), (h:HUB {name: $hubName}) MERGE (v)-[:CONNECTED_TO]->(h)")
    void connectToHub(String valveName, String hubName);
    
    /**
    * Deleta a conexão entre uma Valve e um HUB.
    *
    * @param valveName Nome da Valve cuja conexão será deletada.
    * @param hubName Nome do HUB do qual a Valve será desconectada.
    */
    @Query("MATCH (v:Valve {name: $valveName})-[r:CONNECTED_TO]->(h:HUB {name: $hubName}) DELETE r")
    void deleteConnectionToHub(String valveName, String hubName);
    
    /**
    * Atualiza o nome de uma Valve.
    *
    * @param currentName Nome atual da Valve.
    * @param newName Novo nome para a Valve.
    * @return A Valve com o nome atualizado.
    */
    @Query("MATCH (v:Valve {name: $currentName}) SET v.name = $newName RETURN v")
    Valve updateName(String currentName, String newName);
    
}