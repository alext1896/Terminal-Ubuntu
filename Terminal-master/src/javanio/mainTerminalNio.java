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
			int tamaño = separacion.length;
		
			//Comando help
			if (separacion[0].equals("help")) {
				if (tamaño > 1) {
					System.out.println("Escriba bien el comando");
					System.out.println(comandos.rutaInterfaz());
				}else {
					try {
						comandos.help();
						System.out.println(comandos.rutaInterfaz());
					} catch (IOException e) {
						System.out.println("Escriba bien el comando.");
					}
				}
			}	
			
			//Comando cd
			try {
				if (separacion[0].equals("cd")) {
					
					if (tamaño > 1) {
						if (separacion [1].startsWith("/")){
							comandos.setRutaInicial(separacion[1]);;
							System.out.println(comandos.rutaInterfaz());
						}else if (separacion [1].startsWith("..")) {
							comandos.setRutaInicial(separacion[1]);
							System.out.println(comandos.rutaInterfaz());
							
						}else {
							if (comandos.crearPath(separacion[1]).toFile().isFile()) {
								System.out.println("cd: " +  separacion [1] + " No es un directorio");
								System.out.println(comandos.rutaInterfaz());

							}else {
								comandos.setRutaInicial(separacion[1]);
								System.out.println(comandos.rutaInterfaz());
							}
							
						}
					}else {
						System.out.println(comandos.rutaInterfaz());
					}
					
			}	
		}catch (ArrayIndexOutOfBoundsException e) {
			comandos.setRutaInicial(separacion[1]);
			System.out.println(comandos.rutaInterfaz());
			
		}

			//Comando dir
			if (separacion[0].equals("dir")) {
				
				if (tamaño < 3) {
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
				}else {
					System.out.println("El comando ha sido mal escrito.");
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
			if (separacion [0].equalsIgnoreCase("top")) {
				if (tamaño < 3) {
					System.out.println("El comando top debe tener 2 argumentos. Por ejemplo: top 5 hola. Siendo hola el nombre del fichero.");
					System.out.println(comandos.rutaInterfaz());
				}else {
					comandos.top(separacion [1], separacion [2]);
					System.out.println(comandos.rutaInterfaz());
					
				}
			}
			
			
			//Comando mkfile
			if (separacion [0].equals("mkfile")) {
				
				if (tamaño > 2) {
					comandos.mkfile(separacion[1], separacion);
					System.out.println(comandos.rutaInterfaz());
				}else {
					try {
						comandos.mkfile(separacion[1]);
						System.out.println(comandos.rutaInterfaz());
					} catch (IOException e) {
						e.printStackTrace();
					}
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
				
				if (comandos.existe(separacion[1]) == false) {
					System.out.println("El archivo que quiere copiar no existe");
					System.out.println(comandos.rutaInterfaz());
				}else if (comandos.existe(separacion [2])) {
						System.out.println("El archivo que quiere copiar ya existe, desea sobreescribirlo? \n"
								+ "Escriba Si o no.");
						operacion = sc.nextLine();
						
						while (operacion.equalsIgnoreCase("si") == false && operacion.equalsIgnoreCase("no") == false)  {
							System.out.println("Escriba Si o No.");
							operacion = sc.nextLine();
						}
							
						if (operacion.equalsIgnoreCase("si")) {
							comandos.delete(separacion [2]);
							comandos.cp(separacion [1], separacion [2]);
							System.out.println(comandos.rutaInterfaz());
						}else {
							System.out.println("Operacion Cancelada.");
						}
				}else {
					comandos.cp(separacion [1], separacion [2]);
					System.out.println(comandos.rutaInterfaz());
				}
			}
			
			if (separacion [0].equals("help") == false || separacion [0].equals("cd") == false || separacion [0].equals("dir") == false || 
				separacion [0].equals("mkdir") == false || separacion [0].equals("info") == false || separacion [0].equals("cat") == false ||
				separacion [0].equals("cat") == false || separacion [0].equals("top") == false || separacion [0].equals("mkfile") == false ||
				separacion [0].equals("write") == false || separacion [0].equals("delete") == false || separacion [0].equals("cp") == false){
				
				if (separacion [0].equals("help") || separacion [0].equals("cd") || separacion [0].equals("dir") || 
					separacion [0].equals("mkdir") || separacion [0].equals("info") || separacion [0].equals("cat") ||
					separacion [0].equals("cat") || separacion [0].equals("top") || separacion [0].equals("mkfile") ||
					separacion [0].equals("write") || separacion [0].equals("delete") || separacion [0].equals("cp")) {
					
				}else {
					System.out.println("Comando no encontrado.");
					System.out.println(comandos.rutaInterfaz());
				}
				
				
			}
			operacion = sc.nextLine();
		}while (operacion.equalsIgnoreCase("exit") == false);
		
		
		sc.close();
	}
}