package actividad1x04;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Primo {
	public static void main(String[] args) throws IOException {
		
		BufferedReader teclado = null;
		BufferedReader entrada = null;
		File directorio = new File("bin");
		PrintStream ps = null;
		
		try {
			ProcessBuilder pb = new ProcessBuilder("java", "actividad1x04.CalculaPrimo");
			pb.directory(directorio);
			teclado = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Di un numero");
			String linea = teclado.readLine();
			while (!linea.equals("fin")) {
				Process hijo = pb.start();
				entrada = new BufferedReader(new InputStreamReader(hijo.getInputStream()));
				ps = new PrintStream(hijo.getOutputStream());

				ps.println(linea);
				ps.flush();

				String respuesta = entrada.readLine();
				while (respuesta != null) {
					System.out.println(respuesta);
					respuesta = entrada.readLine();
				}
				System.out.println("Di un numero");
				linea = teclado.readLine();
			}
		} finally {
			ps.close();
			entrada.close();
		}
	}
}
