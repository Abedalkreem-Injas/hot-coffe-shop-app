package application;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class FeedBackController {

    @FXML
    private ToggleGroup A;

    @FXML
    private Button Avg;

    @FXML
    private TextArea Avgg;

    @FXML
    private Button back;

    @FXML
    private TextField cusid;

    @FXML
    private RadioButton star1;

    @FXML
    private RadioButton star2;

    @FXML
    private RadioButton star3;

    @FXML
    private RadioButton star4;

    @FXML
    private RadioButton star5;

    @FXML
    private Button submit;

    @FXML
    void AvgOnAction(ActionEvent event) {
        try {
            double avgRating = 0;
            connector.a.connectDB();
            String sql = "select avg(feedback_rating) from feedback_give";
            PreparedStatement ps = connector.a.connectDB().prepareStatement(sql);
            ResultSet r3 = ps.executeQuery();
            if (r3.next()) {
                avgRating = r3.getDouble(1);
            }
            Avgg.setText(String.valueOf(avgRating));
            r3.close();
            ps.close();
            connector.a.connectDB().close();
        } catch (SQLException | ClassNotFoundException e) {
            showDialog("Error", "Database connection failed", e.getMessage(), AlertType.ERROR);
        }
    }

    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene scene = new Scene(root, 901, 649);
        stage.setScene(scene);
        stage.show();
		stage.setMaximized(false);

    }

    @FXML
    void star1OnAction(ActionEvent event) {
    }

    @FXML
    void star2OnAction(ActionEvent event) {
    	
    }

    @FXML
    void star3OnAction(ActionEvent event) {
    	 
    }

    @FXML
    void star4OnAction(ActionEvent event) {
    }

    @FXML
    void star5OnAction(ActionEvent event) {
    }

    @FXML
    void submitOnAction(ActionEvent event) {
        try {
            int state = 0;
            int customerId;
            
            // Check if customer ID is a number
            try {
                customerId = Integer.parseInt(cusid.getText().trim());
            } catch (NumberFormatException e) {
                showDialog("Input Error", "Invalid Customer ID", "Please enter a numeric customer ID.", AlertType.ERROR);
                return;
            }

            connector.a.connectDB();
            String checkSql = "SELECT COUNT(*) FROM customers WHERE customer_id = ?"; 
            PreparedStatement checkPs = connector.a.connectDB().prepareStatement(checkSql);
            checkPs.setInt(1, customerId);
            ResultSet rs = checkPs.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                showDialog("Error", "Non-existent Customer ID", "The customer ID you entered does not exist in our records.", AlertType.ERROR);
                return;
            }

            if(star1.isSelected()) {
                state=1;
            } else if(star2.isSelected()) {
                state=2;
            } else if(star3.isSelected()) {
                state=3;
            } else if(star4.isSelected()) {
                state=4;
            } else if(star5.isSelected()) {
                state=5;
            }

            String sql = "Insert into feedback_give (customer_id, feedback_rating, cleaness, suggestion, service, quality) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connector.a.connectDB().prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.setInt(2, state);
            ps.setString(3, "Yes, it's clean");
            ps.setString(4, "There is no suggestion");
            ps.setString(5, "GOOD Service");
            ps.setString(6, "Perfect");

            ps.execute();
            cusid.clear();

        } catch (SQLException e) {
            e.printStackTrace();
            showDialog("SQL Error", "An SQL Exception Occurred", e.getMessage(), AlertType.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            showDialog("Error", "An Exception Occurred", e.getMessage(), AlertType.ERROR);
        }
    }
    
private void showDialog(String title, String header, String body, AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(body);
    alert.show();
}
}



