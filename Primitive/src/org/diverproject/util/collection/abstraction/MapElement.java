package org.diverproject.util.collection.abstraction;

import org.diverproject.util.ObjectDescription;

/**
 * <p><h1>Elemento Mapeado</h1></p>
 *
 * <p>Objetos desse tipo s�o utilizados pelas estruturas de dados do tipo mapa.
 * Essas estruturas utilizando determinadas chaves para mapear seus elementos.
 * Assim eles s�o organizados de acordo com a estrutura e a chave definida.</p>
 *
 * <p>Al�m disso os atributos s�o definidos como package como a classe tamb�m.
 * De modo que estes possam ser utilizados apenas pelo pacote das estruturas.
 * Para ser mais preciso, na implementa��o da abstra��o do conceito delas.</p>
 *
 * @author Andrew
 *
 * @param <K> qual ser� o tipo de chave que ser� ligado ao elemento.
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

class MapElement<K, E>
{
	/**
	 * Qual ser� a chama desse elemento para ser mape�vel.
	 */
	K key;

	/**
	 * Refer�ncia do elemento que ser� ligado a chave deste.
	 */
	E value;

	/**
	 * Constr�i um novo elemento mape�vel iniciando seus atributos.
	 * @param key chave que ser� definido ao elemento mape�vel.
	 * @param element refer�ncia do elemento que ser� armazenado.
	 */

	public MapElement(K key, E element)
	{
		this.key = key;
		this.value = element;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("key", key);
		description.append("element", value);

		return description.toString();
	}
}
