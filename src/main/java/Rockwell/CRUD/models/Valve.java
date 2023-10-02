package Rockwell.CRUD.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Valve {
    @Id 
    @GeneratedValue
    private long id;
    private String name;
    private Integer positionX;
    private Integer positionY;

    // Construtor padrão da classe Valve
    public Valve() {
        this.positionX = 0;
        this.positionY = 0;
    }

    // Método para obter o ID da válvula
    public long getId() {
        return id;
    }

    // Método para obter o nome da válvula
    public String getName() {
        return name;
    }

    // Método para obter a posição X da válvula
    public Integer getPositionX(){
        return positionX;
    }

    // Método para obter a posição Y da válvula
    public Integer getPositionY(){
        return positionY;
    }

    // Método para definir o ID da válvula
    public void setId(long id) {
        this.id = id;
    }

    // Método para definir o nome da válvula
    public void setName(String name) {
        this.name = name;
    }

    // Método para definir a posição X da válvula
    public void setPositionX(Integer positionX){
        this.positionX = positionX;
    }

    // Método para definir a posição Y da válvula
    public void setPositionY(Integer positionY){
        this.positionY = positionY;
    }
}
