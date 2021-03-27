package com.epf.rentmanager.ui.cli;

import java.time.LocalDate;
import java.util.ListIterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.epf.rentmanager.configuration.AppConfiguration;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.utils.IOUtils;

public class CLI {
	private VehicleService vehicleService;
	private ClientService clientService;
	private ReservationService reservationService;
	
	private CLI() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		this.clientService = context.getBean(ClientService.class);
	}
	
	/**
	 * Prompt de création d'un client
	 */
	public void createClient() {
		IOUtils.print(" 🛠 Creation d'un nouveau client...\nVeuillez remplir les champs ci-dessous\n");
		String nom = IOUtils.readString("Nom:", true);
		String prenom = IOUtils.readString("Prenom:", true);
		String email = IOUtils.readString("Email:", true);
		LocalDate naissance = IOUtils.readDate("Date de naissance:", true);
		try {
			clientService.create(new Client(nom, prenom, email, naissance));
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt d'affichage de tous les clients
	 */
	public void getClients() {
		IOUtils.print(" 🛠 Récupération de la liste des clients...");
		try {
			ListIterator<Client> clients = clientService.findAll().listIterator();
			while(clients.hasNext())
				IOUtils.print(clients.next().toString());
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt de création d'un véhicule
	 */
	public void createVehicle() {
		IOUtils.print(" 🛠 Creation d'un nouveau véhicule...\nVeuillez remplir les champs ci-dessous\n");
		String constructeur = IOUtils.readString("Constructeur:", true);
		String modele = IOUtils.readString("Modèle de véhicule:", true);
		int nb_places = IOUtils.readInt("Nombre de places:");
		try {
			vehicleService.create(new Vehicle(constructeur, modele, nb_places));
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt d'affichage de tous les véhicules
	 */
	public void getVehicles() {
		IOUtils.print(" 🛠 Récupération de la liste des véhicules...");
		try {
			ListIterator<Vehicle> vehicles = vehicleService.findAll().listIterator();
			while(vehicles.hasNext())
				IOUtils.print(vehicles.next().toString());
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt de création d'une réservation
	 */
	public void createReservation() {
		IOUtils.print(" 🛠 Creation d'une nouvelle réservation...\nVeuillez remplir les champs ci-dessous\n");
		int clientId = IOUtils.readInt("Identifiant du client:");
		int vehicleId = IOUtils.readInt("Identifiant du véhicule:");
		LocalDate debut = IOUtils.readDate("Date de début:", true);
		LocalDate fin = IOUtils.readDate("Date de fin:", true);
		try {
			reservationService.create(new Reservation(clientId, vehicleId, debut, fin));
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt d'affichage de toutes les réservations
	 */
	public void getReservations() {
		IOUtils.print(" 🛠 Récupération de la liste des réservations...");
		try {
			ListIterator<Reservation> reservations = reservationService.findAll().listIterator();
			while(reservations.hasNext())
				IOUtils.print(reservations.next().toString());
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt d'affichage de toutes les réservations liées à un client
	 */
	public void getReservationsByClient() {
		IOUtils.print(" 🛠 Récupération de la liste des réservations associées à un client...\nVeuillez remplir les champs ci-dessous\n");
		long clientId = IOUtils.readInt("Identifiant du client:");
		try {
			ListIterator<Reservation> reservations = reservationService.findByClient(clientId).listIterator();
			while(reservations.hasNext())
				IOUtils.print(reservations.next().toString());
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt d'affichage de toutes les réservations liées à un véhicule
	 */
	public void getReservationsByVehicle() {
		IOUtils.print(" 🛠 Récupération de la liste des réservations associées à un véhicule...\nVeuillez remplir les champs ci-dessous\n");
		long vehicleId = IOUtils.readInt("Identifiant du véhicule:");
		try {
			ListIterator<Reservation> reservations = reservationService.findByVehicle(vehicleId).listIterator();
			while(reservations.hasNext())
				IOUtils.print(reservations.next().toString());
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt de suppression d'un client
	 */
	public void supprClient() {
		IOUtils.print(" 🛠 Suppression d'un client...\nVeuillez remplir les champs ci-dessous\n");
		int id = IOUtils.readInt("Identifiant:");
		try {
			clientService.delete(id);
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt de suppression d'un véhicule
	 */
	public void supprVehicle() {
		IOUtils.print(" 🛠 Suppression d'un véhicule...\nVeuillez remplir les champs ci-dessous\n");
		int id = IOUtils.readInt("Identifiant:");
		try {
			vehicleService.delete(id);
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Prompt de suppression d'une réservation
	 */
	public void supprReservation() {
		IOUtils.print(" 🛠 Suppression d'une réservation...\nVeuillez remplir les champs ci-dessous\n");
		int id = IOUtils.readInt("Identifiant:");
		try {
			reservationService.delete(id);
			IOUtils.print(" ✔️ Opération réussie");
		} catch (ServiceException e) {
			IOUtils.print(" 😔 Une erreur est survenue\n" + e.getMessage());
			e.printStackTrace();
		}
	}

}
