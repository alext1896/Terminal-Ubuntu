package javanio;

import java.io.*;
import java.util.Scanner;

public class mainTerminalNio {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String operacion = "";
		Commands comandos = new Commands ();
				
		System.out.println(comandos.rutaInterfaz());
		operacion = sc.nextLine();

		do {
			
			String [] separacion = operacion.split(" ");
			
			//Comando help
			if (separacion[0].equals("help")) {
				try {
					comandos.help();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//Comando cd
			try {
					if (separacion[0].equals("cd")) {
						
	
					if (separacion [1].startsWith("/")){
						System.out.println(comandos.cdAbsouluta(separacion [1].toString()));
						comandos.setRutaInicial(separacion[1]);;
						
					}else if (separacion [1].startsWith("..")) {
						System.out.println(comandos.cdPadre().toString());
						comandos.setRutaInicial(separacion[1]);
						
					}else {
						System.out.println(comandos.cd(separacion[1].toString()));
						comandos.setRutaInicial(separacion[1]);
					}
				}	
			}catch (ArrayIndexOutOfBoundsException e) {
				comandos.setRutaInicial(separacion[1]);
				System.out.println(comandos.cdPadre().toString());
				
			}
			
			
			//Comando dir
			if (separacion[0].equals("dir")) {
				comandos.dir();
				System.out.println(comandos.rutaInterfaz());
			}
			
			//Comando mkdir
			if (separacion [0].equals("mkdir")) {
				comandos.mkdir(separacion[1].toString());
				System.out.println(comandos.rutaInterfaz());
			}
			
			//Comando info
			if (separacion [0].equals("info")) {
				comandos.info(separacion[1]);
				System.out.println(comandos.rutaInterfaz());
			}
			
			//Comando cat
			if (separacion [0].equals("cat")) {
				comandos.cat(separacion [1]);
				System.out.println(comandos.rutaInterfaz());
			}
			
			//Comando top
			if (separacion [0].equals("top")) {
				comandos.top(separacion [1], separacion [2]);
				System.out.println(comandos.rutaInterfaz());
			}
			
			//Comando write
			if (separacion [0].equals("write")){
				comandos.write(separacion [1], separacion);
				System.out.println(comandos.rutaInterfaz());
				
			}
			
			
			operacion = sc.nextLine();
		}while (operacion.equalsIgnoreCase("exit") == false);
		
		sc.close();
	}
}
