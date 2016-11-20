package org.diverproject.util.collection.abstraction;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.collection.ArrayUtil;
import org.diverproject.util.collection.Index;

/**
 * <p><h1>Vetor Abstrato</h1></p>
 *
 * <p>Nesta caso o �ndice � feita atrav�s de um vetor e podendo ser manipulados.
 * Implementa alguns m�todos que s�o semelhantes entre a �ndice est�tica e din�mica.
 * Diferente de um �ndice ele ir� ordenar os elementos por �ndice no vetor usado.</p>
 *
 * <p>A diferen�a entre os dois modelos � uma fun��o adicional ao adicionar e remover.
 * No caso do din�mico o tamanho da lista � fixa e no din�mico pode ser vari�vel.</p>
 *
 * @see AbstractCollection
 * @see Index
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public abstract class AbstractArray<E> extends AbstractCollection<E> implements Index<E>
{
	/**
	 * Tamanho padr�o para iniciar uma lista, reduzir ou aumentar o tamanho desta.
	 */
	public static final int DEFAULT_SIZE = 10;

	/**
	 * Vetor que ir� armazenar os elementos da lista.
	 */
	protected Object elements[];

	/**
	 * Constr�i um novo �ndice abstrata inicializando o vetor para armazenamento.
	 * Neste caso o tamanho inicial do vetor � o DEFAULT_SIZE para tal.
	 */

	public AbstractArray()
	{
		this(DEFAULT_SIZE);
	}

	/**
	 * Constr�i um novo �ndice abstrato inicializando o vetor para armazenamento.
	 * @param start quantos elementos poder�o ser armazenados nessa lista.
	 */

	public AbstractArray(int start)
	{
		elements = new Object[start];
	}

	@Override
	public void clear()
	{
		size = 0;
		elements = new Object[elements.length];
	}

	@Override
	public int length()
	{
		return elements.length;
	}

	@Override
	public boolean contains(E element)
	{
		for (Object object : elements)
			if (object != null)
				if (object.equals(element))
					return true;

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

	private boolean decreaseSize()
	{
		size--;

		return true;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<E>()
		{
			private int iteration;

			@Override
			public boolean hasNext()
			{
				return iteration != size;
			}

			@Override
			@SuppressWarnings("unchecked")
			public E next()
			{
				for (int i = iteration; i < elements.length; i++)
					if (elements[i] != null)
					{
						iteration++;
						Object selected = elements[i];

						return (E) selected;
					}

				return null;
			}

			@Override
			public String toString()
			{
				ObjectDescription description = new ObjectDescription(getClass());

				description.append("iteration", iteration);
				description.append("size", size);
				description.append("elements", elements.length);

				return description.toString();
			}
		};
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("size", size());
		description.append("length", length());

		for (int i = 0; i < elements.length; i++)
			description.append(Integer.toString(i),	elements[i]);

		return description.toString();
	}
}
