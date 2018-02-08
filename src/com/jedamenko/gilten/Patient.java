package com.jedamenko.gilten;

public class Patient implements DBCommonObject
{
	private int id;
	private String last_name;
	private String first_name;
	private String id_code;
	private String floor;
	private String room;
	public Patient(int id, String last_name, String first_name, String id_code, String floor, String room) {
		super();
		this.id = id;
		this.last_name = last_name;
		this.first_name = first_name;
		this.id_code = id_code;
		this.floor = floor;
		this.room = room;
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
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}

}
