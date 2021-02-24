package com.epf.rentmanager.utils;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class FormatChecker {
	/**
	 * Vérifie le format d'un email en utilisant le paquet java email officiel
	 * https://stackoverflow.com/a/5931718
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}
}
