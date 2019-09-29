package javanio;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Commands {
	
	Path rutaInicial = null;
	public Commands () {
		rutaInicial = Paths.get("/home/alejandro");
		
	}
	
	public String rutaHome () {
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
		String [] separacion = ruta.rutaHome().split(":");
		String newPath = separacion [0] + "~/" + nombreCarpeta + "$";
		Path nuevaRuta = Paths.get(newPath);
		
		return nuevaRuta;
	}
	
	public Path cdAbsouluta (String rutaAbsoluta) {
		Commands ruta = new Commands ();
		String [] separacion = ruta.rutaHome().split(":");
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
		
		String [] pantalla = ruta.rutaHome().split(":");
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
	

}
