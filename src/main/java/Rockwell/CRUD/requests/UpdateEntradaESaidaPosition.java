package Rockwell.CRUD.requests;

// Definição da classe que representa uma solicitação para atualizar a posição de uma Entrada ou Saída
public class UpdateEntradaESaidaPosition {
    private Integer positionX;
    private Integer positionY;

    // Método para obter a nova posição X
    public Integer getPositionX() {
        return positionX;
    }

    // Método para obter a nova posição Y
    public Integer getPositionY() {
        return positionY;
    }

    // Método para definir a nova posição X
    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    // Método para definir a nova posição Y
    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}
