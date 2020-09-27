package TDALista;

/**
 * Modela una excepción de violación de los límites de una Lista
 * De tipo checked excepción
 */
public class BoundaryViolationException extends Exception {
    /**
     * Inicializa una excepción de violación de los limites de una Lista con un mensaje asociado.
     * @param msg Mensaje asociado a la excepcion.
     */
    public BoundaryViolationException(String msg){
        super(msg);
    }
}
