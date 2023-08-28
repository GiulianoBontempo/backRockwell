package Rockwell.CRUD.models;

// Importações das classes e anotações necessárias
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

// Anotação que indica que essa classe é um nó no banco de dados orientado a grafos
@Node
public class Item {
    @Id                  //@GeneratedValue
    private long id;     // Identificador único do item

    private String name; // Nome do item

    // Anotação para definir um relacionamento com outros itens
    @Relationship(type = "RELATES_TO", direction = Relationship.Direction.OUTGOING)
    private List<Item> relatedItems; // Lista de itens relacionados

    // Construtor padrão vazio
    public Item(){

    }

    // Método getter para obter o identificador único do item
    public long getId(){
        return id;
    }

    // Método getter para obter o nome do item
    public String getName(){
        return name;
    }

    // Método setter para definir o identificador único do item
    public void setId(long id){
        this.id = id;
    }

    // Método setter para definir o nome do item
    public void setName(String name){
        this.name = name;
    }
}
