import TDAGrafoLAdyacencia.Grafo;

public class LoggingPrueba {
    public static void main(String[] args){
        Grafo g = new Grafo();

        //Probando la insercion de nodos
        g.addNode(1);
        g.addNode(2);
        g.addNode(3);
        g.addNode(4);

        g.addNode(4); //pruebo insertar un nodo ya existente

        //Probando la insercion de arcos
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(1, 3);

        g.addEdge(4, 1); //pruebo insertar un arco ya existente


        g.addEdge(3, 9999); //pruebo insertar un arco entre un nodo pertenciente al grafo y uno inexistente

        //Probando la remocion de arcos
        g.removeEdge(2, 1);
        g.removeEdge(4, 1);
        g.removeEdge(99999, 1); //pruebo remover un arco entre un nodo inexistente y uno perteneciente al grafo

        //Probando la remocion de nodos
        g.removeNode(1);
        g.removeNode(3);
        g.removeEdge(1, 3); //pruebo la remocion de un arco entre nodos ya eliminados
    }
}
