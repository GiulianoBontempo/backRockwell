package Rockwell.CRUD.repositories;
import java.util.Optional;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;


import Rockwell.CRUD.models.EntradaESaida;


public interface EntradaESaidaRepository extends Neo4jRepository<EntradaESaida, Long> {

    /**
     * Encontra uma entrada ou saida pelo nome.
     * @param name Nome da entrada ou saida
     * @return Um Optional contendo a entrada ou saida se ele existir
     */
    Optional<EntradaESaida> findEntradaOuSaidaByName(String name);


    /**
     * Cria uma conexão entre duas entradas ou saidas.
     * @param startName Nome do primeiro Item
     * @param endName Nome do segundo Item
     */
    @Query("MATCH (a:EntradaESaida {name: $startName}), (b:EntradaESaida {name: $endName}) CREATE (a)-[:CONNECTED_TO]->(b)")
    void createConnection(String startName, String endName);


     /**
     * Deleta a conexão entre duas entradas ou saidas pelo nome.
     * @param startName Nome do primeiro Item
     * @param endName Nome do segundo Item
     */
    @Query("MATCH (a:EntradaESaida {name: $startName})-[r:RELATES_TO]->(b:EntradaESaida {name: $endName}) DELETE r")
    void deleteConnectionByName(String startName, String endName);


    /**
     * Cria uma conexão entre uma entrada ou saida a um Hub.
     * @param itemName ID do Item
     * @param hubName Nome do Hub
     */
    @Query("MATCH (e:EntradaESaida {name: $entradaESaidaName}), (h:HUB {name: $hubName}) CREATE (e)-[:CONNECTED_TO]->(h)")
    void connectToHub(String entradaESaidaName, String hubName);

    @Query("MATCH (v:EntradaESaida {name: $EntradaESaidaName}) DETACH DELETE v")
      void deleteByName(String EntradaESaidaName);

    @Query("MATCH (i:EntradaESaida { name: $entradaESaidaName }), (t:Tank { name: $tankNumber }) CREATE (i)-[:CONNECTED_TO]->(t)")
    void connectEntradaESaidaToTank(String entradaESaidaName, Integer tankNumber);

    @Query("MATCH (e:EntradaESaida {name: $entradaESaidaName}), (v:Valve {name: $valveName}) CREATE (e)-[:CONNECTED_TO]->(v)")
    void connectEntradaESaidaToValve(String entradaESaidaName, String valveName);

    @Query("MATCH (e:EntradaESaida {name: $entradaESaidaName})-[r:CONNECTED_TO]->(v:Valve {name: $valveName}) DELETE r")
    void deleteConnectionToValve(String entradaESaidaName, String valveName);

    @Query("MATCH (e:EntradaESaida {name: $entradaESaidaName})-[r:CONNECTED_TO]->(h:HUB {name: $hubName}) DELETE r")
    void deleteConnectionToHub(String entradaESaidaName, String hubName);

    @Query("MATCH (e:EntradaESaida {name: $entradaESaidaName})-[r:CONNECTED_TO]->(t:Tank {number: $tankNumber}) DELETE r")
    void deleteConnectionToTank(String entradaESaidaName, int tankNumber);
}
   
