package semantico;

import ast.ASTNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TablaSimbolos {

    // Pila de tablas para manejar la estructura de bloques anidados
    private Stack<Map<String, ASTNode>> pila;

    // Para manejar los structs independientemente del ámbito
    private Map<String, Map<String, String>> structsDefinidos;

    public TablaSimbolos() {
        inicializa();
        abreBloque();
    }

    public void inicializa() {
        pila = new Stack<>();
        structsDefinidos = new HashMap<>();
    }

    public void abreBloque() {
        pila.push(new HashMap<>());
    }

    public void cierraBloque() {
        pila.pop();
    }

    public boolean insertaId(String id, ASTNode puntero) {
        Map<String, ASTNode> cima = pila.peek();

        if (cima.containsKey(id)) {
            return false;
        }

        cima.put(id, puntero);
        return true;
    }

    public ASTNode buscaId(String id) {
        for (int i = pila.size() - 1; i >= 0; i--) {
            Map<String, ASTNode> tablaBloque = pila.get(i);
            if (tablaBloque.containsKey(id)) {
                return tablaBloque.get(id);
            }
        }
        return null;
    }

    public boolean registrarStruct(String nombreStruct, Map<String, String> campos) {
        if (structsDefinidos.containsKey(nombreStruct)) {
            return false;
        }
        structsDefinidos.put(nombreStruct, campos);
        return true;
    }

    public boolean esStructDefinido(String nombreTipo) {
        return structsDefinidos.containsKey(nombreTipo);
    }

    public String getTipoCampoDeStruct(String nombreStruct, String nombreCampo) {
        if (esStructDefinido(nombreStruct)) {
            Map<String, String> camposDelStruct = structsDefinidos.get(nombreStruct);
            return camposDelStruct.get(nombreCampo);
        }
        return null;
    }
}