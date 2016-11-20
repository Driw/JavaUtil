package org.diverproject.util.database;

/**
 * Banco de Dados
 *
 * <p>Implementa alguns dos m�todos necess�rios para se criar um banco de dados.
 * Nesse caso � utilizado um vetor interno de tamanho fixo definido no construtor.
 * Outras classes devem herdar desta para implementar o modo que ser� operado.
 * O modo de opera��o se refere a inserir, atualizar, remover e selecionar.</p>
 *
 * <p>Al�m disso deve ser definido o tipo de classe que ser� utilizada para instanciar.
 * De modo que quando for necess�rio mexer em <b>elements</b> seja poss�vel usar sem cast.</p>
 *
 * @see DatabaseAbstract
 * @see IDatabaseNoIndex
 *
 * @author Andrew
 *
 * @param <E> tipo de dado que ser� armazenado.
 */

public abstract class DatabaseNoIndex<E> extends DatabaseAbstract<E> implements IDatabaseNoIndex<E>
{
	/**
	 * Constr�i um novo banco de dados de acordo com os par�metros a baixos.
	 * @param name nome que ser� dado ao banco de dados para reconhecimento.
	 * @param generic tipo de dado que a classe ir� armazenar, necess�rio
	 * para que <b>elements</b> e <b>select</b> possa fazer o cast dos dados.
	 * @param max tamanho do qual ter� o vetor interno para guardar elementos.
	 */

	public DatabaseNoIndex(String name, Class<?> generic, int max)
	{
		super(name, generic, max);
	}

	@Override
	public boolean update(int index, E element)
	{
		if (!isIndex(index))
			return false;

		elements[index] = element;

		return true;
	}

	@Override
	public boolean remove(int index)
	{
		if (!isIndex(index) || elements[index] == null)
			return false;

		size--;
		elements[index] = null;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E select(int index)
	{
		if (isIndex(index))
			return (E) elements[index];

		return null;
	}
}
