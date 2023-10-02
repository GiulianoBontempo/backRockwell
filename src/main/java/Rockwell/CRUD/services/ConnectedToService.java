package Rockwell.CRUD.services;

import java.util.List;

import org.springframework.stereotype.Service;

import Rockwell.CRUD.queries.GetAllConnectionsQueryResult;
import Rockwell.CRUD.repositories.HUBRepository;



@Service
public class ConnectedToService {
    private final HUBRepository hubRepository;
    
    /**
     * Construtor para injeção de dependência do repositório HUBRepository.
     *
     * @param hubRepository O repositório responsável pela persistência dos HUBs.
     */
    public ConnectedToService(HUBRepository hubRepository) {
        this.hubRepository = hubRepository;
    }

    /**
     * Função para retornar todas as arestas existentes
     *
     * @return todas as conexões
     */
    public List<GetAllConnectionsQueryResult> getAllConnections(){
        return hubRepository.getAllConnections();
    }
}
