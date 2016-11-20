package org.diverproject.util.collection.abstraction;

import org.diverproject.util.collection.ArrayUtil;

/**
 * <p><h1>Pilha Din�mica</h1></p>
 *
 * <p>Pilhas din�micas n�o possui nada de especial se comparado com o conceito de pilha.
 * Por se uma estrutura din�mica, sua capacidade para armazenamento dos elementos
 * empilhados ser� vari�vel, ou seja, quando necess�rio ser� adicionado mais espa�o.
 * O mesmo vale para quando remover elementos, se sobrar espa�o reduz a capacidade.</p>
 *
 * @see AbstractStack
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class DynamicStack<E> extends AbstractStack<E>
{
	/**
	 * Constr�i uma nova pilha din�mica e inicializa o vetor para armazenamento.
	 * A capacidade inicial neste construtor � definido por DEFAULT_SIZE.
	 */

	public DynamicStack()
	{
		super(DEFAULT_SIZE);
	}

	/**
	 * Constr�i uma nova pilha din�mica e inicializa o vetor para armazenamento.
	 * @param size tamanho para a capacidade de elementos na pilha.
	 */

	public DynamicStack(int size)
	{
		super(size);
	}

	@Override
	public boolean push(E element)
	{
		if (isFull())
			elements = ArrayUtil.increseIn(elements, DEFAULT_SIZE);

		elements[size++] = element;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E pop()
	{
		if (isEmpty())
			return null;

		E pop = (E) elements[size--];

		if (elements.length - size >= DEFAULT_SIZE)
			elements = ArrayUtil.decreaseIn(elements, DEFAULT_SIZE);

		return pop;
	}
}
