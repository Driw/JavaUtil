package org.diverproject.util.collection.abstraction;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.collection.Queue;

/**
 * <p><h1>Fila Est�tica</h1></p>
 *
 * <p>Neste caso a fila est�tica utiliza o conceito da fila circular para tal.
 * De modo que esta possa ser usada infinitas vezes a mesma inst�ncia, desde
 * que haja elementos para trabalhar nele, onde a frente e tr�s circulam.</p>
 *
 * <p>Para especificar melhor o funcionamento � trabalhado o armazenamento com vetor.
 * Salvando o �ndice de inicio da fila e fim do mesmo para enfileirar e desenfileirar.
 * Quando enfileirado insere no final da fila e desenfileirar retira do inicio.</p>
 *
 * <p>Quando aos �ndices seja ele de fim ou de inicio da fila ir� circular pelo vetor.
 * De modo que quando chegarem no �ltimo �ndice passam para o primeiro (circulando).
 * Isso ocorre sempre ao inicio da inser��o e remo��o de elementos e se poss�vel.</p>
 *
 * @see AbstractQueue
 *
 * @author Andrew qual ser� o tipo de dado que ser� armazenado na cole��o.
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class StaticQueue<E> extends AbstractCollection<E> implements Queue<E>
{
	/**
	 * �ndice do elemento mais antigo da fila (primeiro).
	 */
	private int start;

	/**
	 * �ndice do elemento mais recente da fila (�ltimo).
	 */
	private int end;
	/**
	 * Vetor usado para armazenar os elementos adicionados na fila.
	 */
	protected Object elements[];

	/**
	 * Constr�i uma nova fila com tamanho est�tico sendo necess�rio definir tal.
	 * @param size qual ser� o tamanho dessa fila (capacidade).
	 */

	public StaticQueue(int size)
	{
		this(null, size);
	}

	/**
	 * Constr�i uma nova fila com tamanho est�tico sendo necess�rio definir tal.
	 * Al�m disso tamb�m define do tipo de objeto que ser� usado em toArray.
	 * @param generic classe respectiva ao tipo de dado armazenado.
	 * @param size qual ser� o tamanho dessa fila (capacidade).
	 */

	public StaticQueue(Class<?> generic, int size)
	{
		end = 0;
		start = 0;
		elements = new Object[size];

		setGeneric(generic);
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
	public Iterator<E> iterator()
	{
		return new Iterator<E>()
		{
			private int iterate = start;
			private int iteration;

			@Override
			public boolean hasNext()
			{
				return iteration < size();
			}

			@Override
			@SuppressWarnings("unchecked")
			public E next()
			{
				iteration++;
				Object next = elements[iterate++];

				if (iterate == elements.length)
					iterate = 0;

				return (E) next;
			}

			@Override
			public String toString()
			{
				ObjectDescription description = new ObjectDescription(getClass());

				description.append("generic", getGeneric().getSimpleName());
				description.append("iteration", iteration);
				description.append("size", size);

				return description.toString();
			}
		};
	}

	@Override
	public boolean offer(E element)
	{
		if (isFull())
			return false;

		size++;
		elements[end++] = element;

		if (end == elements.length)
			end = 0;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E poll()
	{
		if (isEmpty())
			return null;

		size--;

		Object poll = elements[start];
		elements[start++] = null;

		if (start == elements.length)
			start = 0;

		return (E) poll;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		for (Object element : elements)
			description.append(element);

		return description.toString();
	}
}
