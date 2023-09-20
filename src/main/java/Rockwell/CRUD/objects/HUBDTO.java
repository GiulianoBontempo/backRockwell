package Rockwell.CRUD.objects;

import java.util.ArrayList;
import java.util.List;

import Rockwell.CRUD.models.Tank;

// Definição da classe que representa um objeto DTO para informações de um HUB e seus tanques associados
public class HUBDTO {

    private String name;                         
    private int positionX;                        
    private int positionY;                       
    private List<Tank> tanks = new ArrayList<>(); 

    // Construtor que recebe o nome do HUB
    public HUBDTO(String name) {
        this.name = name;
    }

    // Método getter para obter o nome do HUB
    public String getName() {
        return name;
    }

    // Método setter para definir o nome do HUB
    public void setName(String name){
        this.name = name;
    }

    // Método getter para obter a coordenada X do HUB
    public int getPositionX() {
        return positionX;
    }

    // Método setter para definir a coordenada X do HUB
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    // Método getter para obter a coordenada Y do HUB
    public int getPositionY() {
        return positionY;
    }

    // Método setter para definir a coordenada Y do HUB
    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    // Método getter para obter a lista de tanques associados ao HUB
    public List<Tank> getTanks() {
        return tanks;
    }

    // Método setter para definir a lista de tanques associados ao HUB
    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }
}
