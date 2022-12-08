package utilitarios;

import java.awt.Desktop;
import java.io.*;

public class FileUtilities {

	public String readFile(String FilePath) {
		try{
			//constructor of file class having file as argument  
			File file=new File(FilePath);
			FileInputStream fis = new FileInputStream(file);//opens a connection to an actual file  
			System.out.println("file content: ");
			int r=0;
			while((r=fis.read())!=-1){
				System.out.print((char)r);//prints the content of the file  
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	public static void muestraContenido(String archivo) throws FileNotFoundException, IOException { 
    	String cadena; 
        FileReader f = new FileReader(archivo); 
        BufferedReader b = new BufferedReader(f); 
        while((cadena = b.readLine())!=null) { 
        	System.out.println(cadena); 
        } 
        b.close(); 
	} 

	public String readFile2(String FilePath) {
		File file;
		try {
			file = new File(FilePath);
			if (!Desktop.isDesktopSupported()) {
				System.out.println("not supported");
				return "";
			}
			Desktop desktop = Desktop.getDesktop();
			if (file.exists())// checks file exists or not  
				desktop.open(file);// opens the specified file  
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String saveFile() {
		return "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ruta = "C:\\Users\\Kevin Palacios\\Documents\\contraseña.txt";
		FileUtilities fu = new FileUtilities();
		fu.readFile(ruta);
		try {
			fu.muestraContenido(ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
