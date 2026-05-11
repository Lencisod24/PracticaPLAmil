package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.atomic.AtomicInteger;
import alex.AnalizadorLexicoExp;
import ast.NodoPrograma;
import semantico.TablaSimbolos;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
			AnalizadorSintactico asint = new AnalizadorSintactico(alex);
			NodoPrograma ast = (NodoPrograma) asint.parse().value;
			System.out.println(ast.toString(""));

			TablaSimbolos ts = new TablaSimbolos();
			ast.chequea(ts);
			ast.calcularMem(new AtomicInteger(0), new AtomicInteger(0));
			ast.asignarDelta(4); // El tamaño del dynamic link (DL)

			// Generar código
			StringBuilder sb = new StringBuilder();
			ast.generateCodeInstruccion(sb, 0);

			// Escribir al fichero .wat
			String rutaSalida = args[1];
			java.nio.file.Path path = java.nio.file.Path.of(rutaSalida);
			java.nio.file.Files.createDirectories(path.getParent()); // crea la carpeta si no existe
			java.nio.file.Files.writeString(path, sb.toString());
			System.out.println("Código generado en: " + rutaSalida);
		} catch (Exception e) {
			System.out.println("Excepción durante la compilación:");
			e.printStackTrace();
		}

	}
}
