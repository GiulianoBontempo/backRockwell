package Rockwell.CRUD.models;

// Importação das classes e anotações necessárias
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;

// Anotação que indica que essa classe é um nó no banco de dados orientado a grafos
@Node
public class Tank {

    @Id @GeneratedValue
    private Long id;    // Identificador único gerado automaticamente

    private int number; // Número do tanque

    // Construtor padrão vazio
    public Tank() {
    }

    // Método getter para obter o identificador único do tanque
    public Long getId() {
        return id;
    }

    // Método getter para obter o número do tanque
    public int getNumber() {
        return number;
    }

    // Método setter para definir o número do tanque
    public void setNumber(int number) {
        this.number = number;
    }
}
