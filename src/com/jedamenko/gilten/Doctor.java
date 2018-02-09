package com.jedamenko.gilten;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor implements DBCommonObject
{
	private int idDoctors;
	private String doctor_last_name;
	private String doctor_first_name;
	private String doctor_id_code;
	public Doctor(ResultSet rs) {
		
		super();
		try {
			this.idDoctors = rs.getInt("idDoctors");
			this.doctor_last_name = rs.getString("doctor_last_name");
			this.doctor_first_name = rs.getString("doctor_first_name");
			this.doctor_id_code =rs.getString("doctor_id_code");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getIdDoctors() {
		return idDoctors;
	}
	public void setIdDoctors(int idDoctors) {
		this.idDoctors = idDoctors;
	}
	public String getDoctor_last_name() {
		return doctor_last_name;
	}
	public void setDoctor_last_name(String doctor_last_name) {
		this.doctor_last_name = doctor_last_name;
	}
	public String getDoctor_first_name() {
		return doctor_first_name;
	}
	public void setDoctor_first_name(String doctor_first_name) {
		this.doctor_first_name = doctor_first_name;
	}
	public String getDoctor_id_code() {
		return doctor_id_code;
	}
	public void setDoctor_id_code(String doctor_id_code) {
		this.doctor_id_code = doctor_id_code;
	}
	

}
