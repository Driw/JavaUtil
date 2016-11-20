package org.diverproject.util.database;

/**
 * <p><h1>Database sem �ndice</h1></p>
 *
 * <p>Interface que permite a implementa��o da estrutura b�sica de um banco de dados.
 * A diferen�a para DatabaseIndex � que nesse caso a inser��o n�o precisa de �ndice.
 * Portanto espera-se que internamente no m�todo seja definido o �ndice do elemento./p>
 *
 * @see IDatabase
 *
 * @author Andrew
 *
 * @param <E> tipo de da dado que ser� armazenado no banco de dados.
 */

public interface IDatabaseNoIndex<E> extends IDatabase<E>
{
	/**
	 * Permite adicionar um novo elemento e esse elemento deve ser posicionado no banco.
	 * @param element refer�ncia do elemento que ir� ocupar o �ndice passado.
	 * @return true se adicionado ou false caso o �ndice seja inv�lido ou ocupado.
	 */

	boolean insert(E element);
}
