package Rockwell.CRUD.models;

// Importações das classes e anotações necessárias
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

// Anotação que indica que essa classe é um nó no banco de dados orientado a grafos
@Node
public class HUB {

    @Id @GeneratedValue
    private long id;     // Identificador único do HUB

    private String name;
    
    private Integer positionX = 0;
    private Integer positionY = 0;

    public HUB(){
        this.positionX = 0;
        this.positionY = 0;
    }
    
    public long getId() {
        return id;
    }

    // Método getter para obter o nome do HUB
    public String getName() {
        return name;
    }

    public Integer getPositionX(){
        return positionX;
    }

    public Integer getPositionY(){
        return positionY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPositionX(int positionX){
        this.positionX = positionX;
    }

    public void setPositionY(int positionY){
        this.positionY = positionY;
    }
}
