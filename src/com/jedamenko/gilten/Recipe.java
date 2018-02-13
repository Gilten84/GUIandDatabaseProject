package com.jedamenko.gilten;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Recipe implements DBCommonObject
{
	private String idRecipes;
	private String recipe_medicament;
	private String recipe_morning_dosage;
	private String recipe_day_dosage;
	private String recipe_evening_dosage;
	private String Patients_idPatient;
	private String Doctors_idDoctors;
	public Recipe(ResultSet rs) {
		
		super();
		try {
			this.idRecipes = rs.getString("idRecipes");
			this.recipe_medicament = rs.getString("recipe_medicament");
			this.recipe_morning_dosage = rs.getString("recipe_morning_dosage");
			this.recipe_day_dosage = rs.getString("recipe_day_dosage");
			this.recipe_evening_dosage = rs.getString("recipe_evening_dosage");
			this.Patients_idPatient = rs.getString("Patients_idPatient");
			this.Doctors_idDoctors = rs.getString("Doctors_idDoctors");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getIdRecipes() {
		return idRecipes;
	}
	public void setIdRecipes(String idRecipes) {
		this.idRecipes = idRecipes;
	}
	public String getRecipe_medicament() {
		return recipe_medicament;
	}
	public void setRecipe_medicament(String recipe_medicament) {
		this.recipe_medicament = recipe_medicament;
	}
	public String getRecipe_morning_dosage() {
		return recipe_morning_dosage;
	}
	public void setRecipe_morning_dosage(String recipe_morning_dosage) {
		this.recipe_morning_dosage = recipe_morning_dosage;
	}
	public String getRecipe_day_dosage() {
		return recipe_day_dosage;
	}
	public void setRecipe_day_dosage(String recipe_day_dosage) {
		this.recipe_day_dosage = recipe_day_dosage;
	}
	public String getRecipe_evening_dosage() {
		return recipe_evening_dosage;
	}
	public void setRecipe_evening_dosage(String recipe_evening_dosage) {
		this.recipe_evening_dosage = recipe_evening_dosage;
	}
	public String getPatients_idPatient() {
		return Patients_idPatient;
	}
	public void setPatients_idPatient(String patients_idPatient) {
		Patients_idPatient = patients_idPatient;
	}
	public String getDoctors_idDoctors() {
		return Doctors_idDoctors;
	}
	public void setDoctors_idDoctors(String doctors_idDoctors) {
		Doctors_idDoctors = doctors_idDoctors;
	}
	@Override
	public String toString() {
		return "Recipe [idRecipes=" + idRecipes + ", recipe_medicament=" + recipe_medicament
				+ ", recipe_morning_dosage=" + recipe_morning_dosage + ", recipe_day_dosage=" + recipe_day_dosage
				+ ", recipe_evening_dosage=" + recipe_evening_dosage + ", Patients_idPatient=" + Patients_idPatient
				+ ", Doctors_idDoctors=" + Doctors_idDoctors + "]";
	}
	
}
