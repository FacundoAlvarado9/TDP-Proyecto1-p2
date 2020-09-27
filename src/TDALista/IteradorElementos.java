package TDALista;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteradorElementos<E> implements Iterator<E> {

    private PositionList<E> lista;
    private Position<E> cursor;

    /**
     * Inicializa un nuevo iterador de elementos de la Lista pasada por parametro
     * @param l lista a la que estará asociado el iterador.
     */
    public IteradorElementos(Lista<E> l) {
        try {
            lista = l;

            //Si la lista no está vacía, el cursor apuntará a la primera posicion
            if (lista != null && !lista.isEmpty()) {
                cursor = lista.first();
            } else {

                //Caso contrario, el cursor no apuntará a una posicion nula
                cursor = null;

            }
        } catch (EmptyListException e) {
            cursor = null;
        }
    }

    /**
     * Verifica que sea posible solicitar al iterador un elemento siguiente. Esto es, que la posicion a la que apunta
     * el cursor no sea nula.
     * @return verdadero si la si el cursor esta en una posicion no nula, falso en caso contrario.
     */
    public boolean hasNext() {
        return cursor != null;
    }

    /**
     * Avanza de ser posible el cursor a la siguiente posicion de la lista y
     * retorna el elemento de la posicion en la que estaba.
     * @return el elemento de la posicion a la que apuntaba el cursor antes de avanzar un lugar.
     * @throws NoSuchElementException si el cursor apuntaba a una posicion nula, no hay siguiente.
     */
    public E next() throws NoSuchElementException {

        if(cursor == null){
            throw new NoSuchElementException("No hay siguiente");
        }

        E salida = null;

        try{

            //Guardo el elemento donde se para el cursor para luego retornarlo
            salida = cursor.element();

            //Si estaba parado en el ultimo elemento, seteo el cursor en nulo.
            //Caso contrario, avanzo un lugar.
            if(cursor == lista.last()){
                cursor = null;
            } else{
                cursor = lista.next(cursor);
            }

        } catch (BoundaryViolationException e) {
            throw new NoSuchElementException("Se llego al final de la lista");
        } catch (InvalidPositionException e) {
            throw new NoSuchElementException("Posicion invalida");
        } catch (EmptyListException e) {
            throw new NoSuchElementException("Lista vacia");
        }
        return salida;
    }
}
