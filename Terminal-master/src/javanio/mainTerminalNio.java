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
					System.out.println("Escriba bien el comando.");
					
				}
			}			
			//Comando cd
			try {
					if (separacion[0].equals("cd")) {
						if (separacion [1].startsWith("/")){
							//System.out.println(comandos.cdAbsouluta(separacion [1].toString()));
							comandos.setRutaInicial(separacion[1]);;
							System.out.println(comandos.rutaInterfaz());
						}else if (separacion [1].startsWith("..")) {
							//System.out.println(comandos.cdPadre().toString());
							comandos.setRutaInicial(separacion[1]);
							System.out.println(comandos.rutaInterfaz());
							
						}else {
							//System.out.println(comandos.cd(separacion[1].toString()));
							comandos.setRutaInicial(separacion[1]);
							System.out.println(comandos.rutaInterfaz());
						}
				}	
			}catch (ArrayIndexOutOfBoundsException e) {
				comandos.setRutaInicial(separacion[1]);
				System.out.println(comandos.rutaInterfaz());
				
			}
			
			
			//Comando dir
			if (separacion[0].equals("dir")) {
				int tamaño = separacion.length;
				
				if (tamaño > 1) {
					if (separacion [1].startsWith("/")) {
						comandos.dirAbsolute(separacion [1]);
						System.out.println(comandos.rutaInterfaz());
					}else {
						comandos.dirArchivo(separacion [1]);
						System.out.println(comandos.rutaInterfaz());
					}
				}else {
					comandos.dir();
					System.out.println(comandos.rutaInterfaz());
				}
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
			
			//Comando mkfile
			if (separacion [0].equals("mkfile")) {
				try {
					comandos.mkfile(separacion[1]);
					System.out.println(comandos.rutaInterfaz());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
			//Comando write
			if (separacion [0].equals("write")){
				comandos.write(separacion [1], separacion);
				System.out.println(comandos.rutaInterfaz());
				
			}
			
			//Comando delete
			if (separacion[0].equals("delete")) {
				comandos.delete(separacion [1]);
				System.out.println(comandos.rutaInterfaz());
			}
			
			//Comando cp
			if (separacion [0].equals("cp")) {
				int tam = separacion.length;
				if (tam > 2) {
					if (comandos.existe(separacion [2])) {
						System.out.println("El archivo o fichero que quiere copiar ya existe, ¿Desea sobreescribirlo?");
						System.out.println("Introduzca Si o No");
						String confirmacion = sc.nextLine();
						
						while (confirmacion.equalsIgnoreCase("si") || confirmacion.equalsIgnoreCase("no")) {
							if (confirmacion.equalsIgnoreCase("si")) {
								comandos.cpLocal(separacion [1], separacion [2]);
							}else {
								System.out.println("Operacion Cancelada");
							}
						}
					}else {
						System.out.println("El fichero no existe");
						System.out.println(comandos.rutaInterfaz());
					}
				}
			}else {
				System.out.println("Comando no encontrado.");
				System.out.println(comandos.rutaInterfaz());
			}
			operacion = sc.nextLine();
		}while (operacion.equalsIgnoreCase("exit") == false);
		
		sc.close();
	}
}
