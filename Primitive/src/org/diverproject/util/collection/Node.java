package org.diverproject.util.collection;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;

/**
 * <p><h1>N�</h1></p>
 *
 * <p>Classe que permite a constru��o de uma estrutura de dados simples conhecida como n�.
 * Essas estruturas na verdade s�o peda�os usados em um verdadeira estrutura de dados.
 * Onde cada elemento nesta inserido ser� armazenado dentro de um n� para ordenar.</p>
 *
 * <p>S�o conhecidos por n�o determinarem uma capacidade limite pois s�o ligados entre si.
 * Cada n� possui a refer�ncia do seu elemento ra�z, que ser� o n� deste mesmo na estrutura.
 * Al�m disso permite adicionar refer�ncia como elemento anterior/pr�ximo, criando o n�.</p>
 *
 * <p>Nada impede que dois n�s sejam ligados sequencialmente usando anterior e pr�ximo.
 * Como tamb�m permite o uso de uma liga��o em loop infinito onde o n� em quest�o aponta para
 * o n� anterior e este n� anterior aponta para o n� seguinte (primeiro n� mencionado).</p>
 *
 * <p>Essas refer�ncias para qualquer um dos lados que seja (anterior ou pr�ximo),
 * podem ser definidos atrav�s dos setters ou obtidos atrav�s dos getters do mesmo.
 * Isso tamb�m vale para o elemento do qual est� sendo armazenado pelo n�.</p>
 *
 * @see Collection
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class Node<E> implements Iterable<E>
{
	/**
	 * Refer�ncia do valor que este n� est� armazenado.
	 */
	private E value;

	/**
	 * Refer�ncia do n� que ir� seguir este, n� seguinte.
	 */
	private Node<E> next;

	/**
	 * Refer�ncia do n� que ir� anteceder este, n� anterior.
	 */
	private Node<E> prev;

	/**
	 * Constr�i um novo n� onde deve ser definido o seu valor inicial.
	 * Espera-se que todo n� constru�do tenha um valor inicial para tal.
	 * Pois a utiliza��o deste n�o faz sentido sem possuir um valor.
	 * @param value refer�ncia do valor que ser� armazenado neste n�.
	 */

	public Node(E value)
	{
		set(value);
	}

	/**
	 * Procedimento que permite obter o valor armazenado dentro deste n�.
	 * @return aquisi��o do objeto valor que foi atribu�do a esse n�.
	 */

	public E get()
	{
		return value;
	}

	/**
	 * Permite definir qual ser� o objeto de valor atribu�do a esse n�.
	 * @param element refer�ncia do novo objeto que ser� definido como valor.
	 */

	public void set(E element)
	{
		value = element;
	}

	/**
	 * N� seguinte � um dos lados do n� que visualmente consideramos como lado direito.
	 * @return aquisi��o da refer�ncia do n� seguinte a este na estrutura.
	 */

	public Node<E> getNext()
	{
		return next;
	}

	/**
	 * Permite definir qual ser� o n� que ser� considerado como seguinte a este.
	 * @param next refer�ncia do novo n� que ser� definido como posterior.
	 */

	public void setNext(Node<E> next)
	{
		this.next = next;
	}

	/**
	 * N� anterior � um dos lados do n� que visualmente consideramos como lado esquerdo.
	 * @return aquisi��o da refer�ncia do n� anterior a este na estrutura.
	 */

	public Node<E> getPrev()
	{
		return prev;
	}

	/**
	 * Permite definir qual ser� o n� que ser� considerado como anterior a este.
	 * @param next refer�ncia do novo n� que ser� definido como antecedente.
	 */

	public void setPrev(Node<E> prev)
	{
		this.prev = prev;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("value", value);
		description.append("prev", prev == null ? null : prev.get());
		description.append("next", next == null ? null : next.get());

		return description.toString();
	}

	/**
	 * Procedimento que deve fazer o anexo de dois n�s previamente definidos.
	 * Onde o primeiro ter� como seguinte o segundo e o segundo ter� como
	 * anterior o primeiro n�, torando-os assim sequenciais como esperado.
	 * @param first refer�ncia do primeiro n� que ser� o lado esquerdo.
	 * @param second refer�ncia do segundo n� que ser� o lado direito.
	 * @return true se conseguir anexar ou false caso um deles seja nulo.
	 */

	@SuppressWarnings("unchecked")
	public static boolean attach(Node<?> first, Node<?> second)
	{
		if (first == null || second == null)
			return false;

		Node<Object> left = (Node<Object>) first;
		Node<Object> right = (Node<Object>) second;

		left.setNext(right);
		right.setPrev(left);

		return false;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<E>()
		{
			private Node<E> node = new Node<E>(get());

			@Override
			public boolean hasNext()
			{
				return node != null && node.get() != null;
			}

			@Override
			public E next()
			{
				E e = node.get();
				node = node.getNext();

				return e;
			}
		};
	}
}
