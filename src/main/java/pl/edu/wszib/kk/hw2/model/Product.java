package pl.edu.wszib.kk.hw2.model;

public class Product {
    private int id;
    private String name;
    private String descpription;
    private String type;
    private int amount;

    public Product(int id, String name, String descpription, String type, int amount) {
        this.id = id;
        this.name = name;
        this.descpription = descpription;
        this.type = type;
        this.amount = amount;
    }
    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescpription() {
        return descpription;
    }

    public void setDescpription(String descpription) {
        this.descpription = descpription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product clone(){return new Product(this.id,this.name,this.descpription,this.type,this.amount); }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", descpription='" + descpription + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}

