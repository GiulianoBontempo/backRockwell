package Rockwell.CRUD.requests;

public class CreateValveRequest {
   private String name;
    private Integer positionX;
    private Integer positionY;

    public CreateValveRequest() {
    }


    public String getName() {
        return name;
    }

    public Integer getPositionX(){
        return positionX;
    }

    public Integer getPositionY(){
        return positionY;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPositionX(Integer positionX){
        this.positionX = positionX;
    }

    public void setPositionY(Integer positionY){
        this.positionY = positionY;
    }
}
