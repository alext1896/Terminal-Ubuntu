package javanio;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
/**
 * @author jose.guapache
 *Esta clase tiene algunos de las funciones que tiene la terminal. Funciones como help, dir, info, cat, etc...
 */
public class Commands {
	private Path rutaInicial = null;
	
	/**
	 * El único atributo que tiene el constructor es la ruta inicial, la cual se inicializa con la ruta Home 
	 */
	public Commands () {
		rutaInicial = Paths.get("/home");
	}
	
	/**
	 * @return Devuelve una cadena de texto con la ruta en la que se encuentra el programa
	 * Este método es simplemente para que se vea como la terminal de Linux
	 * 
	 */
	public String rutaInterfaz () {
		if (rutaInicial.toString().equals("/")) {
			String rutaHome = "jose.guapache@Linux:" + "/" + "~$ ";
			return rutaHome;
		}else {
			String rutaHome =  "jose.guapache@Linux:" + rutaInicial.getName(rutaInicial.getNameCount()-1) + "~$ ";
			return rutaHome;
		}
	}
	
	/*
	 * @param ruta
	 * 
	 * Este método recibe un String con el nombre de la carpeta que se quiere acceder.
	 * A continuación crea una ruta a partir de la ruta home más el nombre de la carpeta, creando la Path de la
	 * carpeta a la que se quiere acceder y cambiando la variable rutaInicial.
	 * 
	 * Este método funciona tanto para rutas absolutas como para volver una carpeta atras pasando como argumento ..
	 */
	public void setRutaInicial (String nombreCarpeta) {
		if (nombreCarpeta.startsWith("/")) {
		Path nuevaPath = Paths.get(nombreCarpeta);
		
			if (nuevaPath.toFile().exists()) {
				this.rutaInicial = nuevaPath;
			}else {
				System.out.println("cd: " + nombreCarpeta + " No existe el archivo o el directorio");
			}
			
		}else if (nombreCarpeta.equals("..")) {
			Path ultimaCarpeta = rutaInicial.normalize().getName(rutaInicial.getNameCount()-1);
			
			if (ultimaCarpeta.toString().equals("home")) {
				Path nuevaPath = Paths.get("/");
				this.rutaInicial = nuevaPath;
			}else {
				String [] newPath = rutaInicial.toString().split("/" + ultimaCarpeta.toString());
				Path nuevaPath = Paths.get(newPath[0]);
				
				if (nuevaPath.toFile().exists()) {
					this.rutaInicial = nuevaPath;
				}else {
					System.out.println("cd: " + nombreCarpeta + " No existe el archivo o el directorio");
				}
			}
		}else {
			String nueva = rutaInicial.normalize() + "/"+ nombreCarpeta;
			Path nuevaPath = Paths.get(nueva);
			
			if (nuevaPath.toFile().exists()) {
				this.rutaInicial = nuevaPath;
			}else {
				System.out.println("cd: " + nombreCarpeta + " No existe el archivo o el directorio");
			}
		}
	}

	
	/**
	 * @throws IOException
	 * Esta funcion muestra por pantalla una breve descripcion de cada comando.
	 */
	public void help () throws IOException {
		System.out.println("ls: Hace un listado de los directorios y archivos de la ruta en la que se encuentra.");
		System.out.println();
		System.out.println("cd <nombre directorio>: Este comando permite accede a un directorio de manera que al escribir cd y el nombre de un directorio accederá a él.");
		System.out.println();
		System.out.println("cd : Muestra el directorio actual.");
		System.out.println();
		System.out.println("cd .. : Accede al directorio padre.");
		System.out.println();
		System.out.println("cd <nombre_directorio>: Accede a un directorio dentro del directorio actual.");
		System.out.println();
		System.out.println("cd <C:\\Nombre_Directorio>: Accede a una ruta absoluta del sistema.");
		System.out.println();
		System.out.println("dir: Lista los archivos contenidos en el directorio actual.");
		System.out.println();
		System.out.println("mkdir <nombre_directorio>: Crea un directorio en la ruta actual.");
		System.out.println();
		System.out.println("Info <nombre_archivo_o_directorio> este comando imprimirá los datos básicos de un archivo o directorio: su nombre, nombre de la carpeta padre, el tamaño del archivo o la carpeta.Acceso a datos Desarrollo web Entorno servidor Despliegue de aplicaciones web.");
		System.out.println();
		System.out.println("cat <nombre_fichero> : muestra el contenido de un fichero a través del terminal.");
		System.out.println();
		System.out.println("top <numero_lineas> <nombre_fichero>: muestras las primeras <numero_lineas> de un fichero. De manera que top 20 hola.txt muestra las primeras 20 líneas del fichero hola.txt");
		System.out.println();
		System.out.println("mkfile <nombre_fichero> <texto>: Crea un nuevo fichero llamado <nombre fichero> y escribe en el <texto>."); 
		System.out.println();
		System.out.println("write <nombre_fichero> <texto>: Añade <texto> al final de <nombre_fichero> que ya existe.");
		System.out.println();
		System.out.println("Delete <nombre_archivo_o_directorio>: Borra un archivo o una carpeta, en caso de ser una carpeta borra todos los archivos que la contienen.");
		System.out.println();
		System.out.println("dir: listas los archivos u directorios de la ruta actual.");
		System.out.println();
		System.out.println("dir <nombre_directorio>: listas los archivos y directorios de <nombre_directorio>-"); 
		System.out.println();
		System.out.println("dir <C:\\Nombre_Directorio>: listas los archivos y directorios de una ruta absoluta.");
		System.out.println();
		System.out.println("cp <fichero_original> <fichero_copia>: Realiza una copia del fichero denominado cp <fichero_original> al <fichero_copia>:. El <fichero_original> y/o el <fichero_copia>: se podrán especificar con el nombre de un archivo en el directorio local o una ruta absoluta al fichero. \n" + 
				"En caso de que el nombre del <fichero_copia> ya exista se dará la opción de sobreescribirlo.");
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
	
	public void dirArchivo (String nombreArchivo) {
		Path rutaArchivo = Paths.get(rutaInicial + "/" + nombreArchivo);
		String [] listar = rutaArchivo.toFile().list();
		
		if (rutaArchivo.toFile().exists()) {
			for (int i = 0; i < listar.length; i++) {
				System.out.println(listar [i]);
			}
		}else {
			System.out.println("El archivo que quiere listar no existe");
		}
	}
	
	public void dirAbsolute (String rutaAbsoluta) {
		Path rutaArchivo = Paths.get(rutaAbsoluta);
		String [] listar = rutaArchivo.toFile().list();
		
		if (rutaArchivo.toFile().exists()) {
			for (int i = 0; i < listar.length; i++) {
				System.out.println(listar [i]);
			}
		}else {
			System.out.println("El archivo que quiere listar no existe");
		}
	}
	
	/**
	 * @param nombreArchivo
	 * Este método crea un directorio
	 */
	public void mkdir (String nombreArchivo) {
		Path rutaArchivo = Paths.get(rutaInicial.toAbsolutePath() + "/"+nombreArchivo);

			if (rutaArchivo.toFile().isDirectory() == true) {
				System.out.println("mkdir: no se puede crear el directorio " + nombreArchivo +" El archivo ya existe");
			}else {
				rutaArchivo.toFile().mkdir();
		}
	}
	
	/**
	 * @param nombreArchivo
	 * @throws IOException
	 * Este comando imprimirá los datos básicos de un archivo o directorio: su nombre, nombre de la carpeta padre, el tamaño del archivo o la carpeta.
	 * 
	 */
	public void info (String nombreArchivo) throws IOException {
		Path rutaArchivo = Paths.get(rutaInicial.normalize() + "/" + nombreArchivo);
		
		if (rutaArchivo.toFile().exists())  {
			if (rutaArchivo.toFile().isDirectory() || rutaArchivo.toFile().isFile()) {
				FileChannel canal = FileChannel.open(rutaArchivo);
				long tamañoArchivo = canal.size();
				System.out.println("Nombre del archivo: " + nombreArchivo);
				System.out.println("Nombre del archivo padre: " + rutaInicial.toAbsolutePath().getName(rutaInicial.getNameCount()-1));
				System.out.println("Tamaño del archivo: " + tamañoArchivo + "Bytes");
		}else {
			System.out.println("El archivo no existe");
			}
		}
	}
	
	/**
	 * @param nombreFichero
	 * Muestra el contenido de un fichero a través del terminal.
	 */
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
	
	/**
	 * @param nombreFichero
	 * @throws IOException
	 * Este método crea un fichero de texto
	 */
	public void mkfile (String nombreFichero) throws IOException {
		Path rutaFichero = Paths.get(rutaInicial.normalize() + "/" + nombreFichero);
		
		if (rutaFichero.toFile().exists()) {
			System.out.println("El fichero ya existe");
		}else {
			rutaFichero.toFile().createNewFile();
		}
		
	}
	
	/**
	 * @param nombreFichero
	 * @param texto
	 * @throws IOException
	 * 
	 * Este método crea un fichero de texto y imprime en él, el texto que se le pasa como argumento 
	 */
	public void mkfile (String nombreFichero, String [] texto) throws IOException {
		Path rutaFichero = Paths.get(rutaInicial.normalize() + "/" + nombreFichero);
		
		if (rutaFichero.toFile().exists()) {
			System.out.println("El fichero ya existe");
		}else {
			rutaFichero.toFile().createNewFile();
			Charset charset = Charset.forName("ISO-8859-1");
			try (BufferedWriter wr = Files.newBufferedWriter(rutaFichero, charset, StandardOpenOption.APPEND)) {
				
					for (int i = 2; i < texto.length; i++) {
					wr.write(texto[i] + " ");
				}
			} catch (IOException e) {
				System.out.println("El archivo no exite");
				System.out.println(rutaFichero.toString());
			}
		}
		
	}
	
	/**
	 * @param nombreFichero
	 * @param texto
	 * Añade <texto> al final de nombreFichero que ya existe.
	 */
	public void write (String nombreFichero, String [] texto) {
		Path rutaFichero = Paths.get(rutaInicial.normalize() + "/" + nombreFichero);
		Charset charset = Charset.forName("ISO-8859-1");
		try (BufferedWriter wr = Files.newBufferedWriter(rutaFichero, charset, StandardOpenOption.APPEND)) {
			
				for (int i = 2; i < texto.length; i++) {
				wr.write(texto[i] + " ");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("El archivo no exite");
			System.out.println(rutaFichero.toString());
		}
	}
	
	/**
	 * @param nombreFichero
	 * Este método borra el fichero o directorio con el nombre que se le pasa como argumento
	 */
	public void delete (String nombreFichero) {
		
		if (nombreFichero.startsWith("/")) {
			Path rutaFichero = Paths.get(nombreFichero);
			if (rutaFichero.toFile().exists()) {
				rutaFichero.toFile().delete();
			}else {
				System.out.println("El archivo no existe.");
			}
		}else {
			Path rutaFichero = Paths.get(rutaInicial.normalize() + "/" + nombreFichero);
			if (rutaFichero.toFile().exists()) {
				rutaFichero.toFile().delete();
			}else {
				System.out.println("El archivo no existe.");
			}
		}
	}
	
	/**
	 * @param nombreArchivo
	 * @return devuelve un true si existe el archivo o fichero o un false si no existe. 
	 */
	public boolean existe (String nombreArchivo) {
		if (nombreArchivo.startsWith("/")) {
			Path rutaArchivo = Paths.get(nombreArchivo);
			return rutaArchivo.toFile().exists();
		}else {
			Path rutaArchivo = Paths.get(rutaInicial.normalize() + "/"+nombreArchivo);
			return rutaArchivo.toFile().exists();
		}
		
	}
	
	/**
	 * @param copiar
	 * @param nuevaDireccion
	 * 
	 * Este método hace una copia a la direccion que se le pasa como segundo argumento.
	 * Si este ya existe preguntara si quiere sobreescribirlo.
	 */
	public void cp(String copiar, String nuevaDireccion)  {
		Commands comandos = new Commands ();
		Path rutaOriginalAbsoluta = Paths.get(copiar);
		Path rutaCopiaAbsoluta = Paths.get(nuevaDireccion);
		
		if (copiar.startsWith("/")) {
			try {
				Files.copy(rutaOriginalAbsoluta, rutaCopiaAbsoluta);
			} catch (IOException e) {
				e.printStackTrace();
				comandos.rutaInterfaz();
			}
		}else if (nuevaDireccion.equalsIgnoreCase("/")){
			try {
				Files.copy(rutaOriginalAbsoluta, rutaCopiaAbsoluta);
			} catch (IOException e) {
				e.printStackTrace();
				comandos.rutaInterfaz();
			}
		}else {
			Path rutaOriginal = Paths.get(rutaInicial.normalize() + "/" + copiar);
			Path archivoCopiado = Paths.get(rutaInicial.normalize() + "/" + nuevaDireccion);
			
			try {
				Files.copy(rutaOriginal, archivoCopiado);
			}catch(IOException e) {
				e.printStackTrace();
				comandos.rutaInterfaz();
			}
		}
	}
	
	public Path crearPath (String nombreArchivo) {
		Path crear = Paths.get(rutaInicial.normalize() + "/" + nombreArchivo);
		
		return  crear;
	}
	
	
}