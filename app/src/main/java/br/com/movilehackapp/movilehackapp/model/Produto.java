package br.com.movilehackapp.movilehackapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Produto implements Serializable {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("image")
    private String image;
    @SerializedName("value")
    private double value;
    @SerializedName("average")
    private double average;
    @SerializedName("customers")
    private int customers;

    public Produto(String id, String name, String description, String image, double value, double average, int customers) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.value = value;
        this.average = average;
        this.customers = customers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }
}
