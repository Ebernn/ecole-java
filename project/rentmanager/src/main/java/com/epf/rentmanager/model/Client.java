package com.epf.rentmanager.model;

import java.sql.Date;

public class Client {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private Date naissance;
	
	public Client() {

	}
	
	public Client(String nom, String prenom, String email, Date naissance) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.naissance = naissance;
	}
	
	public Client(int id, String nom, String prenom, String email, Date naissance) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.naissance = naissance;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", naissance=" + naissance + "]";
	}
	
	public int getId() {
		return id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Date getNaissance() {
		return naissance;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}
}
