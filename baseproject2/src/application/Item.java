package application;

public class Item {

    private int item_id;
    private String item_name;
    private double item_price; 
    private int quantity;
	static Item it;


    public Item() {
        super();
    }

    public Item(int item_id, String item_name, double item_price, int quantity) {
        super();
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.quantity = quantity;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
