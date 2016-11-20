package org.diverproject.util.collection.abstraction;

/**
 * <p><h1>Pilha Est�tica</h1></p>
 *
 * <p>Pilhas est�ticas n�o possui nada de especial se comparado com o conceito de pilha.
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

public class StaticStack<E> extends AbstractStack<E>
{
	/**
	 * Constr�i uma nova pilha est�tica e inicializa o vetor para armazenamento.
	 * A capacidade inicial neste construtor � definido por DEFAULT_SIZE.
	 */

	public StaticStack()
	{
		super(DEFAULT_SIZE);
	}

	/**
	 * Constr�i uma nova pilha est�tica e inicializa o vetor para armazenamento.
	 * @param size tamanho para a capacidade de elementos na pilha.
	 */

	public StaticStack(int length)
	{
		super(length);
	}

	@Override
	public boolean push(E element)
	{
		if (isFull())
			return false;

		elements[size++] = element;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E pop()
	{
		if (isEmpty())
			return null;

		return (E) elements[size--];
	}
}
