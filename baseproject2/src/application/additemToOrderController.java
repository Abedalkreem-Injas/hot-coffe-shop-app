package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class additemToOrderController {

	
	@FXML
	private TextField Item_name;

	@FXML
	private Button addToCart;

	@FXML
	private Button cancelItem;

	@FXML
	private HBox itemName;
	@FXML
	private HBox parcode;
	
	@FXML
	private TextField item_parcode;

	@FXML
	private Spinner<Integer> item_quant;
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	LocalDateTime now = LocalDateTime.now();

	
	@FXML
	public void initialize() {
		
		parcode.setVisible(true);
		SpinnerValueFactory<Integer> quant = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,200,1);
		item_quant.setValueFactory(quant);
	}

	@FXML
	void addToCart(ActionEvent event) {
	    PreparedStatement st2 = null;
	    ResultSet r2 = null;
	    try {
	        if (!item_parcode.getText().equals("")) {
	            try {
	                int itemParcode = Integer.parseInt(item_parcode.getText());
	                st2 = connector.a.connectDB().prepareStatement("SELECT * FROM items WHERE item_id = ?");
	                st2.setInt(1, itemParcode);
	            } catch (NumberFormatException e) {
 	                // Handle the case where item_parcode is not a valid integer
	                Message.displayMassage("Invalid Item ID. Please enter a valid numeric ID.", "error");
	                return;
	            }
	        } else {
	            // If item_parcode is empty, use item name
	            st2 = connector.a.connectDB().prepareStatement("SELECT * FROM items WHERE item_name = ?");
	            st2.setString(1, Item_name.getText());
	        }

	        r2 = st2.executeQuery();

	        if (r2.next()) {
	            System.out.println("name >>" + r2.getString(2));
	            System.out.println("id >>" + r2.getInt(1));
	            System.out.println("quant >>" + r2.getInt(4));

	        } else {
	            Message.displayMassage("This item was not found!", "error");
	            return;
	        }
	    } catch (ClassNotFoundException | SQLException e) {
	        // Handle other SQLExceptions or ClassNotFoundException if needed
	        e.printStackTrace();
	    }

	    try {
	        if (r2.getInt(4) - item_quant.getValue() >= 0) {

	            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	            java.util.Date myDate = null;
	            @SuppressWarnings("unused")
	            java.sql.Date sqlDate;
	            try {
	                myDate = formatter.parse(dtf.format(now));

	            } catch (Exception e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }

	            sqlDate = new java.sql.Date(myDate.getTime());

	            try {

	                connector.a.connectDB();
	                String sql = "insert into invoice(quantity ,price,item_id,order_id,order_date) values (?,?,?,?,?);";
	                PreparedStatement ps = (PreparedStatement) connector.a.connectDB().prepareStatement(sql);
	                connector.a.ExecuteStatement("update items set quantity = " + (r2.getInt(4) - item_quant.getValue())
	                        + " where item_id = " + r2.getInt(1) + ";");

	                ps.setInt(1, item_quant.getValue());

	                ps.setDouble(2, r2.getDouble(3));
	                ps.setInt(3, r2.getInt(1));
	                ps.setInt(4, ordersController.orderId);
	                ps.setTimestamp(5, new java.sql.Timestamp(myDate.getTime()));
	                ps.execute();

	            } catch (SQLException e) {
	                if (e.getErrorCode() == 1062) {
	                    Message.displayMassage("This item is already added!", "error");
	                    return;
	                }
	                e.printStackTrace();
	            } catch (ClassNotFoundException e) {
	                e.printStackTrace();
	            }

	            Stage stage = (Stage) cancelItem.getScene().getWindow();
	            stage.close();
	        } else {
	            if (r2.getInt(4) > 0) {
	                Message.displayMassage("There is not enough quantity of this product!\r\n" + "There is only: "
	                        + r2.getInt(4), "warning");

	            } else {
	                Message.displayMassage("Out of stock!", "warning");
	            }
	        }
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}

	@FXML
	void byItemName(ActionEvent event) {

		item_parcode.clear();
		Item_name.clear();
		parcode.setVisible(false);
		itemName.setVisible(true);
		itemName.setLayoutX(67);
		itemName.setLayoutY(270);

	}

	@FXML
	void byParcode(ActionEvent event) {
		item_parcode.clear();
		Item_name.clear();
		parcode.setVisible(true);
		itemName.setVisible(false);
		parcode.setLayoutX(67);
		parcode.setLayoutY(270);

	}

	@FXML
	void cancelItem(ActionEvent event) {

		Stage stage = (Stage) cancelItem.getScene().getWindow();
		stage.close();
	}

}