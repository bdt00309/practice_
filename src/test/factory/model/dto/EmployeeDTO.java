package test.factory.model.dto;

import java.sql.Date;

public class EmployeeDTO {

	private int sabun; 
	private String name; 
	private String phone; 
	private String email; 
	private Date hireDate; 
	private int salary; 
	private int buseoNo; 
	private int positionNo; 
	private Date regiDate;
	
	private String positionName; 
	private String buseoName;
	
	public int getSabun() {
		return sabun;
	}
	public void setSabun(int sabun) {
		this.sabun = sabun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getBuseoNo() {
		return buseoNo;
	}
	public void setBuseoNo(int buseoNo) {
		this.buseoNo = buseoNo;
	}
	public int getPositionNo() {
		return positionNo;
	}
	public void setPositionNo(int positionNo) {
		this.positionNo = positionNo;
	}
	public Date getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}

	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getBuseoName() {
		return buseoName;
	}
	public void setBuseoName(String buseoName) {
		this.buseoName = buseoName;
	} 
	
	
	
}
