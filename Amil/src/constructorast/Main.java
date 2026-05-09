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
			StringBuilder sb= new StringBuilder();
			ast.generateCodeInstruccion(sb, 0);
			System.out.println("Codigo generado");
			System.out.println(sb);
		} catch (Exception e) {
			System.out.println("Excepción durante la compilación:");
			e.printStackTrace();
		}

	}
}
