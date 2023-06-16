package test.factory.model.dto;

import java.sql.Date;
import java.util.List;

public class BuseoDTO {
	
	private int buseoNo; 
	private String buseoName; 
	private Date regiDate;
	
	public int getBuseoNo() {
		return buseoNo;
	}
	public void setBuseoNo(int buseoNo) {
		this.buseoNo = buseoNo;
	}
	public String getBuseoName() {
		return buseoName;
	}
	public void setBuseoName(String buseoName) {
		this.buseoName = buseoName;
	}
	public Date getRegiDate() {
		return regiDate;
	}
	public void setRegiDate(Date regiDate) {
		this.regiDate = regiDate;
	}
	
}
