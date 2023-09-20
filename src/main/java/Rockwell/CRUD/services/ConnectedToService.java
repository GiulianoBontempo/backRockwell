package Rockwell.CRUD.services;

import java.util.List;
import java.util.Map;

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

    public List<GetAllConnectionsQueryResult> getAllConnections(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(hubRepository.getAllConnections().get(0));
        return hubRepository.getAllConnections();
    }
}
