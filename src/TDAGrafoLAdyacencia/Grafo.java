package TDAGrafoLAdyacencia;

import java.util.Iterator;
import java.util.logging.*;

/**
 * TDA Grafo a utilizar para la 2da parte del primer proyecto de la
 * cátedra TDP. Extiende al TDA realizado para Estructuras de Datos
 * "Grafo con listas de adyacencia". La signatura de los métodos es la
 * solicitada por el proyecto.
 */
public class Grafo extends GrafoAdyacencia<Integer, Integer>{

    protected static Logger logger;

    public Grafo(){
        super();

        //Inicializo el logger
        if(logger == null){

            logger = Logger.getLogger(Grafo.class.getName());

            Handler hdl = new ConsoleHandler(); //Declaro e inicializo el console handler, para que los mensajes salgan por consola

            logger.addHandler(hdl);

            logger.setLevel(Level.ALL);
            hdl.setLevel(Level.WARNING); //Defino el nivel minimo de los mensajes por consola en WARNING, imprimiendo solo WARNING

            //Desactivo el logger padre para evitar repeticion de mensajes
            Logger rootLogger = logger.getParent();
            for(Handler h : rootLogger.getHandlers()){
                h.setLevel(Level.OFF);
            }

        }
    }

    /**
     * Añade un nodo al grafo si es que no existe ya uno rotulado
     * con el mismo entero.
     * @param node rotulo del nodo a añadir
     */
    public void addNode(int node){

        boolean encontre = false;
        Iterator<Vertice<Integer, Integer>> it = listaVertices.iterator();
        Vertice<Integer, Integer> aux;

        //Me aseguro de que no exista nodo rotulado con el entero node
        while(it.hasNext() && !encontre){
            aux = it.next();
            if(aux.element() == node){
                logger.warning("Ya existe un nodo de rotulo " + node + ".");
                encontre = true;
            }
        }

        if(!encontre){
            super.insertVertex(node);
            logger.info("Ha sido añadido el nodo " + node + ".");
        }
    }

    /**
     * Añade un arco al grafo, conectando a los nodos pasados como parametro.
     * @param node1 rotulo del nodo en un extremo
     * @param node2 rotulo del nodo en otro extremo
     */
    public void addEdge(int node1, int node2){

        boolean encontre1 = false;
        boolean encontre2 = false;
        Iterator<Vertice<Integer, Integer>> it = listaVertices.iterator();
        Vertice<Integer, Integer> aux, n1 = null, n2 = null;

        //Busco los nodos que tengan como rotulo al node1 y node2
        //recordemos que tenemos acceso a la lista de vertices}
        while(it.hasNext() && (!encontre1 || !encontre2)){
            aux = it.next();
            if(aux.element() == node1){
                n1 = aux;
                encontre1 = true;
            }
            if(aux.element() == node2){
                n2 = aux;
                encontre2 = true;
            }
        }

        if(encontre1 && encontre2){
            try {

                //Chequeo que no exista ya un arco entre ellos

                if(!super.areAdjacent(n1, n2)) { //Si no existe, esto es, si es valida la insercion del arco

                    super.insertEdge(n1, n2, 0);
                    logger.info("Ha sido añadido un arco conectando los nodos " + node1 + " y " + node2 + ".");

                } else{ //caso contrario, si es que ya existe un arco conectando a los nodos
                    logger.warning("Ya existe un arco conectando el nodo " + node1 + " y " + node2 + ".");
                }

            } catch (InvalidVertexException e) {
                logger.warning("Hubo un error insertando el arco que une el nodo " + node1 + " y " + node2 + ".");
            }
        } else{
            //Si no encontré los nodos que busco, no puedo crear el arco y emito un mensaje
            logger.warning("Al menos un nodo, " + node1 + " o " + node2 + " no existen en el grafo. No ha podido crearse el arco.");
        }
    }

    /**
     * En caso de existir, elimina el nodo rotulado con el entero pasado como parametro.
     * @param node rotulo del nodo a eliminar
     */
    public void removeNode(int node){

        boolean encontre = false;
        Iterator<Vertice<Integer, Integer>> it = listaVertices.iterator();
        Vertice<Integer, Integer> aux;

        //Busco los nodos que tengan como rotulo al node1 y node2
        while(it.hasNext() && !encontre){
            aux = it.next();

            if(aux.element() == node){
                try {
                    super.removeVertex(aux);
                    logger.info("Ha sido removido el nodo con rotulo " + node + ".");
                    encontre = true;
                } catch (InvalidVertexException e) {
                    logger.warning("Ha habido un error removiendo el nodo de rotulo " + node + ".");
                }
            }

        }

        if(!encontre){
            logger.warning("No existe el nodo de rotulo " + node + ". No pudo removerse.");
        }

    }

    /**
     * En caso de existir, remueve del grafo el arco que conecta
     * a los nodos rotulados con los enteros pasados por parametro.
     * @param node1 rotulo del nodo en un extremo
     * @param node2 rotulo del nodo en el otro extremo
     */
    public void removeEdge(int node1, int node2){

        boolean encontre = false;
        Iterator<Arco<Integer, Integer>> it = listaArcos.iterator();
        Arco<Integer, Integer> aux;
        Vertice<Integer, Integer> v1, v2;

        //Busco en la lista de arcos los que tengan a nodos rotulados con node1 y node2 como extremos
        while(it.hasNext() && !encontre){
            aux = it.next();
            v1 = aux.getVEmergente();
            v2 = aux.getVIncidente();

            //Si lo encuentro,
            if((v1.element() == node1 && v2.element() == node2) || (v1.element() == node2 && v2.element() == node1)){
                try{
                    super.removeEdge(aux); //lo elimino
                    logger.info("Ha sido eliminado el arco que conectaba los nodos " + node1 + " y " + node2 + ".");
                } catch (InvalidEdgeException e) {
                    e.printStackTrace();
                }
                encontre = true;
            }

        }

        if(!encontre){
            //Si no lo encuentro, emito un warning
            logger.warning("No existe el arco que uno los nodos " + node1 + " y " + node2 + ", o bien no existe al menos uno de los nodos. No pudo removerse.");
        }
    }
}
