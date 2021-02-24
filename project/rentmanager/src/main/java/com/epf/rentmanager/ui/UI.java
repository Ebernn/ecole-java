package com.epf.rentmanager.ui;

import java.util.List;

import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.service.ClientService;
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
		CLI.createVehicle();
    }
}
