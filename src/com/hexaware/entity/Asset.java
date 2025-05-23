package com.hexaware.entity;

public class Asset {
	private int id;
    private String name;
    private String type;
    private String serialNumber;
    private String purchaseDate;
    private String location;
    private String status;
    private int ownerId;

    public Asset(int id, String name, String type,String serialNumber, String purchaseDate,
    String location,String status,int ownerId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.location = location;
        this.status = status;
        this.ownerId = ownerId;
    }

    public Asset(String name, String type,String serialNumber, String purchaseDate,
    String location,String status,int ownerId) {
        this.name = name;
        this.type = type;
        this.serialNumber = serialNumber;
        this.purchaseDate = purchaseDate;
        this.location = location;
        this.status = status;
        this.ownerId = ownerId;
    }

    public int getAssetId() { return id; }
    public String getAssetName() { return name; }
    public String getType() { return type; }
    public String getSerialNumber(){return serialNumber; }
    public String getPurchaseDate(){return purchaseDate; }
    public String getLocation(){return location; }
    public String getStatus() { return status; }
    public int getOwnerId() {return ownerId; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setSerialNumber(String serialNumber){this.serialNumber = serialNumber; }
    public void setPurchaseDate(String purchaseDate){this.purchaseDate = purchaseDate; }
    public void setLocatoin(String location){this.location = location; }
    public void setStatus(String status) { this.status = status; }
    public void setOwnerId(int ownerId) {this.ownerId = ownerId; }

    @Override
    public String toString() {
        return "Asset{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", type='" + type + '\'' +
        ", serialNumber='" + serialNumber + '\'' +
        ", purchaseDate='" + purchaseDate + '\'' +
        ", location='" + location + '\'' +
        ", status='" + status + '\'' +
        ", ownerId=" + ownerId +
        '}';
    }
	
}
