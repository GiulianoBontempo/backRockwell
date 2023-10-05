package Rockwell.CRUD.repositories;

import Rockwell.CRUD.models.HUB;
import Rockwell.CRUD.queries.GetAllConnectionsQueryResult;

import java.util.List;
import java.util.Optional;

// Importações de classes necessárias
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

// Definição da interface do repositório que estende Neo4jRepository e trabalha com a entidade HUB e o tipo Long (para o ID)
public interface HUBRepository extends Neo4jRepository<HUB, Long> {


        /**
     * Encontra um HUB pelo nome.
     * @param name Nome do HUB
     * @return Um Optional contendo o HUB se ele existir
     */
     Optional<HUB> findHUBByName(String name);

        /**
     * Verifica se um HUB está conectado a um Tank específico.
     * @param hubName Nome do HUB
     * @param tankNumber Número do Tank
     * @return Verdadeiro se o HUB estiver conectado ao Tank, falso caso contrário
     */
     @Query("MATCH (hub:HUB), (tank:Tank) WHERE hub.name = $hubName AND tank.number = $tankNumber " + 
        "RETURN EXISTS((hub)-[:CONNECTED_TO]->(tank))")
    Boolean checkIfConnectedToTank(String hubName, int tankNumber);


        /**
     * Cria uma conexão entre um HUB e um Tank.
     * @param hubName Nome do HUB
     * @param tankNumber Número do Tank
     */

     @Query("MATCH (h:HUB {name: $hubName}), (t:Tank {number: $tankNumber}) CREATE (h)-[:CONNECTED_TO]->(t)")
     void connectToTank(String hubName, int tankNumber);


    /**
     * Verifica se um HUB está conectado a um EntradaESaida específico.
     * @param hubName Nome do HUB
     * @param entradaESaidaName Nome do EntradaESaida
     * @return Verdadeiro se o HUB estiver conectado ao EntradaESaida, falso caso contrário
     */
    @Query("MATCH (hub:HUB), (entradaESaida:EntradaESaida) WHERE hub.name = $hubName AND entradaESaida.name = $entradaESaidaName " + 
        "RETURN EXISTS((hub)-[:CONNECTED_TO]->(entradaESaida))")
    Boolean checkIfConnectedToEntradaESaida(String hubName, String entradaESaidaName);


    /**
     * Cria uma conexão entre um HUB e um EntradaESaida.
     * @param hubName Nome do HUB
     * @param entradaESaidaName Nome do EntradaESaida
     */
    @Query("MATCH (hub:HUB {name: $hubName}), (entradaESaida:EntradaESaida {name: $entradaESaidaName}) " + 
            "CREATE (hub)-[:CONNECTED_TO]->(entradaESaida)")
    void createConnectionToEntradaESaida(String hubName, String entradaESaidaName);


    /**
     * Verifica se um HUB está conectado a outro HUB.
     * @param startHubName Nome do HUB inicial
     * @param endHubName Nome do HUB final
     * @return Verdadeiro se o HUB inicial estiver conectado ao HUB final, falso caso contrário
     */
    @Query("MATCH (start_hub: HUB), (end_hub:HUB) WHERE start_hub.name = $startHubName AND end_hub.name = $endHubName " + 
        "RETURN EXISTS((start_hub)-[:CONNECTED_TO]->(end_hub))")
    Boolean checkIfConnectedToHub(String startHubName, String endHubName);



    /**
     * Cria uma conexão entre dois HUBs.
     * @param startHubName Nome do HUB inicial
     * @param endHubName Nome do HUB final
     */
    @Query("MATCH (start_hub:HUB {name: $startHubName}), (end_hub:HUB {name: $endHubName}) " + 
            "CREATE (start_hub)-[:CONNECTED_TO]->(end_hub)")
    void createConnectionToHub(String startHubName, String endHubName);

     /**
     * Deleta a conexão entre um HUB e um Tank.
     * @param hubName Nome do HUB
     * @param tankNumber Número do Tank
     */
    @Query("MATCH (hub:HUB {name: $hubName})-[r:CONNECTED_TO]->(tank:Tank {number: $tankNumber}) " +
            "DELETE r")
    void deleteConnectionToTank(String hubName, int tankNumber);
    
    /**
     * Deleta a conexão entre um HUB e um EntradaESaida.
     * @param hubName Nome do HUB
     * @param entradaESaidaName Nome do EntradaESaida
     */
    @Query("MATCH (hub:HUB {name: $hubName})-[r:CONNECTED_TO]->(entradaESaida:EntradaESaida {name: $entradaESaidaName}) " +
            "DELETE r")
    void deleteConnectionToEntradaESaida(String hubName, String entradaESaidaName);

    /**
     * Deleta a conexão entre dois HUBs.
     * @param startHubName Nome do HUB inicial
     * @param endHubName Nome do HUB final
     */
    @Query("MATCH (start_hub:HUB {name: $startHubName})-[r:CONNECTED_TO]->(end_hub:HUB {name: $endHubName}) " +
            "DELETE r")
    void deleteConnectionToHub(String startHubName, String endHubName);



        /**
     * Conecta um HUB a uma Valve.
     *
     * @param hubName Nome do HUB que será conectado.
     * @param valveName Nome da Valve à qual o HUB será conectado.
     */
    @Query("MATCH (h:HUB {name: $hubName}), (v:Valve {name: $valveName}) CREATE (h)-[:CONNECTED_TO]->(v)")
    void connectHUBToValve(String hubName, String valveName);


        /**
     * Deleta a conexão entre um HUB e uma Valve.
     *
     * @param hubName Nome do HUB cuja conexão será deletada.
     * @param valveName Nome da Valve da qual o HUB será desconectado.
     */
    @Query("MATCH (h:HUB {name: $hubName})-[r:CONNECTED_TO]->(v:Valve {name: $valveName}) DELETE r")
    void deleteConnectionToValve(String hubName, String valveName);


        /**
     * Retorna todas as conexões entre os nós da base de dados.
     *
     * @return Uma lista de resultados que contêm informações sobre os nós conectados.
     */
    @Query("MATCH (a)-[:CONNECTED_TO]->(b) " + 
            "RETURN " + 
                "labels(a) AS sourceNodeType, " +
                "CASE " +
                    "WHEN 'HUB' IN labels(a) OR 'Valve' IN labels(a) OR 'EntradaESaida' IN labels(a) THEN a.name " +
                    "WHEN 'Tank' IN labels(a) THEN toString(a.number) " +
                    "ELSE null " +
                    "END AS sourceNameOrNumber, " +
                "labels(b) AS targetNodeType, " +
                "CASE " +
                    "WHEN 'HUB' IN labels(b) OR 'Valve' IN labels(b) OR 'EntradaESaida' IN labels(b) THEN b.name " +
                    "WHEN 'Tank' IN labels(b) THEN toString(b.number) " +
                    "ELSE null " +
                    "END AS targetNameOrNumber")
        List<GetAllConnectionsQueryResult> getAllConnections();


                /**
         * Atualiza o nome de um HUB.
         *
         * @param currentName Nome atual do HUB.
         * @param newName Novo nome para o HUB.
         * @return O HUB com o nome atualizado.
         */
        @Query("MATCH (h:HUB {name: $currentName}) SET h.name = $newName RETURN h")
        HUB updateName(String currentName, String newName);

}

