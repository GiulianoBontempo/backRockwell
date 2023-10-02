package Rockwell.CRUD.repositories;

import java.util.Optional;

import Rockwell.CRUD.models.Valve;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ValveRepository extends Neo4jRepository<Valve, Long>{
    Optional<Valve> findValveByName(String name);

    @Query("MATCH (valve:Valve { name: $valveName }), (tank:Tank { number: $tankNumber }) " +
       "MERGE (valve)-[:CONNECTED_TO]->(tank)")
    void connectToTank(String valveName, int tankNumber);

    @Query("MATCH (v:Valve {name: $valveName}) DETACH DELETE v")
      void deleteValve(String valveName);
      
    @Query("MATCH (valve:Valve { name: $valveName }), (entradaESaida:EntradaESaida { name: $entradaESaidaName }) " +
    "MERGE (valve)-[:CONNECTED_TO]->(entradaESaida)")
   void connectToEntradaESaida(String valveName, String entradaESaidaName);


    @Query("MATCH (valve:Valve { name: $valveName })-[r:CONNECTED_TO]->(tank:Tank { number: $tankNumber }) " +
       "DELETE r")
    void deleteConnectionToTank(String valveName, int tankNumber);

    @Query("MATCH (valve:Valve { name: $valveName })-[r:CONNECTED_TO]->(entradaESaida:EntradaESaida { name: $entradaESaidaName }) DELETE r")
      void deleteConnectionToEntradaESaida(String valveName, String entradaESaidaName);
;


    @Query("MATCH (v1:Valve { name: $valveName }), (v2:Valve { name: $valveName2 }) " +
      "CREATE (v1)-[:CONNECTED_TO]->(v2)")
     void connectToValve(String valveName, String valveName2);

     @Query("MATCH (valve1:Valve { name: $valveName })-[r:CONNECTED_TO]->(valve2:Valve { name: $valveName2 }) " +
     "DELETE r")
      void deleteConnectionToValve(String valveName, String valveName2);


    @Query("MATCH (valve:Valve { name: $valveName }) DETACH DELETE valve")
    void deleteValveByName(String valveName);


    @Query("MATCH (v:Valve {name: $valveName}), (h:HUB {name: $hubName}) MERGE (v)-[:CONNECTED_TO]->(h)")
    void connectToHub(String valveName, String hubName);

    
    @Query("MATCH (v:Valve {name: $valveName})-[r:CONNECTED_TO]->(h:HUB {name: $hubName}) DELETE r")
    void deleteConnectionToHub(String valveName, String hubName);


    @Query("MATCH (v:Valve {name: $currentName}) SET v.name = $newName RETURN v")
    Valve updateName(String currentName, String newName);
}