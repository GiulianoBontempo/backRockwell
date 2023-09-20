package Rockwell.CRUD.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Valve {
    @Id 
    private long id;
    private String name;
    private Integer positionX;
    private Integer positionY;

    public Valve() {
        this.positionX = 0;
        this.positionY = 0;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPositionX(){
        return positionX;
    }

    public Integer getPositionY(){
        return positionY;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPositionX(Integer positionX){
        this.positionX = positionX;
    }

    public void setPositionY(Integer positionY){
        this.positionY = positionY;
    }
}
