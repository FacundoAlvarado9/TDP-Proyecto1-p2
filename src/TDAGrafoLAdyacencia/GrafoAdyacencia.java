package TDAGrafoLAdyacencia;

import TDALista.EmptyListException;
import TDALista.InvalidPositionException;
import TDALista.Lista;
import TDALista.PositionList;

import java.util.Iterator;

public class GrafoAdyacencia<V,E> implements Graph<V,E> {

    protected Lista<Arco<V, E>> listaArcos;
    protected Lista<Vertice<V,E>> listaVertices;

    public GrafoAdyacencia(){
        listaArcos = new Lista<Arco<V, E>>();
        listaVertices = new Lista<Vertice<V,E>>();
    }


    public Iterable<Vertex<V>> vertices() {
        PositionList<Vertex<V>> toRet = new Lista<Vertex<V>>();
        for(Vertice<V,E> v : listaVertices) {
            toRet.addLast(v);
        }
        return toRet;
    }


    public Iterable<Edge<E>> edges() {

        PositionList<Edge<E>> listaResultado = new Lista<Edge<E>>();

        for(Arco<V, E> e : listaArcos){
            listaResultado.addLast(e);
        }

        return listaResultado;
    }

    public Iterable<Edge<E>> incidentEdges(Vertex<V> v) throws InvalidVertexException {
        if(v == null){
            throw new InvalidVertexException("Vertice no valido");
        }

        PositionList<Edge<E>> listaIncidentEdgesResult = new Lista<Edge<E>>();

        for(Arco<V, E> e : listaArcos){
            if(e.getVEmergente() == v || e.getVIncidente() == v){
                listaIncidentEdgesResult.addLast(e);
            }
        }

        return listaIncidentEdgesResult;
    }

    private Arco<V,E> checkEdge(Edge<E> e) throws InvalidEdgeException {
        Arco<V,E> arcoSalida = null;

        try {
            if(e == null){
                throw new InvalidEdgeException("Edge invalido");
            }
            arcoSalida = (Arco<V, E>) e;
            if(arcoSalida.getVIncidente() == null && arcoSalida.getVEmergente() == null){
                throw new InvalidEdgeException("Arco eliminado previamente");
            }
        } catch (ClassCastException err){
            System.out.println("Ocurrio un error");
        }
        return arcoSalida;
    }

    private Vertice<V,E> checkVertice(Vertex<V> v) throws InvalidVertexException {
        Vertice<V,E> vertice = null;

        try{
            if(v == null){
                throw new InvalidVertexException("Vertice invalido");
            }
            vertice = (Vertice<V,E>) v;
            if(v.element() == null){
                System.out.println("Vertice eliminado previamente");
            }
        } catch(ClassCastException err){
            System.out.println("Hubo un error de casteo");
        }

        return vertice;
    }

    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws InvalidVertexException, InvalidEdgeException {
        if(v == null){
            throw new InvalidVertexException("Vertice no valido");
        }
        if(e == null){
            throw new InvalidEdgeException("Arco no valido");
        }

        Vertice<V,E> opp = null;
        Arco<V, E> arcoDeE = checkEdge(e);

        if(arcoDeE.getVEmergente() == v){
            opp = arcoDeE.getVIncidente();
        } else if(arcoDeE.getVIncidente() == v){
            opp = arcoDeE.getVEmergente();
        } else{
            throw new InvalidEdgeException("El arco no conecta al vertice v");
        }

        return opp;
    }

    public Vertex<V>[] endvertices(Edge<E> e) throws InvalidEdgeException {

        Arco<V, E> arcoDeE = checkEdge(e);
        Vertex<V>[] arrVertices = new Vertex[2];

        if(arcoDeE.getVIncidente() != null){
            arrVertices[0] = arcoDeE.getVIncidente();
        }
        if(arcoDeE.getVEmergente() != null){
            arrVertices[1] = arcoDeE.getVEmergente();
        }

        return arrVertices;
    }

    public boolean areAdjacent(Vertex<V> v, Vertex<V> w) throws InvalidVertexException {

        if(v == null || w == null){
            throw new InvalidVertexException("Vertice o vertices invalidos");
        }

        boolean areAdjacent = false;
        Iterator<Arco<V, E>> itListaArcos = listaArcos.iterator();
        Arco<V, E> indice;

        while(itListaArcos.hasNext() && !areAdjacent){
            indice = itListaArcos.next();

            if(indice.getVEmergente() == v || indice.getVIncidente() == v){
                if(indice.getVIncidente() == w || indice.getVEmergente() == w){
                    areAdjacent = true;
                }
            }

        }

        return areAdjacent;
    }

    public V replace(Vertex<V> v, V x) throws InvalidVertexException {

        if(v == null){
            throw new InvalidVertexException("vertice invalido");
        }

        Vertice<V,E> ver = checkVertice(v);
        V salida = ver.element();

        ver.setRotulo(x);
        return salida;
    }

    public Vertex<V> insertVertex(V x) {

        Vertice<V,E> nuevoVertice = new Vertice<V,E>(x);

        try {
            listaVertices.addLast(nuevoVertice);
            nuevoVertice.setPosListaVertices(listaVertices.last());
        } catch (EmptyListException e) {
            e.printStackTrace();
        }

        return nuevoVertice;
    }

    public Edge<E> insertEdge(Vertex<V> v, Vertex<V> w, E e) throws InvalidVertexException {
        if(v == null || w == null){
            throw new InvalidVertexException("Vertice o vertices invalidos");
        }

        Arco<V, E> nuevoArco = new Arco<V, E>(checkVertice(v), checkVertice(w), e);

        try {
            listaArcos.addLast(nuevoArco);

            nuevoArco.setPosListaArcos(listaArcos.last());
        } catch (EmptyListException emptyListException) {
            emptyListException.printStackTrace();
        }

        return nuevoArco;
    }

    public V removeVertex(Vertex<V> v) throws InvalidVertexException {
        if(v == null){
            throw new InvalidVertexException("vertice invalido");
        }
        V salida = v.element();

        Vertice<V,E> vertice = checkVertice(v);

        try {

            for(Arco<V, E> a : listaArcos){
                if(a.getVIncidente() == vertice || a.getVEmergente() == vertice){
                    listaArcos.remove(a.getPosListaArcos());
                }
            }

            listaVertices.remove(vertice.getPosListaVer());
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }

        return salida;
    }

    public E removeEdge(Edge<E> e) throws InvalidEdgeException {
        Arco<V, E> arco = checkEdge(e);
        E salida = arco.element();

        try {
            listaArcos.remove(arco.getPosListaArcos());
        } catch (InvalidPositionException err) {
            err.printStackTrace();
        }

        return salida;
    }
}