package mybean.impl;

public class Employee {

	public String employeeName;
	
	public String employeeId;
	
	public String age;
	
	
	public String company;


	@Override
	public String toString() {
		return "Employee [employeeName=" + employeeName + ", employeeId=" + employeeId + ", age=" + age + ", company="
				+ company + "]";
	}


	public Employee(String employeename, String employeeId, String age, String company) {
		super();
		this.employeeName = employeename;
		this.employeeId = employeeId;
		this.age = age;
		this.company = company;
	}


	
	
	
}
