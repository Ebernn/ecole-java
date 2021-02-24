package com.epf.rentmanager.ui.cli;

import java.time.LocalDate;
import java.util.ListIterator;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

public class CLI {
	public static void createClient() {
		IOUtils.print(" 🛠 Creation d'un nouveau client...\nVeuillez remplir les champs ci-dessous\n");
		String nom = IOUtils.readString("Nom:", true);
		String prenom = IOUtils.readString("Prenom:", true);
		String email = IOUtils.readString("Email:", true);
		LocalDate naissance = IOUtils.readDate("Date de naissance:", true);
		try {
			ClientService.getInstance().create(new Client(nom, prenom, email, naissance));
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void getClients() {
		IOUtils.print(" 🛠 Récupération de la liste des clients...");
		try {
			ListIterator<Client> clients = ClientService.getInstance().findAll().listIterator();
			while(clients.hasNext())
				IOUtils.print(clients.next().toString());
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void createVehicle() {
		IOUtils.print(" 🛠 Creation d'un nouveau véhicule...\nVeuillez remplir les champs ci-dessous\n");
		String constructeur = IOUtils.readString("Constructeur:", true);
		String modele = IOUtils.readString("Modèle de véhicule:", true);
		int nb_places = IOUtils.readInt("Nombre de places:");
		try {
			VehicleService.getInstance().create(new Vehicle(constructeur, modele, nb_places));
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void getVehicles() {
		IOUtils.print(" 🛠 Récupération de la liste des véhicules...");
		try {
			ListIterator<Vehicle> vehicles = VehicleService.getInstance().findAll().listIterator();
			while(vehicles.hasNext())
				IOUtils.print(vehicles.next().toString());
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void supprClient() {
		IOUtils.print(" 🛠 Suppression d'un client...\nVeuillez remplir les champs ci-dessous\n");
		int id = IOUtils.readInt("Identifiant:");
		try {
			ClientService.getInstance().delete(id);
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void supprVehicle() {
		IOUtils.print(" 🛠 Suppression d'un véhicule...\nVeuillez remplir les champs ci-dessous\n");
		int id = IOUtils.readInt("Identifiant:");
		try {
			VehicleService.getInstance().delete(id);
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}

}
