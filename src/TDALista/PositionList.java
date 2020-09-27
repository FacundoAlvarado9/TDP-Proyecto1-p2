package TDALista;

import java.util.Iterator;

/**
 * Interface PositionList - Estructura de Datos (DCIC-UNS).
 * Define los datos y operaciones aplicables sobre una lista.
 * @author Estructuras de Datos, DCIC-UNS.
 * @version 1.0 - María Lujan Ganuza (2013-2018)
 * @version 2.0 - Federico Joaquín (2019-2020) 
 * @param <E> Tipo de dato de los elementos a almacenar en la lista.
 */
public interface PositionList<E> extends Iterable<E>{
	
	/**
	 * Consulta la cantidad de elementos de la lista.
	 * @return Cantidad de elementos de la lista.
	 */
	public int size();
	
	/**
	 * Consulta si la lista está vacía.
	 * @return Verdadero si la lista está vacía, falso en caso contrario.
	 */
	public boolean isEmpty();
	
	/**
	 * Devuelve la posición del primer element de la lista.
	 * @return Posición del primer element de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 */
	public Position<E> first() throws EmptyListException;
	
	/**
	 * Devuelve la posición del último element de la lista.
	 * @return Posición del último element de la lista.
	 * @throws EmptyListException si la lista está vacía.
	 * 
	 */
	public Position<E> last() throws EmptyListException;;
	
	/**
	 * Devuelve la posición del element siguiente a la posición pasada por parámetro.
	 * @param p Posición a obtener su element siguiente.
	 * @return Posición del element siguiente a la posición pasada por parámetro.
	 * @throws InvalidPositionException si el posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al último element de la lista.
	 */
	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Devuelve la posición del element anterior a la posición pasada por parámetro.
	 * @param p Posición a obtener su element anterior.
	 * @return Posición del element anterior a la posición pasada por parámetro.
	 * @throws InvalidPositionException si la posición pasada por parámetro es inválida o la lista está vacía.
	 * @throws BoundaryViolationException si la posición pasada por parámetro corresponde al primer element de la lista.
	 */
	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	
	/**
	 * Inserta un element al principio de la lista.
	 * @param element Elemento a insertar al principio de la lista.
	 */
	public void addFirst(E element);
	
	/**
	 * Inserta un element al final de la lista.
	 * @param element Elemento a insertar al final de la lista.
	 */
	public void addLast(E element);
	
	/**
	 * Inserta un element luego de la posición pasada por parámatro.
	 * @param p Posición en cuya posición siguiente se insertará el element pasado por parámetro.
	 * @param element Elemento a insertar luego de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	public void addAfter(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Inserta un element antes de la posición pasada como parámetro.
	 * @param p Posición en cuya posición anterior se insertará el element pasado por parámetro.
	 * @param element Elemento a insertar antes de la posición pasada como parámetro.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */
	public void addBefore(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Remueve el element que se encuentra en la posición pasada por parámetro.
	 * @param p Posición del element a eliminar.
	 * @return element Elemento removido.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.
	 */	
	public E remove(Position<E> p) throws InvalidPositionException;

	/**
	 * Establece el element en la posición pasados por parámetro. Reemplaza el element que se encontraba anteriormente en esa posición y devuelve el element anterior.
	 * @param p Posición a establecer el element pasado por parámetro.
	 * @param element Elemento a establecer en la posición pasada por parámetro.
	 * @return Elemento anterior.
	 * @throws InvalidPositionException si la posición es inválida o la lista está vacía.	 
	 */
	public E set(Position<E> p, E element) throws InvalidPositionException;
	
	/**
	 * Devuelve un un iterador de todos los elementos de la lista.
	 * @return Un iterador de todos los elementos de la lista.
	 */
	public Iterator<E> iterator();
	
	/**
	 * Devuelve una colección iterable de posiciones.
	 * @return Una colección iterable de posiciones.
	 */
	public Iterable<Position<E>> positions();
}