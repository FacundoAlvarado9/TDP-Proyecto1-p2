package TDALista;

/**
 * Modela una excepción de Posición de Lista inválida.
 * De tipo checked exception.
 */
public class InvalidPositionException extends Exception {
    /**
     * Inicializa una excepción de Posicion de Lista inválida con un mensaje asociado.
     * @param msg Mensaje asociado a la excepción
     */
    public InvalidPositionException(String msg){
        super(msg);
    }
}
