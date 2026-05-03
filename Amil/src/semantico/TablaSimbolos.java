package semantico;

import ast.ASTNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaSimbolos {

    // Lista de tablas para manejar la estructura de bloques anidados
    // El profesor indicó en clase que no hay que verlo como una pila (stack),
    // ya que eso no tenía sentido, y que lo implementáramos simplemente como una lista.
    // La lista tiene al final el ámbito más reciente; el ámbito se define con un mapa
    // en el que se almacenan los ids de las variables (o lo que sea) y una referencia a su ASTNode.
    private List<Map<String, ASTNode>> pila;

    // Para manejar los structs independientemente del ámbito
    private Map<String, Map<String, String>> structsDefinidos;

    public TablaSimbolos() {
        inicializa();
        abreBloque();
    }

    public void inicializa() {
        pila = new ArrayList<>();
        structsDefinidos = new HashMap<>();
    }

    public void abreBloque() {
        pila.add(new HashMap<>());
    }
    
    public void cierraBloque() {
        pila.remove(pila.size() - 1);
        
    }

    public boolean insertaId(String id, ASTNode puntero) {
        Map<String, ASTNode> cima = pila.get(pila.size() - 1);

        if (cima.containsKey(id)) {
            return false;
        }

        cima.put(id, puntero);
        return true;
    }

    public ASTNode buscaId(String id) {
        //por eso hay que usar List en vez de Stack, si no aquí tendrías
        //que ir desapilando, guardando lo que sea y luego volver a apilar
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
    //ESTO HAY QUE REVISARLO PORQUE EN CLASE INDICÓ ALGO DE COMO SE HACÍA LO DEL TIPO DE LOS CAMPOS DEL STRUCT
    //Y NO ME ACUERDO COMO ERA EXACTAMENTE
    public String getTipoCampoDeStruct(String nombreStruct, String nombreCampo) {
        if (esStructDefinido(nombreStruct)) {
            Map<String, String> camposDelStruct = structsDefinidos.get(nombreStruct);
            return camposDelStruct.get(nombreCampo);
        }
        return null;
    }
}