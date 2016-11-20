package org.diverproject.util.collection.abstraction;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.collection.List;
import org.diverproject.util.collection.Node;
import org.diverproject.util.lang.IntUtil;

/**
 * <h1>Lista por N�</h1>
 *
 * <p>Essa lista ir� armazenar os elementos dentro de n�s e permitir� trabalhar atrav�s de �ndices se necess�rio.
 * Al�m de funcionar como uma lista qualquer (tipos de m�todos) permite usar a interface Comparable como crit�rio.
 * Se habilitado, ao adicionar ir� priorizar o valor de Comparable, onde - 0: igual, >1: maior, <1: menor.</p>
 *
 * @see List
 * @see Node
 * @see Comparable
 *
 * @author Andrew Mello
 *
 * @param <E> tipo de dado que ser� armazenado
 */

public class NodeList<E> extends AbstractCollection<E> implements List<E>
{
	/**
	 * Quantidade limite de elementos na lista.
	 */
	private int length;

	/**
	 * Utiliza��o da interface Comparable.
	 */
	private boolean compare;

	/**
	 * Primeiro elemento da lista.
	 */
	private Node<E> first;

	/**
	 * �ltimo elemento da lista.
	 */
	private Node<E> last;

	/**
	 * Cria uma nova lista para armazenamento de elementos em n�.
	 * Para esse construtor ser� considerado um limite infinito de dados.
	 */

	public NodeList()
	{
		length = 0;
	}

	/**
	 * Cria uma nova lista para armazenamento de elementos em n�.
	 * @param maxSize quantidade limite de elementos armazen�veis.
	 */

	public NodeList(int maxSize)
	{
		if (maxSize > 0)
			length = size;
	}

	/**
	 * Permite saber se a lista est� utilizando o crit�rio de compara��o.
	 * O crit�rio de compara��o � aplicado ao adicionar elementos na lista.
	 * @return true se estiver habilitado ou false caso contr�rio.
	 * @see Comparable
	 */

	public boolean isCompare()
	{
		return compare;
	}

	/**
	 * Definir a lista como compar�vel ir� habilitar o crit�rio de compara��o por Comparable.
	 * Quando um novo elemento for adicionar e estiver habilitado ir� considerar Comparable.
	 * Caso seja igual a zero fica no mesmo local, menor que zero a frente e maior atr�s.
	 * Se o elemento n�o tiver implementado Comparable ser� adicionado ao fim da lista.
	 * @param compare true para habilitar ou false para desabilitar.
	 * @see Comparable
	 */

	public void setCompare(boolean compare)
	{
		this.compare = compare;
	}

	/**
	 * Remove o primeiro elemento da lista, alocando o segundo n� como prim�rio.
	 * @return true se removido ou false caso contr�rio (lista vazia).
	 */

	private boolean removeFirst()
	{
		if (first == null)
			return false;

		if (size == 1)
			first = last = null;

		else
		{
			first = first.getNext();
			first.setPrev(null);
		}

		size--;

		return true;
	}

	/**
	 * Remove o �ltimo elemento da lista, alocando o pen�ltimo n� como �ltimo.
	 * @return true se removido ou false caso contr�rio (lista vazia).
	 */

	private boolean removeLast()
	{
		if (last == null)
			return false;

		if (size == 1)
			last = first = null;
		else
		{
			last = last.getPrev();
			last.setNext(null);
		}

		size--;

		return true;
	}

	/**
	 * Remove um determinado n� encontrado no meio da lista.
	 * Aloca o anterior ao pr�ximo e vice-versa, removendo o alvo.
	 * @param node n� alvo que ser� removido da lista.
	 * @return true se removido ou false caso contr�rio.
	 */

	private boolean removeNode(Node<E> node)
	{
		if (node == null)
			return false;

		size--;
		Node.attach(node.getPrev(), node.getNext());

		return true;
	}

	/**
	 * Permite obter um determinado n� da lista com base no seu �ndice.
	 * Atrav�s do �ndice verifica se a busca ser� feito do inicio ou fim da lista.
	 * Assim � poss�vel reduzir o tempo de busca em casos com �ndice mais ao fim.
	 * @param index �ndice do n� alocado na listas do qual � desejado.
	 * @return null se o �ndice for inv�lido ou o n� do �ndice acima.
	 */

	private Node<E> getNode(int index)
	{
		if (!IntUtil.interval(index, 0, size - 1))
			return null;

		int firstOffset = index;
		int lastOffset = size - 1 - index;

		Node<E> node;

		if (firstOffset <= lastOffset)
		{
			node = first;

			for (int i = 0; i < firstOffset; i++)
				node = node.getNext();
		}

		else
		{
			node = last;

			for (int i = 0; i < lastOffset; i++)
				node = node.getPrev();
		}

		return node;
	}

	@Override
	public void clear()
	{
		first = null;
	}

	@Override
	public int length()
	{
		return length == 0 ? Integer.MAX_VALUE : length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E element)
	{
		if (isFull() || element == null)
			return false;

		Node<E> node = new Node<E>(element);

		if (first == null)
			first = last = node;

		else if (compare && element instanceof Comparable)
		{
			Node<E> prev = null;
			Node<E> aux = first;

			Comparable<Object> comparable = (Comparable<Object>) element;

			while (aux != null)
			{
				int compare = comparable.compareTo((Object) aux.get());

				if (compare == 0)
					return false;

				else if (compare > 0)
				{
					prev = aux;
					aux = aux.getNext();
				}

				else
					break;
			}

			if (prev == null)
			{
				Node.attach(node, first);
				first = node;
			}

			else if (aux == null)
			{
				Node.attach(prev, node);
				last = node;
			}

			else
			{
				Node.attach(prev, node);
				Node.attach(node, aux);
			}
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
	public boolean remove(E element)
	{
		if (isEmpty())
			return false;

		Node<E> node = first;

		if (node.get().equals(element))
		{
			if (size == 1)
				first = last = null;
			else
				first = first.getNext();

			size--;
			return true;
		}

		node = node.getNext();

		while (node != null)
			if (node.get().equals(element))
			{
				if (node.getPrev() == null)
					return removeFirst();

				else if (node.getNext() == null)
					return removeLast();

				return removeNode(node);
			}
			else
				node = node.getNext();

		return false;
	}

	@Override
	public boolean remove(int index)
	{
		if (isEmpty())
			return false;

		if (index == 0)
			return removeFirst();

		else if (index == size - 1)
			return removeLast();

		return removeNode(getNode(index));
	}

	@Override
	public boolean update(int index, E element)
	{
		if (isEmpty())
			return false;

		Node<E> node = getNode(index);

		if (node != null)
		{
			node.set(element);
			return true;
		}

		return false;
	}

	@Override
	public E get(int index)
	{
		if (isEmpty())
			return null;

		Node<E> node = getNode(index);

		return node != null ? node.get() : null;
	}

	@Override
	public boolean contains(E element)
	{
		Node<E> node = first;

		while (node != null)
		{
			if (node.get().equals(element))
				return true;
			else
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
				return node != null && node.get() != null;
			}

			@Override
			public E next()
			{
				E element = node.get();
				node = node.getNext();

				return element;
			}

			@Override
			public String toString()
			{
				ObjectDescription description = new ObjectDescription(getClass());

				description.append("hasNext", hasNext());
				description.append("next", node == null ? null : node.get());

				return description.toString();
			}
		};
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("size", size());

		Node<E> node = first;

		for (int i = 0; node != null; i++, node = node.getNext())
			description.append("i" +i, node.get());

		return description.toString();
	}
}
