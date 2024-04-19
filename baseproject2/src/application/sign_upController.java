
package application;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class sign_upController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="addressTextField"
	private TextField addressTextField; // Value injected by FXMLLoader

	@FXML // fx:id="birthdatePicker"
	private DatePicker birthdatePicker; // Value injected by FXMLLoader

	@FXML // fx:id="genderTextField"
	private TextField genderTextField; // Value injected by FXMLLoader

	@FXML // fx:id="idTextField"
	private TextField idTextField; // Value injected by FXMLLoader

	@FXML // fx:id="nameTextField"
	private TextField nameTextField; // Value injected by FXMLLoader

	@FXML // fx:id="passwordField"
	private PasswordField passwordField; // Value injected by FXMLLoader

	@FXML // fx:id="phoneNumberTextField"
	private TextField phoneNumberTextField; // Value injected by FXMLLoader

	@FXML // fx:id="salaryTextField"
	private TextField salaryTextField; // Value injected by FXMLLoader

	@FXML // fx:id="signUpButton"
	private Button signUpButton; // Value injected by FXMLLoader
	
	
	@FXML 
	private Button loginbutton; 
	
	
	@FXML
	void handleSignUp(ActionEvent event) {
	    try {
	        employee emp = new employee();

	        // Validate name
	        String empName = nameTextField.getText().trim();
	        if (empName.isEmpty() || !empName.matches("[A-Za-z ]+")) {
	            Message.displayMassage("Invalid name. Please enter a valid string.", "error");
	            return;
	        }
	        emp.setEmployee_name(empName);

	        // Validate ID
	        int empId;
	        try {
	            empId = Integer.parseInt(idTextField.getText().trim());
	        } catch (NumberFormatException e) {
	            Message.displayMassage("Invalid ID. Please enter a number.", "error");
	            return;
	        }

	        // Check if the entered ID exists in the database
	        if (isEmployeeIdEqual(empId)) {
	            Message.displayMassage("Entered Employee ID matches an existing ID. Please enter a different ID.", "error");
	            return;
	        }
	        emp.setEmp_id(empId);

	        // Validate birthdate
	        LocalDate birthdate = birthdatePicker.getValue();
	        if (birthdate == null) {
	            Message.displayMassage("Invalid birthdate. Please enter a valid date.", "error");
	            return;
	        }
	        String formattedDate = birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	        emp.setEmp_birthdate(formattedDate);

	        // Validate phone number
	        String phone = phoneNumberTextField.getText().trim();
	        if (!phone.matches("\\d+")) {
	            Message.displayMassage("Invalid phone number. Please enter a number.", "error");
	            return;
	        }
	        emp.setEmp_phone(phone);

	        // Validate address
	        String address = addressTextField.getText().trim();
	        if (address.isEmpty()) {
	            Message.displayMassage("Invalid address. Please enter a string.", "error");
	            return;
	        }
	        emp.setEmp_address(address);

	        // Validate gender
	        String gender = genderTextField.getText().trim().toLowerCase();
	        if (!(gender.equals("male") || gender.equals("female"))) {
	            Message.displayMassage("Invalid gender. Please enter 'male' or 'female'.", "error");
	            return;
	        }
	        emp.setEmp_gender(gender);

	        // Validate salary
	        double salary;
	        try {
	            salary = Double.parseDouble(salaryTextField.getText());
	        } catch (NumberFormatException e) {
	            Message.displayMassage("Invalid salary. Please enter a number.", "error");
	            return;
	        }
	        emp.setEmp_salary(salary);

	        emp.setEmp_password(passwordField.getText());

	        // Database insertion
	        java.sql.Connection conn = connector.a.connectDB();
	        String sql = "INSERT INTO employee_manage (emp_id, emp_name, emp_birthdate, emp_phone, emp_address, emp_gender, emp_salary, emp_password, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

	        ps.setInt(1, emp.getEmp_id());
	        ps.setString(2, emp.getEmployee_name());

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate localDate = LocalDate.parse(formattedDate, formatter);
	        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
	        ps.setDate(3, sqlDate);

	        ps.setString(4, emp.getEmp_phone());
	        ps.setString(5, emp.getEmp_address());
	        ps.setString(6, emp.getEmp_gender());
	        ps.setDouble(7, emp.getEmp_salary());
	        ps.setString(8, emp.getEmp_password());
	        ps.setInt(9, 1); // Assuming manager_id is 1 for this example

	        ps.execute();
	        conn.close();
	        System.out.println("SIGN UP CORRECTLY!, THANKS");

	        // Reset fields
	        birthdatePicker.setValue(null);
	        nameTextField.clear();
	        idTextField.clear();
	        phoneNumberTextField.clear();
	        addressTextField.clear();
	        genderTextField.clear();
	        salaryTextField.clear();
	        passwordField.clear();

	    } catch (Exception e) {
	        e.printStackTrace();
	        Message.displayMassage("An error occurred: " + e.getMessage(), "error");
	    }
	}

	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert addressTextField != null
				: "fx:id=\"addressTextField\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert birthdatePicker != null
				: "fx:id=\"birthdatePicker\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert genderTextField != null
				: "fx:id=\"genderTextField\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert idTextField != null : "fx:id=\"idTextField\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert phoneNumberTextField != null
				: "fx:id=\"phoneNumberTextField\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert salaryTextField != null
				: "fx:id=\"salaryTextField\" was not injected: check your FXML file 'SignUp.fxml'.";
		assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'SignUp.fxml'.";

	}

	// Method to check if the entered employee ID matches an existing ID in the database
	private boolean isEmployeeIdEqual(int empId) throws ClassNotFoundException, SQLException {
	    java.sql.Connection conn = connector.a.connectDB();
	    String sql = "SELECT emp_id FROM employee_manage WHERE emp_id = ?";
	    java.sql.PreparedStatement ps = conn.prepareStatement(sql);
	    ps.setInt(1, empId);

	    java.sql.ResultSet rs = ps.executeQuery();
	    boolean idExists = rs.next(); // If rs.next() is true, the ID exists

	    conn.close();

	    return idExists;
	}
	
	///////
	
	 @FXML
	   void loginbuttonaction(ActionEvent event) {

    	try { // open new stage
			Stage stage;
			Parent root;
			stage = (Stage) loginbutton.getScene().getWindow();
			stage.close();
			root = FXMLLoader.load(getClass().getResource("Sign_in.fxml"));
			Scene scene = new Scene(root, 600, 400);
			stage.setScene(scene);
			stage.setTitle("Hot coffee");
			stage.show();

		} catch (IOException e1) {

		}
    }
	
	
	
	
	
	
}
