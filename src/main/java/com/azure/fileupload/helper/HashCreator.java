package com.azure.fileupload.helper;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

public class HashCreator {

	public static String getFileChecksum(MessageDigest digest, String input) throws IOException {
		// digest() method is called to calculate message digest
		// of an input digest() return array of byte
		byte[] messageDigest = digest.digest(input.getBytes());

		// Convert byte array into signum representation
		BigInteger no = new BigInteger(1, messageDigest);

		// Convert message digest into hex value
		String hashtext = no.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;

	}
}
