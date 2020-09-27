package TDAGrafo;

import TDALista.Position;

public class Arco<V, E> implements Edge<E> {

    protected E rotulo;
    protected Position<Arco<V, E>> posListaArcos;

    protected Vertice<V, E> v1, v2;

    public Arco(Vertice<V,E> v1, Vertice<V,E> v2, E rotulo){
        this.rotulo = rotulo;
        this.v1 = v1;
        this.v2 = v2;
    }

    public E element(){
        return rotulo;
    }

    public Vertice<V, E> getVEmergente(){
        return v1;
    }

    public Vertice<V, E> getVIncidente(){
        return v2;
    }

    public Position<Arco<V,E>> getPosListaArcos() {
        return posListaArcos;
    }

    public void setPosListaArcos(Position<Arco<V, E>> last) {
        posListaArcos = last;
    }
}
