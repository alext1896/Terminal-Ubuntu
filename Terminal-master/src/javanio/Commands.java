package javanio;


import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;

public class Commands {
	
	private Path rutaInicial = null;
	
	public Commands () {
		rutaInicial = Paths.get("/home");
		
	}
	
	public String rutaInterfaz () {
		String rutaHome = "jose.guapache@Linux:" + rutaInicial.getName(rutaInicial.getNameCount()-1) + "~$ ";
		return rutaHome;
		
	}
	
	public void setRutaInicial (String ruta) {
		if (ruta.startsWith("/")) {
		Path nuevaPath = Paths.get(ruta);
		this.rutaInicial = nuevaPath;
		
		}else {
			String nueva = rutaInicial.normalize() + "/"+ ruta;
			Path nuevaPath = Paths.get(nueva);
			this.rutaInicial = nuevaPath; 
		}

	}
	
	public void help () throws IOException {
		Path ruta = Paths.get("/home/alejandro/eclipse-workspace/Terminal-master/src/javanio/help");
		Charset charset = Charset.forName("ISO-8859-1");
		BufferedReader br = null;
		try {
			br = Files.newBufferedReader(ruta, charset);
			String linea = null;
			
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
				}
			
			br.close();
			} catch (IOException x) {
				System.err.format("IOException: %s%n", x);
		}
	}
	
	/*
	 * @param nombreCarpeta
	 * 
	 * Este método recibe un String con el nombre de la carpeta que se quiere acceder.
	 * A continuación crea una ruta a partir de la ruta home más el nombre de la carpeta, creando la Path de la
	 * carpeta a la que se quiere acceder.
	 * 
	 * @return Devuelve la ruta de tipo Path de la carpeta que se quiere acceder. 
	 */
	public Path cd (String nombreCarpeta) {
		Commands ruta = new Commands ();
		String [] separacion = ruta.rutaInterfaz().split(":");
		String newPath = separacion [0] + "~/" + nombreCarpeta + "$";
		Path nuevaRuta = Paths.get(newPath);
		
		return nuevaRuta;
	}
	
	public Path cdAbsouluta (String rutaAbsoluta) {
		Commands ruta = new Commands ();
		String [] separacion = ruta.rutaInterfaz().split(":");
		String [] separacionRuta = rutaAbsoluta.split("alejandro");
		
		String newPath = separacion [0] + ":alejandro" + separacionRuta [1]+ "~$";
		Path nuevaRuta = Paths.get(newPath);
		return nuevaRuta;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Path cdPadre () {
		Commands ruta = new Commands ();
		
		String [] pantalla = ruta.rutaInterfaz().split(":");
		String newPath = pantalla [0] + "~/"  + "$";
		Path nuevaRuta = Paths.get(newPath);
		return nuevaRuta;
	}
	
	/**
	 * @param ruta
	 * Recibe un parámetro de tipo Path el cual lista los directorios y ficheros de la ruta en un array 
	 * con el metodo .list().
	 * 
	 * por último, se imprime en pantalla el array.
	 */
	public void dir () {
		String [] listar = rutaInicial.toFile().list();
		
		for (int i = 0; i < listar.length; i++) {
			System.out.println(listar[i]);
		}
	}
	
	public void mkdir (String nombreArchivo) {
		Path rutaArchivo = Paths.get(rutaInicial.toAbsolutePath() + "/"+nombreArchivo);

		if (rutaArchivo.toFile().isDirectory() == true) {
			System.out.println("mkdir: no se puede crear el directorio " + nombreArchivo +" El archivo ya existe");
			
		}else {
			rutaArchivo.toFile().mkdir();
			
		}
	}
	
	public void info (String nombreArchivo) throws IOException {
		Path rutaArchivo = Paths.get(rutaInicial.toAbsolutePath() + "/" + nombreArchivo);
		FileChannel canal = FileChannel.open(rutaArchivo);
		long tamañoArchivo = canal.size();
		
		if (rutaArchivo.toFile().isDirectory() || rutaArchivo.toFile().isFile()) {
			System.out.println("Nombre del archivo: " + nombreArchivo);
			System.out.println("Nombre del archivo padre: " + rutaInicial.toAbsolutePath().getName(rutaInicial.getNameCount()-1));
			System.out.println("Tamaño del archivo: " + tamañoArchivo + "Bytes");
		}else {
			System.out.println("El archivo no existe");
		}
	}
	
	public void cat (String nombreFichero) {
		Path rutaFichero = Paths.get(rutaInicial.toAbsolutePath()+ "/" + nombreFichero);
		Charset charset = Charset.forName("ISO-8859-1");
		BufferedReader br = null;
		
		if (rutaFichero.toFile().isFile()) {
			try {
				br = Files.newBufferedReader(rutaFichero, charset);
				String linea = null;
				
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
					}
				
				br.close();
				} catch (IOException x) {
					System.err.format("IOException: %s%n", x);
			}
		}else {
			System.out.println(nombreFichero + ": Es un directorio");
		}
	}

	/**
	 * Muestras las primeras <numlineas> de un fichero.	De manera que top 20 hola.txt muestra las primeras 20 líneas del fichero hola.txt
	 * @param numLineas
	 * @param nombreFichero
	 */
	public void top (String numLineas, String nombreFichero) {
		try {
			Integer numeroLineas = Integer.parseInt(numLineas);
			int contador = 0;
			
			Path rutaFichero = Paths.get(rutaInicial.toAbsolutePath()+ "/" + nombreFichero);
			Charset charset = Charset.forName("ISO-8859-1");
			BufferedReader br = null;
			
			 if (rutaFichero.toFile().exists()) {
				 if (rutaFichero.toFile().isFile()) {
						try {
							br = Files.newBufferedReader(rutaFichero, charset);
							String linea = null;
							
							while ((linea = br.readLine()) != null && contador < numeroLineas) {
								System.out.println(linea);
								contador++;
								}
							
							br.close();
							} catch (IOException x) {
								System.err.format("IOException: %s%n", x);
						}
					}else {
						System.out.println(nombreFichero + ": Es un directorio");
					}
				}else {
					System.out.println("El archivo al que quieres acceder no existe.");
			}
		}catch (NumberFormatException e) {
			System.out.println("El segundo valor debe ser un número");
			System.out.println("Por ejemplo: top 5 hola. Siendo hola el nombre del archivo .txt");
		}
	}
	
	public void write (String nombreFichero, String [] texto) {
		Path rutaFichero = Paths.get(rutaInicial.normalize() + "/" + nombreFichero);
		Charset charset = Charset.forName("ISO-8859-1");
		try (BufferedWriter wr = Files.newBufferedWriter(rutaFichero, charset)) {
			
			for (int i = 2; i < texto.length; i++) {
				wr.write(texto[i] + " ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("El archivo no exite");
		}
	}
}































