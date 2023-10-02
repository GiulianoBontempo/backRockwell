package Rockwell.CRUD.models;

// Importações das classes e anotações necessárias
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

// Anotação que indica que essa classe é um nó no banco de dados orientado a grafos
@Node
public class EntradaESaida {
    @Id
    @GeneratedValue
    private long id;     // Identificador único do item

    private String name;
    private int positionX;
    private int positionY; // Nome do item

    // Anotação para definir um relacionamento com outros itens
    @Relationship(type = "RELATES_TO", direction = Relationship.Direction.OUTGOING)
    private List<EntradaESaida> relatedItems; // Lista de itens relacionados

    // Construtor padrão vazio
    public EntradaESaida(){

    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getPositionX(){
        return positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPositionX(int posicaoX) {
        this.positionX = posicaoX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }
}
