package org.diverproject.util.collection.abstraction;

/**
 * <p><h1>Vetor Est�tico</h1></p>
 *
 * <p>Esse tipo de estrutura de dados utiliza o conceito de estrutura por �ndice.
 * Onde utiliza um �ndice para posicionar seus elementos em uma estrutura interna.
 * A estrutura utilizado no caso como o pr�prio nome j� diz � um vetor.</p>
 *
 * <p>A forma como trabalha os �ndices usados para posicionar os elementos no mesmo,
 * tamb�m � bem simples, onde o �ndice nos procedimentos s�o respectivos aos
 * �ndices do vetor interno usado para armazenar os elementos deste.</p>
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class StaticArray<E> extends AbstractArray<E>
{
	/**
	 * Constr�i um novo vetor est�tico iniciando o vetor de elementos.
	 * Neste caso o tamanho inicial do vetor � o DEFAULT_SIZE para tal.
	 */

	public StaticArray()
	{
		this(DEFAULT_SIZE);
	}

	/**
	 * Constr�i um novo vetor est�tico iniciando o vetor de elementos.
	 * @param start quantos elementos poder�o ser armazenados nessa lista.
	 */

	public StaticArray(int start)
	{
		elements = new Object[start];
	}

	@Override
	public boolean add(int index, E element)
	{
		if (!isIndex(index) || elements[index] != null)
			return false;

		size++;
		elements[index] = element;

		return true;
	}

	@Override
	public boolean remove(int index)
	{
		if (!isIndex(index) && elements[index] != null)
			return false;

		size--;
		elements[index] = null;

		return true;
	}

	@Override
	public boolean update(int index, E element)
	{
		if (!isIndex(index))
			return false;

		if (elements[index] == null)
			size++;

		elements[index] = element;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean change(int first, int second)
	{
		if (!isIndex(first) || !isIndex(second))
			return false;

		E aux = (E) elements[second];
		elements[second] = elements[first];
		elements[first] = aux;

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public E get(int index)
	{
		return isIndex(index) ? (E) elements[index] : null;
	}

	/**
	 * Procedimento que ir� fazer a valida��o para �ndice v�lido ou inv�lido.
	 * @param index n�mero do �ndice do qual deve ser validado.
	 * @return true se for v�lido ou false caso n�o seja.
	 */

	protected boolean isIndex(int index)
	{
		return index >= 0 && index < elements.length;
	}
}
