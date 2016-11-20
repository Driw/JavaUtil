package org.diverproject.util.collection;

/**
 * <p><h1>Lista</h1></p>
 *
 * <p>As estruturas de dados do tipo lista s�o as mais comuns de serem encontradas.
 * Pois a sua forma de manipular os dados � a que mais encaixa na maioria dos casos.
 * Possuindo um protocolo bem simples para os procedimentos de adicionar e remover.</p>
 *
 * <p>Os novos elementos adicionados em uma lista sempre ser�o adicionados ao fim desta.
 * H� um caso em que � poss�vel adicionar um no meio da lista, por�m � especificado.
 * No caso da remo��o de um item todos os elementos seguintes s�o movidos para tr�s.</p>
 *
 * <p>Uma diferen�a bem importante para se ressaltar de diferen�a dentre essa estrutura,
 * e as outras estruturas, � que esta permite atualizar determinados �ndices da lista.
 * Talvez este seja o ponto mais importante dos mecanismos dispostos em compara��o.</p>
 *
 * @see Collection
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public interface List<E> extends Collection<E>
{
	/**
	 * Adiciona um novo elemento especificado posicionando-o ao final da lista.
	 * @param element refer�ncia do elemento que ser� adicionado � lista.
	 * @return true se conseguir adicionar ou false se estiver cheia.
	 */

	boolean add(E element);

	/**
	 * Remove um elemento atrav�s da especifica��o da sua refer�ncia como objeto.
	 * Quando um elemento � removido todos os seguintes passam para a esquerda.
	 * @param element refer�ncia do objeto elemento que ser� removido da lista.
	 * @return true se encontrar e portanto remover ou false se n�o encontrar.
	 */

	boolean remove(E element);

	/**
	 * Remove um elemento atrav�s do seu �ndice de posicionamento na lista.
	 * Quanto um elemento � removido todos os seguintes passam para a esquerda.
	 * @param index n�mero do �ndice do elemento que ser� removido da lista.
	 * @return true se conseguir remover ou false caso o �ndice seja inv�lido.
	 */

	boolean remove(int index);

	/**
	 * Atualiza um determinado �ndice da lista for�ando a sobrescrita do elemento.
	 * Ou seja, o elemento no �ndice passado ser� substitu�do pelo elemento passado.
	 * @param index n�mero do �ndice na lista que ter� o seu valor substitu�do.
	 * @param element refer�ncia do elemento que ser� inserido nesse �ndice.
	 * @return true se conseguir atualizar ou false caso o �ndice seja inv�lido.
	 */

	boolean update(int index, E element);

	/**
	 * Obt�m um determinado item de dentro da lista a partir do seu �ndice.
	 * @param index n�mero do �ndice do elemento que ser� obtido da lista.
	 * @return aquisi��o do elemento respectivo no �ndice especificado.
	 */

	E get(int index);
}
