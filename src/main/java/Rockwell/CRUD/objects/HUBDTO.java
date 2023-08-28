package Rockwell.CRUD.objects;

import java.util.ArrayList;
import java.util.List;

import Rockwell.CRUD.models.Tank;

// Definição da classe que representa um objeto DTO para informações de um HUB e seus tanques associados
public class HUBDTO {

    private String name;                          // Nome do HUB
    private List<Tank> tanks = new ArrayList<>(); // Lista de tanques associados ao HUB

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

    // Método getter para obter a lista de tanques associados ao HUB
    public List<Tank> getTanks() {
        return tanks;
    }

    // Método setter para definir a lista de tanques associados ao HUB
    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }
}
