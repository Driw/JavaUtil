package org.diverproject.util.collection.abstraction;

import org.diverproject.util.collection.ArrayUtil;

/**
 * <p><h1>Lista Circular</h1></p>
 *
 * <p>Utiliza vetor para armazenar os elementos e possui uma capacidade limitada.
 * Ela funciona como circular pois ir� da �ltimo �ndice utilizado at� o final
 * da lista, e se assim n�o encontrar retrocede para o inicio com o mesmo processo.</p>
 *
 * <p>A inser��o dos elementos � diferente de todas as outras estruturas, pois ir�
 * procurar no vetor uma posi��o do qual ainda n�o foi ocupada pela lista.
 * J� a remo��o ir� apenas remover o elemento, deixando a posi��o em branco.</p>
 *
 * @see AbstractList
 *
 * @author Andrew
 *
 * @param <E>
 */

public class LoopList<E> extends AbstractList<E>
{
	/**
	 * Constr�i uma lista circular e inicializa o vetor para armazenamento.
	 * A capacidade inicial neste construtor � definido por DEFAULT_SIZE.
	 */

	public LoopList(int size)
	{
		super(size);
	}

	@Override
	public boolean add(E element)
	{
		for (int i = size; i < elements.length; i++)
			if (elements[i] == null)
			{
				elements[i] = element;
				return increaseSize();
			}

		for (int i = 0; i < size; i++)
			if (elements[i] == null)
			{
				elements[i] = element;
				return increaseSize();
			}

		return false;
	}

	@Override
	public boolean remove(E element)
	{
		if (element == null)
			return false;

		for (int i = 0; i < elements.length; i++)
			if (elements[i] != null)
				if (elements[i].equals(element))
					if (ArrayUtil.moveLeft(elements, i))
						return decreaseSize();

		return false;
	}

	@Override
	public boolean remove(int index)
	{
		if (ArrayUtil.moveLeft(elements, index))
			return decreaseSize();

		return false;
	}

	@Override
	public boolean update(int index, E element)
	{
		if (index < 0 || index >= elements.length)
			return false;

		elements[index] = element;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		if (index >= 0 && index < elements.length)
			return (E) elements[index];

		return null;
	}
}
