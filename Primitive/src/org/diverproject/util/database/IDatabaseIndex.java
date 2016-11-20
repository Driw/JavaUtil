package org.diverproject.util.database;

/**
 * <p><h1>Database com �ndice</h1></p>
 *
 * <p>Interface que permite a implementa��o da estrutura b�sica de um banco de dados.
 * A diferen�a para DatabaseNoIndex � que nesse caso a inser��o precisa de um �ndice.
 * Assim � poss�vel determinar em que parte do banco o elemento ser� alocado./p>
 *
 * @see IDatabase
 *
 * @author Andrew
 *
 * @param <E> tipo de da dado que ser� armazenado no banco de dados.
 */

public interface IDatabaseIndex<E> extends IDatabase<E>
{
	/**
	 * Permite adicionar um novo elemento em um determinado �ndice.
	 * @param index �ndice no banco de dados para posicionar o elemento.
	 * @param element refer�ncia do elemento que ir� ocupar o �ndice passado.
	 * @return true se adicionado ou false caso o �ndice seja inv�lido ou ocupado.
	 */

	boolean insert(int index, E element);
}
