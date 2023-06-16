package test.haksa.model.dto;

import java.sql.Date;

public class SihumDTO {

	private int sihum_no;
	private String sihum_name;
	private Date sihum_date;
	private Date regi_date;
	
	public int getSihum_no() {
		return sihum_no;
	}
	public void setSihum_no(int sihum_no) {
		this.sihum_no = sihum_no;
	}
	public String getSihum_name() {
		return sihum_name;
	}
	public void setSihum_name(String sihum_name) {
		this.sihum_name = sihum_name;
	}
	public Date getSihum_date() {
		return sihum_date;
	}
	public void setSihum_date(Date sihum_date) {
		this.sihum_date = sihum_date;
	}
	public Date getRegi_date() {
		return regi_date;
	}
	public void setRegi_date(Date regi_date) {
		this.regi_date = regi_date;
	}
	
	
	
	
}
