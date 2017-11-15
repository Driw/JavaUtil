package org.diverproject.util.collection;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;

/**
 * <p><h1>Mapa</h1></p>
 *
 * <p>Estruturas de dados do tipo mapa funciona de modo que todos elementos possuam uma chave.
 * Essa chave ser� usada para interagir com o elemento como remover ou selecionar o mesmo.
 * A chave n�o pode ser usada por dos elementos por mais que sejam iguais ou distintos.</p>
 *
 * <p>Nesse caso a itera��o pode ser feita n�o apenas dos elementos como das chaves tamb�m.
 * Remover elementos neste caso pode ser feito ou pela chave ou refer�ncia do elemento em si.
 * Sempre que um novo elemento for adicionado � obrigat�rio ter uma chave identificadora.</p>
 *
 * @see Collection
 *
 * @author Andrew
 *
 * @param <K> qual ser� o tipo de dado que ser� usado como chave.
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public interface Map<K, E> extends Collection<E>
{
	/**
	 * Verifica se um determinada chave j� est� sendo utilizada no mapeador.
	 * @param key chave do qual deve ser verificado a exist�ncia.
	 * @return true se conter ou false caso contr�rio.
	 */

	boolean containsKey(K key);

	/**
	 * Permite adicionar um novo elemento ligado a uma chave de localiza��o.
	 * @param key chave que ser� usada para definir a localiza��o do elemento.
	 * @param element refer�ncia do elemento do qual deseja adicionar ao mapeador.
	 * @return true se conseguir adicionar false se valor nulo ou chave usada.
	 */

	boolean add(K key, E element);

	/**
	 * Permite remover um determinado elemento do mapeador pela sua refer�ncia.
	 * @param element refer�ncia do elemento do qual deseja remover do mapeador.
	 * @return true se conseguir remover false caso n�o encontre no mesmo.
	 */

	boolean remove(E element);

	/**
	 * Permite remover um determinado elemento do mapeador pela sua chave.
	 * @param key chave respectiva ao elemento do qual ser� removido.
	 * @return true se conseguir remover false caso n�o encontre no mesmo.
	 */

	boolean removeKey(K key);

	/**
	 * Permite renomear a chave de um determinado elemento para se poss�vel.
	 * @param oldKey chave antiga � a chave utilizada no momento pelo elemento.
	 * @param newKey qual ser� a nova chave a ser definida a chave antiga.
	 * @return true se conseguir renomear ou false se uma chave for inv�lida,
	 * chave inv�lida pode ser chave j� existente (nova) ou ent�o nula.
	 */

	boolean renameKey(K oldKey, K newKey);

	/**
	 * Permite atualizar um determinado valor no mapeador pela sua chave.
	 * @param key chave respectiva ao elemento do qual ser� atualizado.
	 * @param value refer�ncia do valor que ser� definido a chave.
	 * @return true se existir e atualizar ou false caso contr�rio.
	 */

	boolean update(K key, E value);

	/**
	 * Permite obter um determinado elemento pela sua chave definida.
	 * @param key chave respectiva ao elemento desejado do mapeador.
	 * @return aquisi��o do elemento respectivo na chave passada.
	 */

	E get(K key);

	/**
	 * Deve fazer a itera��o de todos as chaves existente no mapeador.
	 * @return aquisi��o de uma nova itera��o das chaves.
	 */

	Iterator<K> iteratorKey();

	/**
	 * Deve fazer a itera��o de todos os elementos com chaves existente no mapeador.
	 * @return aquisi��o de uma nova itera��o dos elementos com chaves.
	 */

	Iterator<MapItem<K, E>> iteratorItems();

	/**
	 * Essa itera��o ir� passar por todos os elementos retornando suas chaves.
	 * Pode ser usado a fim de obter os elementos atrav�s de suas chaves.
	 * @return aquisi��o de uma itera��o das chaves usadas.
	 */

	Iterable<K> iterateKey();

	/**
	 * Essa itera��o ir� passar por todos os elementos retornando seu item mapeado.
	 * O item mapeado � composto pela sua chave e o valor do elemento armazenado.
	 * @return aquisi��o de uma itera��o dos itens mapeados.
	 */

	Iterable<MapItem<K, E>> iterateItems();

	/**
	 * <p><h1>Item Mapeado</h1></p>
	 *
	 * <p>Usado para permitir uma itera��o que retorne tanto o elemento quando a chave dos itens armazenados.
	 * A forma como as cole��es s�o feitas, a itera��o deve ser apenas dos elementos, possuindo ainda uma
	 * itera��o apenas para as chaves dos elementos, usando esta para unir as duas itera��es.</p>
	 *
	 * @author Andrew Mello
	 *
	 * @param <K> qual o tipo da chave usada.
	 * @param <E> qual o tipo do elemento usado.
	 */

	public class MapItem<K, E>
	{
		/**
		 * Chave usada para identificar o elemento.
		 */
		private K key;

		/**
		 * Valor armazenado na chave desse item.
		 */
		private E value;

		/**
		 * Constr�i um novo item mapeado sendo necess�rio determinar o seu valor e chave.
		 * @param key chave usada para identificar o elemento no mapeador.
		 * @param value valor armazenado na respectiva chave acima.
		 */

		public MapItem(K key, E value)
		{
			this.key = key;
			this.value = value;
		}

		public K getKey()
		{
			return key;
		}
	
		public E getValue()
		{
			return value;
		}
	
		public void setValue(E value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			ObjectDescription description = new ObjectDescription(getClass());

			description.append("key", key);
			description.append("value", value);

			return description.toString();
		}
	}
}
