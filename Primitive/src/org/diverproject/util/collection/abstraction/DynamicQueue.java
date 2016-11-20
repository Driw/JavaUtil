package org.diverproject.util.collection.abstraction;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.collection.Node;
import org.diverproject.util.collection.Queue;

/**
 * <p><h1>Fila Din�mica</h1></p>
 *
 * <p>Neste caso a fila din�mica utiliza o conceito de n�s duplamente encadeados.
 * De modo que cada elemento seja armazenado em um n� especificando quem � que
 * est� a frente deste se houver e quem est� atr�s se assim houver um.</p>
 *
 * <p>Trabalha com dois n�s duplamente encadeados especificando quem � o primeiro
 * elemento na fila e quem � o �ltimo elemento do mesmo, facilitando o processo para
 * pegar o elemento que est� ao inicio da fila e inserir um novo ao final desta..</p>
 *
 * <p>Outra caracter�stica importante para se definir � que esta n�o possui um
 * "tamanho limite", onde o limite e Integer.MAX_VALUE para dimensionar o tamanho
 * dessa estrutura atrav�s do atributo size ou m�todo size() que funciona como get.</p>
 *
 * @see AbstractCollection
 * @see Queue
 *
 * @author Andrew qual ser� o tipo de dado que ser� armazenado na cole��o.
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class DynamicQueue<E> extends AbstractCollection<E> implements Queue<E>
{
	/**
	 * Refer�ncia do n� que representa o inicio da fila (antigos elementos).
	 */
	private Node<E> first;

	/**
	 * Refer�ncia do n� que representa o final da fila (novos elementos).
	 */
	private Node<E> last;

	/**
	 * Constr�i uma nova fila com tamanho din�mico atrav�s de n�s encadeados.
	 * Al�m disso tamb�m define do tipo de objeto que ser� usado em toArray.
	 */

	public DynamicQueue()
	{
		this(null);
	}

	/**
	 * Constr�i uma nova fila com tamanho din�mico atrav�s de n�s encadeados.
	 * Al�m disso tamb�m define do tipo de objeto que ser� usado em toArray.
	 * @param generic classe respectiva ao tipo de dado armazenado.
	 */

	public DynamicQueue(Class<?> generic)
	{
		super();

		setGeneric(generic);
	}

	@Override
	public void clear()
	{
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public int length()
	{
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean contains(E element)
	{
		Node<E> node = first;

		while (node != null)
		{
			if (node.get().equals(element))
				return true;

			node = node.getNext();
		}

		return false;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<E>()
		{
			private Node<E> node = first;

			@Override
			public boolean hasNext()
			{
				return node != null;
			}

			@Override
			public E next()
			{
				Node<E> last = node;
				node = last.getNext();

				return last.get();
			}
		};
	}

	@Override
	public boolean offer(E element)
	{
		if (isFull())
			return false;

		Node<E> node = new Node<E>(element);

		if (isEmpty())
		{
			first = node;
			last = node;
		}

		else if (size == 1)
		{
			Node.attach(first, node);
			last = node;
		}

		else
		{
			Node.attach(last, node);
			last = node;
		}

		size++;

		return true;
	}

	@Override
	public E poll()
	{
		if (isEmpty())
			return null;

		Node<E> poll = first;
		first = first.getNext();
		size--;

		return poll.get();
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("generic", getGeneric().getSimpleName());

		Node<E> node = first;

		while (node != null)
		{
			description.append(node.get());
			node = node.getNext();
		}

		return description.toString();
	}
}
