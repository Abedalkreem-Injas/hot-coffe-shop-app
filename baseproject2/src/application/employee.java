package application;

public class employee {
	private int emp_id;
	private String employee_name;
	private String emp_birthdate;
	private String emp_phone;
	private String emp_address;
	private String emp_gender;
	private double emp_salary;
	private String emp_password;

    static employee emd;

	public employee() {
		super();
	}
	
	public employee(int emp_id, String employee_name, String emp_birthdate, String emp_phone, String emp_address,
			String emp_gender, double emp_salary, String emp_password) {
		super();
		this.emp_id = emp_id;
		this.employee_name = employee_name;
		this.emp_birthdate = emp_birthdate;
		this.emp_phone = emp_phone;
		this.emp_address = emp_address;
		this.emp_gender = emp_gender;
		this.emp_salary = emp_salary;
		this.emp_password = emp_password;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmp_birthdate() {
		return emp_birthdate;
	}

	public void setEmp_birthdate(String emp_birthdate) {
		this.emp_birthdate = emp_birthdate;
	}

	public String getEmp_phone() {
		return emp_phone;
	}

	public void setEmp_phone(String emp_phone) {
		this.emp_phone = emp_phone;
	}

	public String getEmp_address() {
		return emp_address;
	}

	public void setEmp_address(String emp_address) {
		this.emp_address = emp_address;
	}

	public String getEmp_gender() {
		return emp_gender;
	}

	public void setEmp_gender(String emp_gender) {
		this.emp_gender = emp_gender;
	}

	public double getEmp_salary() {
		return emp_salary;
	}

	public void setEmp_salary(double emp_salary) {
		this.emp_salary = emp_salary;
	}

	public String getEmp_password() {
		return emp_password;
	}

	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
    
	
}