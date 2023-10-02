package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para atualizar a posição de um tanque
public class UpdateTankPositionRequest {
    private Integer positionX;
    private Integer positionY;

    // Método para obter a nova posição X do tanque
    public Integer getPositionX() {
        return positionX;
    }

    // Método para obter a nova posição Y do tanque
    public Integer getPositionY() {
        return positionY;
    }

    // Método para definir a nova posição X do tanque
    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    // Método para definir a nova posição Y do tanque
    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}
