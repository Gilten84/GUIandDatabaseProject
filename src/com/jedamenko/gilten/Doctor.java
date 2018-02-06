package com.jedamenko.gilten;

public class Doctor 
{
	private int id;
	private String last_name;
	private String first_name;
	private String id_code;
	public Doctor(int id, String last_name, String first_name, String id_code) {
		super();
		this.id = id;
		this.last_name = last_name;
		this.first_name = first_name;
		this.id_code = id_code;
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
