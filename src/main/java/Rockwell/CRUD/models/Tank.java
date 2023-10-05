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

    private Integer positionX;
    private Integer positionY; // Posição do tanque
    // Construtor padrão vazio
    public Tank() {
        this.positionX = 0;  
        this.positionY = 0;  
    }

    // Método getter para obter o identificador único do tanque
    public Long getId() {
        return id;
    }

    // Método getter para obter o número do tanque
    public Integer getNumber() {
        return number;
    }
    
    // Método getter para obter a posição do tanque
    public Integer getPositionX(){
        return positionX;
    }

    // Método getter para obter a posição do tanque
    public Integer getPositionY(){
        return positionY;
    }   

    // Método setter para definir o número do tanque
    public void setNumber(Integer number) {
        this.number = number;
    }

    // Método setter para definir a posição do tanque
    public void setPositionX(Integer positionX){
        this.positionX = positionX;
    }


    // Método setter para definir a posição do tanque
    public void setPositionY(int positionY){
        this.positionY = positionY;
    }
}
