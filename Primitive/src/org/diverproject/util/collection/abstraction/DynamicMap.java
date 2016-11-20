package org.diverproject.util.collection.abstraction;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.collection.ArrayUtil;

/**
 * <p><h1>Mapeador Abstrato</h1></p>
 *
 * <p>Primeiramente para definir o que � um mapeador, � uma estrutura e dados do qual,
 * armazena seus elementos de alguma maneira ligados a uma chave para identifica-los.
 * Assim externamente n�o � poss�vel saber como estes s�o organizados na tabela.</p>
 *
 * <p>No caso do mapeador din�mico a capacidade da estrutura de dados varia.
 * Essa varia��o vai de acordo com o n�mero de elementos adicionados ou removido.
 * Quando adicionados verifica se necessita de mais espa�o e quando um for removido,
 * ir� verificar se h� muito espa�o internamente sobrando sem elementos ocupando-os.</p>
 *
 * @see AbstractMap
 *
 * @author Andrew
 *
 * @param <K> qual ser� o tipo de dado que ser� usado como chave.
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public class DynamicMap<K, E> extends AbstractMap<K, E>
{
	/**
	 * Constr�i um novo mapeador din�mico iniciando o vetor interno.
	 * Neste caso n�o determina o tipo de dado obtido por toArray.
	 */

	public DynamicMap()
	{
		this(null, DEFAULT_SIZE);
	}

	/**
	 * Constr�i um novo mapeador din�mico iniciando o vetor interno.
	 * Neste caso n�o determina o tipo de dado obtido por toArray.
	 * @param start limite da capacidade do vetor inicialmente.
	 */

	public DynamicMap(int start)
	{
		this(null, start);
	}

	/**
	 * Constr�i um novo mapeador din�mico iniciando o vetor interno.
	 * @param generic tipo de dado que ser� obtido por toArray.
	 */

	public DynamicMap(Class<?> generic)
	{
		this(generic, DEFAULT_SIZE);
	}

	/**
	 * Constr�i um novo mapeador din�mico iniciando o vetor interno.
	 * @param generic tipo de dado que ser� obtido por toArray.
	 * @param start limite da capacidade do vetor inicialmente.
	 */

	public DynamicMap(Class<?> generic, int start)
	{
		super(generic, start);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean add(K key, E element)
	{
		if (isFull())
			elements = (MapElement<K, E>[]) ArrayUtil.increseIn(elements, DEFAULT_SIZE);

		elements[size++] = new MapElement<K, E>(key, element);

		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean removeKey(K key)
	{
		if (super.removeKey(key))
		{
			if (elements.length - DEFAULT_SIZE > size)
				elements = (MapElement<K, E>[]) ArrayUtil.decreaseIn(elements, DEFAULT_SIZE);

			return true;
		}

		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean remove(E element)
	{
		if (super.remove(element))
		{
			if (elements.length - DEFAULT_SIZE > size)
				elements = (MapElement<K, E>[]) ArrayUtil.decreaseIn(elements, DEFAULT_SIZE);

			return true;
		}

		return false;
	}

	@Override
	public E get(K key)
	{
		for (MapElement<K, E> element : elements)
			if (element != null && element.key.equals(key))
				return element.value;

		return null;
	}

	@Override
	public Iterator<K> iteratorKey()
	{
		return new Iterator<K>()
		{
			private int iterate;

			@Override
			public boolean hasNext()
			{
				return iterate < size;
			}

			@Override
			public K next()
			{
				return elements[iterate++].key;
			}

			@Override
			public String toString()
			{
				ObjectDescription description = new ObjectDescription(getClass());

				description.append("generic", getGeneric());
				description.append("iterate", iterate);
				description.append("size", size);

				return description.toString();
			}
		};
	}

	@Override
	public Iterator<MapItem<K, E>> iteratorItems()
	{
		return new Iterator<MapItem<K, E>>()
		{
			private int iterate;

			@Override
			public boolean hasNext()
			{
				return iterate < size;
			}

			@Override
			public MapItem<K, E> next()
			{
				return new MapItem<K, E>(elements[iterate].key, elements[iterate++].value);
			}

			@Override
			public String toString()
			{
				ObjectDescription description = new ObjectDescription(getClass());

				description.append("generic", getGeneric());
				description.append("iterate", iterate);
				description.append("size", size);

				return description.toString();
			}
		};
	}
}
