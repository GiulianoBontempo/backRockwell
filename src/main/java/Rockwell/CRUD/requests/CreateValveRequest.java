package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para criar uma nova válvula
public class CreateValveRequest {
    private String name;
    private Integer positionX;
    private Integer positionY;

    // Construtor padrão da classe CreateValveRequest
    public CreateValveRequest() {
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
