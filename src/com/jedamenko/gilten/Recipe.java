package com.jedamenko.gilten;

public class Recipe implements DBCommonObject
{
	private int id;
	private String drug;
	private String morning_dosage;
	private String day_dosage;
	private String evening_dosage;
	private Doctor doctor;
	private Patient patient;
	public Recipe(int id, String drug, String morning_dosage, String day_dosage, String evening_dosage, Doctor doctor,
			Patient patient) {
		super();
		this.id = id;
		this.drug = drug;
		this.morning_dosage = morning_dosage;
		this.day_dosage = day_dosage;
		this.evening_dosage = evening_dosage;
		this.doctor = doctor;
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDrug() {
		return drug;
	}
	public void setDrug(String drug) {
		this.drug = drug;
	}
	public String getMorning_dosage() {
		return morning_dosage;
	}
	public void setMorning_dosage(String morning_dosage) {
		this.morning_dosage = morning_dosage;
	}
	public String getDay_dosage() {
		return day_dosage;
	}
	public void setDay_dosage(String day_dosage) {
		this.day_dosage = day_dosage;
	}
	public String getEvening_dosage() {
		return evening_dosage;
	}
	public void setEvening_dosage(String evening_dosage) {
		this.evening_dosage = evening_dosage;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
