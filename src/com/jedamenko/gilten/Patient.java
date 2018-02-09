package com.jedamenko.gilten;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient implements DBCommonObject
{
	private int idPatient;
	private String patient_last_name;
	private String patient_first_name;
	private String patient_id_code;
	private String patient_floor;
	private String patient_room;
	private String patient_diagnosis;
	public Patient(ResultSet rs) {
		
		super();
		try {
			this.idPatient = rs.getInt("idPatient");
			this.patient_last_name = rs.getString("patient_last_name");
			this.patient_first_name = rs.getString("patient_first_name");
			this.patient_id_code = rs.getString("patient_first_name");
			this.patient_floor = rs.getString("patient_floor");
			this.patient_room = rs.getString("patient_room");
			this.patient_diagnosis = rs.getString("patient_diagnosis");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getIdPatient() {
		return idPatient;
	}
	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}
	public String getPatient_last_name() {
		return patient_last_name;
	}
	public void setPatient_last_name(String patient_last_name) {
		this.patient_last_name = patient_last_name;
	}
	public String getPatient_first_name() {
		return patient_first_name;
	}
	public void setPatient_first_name(String patient_first_name) {
		this.patient_first_name = patient_first_name;
	}
	public String getPatient_id_code() {
		return patient_id_code;
	}
	public void setPatient_id_code(String patient_id_code) {
		this.patient_id_code = patient_id_code;
	}
	public String getPatient_floor() {
		return patient_floor;
	}
	public void setPatient_floor(String patient_floor) {
		this.patient_floor = patient_floor;
	}
	public String getPatient_room() {
		return patient_room;
	}
	public void setPatient_room(String patient_room) {
		this.patient_room = patient_room;
	}
	public String getPatient_diagnosis() {
		return patient_diagnosis;
	}
	public void setPatient_diagnosis(String patient_diagnosis) {
		this.patient_diagnosis = patient_diagnosis;
	}

}
