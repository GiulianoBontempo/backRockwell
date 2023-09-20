package Rockwell.CRUD.queries;

import java.util.List;

public class GetAllConnectionsQueryResult {
    private List<String> sourceNodeType;
    private String sourceNameOrNumber;
    private List<String> targetNodeType;
    private String targetNameOrNumber;

    // Constructor
    public GetAllConnectionsQueryResult(List<String> sourceNodeType, String sourceNameOrNumber, List<String> targetNodeType, String targetNameOrNumber) {
        this.sourceNodeType = sourceNodeType;
        this.sourceNameOrNumber = sourceNameOrNumber;
        this.targetNodeType = targetNodeType;
        this.targetNameOrNumber = targetNameOrNumber;
    }

    // Getters and setters for each field
    public String getSourceNodeType() {
        return sourceNodeType.get(0);
    }

    public void setSourceNodeType(List<String> sourceNodeType) {
        this.sourceNodeType = sourceNodeType;
    }

    public String getSourceNameOrNumber() {
        return sourceNameOrNumber;
    }

    public void setSourceNameOrNumber(String sourceNameOrNumber) {
        this.sourceNameOrNumber = sourceNameOrNumber;
    }

    public String getTargetNodeType() {
        return targetNodeType.get(0);
    }

    public void setTargetNodeType(List<String> targetNodeType) {
        this.targetNodeType = targetNodeType;
    }

    public String getTargetNameOrNumber() {
        return targetNameOrNumber;
    }

    public void setTargetNameOrNumber(String targetNameOrNumber) {
        this.targetNameOrNumber = targetNameOrNumber;
    }

    @Override
    public String toString() {
        return "QueryResult{" +
                "sourceNodeType='" + sourceNodeType.get(0) + '\'' +
                ", sourceNameOrNumber='" + sourceNameOrNumber + '\'' +
                ", targetNodeType='" + targetNodeType.get(0) + '\'' +
                ", targetNameOrNumber='" + targetNameOrNumber + '\'' +
                '}';
    }
}

