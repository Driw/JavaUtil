package org.diverproject.util.collection.abstraction;

import java.lang.reflect.Array;

import org.diverproject.util.collection.Collection;

/**
 * <p><h1>Cole��o Abstrata</h1></p>
 *
 * <p>Essa classe possui algumas propriedades que todas as cole��es devem possuir.
 * No caso s�o os atributos que definem o seu tipo gen�rico e o tamanho da estrutura.
 * Al�m disso ir� implementar alguns dos m�todos que s�o comuns entre as estruturas.</p>
 *
 * @see Collection
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public abstract class AbstractCollection<E> implements Collection<E>
{
	protected int size;
	private Class<?> generic;

	public AbstractCollection()
	{
		size = 0;
		generic = Object.class;
	}

	@Override
	public Class<?> getGeneric()
	{
		return generic == null ? Object.class : generic;
	}

	@Override
	public void setGeneric(Class<?> generic)
	{
		this.generic = generic;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}

	@Override
	public boolean isFull()
	{
		return size == length();
	}

	@Override
	@SuppressWarnings("unchecked")
	public E[] toArray()
	{
		E[] array = (E[]) Array.newInstance(generic, size());
		int i = 0;

		for (E element : this)
			array[i++] = element;

		return array;
	}

}
