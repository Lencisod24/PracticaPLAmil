package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;
import ast.NodoPrograma;

public class Main {
	public static void main(String[] args) throws Exception {
		try{
			Reader input = new InputStreamReader(new FileInputStream(args[0]));
			AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
			AnalizadorSintactico asint = new AnalizadorSintactico(alex);
			NodoPrograma ast = (NodoPrograma) asint.parse().value;
			System.out.println(ast.toString(""));
		}
		catch (Exception e) {
           System.out.println("Excepción durante la compilación:");
           e.printStackTrace();
       }
		
	} 
}
