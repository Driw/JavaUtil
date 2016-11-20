package org.diverproject.util.collection.abstraction;

import org.diverproject.util.ObjectDescription;

/**
 * <p><h1>Lista Est�tica</h1><p>
 *
 * <p>As listas est�ticas determinam que o seu tamanho para armazenamento dos elementos,
 * deve ser sempre o mesmo, por tanto a sua capacidade n�o pode ser aumentada ou reduzida.
 * De modo geral esse tipo de estrutura � quase como um simples vetor como outro qualquer.</p>
 *
 * @see AbstractList
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class StaticList<E> extends AbstractList<E>
{
	/**
	 * Constr�i uma nova lista est�tica e inicializando o seu vetor interno.
	 * @param size quantos elementos poder�o ser adicionados a essa lista.
	 */

	public StaticList(int size)
	{
		super(size);
	}

	/**
	 * Constr�i uma nova lista est�tica e inicializando o seu vetor interno.
	 * Nesse construtor ser� permitido definir o tipo de dado em toArray.
	 * @param generic classe respectiva ao tipo de dado que ser� armazenado.
	 * @param size quantos elementos poder�o ser adicionados a essa lista.
	 */

	public StaticList(Class<?> generic, int size)
	{
		super(size);

		setGeneric(generic);
	}

	@Override
	public boolean add(E element)
	{
		if (element == null || isFull())
			return false;

		elements[size++] = element;

		return true;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		for (Object element : this)
			description.append(element);

		return description.toString();
	}
}
