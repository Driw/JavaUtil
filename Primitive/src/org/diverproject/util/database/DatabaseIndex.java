package org.diverproject.util.database;

/**
 * <p><h1>Database Indexada</h1></p>
 *
 * <p>Estrutura para banco de dados em mem�ria a partir de um banco de dados abstrato.
 * Possui a implementa��o das funcionalidades para inserir, remover e selecionar elementos.
 * Nesse caso os elementos s�o organizados por �ndices respectivos aos do vetor de elementos.</p>
 *
 * @see DatabaseAbstract
 * @see IDatabaseIndex
 *
 * @author Andrew
 *
 * @param <E> tipo de da dado que ser� armazenado no banco de dados.
 */

public abstract class DatabaseIndex<E> extends DatabaseAbstract<E> implements IDatabaseIndex<E>
{
	/**
	 * Constr�i um novo banco de dados de acordo com os par�metros a baixos.
	 * @param generic tipo de dado que a classe ir� armazenar, necess�rio
	 * para que <b>elements</b> e <b>select</b> possa fazer o cast dos dados.
	 * @param max tamanho do qual ter� o vetor interno para guardar elementos.
	 */

	public DatabaseIndex(String name, Class<?> generic, int length)
	{
		super(name, generic, length);
	}
}
