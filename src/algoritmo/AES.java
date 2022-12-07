package algoritmo;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class AES {
	
	private String atributo1;
	
	private String getAtributo1() {
		return atributo1;
	}
	
	private void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}
	
	private SecretKeySpec crearClave(String clave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] claveEncriptacion = clave.getBytes("UTF-8");
        
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        
        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);
        
        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, "AES");

        return secretKey;
    }
	
	public String cifrado(String texto) {
		System.out.println("---Cifrando...");
		String aux = "";
		//Key aesKey = new SecretKeySpec(getAtributo1().getBytes(), "AES");
		Key aesKey = null;
		try {
			aesKey = this.crearClave(getAtributo1());
			System.out.println("-- Decifrando - Mensaje Cifrado "+texto);
			System.out.println("-- Cifrando - Llave Privada "+aesKey);
			System.out.println("-- Cifrando - Llave Pública "+getAtributo1());
			try {
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.ENCRYPT_MODE, aesKey);
				byte[] encriptado = cipher.doFinal(texto.getBytes()); 
				aux = java.util.Base64.getEncoder().encodeToString(encriptado);
			}catch (NoSuchAlgorithmException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (NoSuchPaddingException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (InvalidKeyException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (IllegalBlockSizeException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (BadPaddingException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("---Cifrado Completo...");
			return aux;
			
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}
	}
	
	public String decifrado(String textocifrado) {
		System.out.println("---Decifrando...");
		String aux = "";
		byte [] encriptedBytes = java.util.Base64.getDecoder().decode(textocifrado.replace("\n", ""));
		Key aesKey = null;
		try {
			aesKey = this.crearClave(getAtributo1());
			System.out.println("-- Decifrando - Mensaje Cifrado "+textocifrado);
			System.out.println("-- Decifrando - Llave Privada   "+aesKey);
			System.out.println("-- Decifrando - Llave Pública   "+getAtributo1());

			try {
				Cipher cipher = Cipher.getInstance("AES");
				cipher.init(Cipher.DECRYPT_MODE, aesKey);
				aux = new String(cipher.doFinal(encriptedBytes));
			}catch (NoSuchAlgorithmException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (NoSuchPaddingException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (InvalidKeyException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (IllegalBlockSizeException e) {
				// TODO: handle exception
				e.printStackTrace();
			}catch (BadPaddingException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println("-- Decifrado Completo...");
			return aux;
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "";
		}		
	}
	
	public void KEY(String k) {
		setAtributo1(k);
	}

}
