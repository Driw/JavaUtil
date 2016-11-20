package org.diverproject.util.collection.abstraction;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.collection.ArrayUtil;

/**
 * <p><h1>Lista Est�tica</h1><p>
 *
 * <p>As listas din�micas determinam que o seu tamanho para armazenamento dos elementos,
 * pode ser redimensionado, por tanto a sua capacidade pode ser aumentada ou reduzida.
 * De modo geral esse tipo de estrutura � quase como um simples vetor vers�til.</p>
 *
 * <p>A quest�o da din�mica em sua capacidade ocorre internamente ao adicionar ou remover.
 * Quando adicionado verifica se est� cheio, e se estiver ir� aumentar o tamanho deste.
 * No caso da remo��o de um elemento verifica se h� espa�os demais sobrando e o corta.</p>
 *
 * @see AbstractList
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class DynamicList<E> extends AbstractList<E>
{
	/**
	 * Constr�i uma nova lista est�tica e inicializando o seu vetor interno.
	 */

	public DynamicList()
	{
		this(DEFAULT_SIZE);
	}

	/**
	 * Constr�i uma nova lista est�tica e inicializando o seu vetor interno.
	 * @param size qual ser� o tamanho usado para inicializa��o da lista.
	 */

	public DynamicList(int size)
	{
		super(size);
	}

	/**
	 * Constr�i uma nova lista est�tica e inicializando o seu vetor interno.
	 * Nesse construtor ser� permitido definir o tipo de dado em toArray.
	 * @param generic classe respectiva ao tipo de dado que ser� armazenado.
	 */

	public DynamicList(Class<?> generic)
	{
		super(DEFAULT_SIZE);

		setGeneric(generic);
	}

	@Override
	public boolean add(E element)
	{
		if (element == null)
			return false;

		if (isFull())
			elements = ArrayUtil.increseIn(elements, DEFAULT_SIZE);

		elements[size++] = element;

		return true;
	}

	@Override
	public boolean remove(E element)
	{
		if (super.remove(element))
			if (elements.length - size >= DEFAULT_SIZE)
			{
				elements = ArrayUtil.decreaseIn(elements, DEFAULT_SIZE);
				return true;
			}

		return false;
	}

	@Override
	public boolean remove(int index)
	{
		if (super.remove(index))
			if (elements.length - size >= DEFAULT_SIZE)
			{
				elements = ArrayUtil.decreaseIn(elements, DEFAULT_SIZE);
				return true;
			}

		return false;
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
