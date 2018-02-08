package com.jedamenko.gilten;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor implements DBCommonObject
{
	private int id;
	private String last_name;
	private String first_name;
	private String id_code;
	public Doctor(ResultSet rs) {
		
		super();
		try {
			this.id = rs.getInt("idDoctors");
			this.last_name = rs.getString("doctor_last_name");
			this.first_name = rs.getString("doctor_first_name");
			this.id_code =rs.getString("doctor_id_code");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getId_code() {
		return id_code;
	}
	public void setId_code(String id_code) {
		this.id_code = id_code;
	}

}
