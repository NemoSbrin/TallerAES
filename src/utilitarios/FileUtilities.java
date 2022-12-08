package utilitarios;

import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;

public class FileUtilities {

	public String muestraContenido(String archivo) {
		String cadena = "";
		String aux = "";
		FileReader f;
		BufferedReader b;
		try {
			f = new FileReader(archivo);
			b = new BufferedReader(f);
			while ((cadena = b.readLine()) != null) {
				aux += cadena;
			}
			b.close();
			return aux;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	public boolean crearArchivo(String nuevoArchivo, ArrayList<String> contenido) {
		FileWriter flwriter = null;
		try {
			//crea el flujo para escribir en el archivo
			flwriter = new FileWriter(nuevoArchivo);
			//crea un buffer o flujo intermedio antes de escribir directamente en el archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			for (String linea : contenido) {
				//escribe los datos en el archivo
				bfwriter.write(linea+"\n");
				////System.out.println("linea: "+linea);
			}
			//cierra el buffer intermedio
			bfwriter.close();
			//System.out.println("Archivo creado satisfactoriamente..");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flwriter != null) {
				try {//cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return false;
			}
		}
	}
/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ruta = "C:\\Users\\Kevin Palacios\\Documents\\contraseña.txt";
		String ruta2 = "C:\\Users\\Kevin Palacios\\Documents\\mensaje.txt";
		FileUtilities fu = new FileUtilities();
		String a = fu.muestraContenido(ruta);
		//System.out.println(a);
		
		ArrayList<String> contenido = new ArrayList<String>();
		contenido.add("la vida es hermosa");
		fu.crearArchivo(ruta2, contenido);
	}
*/
}
