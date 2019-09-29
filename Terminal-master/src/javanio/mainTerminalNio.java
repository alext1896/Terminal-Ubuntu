package javanio;

import java.io.*;
import java.util.Scanner;

public class mainTerminalNio {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String operacion = "";
		Commands comandos = new Commands ();
				
		System.out.println(comandos.rutaHome());
		operacion = sc.nextLine();

		do {
			
			String [] separacion = operacion.split(" ");
			
			if (separacion[0].equals("help")) {
				try {
					comandos.help();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (separacion[0].equals("cd")) {
				if (separacion [1].startsWith("/")){
					System.out.println(comandos.cdAbsouluta(separacion [1].toString()));
					comandos.setRutaInicial(separacion[1]);;
					
				}else if (separacion [1].startsWith("..")) {
					System.out.println(comandos.cdPadre().toString());
					comandos.setRutaInicial(separacion[1]);
				}
				
				
				else {
					System.out.println(comandos.cd(separacion[1].toString()));
					comandos.setRutaInicial(separacion[1]);
				}
				
			}
		
			if (separacion[0].equals("dir")) {
				comandos.dir();
				comandos.rutaHome();
			}
			operacion = sc.nextLine();
			
		}while (operacion.equalsIgnoreCase("exit") == false);
		
		sc.close();
	}
}
