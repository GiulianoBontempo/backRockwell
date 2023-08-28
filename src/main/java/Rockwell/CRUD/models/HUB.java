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

    private String name; // Nome do HUB

    // Construtor padrão vazio
    public HUB(){
        
    }
    
    // Método getter para obter o identificador único do HUB
    public long getId() {
        return id;
    }

    // Método getter para obter o nome do HUB
    public String getName() {
        return name;
    }

    // Método setter para definir o nome do HUB
    public void setName(String name) {
        this.name = name;
    }
}
