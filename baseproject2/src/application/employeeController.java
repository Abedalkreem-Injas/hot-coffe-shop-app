package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class employeeController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="AddBtn"
    private Button AddBtn; // Value injected by FXMLLoader

    @FXML // fx:id="DeleteEmpId"
    private TextField DeleteEmpId; // Value injected by FXMLLoader

    @FXML // fx:id="FindAvg"
    private Button FindAvg; // Value injected by FXMLLoader

    @FXML // fx:id="FindMax"
    private Button FindMax; // Value injected by FXMLLoader

    @FXML // fx:id="MaxArea"
    private TextArea MaxArea; // Value injected by FXMLLoader

    @FXML // fx:id="RefereshBtn"
    private Button SearchBtn; // Value injected by FXMLLoader

    @FXML // fx:id="SearchFiled"
    private TextField SearchFiled; // Value injected by FXMLLoader

    @FXML // fx:id="TableData"
    private TableView<employee> TableData; // Specify the correct type for TableView

    @FXML // fx:id="TableEmpAddress"
    private TableColumn<employee, String> TableEmpAddress; // Value injected by FXMLLoader

    @FXML // fx:id="TableEmpBirth"
    private TableColumn<employee, String> TableEmpBirth; // Value injected by FXMLLoader

    @FXML // fx:id="TableEmpGender"
    private TableColumn<employee, String> TableEmpGender; // Value injected by FXMLLoader

    @FXML // fx:id="TableEmpID"
    private TableColumn<employee, Integer> TableEmpID; // Value injected by FXMLLoader

    @FXML // fx:id="TableEmpName"
    private TableColumn<employee, String> TableEmpName; // Value injected by FXMLLoader

    @FXML // fx:id="TableEmpPass"
    private TableColumn<employee, String> TableEmpPass; // Value injected by FXMLLoader

    @FXML // fx:id="TableEmpPhone"
    private TableColumn<employee, String> TableEmpPhone; // Value injected by FXMLLoader

    @FXML // fx:id="TableEmpSalary"
    private TableColumn<employee, Double> TableEmpSalary; // Value injected by FXMLLoader

    @FXML // fx:id="UpdateBtn"
    private Button UpdateBtn; // Value injected by FXMLLoader

    @FXML // fx:id="addedAddress"
    private TextField addedAddress; // Value injected by FXMLLoader

    @FXML // fx:id="addedBirthDate"
    private DatePicker addedBirthDate; // Value injected by FXMLLoader

    @FXML // fx:id="addedGender"
    private TextField addedGender; // Value injected by FXMLLoader

    @FXML // fx:id="addedID"
    private TextField addedID; // Value injected by FXMLLoader

    @FXML // fx:id="addedName"
    private TextField addedName; // Value injected by FXMLLoader

    @FXML // fx:id="addedPassword"
    private TextField addedPassword; // Value injected by FXMLLoader

    @FXML // fx:id="addedPhone"
    private TextField addedPhone; // Value injected by FXMLLoader

    @FXML // fx:id="addedSalary"
    private TextField addedSalary; // Value injected by FXMLLoader

    @FXML // fx:id="avgArea"
    private TextArea avgArea; // Value injected by FXMLLoader

    @FXML // fx:id="backBtn"
    private Button backBtn; // Value injected by FXMLLoader

    @FXML // fx:id="deleteBtn"
    private Button deleteBtn; // Value injected by FXMLLoader

    @FXML // fx:id="oldempID"
    private TextField oldempID; // Value injected by FXMLLoader

    @FXML // fx:id="updatedAddress"
    private TextField updatedAddress; // Value injected by FXMLLoader

    @FXML // fx:id="updatedBirthDate"
    private DatePicker updatedBirthDate; // Value injected by FXMLLoader

    @FXML // fx:id="updatedGender"
    private TextField updatedGender; // Value injected by FXMLLoader

    @FXML // fx:id="updatedID"
    private TextField updatedID; // Value injected by FXMLLoader

    @FXML // fx:id="updatedName"
    private TextField updatedName; // Value injected by FXMLLoader

    @FXML // fx:id="updatedPassword"
    private TextField updatedPassword; // Value injected by FXMLLoader

    @FXML // fx:id="updatedPhone"
    private TextField updatedPhone; // Value injected by FXMLLoader

    @FXML // fx:id="updatedSalary"
    private TextField updatedSalary; // Value injected by FXMLLoader
    
    @FXML // fx:id="msg"
    private javafx.scene.control.Label msg;
    
    @FXML // fx:id="msg2"
    private javafx.scene.control.Label msg2;
    
    @FXML // fx:id="msg3"
    private javafx.scene.control.Label msg3;
    // Data 
    private ArrayList<employee> data = new ArrayList<>();
    private ObservableList<employee> dataList = FXCollections.observableArrayList();
    int flag=0;
	 @FXML
	    void FindAvgOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		 double avg =0;
		 PreparedStatement ps;
		 ps = (PreparedStatement) connector.a.connectDB().prepareStatement("select avg(emp_salary) from employee_manage;");
		 ResultSet rs = ps.executeQuery();
		 if (rs.next())
			 avg = rs.getDouble(1);
		 avgArea.setText("Average: "+avg);

	    }

    @FXML
    void FindMaxOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
		 double max =0;
		 int id=0;
		 String name="",address="";
		 PreparedStatement ps,ps1;
		 ps = (PreparedStatement) connector.a.connectDB().prepareStatement("select max(emp_salary) from employee_manage;");
		 ResultSet rs = ps.executeQuery();
		 if (rs.next())
			 max = rs.getDouble(1);
		 ps1 = (PreparedStatement) connector.a.connectDB().prepareStatement("select emp_id, emp_name, emp_address from employee_manage where emp_salary= "+max);
		 ResultSet rs1 = ps1.executeQuery();

		 while(rs1.next()) {
			 id = rs1.getInt(1);
			 name = rs1.getString(2);
			 address = rs1.getString(3);
		 }
		 MaxArea.setText("Employee Details of "+max+" Salary: \nID: "+id+"\nName: "+name+"\nAddress: "+address+"\nThis Salary is the max.");
    }

    @FXML
    void addOnAction(ActionEvent event) {
        try {
            // Retrieve the text from the TextField and trim it
            String addText = addedID.getText().trim();

            // If the add id field is empty, reload the initial data
            if (addText.isEmpty()) {
                initialize();
                return;
            }

            employee emp = new employee();
            String empName = addedName.getText().trim();
            if (empName.isEmpty() || !empName.matches("[A-Za-z ]+")) { // Check if name is a valid string
                Message.displayMassage("Invalid name. Please enter a valid string.", "error");
                return;
            }
            emp.setEmployee_name(empName);

            // Check if ID is a number
            int empId;
            try {
                empId = Integer.parseInt(addText);
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
            LocalDate birthdate = addedBirthDate.getValue();
            if (birthdate == null) {
                Message.displayMassage("Invalid birthdate. Please enter a valid date.", "error");
                return;
            }
            String formattedDate = birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            emp.setEmp_birthdate(formattedDate);

            // Validate phone number
            String phone = addedPhone.getText().trim();
            if (!phone.matches("\\d+")) {
                Message.displayMassage("Invalid phone number. Please enter a number.", "error");
                return;
            }
            emp.setEmp_phone(phone);

            // Validate address
            String address = addedAddress.getText().trim();
            if (address.isEmpty()) {
                Message.displayMassage("Invalid address. Please enter a string.", "error");
                return;
            }
            emp.setEmp_address(address);

            // Validate gender
            String gender = addedGender.getText().trim().toLowerCase();
            if (!(gender.equals("male") || gender.equals("female"))) {
                Message.displayMassage("Invalid gender. Please enter 'male' or 'female'.", "error");
                return;
            }
            emp.setEmp_gender(gender);

            // Validate salary
            double salary;
            try {
                salary = Double.parseDouble(addedSalary.getText());
            } catch (NumberFormatException e) {
                Message.displayMassage("Invalid salary. Please enter a number.", "error");
                return;
            }
            emp.setEmp_salary(salary);

            emp.setEmp_password(addedPassword.getText());

            // Database connection and insertion code
            java.sql.Connection conn = connector.a.connectDB();
            String sql = "INSERT INTO employee_manage (emp_id, emp_name, emp_birthdate, emp_phone, emp_address, emp_gender, emp_salary, emp_password, manager_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, emp.getEmp_id());
            ps.setString(2, emp.getEmployee_name());

            // Convert formatted date string to java.sql.Date
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
            msg.setText("ADDED SUCCESSFULLY!");

            // Reset fields
            addedBirthDate.setValue(null);
            addedName.clear();
            addedID.clear();
            addedPhone.clear();
            addedAddress.clear();
            addedGender.clear();
            addedSalary.clear();
            addedPassword.clear();
            initialize();

        } catch (Exception e) {
            e.printStackTrace();
            Message.displayMassage("An error occurred: " + e.getMessage(), "error");
        }
    }

	@FXML
	public void backOnAction(ActionEvent event) throws IOException {
		
		Stage stage;
		Parent root;
		stage = (Stage) backBtn.getScene().getWindow();
		stage.close();
		root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
		Scene scene = new Scene(root, 901, 649);
		stage.setScene(scene);
		//stage.setTitle("Choose order");
		stage.show();
		stage.setMaximized(false);

	}

    
	@FXML
	void deleteOnAction(ActionEvent event) {
	    try {
	        String delText = DeleteEmpId.getText().trim(); // Retrieve the delete id text from the TextField

	        // If the delete id field is empty, reload the initial data
	        if (delText.isEmpty()) {
	            initialize();
	            return;
	        }

	        // Check if ID is a number
	        int deletedId;
	        try {
	            deletedId = Integer.parseInt(delText);
	        } catch (NumberFormatException e) {
	            Message.displayMassage("Invalid ID. Please enter a number.", "error");
	            return;
	        }

	        // Check if the entered ID exists in the database
	        if (!isEmployeeIdEqual(deletedId)) {
	            // If the entered ID doesn't match an existing ID, prompt the user to re-enter the ID
	            Message.displayMassage("The ID that you need to delete its data doesn't exist.", "error");
	            return; // Exit the method as the ID does not exist
	        }

	        // Connect to the database and delete the employee
	        java.sql.Connection conn = connector.a.connectDB();
	        String sql = "DELETE FROM employee_manage WHERE emp_id = ?;";
	        java.sql.PreparedStatement ps = conn.prepareStatement(sql);

	        ps.setInt(1, deletedId);
	        ps.execute();
	        conn.close();
	        msg3.setText("DELETED SUCCESSFULLY!");

	        // Reset the field
	        DeleteEmpId.clear();
	        initialize();

	    } catch (Exception e) {
	        e.printStackTrace();
	        Message.displayMassage("An error occurred: " + e.getMessage(), "error");
	    }
	}

	
	
    ////// To fill the Table data from the database
    private void fillTableFromDatabase() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM employee_manage;";

        // Establish database connection
        Connection conn = (Connection) connector.a.connectDB();

        // Execute query
        Statement statement = (Statement) conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Clear dataList before adding elements from the database
        dataList.clear();
		while (resultSet.next()) {
			employee e = new employee(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),
					resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getDouble(7), resultSet.getString(8));
			dataList.add(e);
		}
		 // Set the emp to the TableView
        TableData.setItems(dataList);
        // Close connections
        resultSet.close();
        statement.close();
        conn.close();
}
    
    @FXML
    void updateOnAction(ActionEvent event) {
        try {
            String updateText = updatedID.getText().trim(); // Retrieve the update id text from the TextField

            // If the update id field is empty, reload the initial data
            if (updateText.isEmpty()) {
                initialize();
                return;
            }

            // Validate old and new IDs
            int newId, oldId;
            try {
                newId = Integer.parseInt(updateText);
                oldId = Integer.parseInt(oldempID.getText().trim());
            } catch (NumberFormatException e) {
                Message.displayMassage("Invalid ID. Please enter a number.", "error");
                return;
            }

            // Check if the old ID exists in the database
            if (!isEmployeeIdEqual(oldId)) {
                Message.displayMassage("The ID that you need to update doesn't exist.", "error");
                return;
            }

            // Check if the new ID is already in use
            if (isEmployeeIdEqual(newId)) {
                Message.displayMassage("Entered Employee ID matches an existing ID. Please enter a different ID.", "error");
                return;
            }

            employee emp = new employee();
            emp.setEmp_id(newId);

            // Validate name
            String empName = updatedName.getText().trim();
            if (empName.isEmpty() || !empName.matches("[A-Za-z ]+")) {
                Message.displayMassage("Invalid name. Please enter a valid string.", "error");
                return;
            }
            emp.setEmployee_name(empName);

            // Validate birthdate
            LocalDate birthdate = updatedBirthDate.getValue();
            if (birthdate == null) {
                Message.displayMassage("Invalid birthdate. Please enter a valid date.", "error");
                return;
            }
            String formattedDate = birthdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            emp.setEmp_birthdate(formattedDate);

            // Validate phone
            String phone = updatedPhone.getText().trim();
            if (!phone.matches("\\d+")) {
                Message.displayMassage("Invalid phone number. Please enter a number.", "error");
                return;
            }
            emp.setEmp_phone(phone);

            // Validate address
            String address = updatedAddress.getText().trim();
            if (address.isEmpty()) {
                Message.displayMassage("Invalid address. Please enter a string.", "error");
                return;
            }
            emp.setEmp_address(address);

            // Validate gender
            String gender = updatedGender.getText().trim().toLowerCase();
            if (!(gender.equals("male") || gender.equals("female"))) {
                Message.displayMassage("Invalid gender. Please enter 'male' or 'female'.", "error");
                return;
            }
            emp.setEmp_gender(gender);

            // Validate salary
            double salary;
            try {
                salary = Double.parseDouble(updatedSalary.getText());
            } catch (NumberFormatException e) {
                Message.displayMassage("Invalid salary. Please enter a number.", "error");
                return;
            }
            emp.setEmp_salary(salary);

            emp.setEmp_password(updatedPassword.getText());

            // Database connection and update operation
            java.sql.Connection conn = connector.a.connectDB();
            String sql = "UPDATE employee_manage SET emp_id = ?, emp_name = ?, emp_birthdate = ?, emp_phone = ?, emp_address = ?, emp_gender = ?, emp_salary = ?, emp_password = ?, manager_id = ? WHERE emp_id = ?;";
            java.sql.PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, emp.getEmp_id());
            ps.setString(2, emp.getEmployee_name());
            LocalDate localDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            ps.setDate(3, sqlDate);
            ps.setString(4, emp.getEmp_phone());
            ps.setString(5, emp.getEmp_address());
            ps.setString(6, emp.getEmp_gender());
            ps.setDouble(7, emp.getEmp_salary());
            ps.setString(8, emp.getEmp_password());
            ps.setInt(9, 1); // Assuming manager_id is 1 for this example
            ps.setInt(10, oldId);

            ps.execute();
            conn.close();
            msg2.setText("UPDATED SUCCESSFULLY!");

            // Reset fields
            updatedBirthDate.setValue(null);
            updatedName.clear();
            updatedID.clear();
            updatedPhone.clear();
            updatedAddress.clear();
            updatedGender.clear();
            updatedSalary.clear();
            updatedPassword.clear();
            oldempID.clear();
            initialize();

        } catch (Exception e) {
            e.printStackTrace();
            Message.displayMassage("An error occurred: " + e.getMessage(), "error");
        }
    }

    /***/
    @FXML
    void searchOnAction(ActionEvent event) {
        try {
            String searchText = SearchFiled.getText().trim();

            // If the search field is empty, reload the initial data
            if (searchText.isEmpty()) {
                initialize();
                return;
            }

            try {
                int empId = Integer.parseInt(searchText);

                if (!isEmployeeIdEqual(empId)) {
                    // If the entered ID doesn't match an existing ID, prompt the user with an error message
                    Message.displayMassage("The ID you searched for doesn't exist.", "error");
                    return; // Exit the method as the ID doesn't match an existing one
                }

                // Clear the existing data from the TableView
                dataList.clear();

                java.sql.Connection conn = connector.a.connectDB();
                String sql = "SELECT * FROM employee_manage WHERE emp_id = ?";
                java.sql.PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, empId);
                java.sql.ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    // Create a new employee instance with the fetched details
                    employee e = new employee(
                            rs.getInt("emp_id"),
                            rs.getString("emp_name"),
                            rs.getString("emp_birthdate"),
                            rs.getString("emp_phone"),
                            rs.getString("emp_address"),
                            rs.getString("emp_gender"),
                            rs.getDouble("emp_salary"),
                            rs.getString("emp_password")
                    );

                    // Add the fetched employee to the ObservableList
                    dataList.add(e);
                }

                // Set the TableView with the updated dataList
                TableData.setItems(dataList);

                conn.close();
            } catch (NumberFormatException e) {
                // Handle the case where the user entered a non-numeric value in the ID field
                Message.displayMassage("Please enter a valid numeric ID.", "error");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately (e.g., log or show an error message)
        }
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

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert AddBtn != null : "fx:id=\"AddBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert DeleteEmpId != null : "fx:id=\"DeleteEmpId\" was not injected: check your FXML file 'employee.fxml'.";
        assert FindAvg != null : "fx:id=\"FindAvg\" was not injected: check your FXML file 'employee.fxml'.";
        assert FindMax != null : "fx:id=\"FindMax\" was not injected: check your FXML file 'employee.fxml'.";
        assert MaxArea != null : "fx:id=\"MaxArea\" was not injected: check your FXML file 'employee.fxml'.";
        assert SearchBtn != null : "fx:id=\"RefereshBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert SearchFiled != null : "fx:id=\"SearchFiled\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableData != null : "fx:id=\"TableData\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpAddress != null : "fx:id=\"TableEmpAddress\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpBirth != null : "fx:id=\"TableEmpBirth\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpGender != null : "fx:id=\"TableEmpGender\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpID != null : "fx:id=\"TableEmpID\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpName != null : "fx:id=\"TableEmpName\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpPass != null : "fx:id=\"TableEmpPass\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpPhone != null : "fx:id=\"TableEmpPhone\" was not injected: check your FXML file 'employee.fxml'.";
        assert TableEmpSalary != null : "fx:id=\"TableEmpSalary\" was not injected: check your FXML file 'employee.fxml'.";
        assert UpdateBtn != null : "fx:id=\"UpdateBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedAddress != null : "fx:id=\"addedAddress\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedBirthDate != null : "fx:id=\"addedBirthDate\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedGender != null : "fx:id=\"addedGender\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedID != null : "fx:id=\"addedID\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedName != null : "fx:id=\"addedName\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedPassword != null : "fx:id=\"addedPassword\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedPhone != null : "fx:id=\"addedPhone\" was not injected: check your FXML file 'employee.fxml'.";
        assert addedSalary != null : "fx:id=\"addedSalary\" was not injected: check your FXML file 'employee.fxml'.";
        assert avgArea != null : "fx:id=\"avgArea\" was not injected: check your FXML file 'employee.fxml'.";
        assert backBtn != null : "fx:id=\"backBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'employee.fxml'.";
        assert msg != null : "fx:id=\"msg\" was not injected: check your FXML file 'employee.fxml'.";
        assert msg2 != null : "fx:id=\"msg2\" was not injected: check your FXML file 'employee.fxml'.";
        assert msg3 != null : "fx:id=\"msg3\" was not injected: check your FXML file 'employee.fxml'.";
        assert oldempID != null : "fx:id=\"oldempID\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedAddress != null : "fx:id=\"updatedAddress\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedBirthDate != null : "fx:id=\"updatedBirthDate\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedGender != null : "fx:id=\"updatedGender\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedID != null : "fx:id=\"updatedID\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedName != null : "fx:id=\"updatedName\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedPassword != null : "fx:id=\"updatedPassword\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedPhone != null : "fx:id=\"updatedPhone\" was not injected: check your FXML file 'employee.fxml'.";
        assert updatedSalary != null : "fx:id=\"updatedSalary\" was not injected: check your FXML file 'employee.fxml'.";
        ////For tableData
        TableData.setEditable(true);
        TableEmpID.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        TableEmpName.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        TableEmpBirth.setCellValueFactory(new PropertyValueFactory<>("emp_birthdate"));
        TableEmpPhone.setCellValueFactory(new PropertyValueFactory<>("emp_phone"));
        TableEmpAddress.setCellValueFactory(new PropertyValueFactory<>("emp_address"));
        TableEmpGender.setCellValueFactory(new PropertyValueFactory<>("emp_gender"));
        TableEmpSalary.setCellValueFactory(new PropertyValueFactory<>("emp_salary"));
        TableEmpPass.setCellValueFactory(new PropertyValueFactory<>("emp_password"));
        ///To fill the data from the dataBase
        try {
            fillTableFromDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
