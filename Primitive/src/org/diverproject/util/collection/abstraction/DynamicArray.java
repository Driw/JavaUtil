package org.diverproject.util.collection.abstraction;

import org.diverproject.util.collection.ArrayUtil;

/**
 * <p><h1>Vetor Din�mico</h1></p>
 *
 * <p>Esse tipo de estrutura de dados utiliza o conceito de estrutura por �ndice.
 * Onde utiliza um �ndice para posicionar seus elementos em uma estrutura interna.
 * A estrutura utilizado no caso como o pr�prio nome j� diz � um vetor.</p>
 *
 * <p>A forma como trabalha os �ndices usados para posicionar os elementos no mesmo,
 * tamb�m � bem simples, onde o �ndice nos procedimentos s�o respectivos aos
 * �ndices do vetor interno usado para armazenar os elementos deste.</p>
 *
 * <p>No caso do modo din�mico para vetor, ele s� ser� redimension�vel no momento em
 * que for adicionado um elemento com um �ndice do qual nao seja v�lido por�m positivo.
 * Por tanto deve ser usado os �ndices cuidadosamente para n�o crescer muito.</p>
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class DynamicArray<E> extends AbstractArray<E>
{
	private int last;

	@Override
	public boolean add(int index, E element)
	{
		if (index < 0)
			return false;

		if (index > elements.length)
			elements = ArrayUtil.resizeTo(elements, index + 1);

		if (last < index)
			last = index;

		size++;
		elements[index] = element;

		return true;
	}

	@Override
	public boolean remove(int index)
	{
		if (!isIndex(index))
			return false;

		size--;
		elements[index] = null;

		if (index == last)
			for (int i = last - 1; i >= 0; i--)
				if (elements[i] != null)
				{
					last = i;
					break;
				}

		if (!isEmpty())
			ArrayUtil.resizeTo(elements, last + (last % DEFAULT_SIZE));
		else
			elements = new Object[DEFAULT_SIZE];

		return true;
	}

	@Override
	public boolean update(int index, E element)
	{
		if (!isIndex(index))
			return false;

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
