package Rockwell.CRUD.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import Rockwell.CRUD.models.HUB;

@Repository
public interface GeneralRepository extends Neo4jRepository<HUB, Long> { 
    @Query("MATCH (n) DETACH DELETE n")
    void deleteAllNodes();
}
