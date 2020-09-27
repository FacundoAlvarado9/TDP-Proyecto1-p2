package TDALista;

/**
 * Modela una excepción de Lista vacía.
 * De tipo checked exception.
 */
public class EmptyListException extends Exception {
    /**
     * Inicializa una excepción de Lista vacía con un mensaje asociado.
     * @param msg Mensaje asociado a la excepcion
     */
    public EmptyListException(String msg){
        super(msg);
    }
}
