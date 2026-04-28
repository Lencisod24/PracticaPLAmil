package semantico;

import ast.ASTNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TablaSimbolos {

    // Pila de tablas donde cada mapa es la tabla de un ámbito
    private Stack<Map<String, ASTNode>> pila;

    public TablaSimbolos() {
        inicializa();
        abreBloque();
    }

    public void inicializa() {
        pila = new Stack<>();
    }

    public void abreBloque() {
        pila.push(new HashMap<>());
    }

    public void cierraBloque() {
        pila.pop();
    }

    public boolean insertaId(String id, ASTNode puntero) {
        Map<String, ASTNode> cima = pila.peek();

        // Comprobación de error que salta si ya está declarada en este mismo bloque
        if (cima.containsKey(id)) {
            return false; // Error semántico, variable duplicada
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
}