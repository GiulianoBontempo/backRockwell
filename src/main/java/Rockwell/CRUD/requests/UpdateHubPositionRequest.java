package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para atualizar a posição de um hub (central)
public class UpdateHubPositionRequest {
    private Integer positionX;
    private Integer positionY;

    // Método para obter a nova posição X do hub
    public Integer getPositionX() {
        return positionX;
    }

    // Método para definir a nova posição X do hub
    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    // Método para obter a nova posição Y do hub
    public Integer getPositionY() {
        return positionY;
    }

    // Método para definir a nova posição Y do hub
    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}
