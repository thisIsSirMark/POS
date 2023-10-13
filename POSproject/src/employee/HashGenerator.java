package employee;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashGenerator {

	private String inputPassword;
	private String sqlPassword;
	private byte[] salt;
	private int iteration = 1000;

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public void setSQLPassword(String sqlPassword) {
		this.sqlPassword = sqlPassword;
	}

	public void setSalt(String salt) {
		this.salt = toByte(salt);
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public String generatePassword() throws NoSuchAlgorithmException, InvalidKeySpecException {

		char[] pass = inputPassword.toCharArray();

		if (salt == null)
			salt = generateSalt();

		PBEKeySpec spec = new PBEKeySpec(pass, salt, iteration, 64 * 8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

		byte[] bytePass = skf.generateSecret(spec).getEncoded();

		return iteration + ":" + toHexString(salt) + ":" + toHexString(bytePass);
	}

	public boolean compareSQLPassword(String pass) {
		if (pass.equals(sqlPassword))
			return true;

		return false;
	}

	public byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

		byte[] b = new byte[16];

		sr.nextBytes(b);

		return b;
	}

	public String toHexString(byte[] b) {
		BigInteger bi = new BigInteger(1, b);
		String hex = bi.toString(16);

		int paddingLength = (b.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	private byte[] toByte(String hex) {
		byte[] bytes = new byte[hex.length() / 2];

		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}

		return bytes;
	}

}
