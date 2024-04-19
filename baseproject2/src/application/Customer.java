package application;

public class Customer {
    private int customer_id;
    private String customer_name;
    private String customer_phone; 
    
    static Customer cust;

    public Customer() {
        super();
    }

    public Customer(String customer_name, String customer_phone) {
        super();
        this.customer_name = customer_name;
        this.customer_phone = customer_phone;
    }


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }
}
