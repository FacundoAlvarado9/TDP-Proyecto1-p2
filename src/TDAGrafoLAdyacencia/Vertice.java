package TDAGrafoLAdyacencia;

import TDALista.Lista;
import TDALista.Position;
import TDALista.PositionList;

public class Vertice<V, E> implements Vertex<V> {

    protected V rotulo;
    protected Position<Vertice<V,E>> posListaVertices;
    protected PositionList<Arco<V, E>> adyacentes;

    public Vertice(V rotulo){
        this.rotulo = rotulo;
        adyacentes = new Lista<Arco<V, E>>();
    }

    public V element(){
        return rotulo;
    }

    public void setRotulo(V rot){
        rotulo = rot;
    }

    public void setPosListaVertices(Position<Vertice<V, E>> pos){
        posListaVertices = pos;
    }

    public PositionList<Arco<V, E>> getAdyacentes() {
        return adyacentes;
    }

    public Position<Vertice<V, E>> getPosListaVer() {
        return posListaVertices;
    }
}
