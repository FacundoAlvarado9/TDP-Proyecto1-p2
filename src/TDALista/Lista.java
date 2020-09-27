package TDALista;

import java.util.Iterator;

/**
 * Implementación del TDA Lista haciendo uso
 * de nodos doblemente enlazados con nodos centinela
 * @param <E> Tipo de los elementos
 */
public class Lista<E> implements PositionList<E> {

    /**
     * Nodos cabeza y rabo hacen referencia a los nodos centinela. Al comienzo y al final de la lista
     * respectivamente.
     */
    protected Nodo<E> cabeza, rabo;

    protected int tamano;

    /**
     * Inicializa una Lista vacia.
     */
    public Lista(){
        cabeza = new Nodo<E>(null, null, null);
        rabo = new Nodo<E>(cabeza, null, null);
        cabeza.setSiguiente(rabo);
        tamano = 0;
    }

    public int size() {
        return tamano;
    }

    public boolean isEmpty() {
        return tamano == 0;
    }

    public Position<E> first() throws EmptyListException {

        if(tamano == 0){ //Si la lista es vacia, no hay ultimo elemento, lanza una excepcion de lista vacia
            throw new EmptyListException("Lista vacia");
        }
        return cabeza.getSiguiente();
    }

    public Position<E> last() throws EmptyListException {

        if(tamano == 0){ //Si la lista es vacia, no hay ultimo elemento, lanza una excepcion de lista vacia
            throw new EmptyListException("Lista vacia");
        }
        return rabo.getAnterior();
    }

    /**
     * Evalúa la validez de una posición pasada como parámetro.
     * Y si es valida, retorna el nodo referenciado por la posicion.
     * @param p nodo a ser evaluado
     * @return el nodo correspondiente a la posición pasada como parametro, de ser posible.
     * @throws InvalidPositionException si la posición es nula o ha sido eliminada con anterioridad.
     */
    private Nodo<E> checkPosition(Position<E> p) throws InvalidPositionException{
        Nodo<E> n;

        try{
            if(p == null){ //verifica que no sea una referencia nula
                throw new InvalidPositionException("Posicion invalida");
            }
            if(p.element() == null){ //verifica que no se haya eliminado antes
                throw new InvalidPositionException("Posicion previamente eliminada");
            }
            n = (Nodo<E>) p; //realiza el casteo para quedarnos con el Nodo referenciado por la posicion
        } catch(ClassCastException e){
            throw new InvalidPositionException("p no es nodo de la lista");
        }
        return n;
    }

    public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException {

        //Evalúa la validez de la posicion pasada como parámetro.
        Nodo<E> nodoP = checkPosition(p);
        Nodo<E> nodoAnterior;

        //Si se trata de la primera posicion de la lista, claramente no tiene anterior, lanza excepcion
        if(nodoP == cabeza.getSiguiente()){
            throw new BoundaryViolationException("p es primer element, no tiene anterior");
        }

        //Si las evaluaciones salen bien, retorno el anterior.
        nodoAnterior = nodoP.getAnterior();
        return nodoAnterior;
    }

    public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException {

        //Evalúa la validez de la posicion pasada como parámetro.
        Nodo<E> nodoP = checkPosition(p);
        Nodo<E> nodoSiguiente;

        //Si se trata de la ultima posicion de la lista, claramente no tiene siguiente, lanza excepcion
        if(nodoP == rabo.getAnterior()){
            throw new BoundaryViolationException("p es ultimo element, no tiene siguiente");
        }

        //Si las evaluaciones salen bien, retorno el nodo siguiente
        nodoSiguiente = nodoP.getSiguiente();
        return nodoSiguiente;
    }

    public void addFirst(E e) {

        //Salva el siguiente element al primer centinela
        Nodo<E> auxSiguiente = cabeza.getSiguiente();

        //Crea un nuevo nodo a ser insertado, ya con el nodo cabeza como anterior y el ex-primero como siguiente
        Nodo<E> nuevoFirst = new Nodo<E>(cabeza, e, auxSiguiente);

        //Liga la cabeza y el ex-ultimo al nuevo nodo
        cabeza.setSiguiente(nuevoFirst);
        auxSiguiente.setAnterior(nuevoFirst);

        tamano++;
    }

    public void addLast(E e) {

        //Salva el nodo anterior al ultimo centinela
        Nodo<E> auxAnterior = rabo.getAnterior();

        //Crea un nuevo nodo a ser insertado, ya con el ex-ultimo como anterior y el rabo como siguiente
        Nodo<E> nuevoUlt = new Nodo<E>(auxAnterior, e, rabo);

        //liga el ex-anterior y el rabo al nuevo nodo
        auxAnterior.setSiguiente(nuevoUlt);
        rabo.setAnterior(nuevoUlt);

        tamano++;
    }

    public void addAfter(Position<E> p, E e) throws InvalidPositionException {

        //Evalúa la validez de la posición
        Nodo<E> nodoP = checkPosition(p);

        //Salva el nodo siguiente
        Nodo<E> auxSiguiente = nodoP.getSiguiente();

        //Crea un nuevo nodo a ser insertado, ya con p como anterior y el siguiente de p como siguiente
        Nodo<E> nuevoNodo = new Nodo<E>(nodoP, e, auxSiguiente);

        //Liga anterior y siguiente
        nodoP.setSiguiente(nuevoNodo);
        auxSiguiente.setAnterior(nuevoNodo);
        tamano++;
    }

    public void addBefore(Position<E> p, E e) throws InvalidPositionException {

        //Evalúa la validez de la posición
        Nodo<E> nodoP = checkPosition(p);

        //Guarda el nodo anterior
        Nodo<E> auxAnterior = nodoP.getAnterior();

        //Crea un nuevo nodo a ser insertado, ya con referencias al anterior a p como anterior y p como siguiente
        Nodo<E> nuevoNodo = new Nodo<E>(auxAnterior, e, nodoP);

        //Liga anterior y siguiente a p
        auxAnterior.setSiguiente(nuevoNodo);
        nodoP.setAnterior(nuevoNodo);;

        tamano++;
    }

    public E remove(Position<E> p) throws InvalidPositionException {

        //Evalúa la validez de la posicion,
        Nodo<E> nodoP = checkPosition(p);

        //Salva el elemento para luego retornarlo
        E elem = nodoP.element();

        //Salva nodo anterior y nodo siguiente
        Nodo<E> nodoSig = nodoP.getSiguiente();
        Nodo<E> nodoAnt = nodoP.getAnterior();

        //Liga siguiente y anterior
        nodoSig.setAnterior(nodoAnt);
        nodoAnt.setSiguiente(nodoSig);

        //Invalida nodo en posicion p para ayudar a checkPosition
        nodoP.setAnterior(null);
        nodoP.setSiguiente(null);
        nodoP.setElemento(null);

        tamano--;
        return elem;
    }

    public E set(Position<E> p, E e) throws InvalidPositionException {
        //Evalúo la validez de la posicion
        Nodo<E> nodoP = checkPosition(p);
        //Salvo el element en la posicion
        E salida = nodoP.element();
        nodoP.setElemento(e);
        return salida;
    }

    public Iterator<E> iterator() {
        return new IteradorElementos<E>(this);
    }

    public Iterable<Position<E>> positions() {
        PositionList<Position<E>> listaSalida;
        Nodo<E> aux;
        listaSalida = new Lista<Position<E>>();
        aux = cabeza.getSiguiente();
        while(aux != rabo){
            listaSalida.addLast(aux);
            aux = aux.getSiguiente();
        }
        return listaSalida;
    }

}
