package com.epf.rentmanager.ui;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.ui.cli.CLI;

public class UI {
	public static void main(String[] args) throws ServiceException {
        /*Client test = new Client();
        test.setId(1);
        test.setNom("nom");
        test.setPrenom("prenom");
        System.out.println(test);
        
        List<Client> clients = ClientService.getInstance().findAll();
        for (Client client : clients){
             System.out.println(client);
        }*/
		
		// CLI.createVehicle();
		// CLI.getVehicles();
		
		// CLI.supprVehicle();
		// CLI.getVehicles();
		
		// CLI.createClient();
		// CLI.getClients();
		
		// CLI.supprClient();
		// CLI.getClients();
		
		CLI.getClients();
		CLI.getVehicles();
		
		CLI.createReservation();
		CLI.getReservations();
		
		CLI.createReservation();
		CLI.getReservations();
		
		CLI.getReservationsByClient();
		CLI.getReservationsByVehicle();
    }
}
