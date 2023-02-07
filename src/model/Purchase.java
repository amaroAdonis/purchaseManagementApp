package model;

public class Purchase {

    private String createdOn;
    private Integer id, productId, userId, quantity;
    private Double cost;

    public Purchase() {
    }

    public Purchase(String createdOn, Integer id, Integer productId, Integer userId, Integer quantity, Double cost) {
        this.createdOn = createdOn;
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return id + "," + productId + "," + userId + "," + quantity + "," + cost + "," + createdOn;
    }
}
