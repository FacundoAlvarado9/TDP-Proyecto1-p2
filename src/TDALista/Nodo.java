package TDALista;

/**
 * Modela un nodo de la lista, con un elemento y la referencia al nodo anterior y siguiente.
 * Implementa la clase Position.
 * @param <E> Tipo del element contenido por el Nodo.
 */
public class Nodo<E> implements Position<E> {


    /**
     * Referencias al nodo anterior y siguiente.
     */
    private Nodo<E> anterior, siguiente;
    private E elemento;


    /**
     * Inicializa un nuevo Nodo con referencia al Nodo anterior, elemento y Nodo siguiente.
     * @param anterior referencia al Nodo anterior del nuevo Nodo
     * @param siguiente referencia al Nodo siguiente al nuevo nodo
     * @param elemento elemento del nuevo Nodo
     */
    public Nodo(Nodo<E> anterior, E elemento, Nodo<E> siguiente){
        this.anterior = anterior;
        this.siguiente = siguiente;
        this.elemento = elemento;
    }

    public E element() {
        return elemento;
    }

    /**
     * Consulta la referencia al nodo anterior al Nodo que recibe el mensaje.
     * @return la referencia al nodo anterior al nodo que recibe el mensaje
     */
    public Nodo<E> getAnterior(){
        return anterior;
    }

    /**
     * Consulta la referencia al nodo siguiente al Nodo que recibe el mensaje.
     * @return la referencia al nodo siguiente al nodo que recibe el mensaje.
     */
    public Nodo<E> getSiguiente(){
        return siguiente;
    }

    /**
     * Define una nueva referencia al Nodo anterior al Nodo que recibe el mensaje. Con la referencia pasada como parametro.
     * @param nodo con el que se actualiza el nodo anterior al nodo que recibe el mensaje.
     */
    public void setAnterior(Nodo<E> nodo){
        anterior = nodo;
    }

    /**
     * Define una nueva referencia al Nodo siguiente al Nodo que recibe el mensaje. Con la referencia pasada como parametro.
     * @param nodo con el que se actualiza el nodo siguiente al nodo que recibe el mensaje
     */
    public void setSiguiente(Nodo<E> nodo){
        siguiente = nodo;
    }

    /**
     * Setea un nuevo elemento para el Nodo que recibe el mensaje. Con el elemento pasado como parametro.
     * @param elem element con el que se actualiza el element contenido por el Nodo.
     */
    public void setElemento(E elem){
        elemento = elem;
    }
}
