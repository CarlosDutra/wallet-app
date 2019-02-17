package br.com.movilehackapp.movilehackapp.model;

public class Compra {
    private String buyerId;
    private double maxValue;
    private String expirationDate;

    public Compra(String buyerId, double maxValue, String expirationDate) {
        this.buyerId = buyerId;
        this.maxValue = maxValue;
        this.expirationDate = expirationDate;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
