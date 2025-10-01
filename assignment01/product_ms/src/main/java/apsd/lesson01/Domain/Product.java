package apsd.lesson01.Domain;

import java.math.BigInteger;
import java.sql.Date;

public class Product {
    private BigInteger  id;
    private String name;
    private Date suppliedDate;
    private int quantity;
    private double unitPrice;

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSuppliedDate(Date suppliedDate) {
        this.suppliedDate = suppliedDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getName() {
        return name;
    }

    public Date getSuppliedDate() {
        return suppliedDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Product(BigInteger id, String name, Date suppliedDate, int quantity, double unitPrice) {
        this.id = id;
        this.name = name;
        this.suppliedDate = suppliedDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public BigInteger getId() {
        return id;
    }
    
}
