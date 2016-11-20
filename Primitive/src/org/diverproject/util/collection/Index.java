package org.diverproject.util.collection;

/**
 * <p><h1>�ndice</h1></p>
 *
 * <p>As estruturas de dados do tipo vetor s�o bastante semelhantes a mapas.
 * Pois posiciona os seus elementos de acordo com o �ndice especificado.
 * De modo que seja um vetor como outro qualquer por�m com um controle.</p>
 *
 * <p>Os novos elementos adicionados sempre ir�o precisar de um �ndice para alocar.
 * Quando um determinado �ndice estiver ocupado esse elemento n�o poder� ser inserido.
 * A aloca��o dos elementos no vetor pode ser feito de diferentes formas.</p>
 *
 * <p>Podendo utilizar tabelas espalhadas quando houver uma varia��o grande por�m
 * poucos elementos para serem inseridos ou ent�o a utiliza��o de n�s duplos.
 * Mas o comum e a utiliza��o de vetores com ordena��o por �ndice ou o pr�prio �ndice.</p>
 *
 * @see Collection
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public interface Index<E> extends Collection<E>
{
	/**
	 * Adiciona um novo elemento especificado posicionando-o ao final da lista.
	 * @param index posicionamento do elemento na lista por prioridade.
	 * @param element refer�ncia do elemento que ser� adicionado � lista.
	 * @return true se conseguir adicionar ou false se estiver cheia.
	 */

	boolean add(int index, E element);

	/**
	 * Remove um elemento atrav�s da especifica��o da sua refer�ncia como objeto.
	 * Quanto um elemento � removido todos os seguintes passam para a esquerda.
	 * @param element refer�ncia do objeto elemento que ser� removido da lista.
	 * @return true se encontrar e portanto remover ou false se n�o encontrar.
	 */

	boolean remove(E element);

	/**
	 * Remove um elemento atrav�s da sua prioridade de posicionamento na lista.
	 * Quando um elemento � removido todos os seguintes passam para a esquerda.
	 * @param index n�mero do �ndice do elemento que ser� removido da lista.
	 * @return true se conseguir remover ou false caso o �ndice seja inv�lido.
	 */

	boolean remove(int index);

	/**
	 * Atualiza um determinado �ndice da lista for�ando a sobrescrita do elemento.
	 * Ou seja, o elemento no �ndice passado ser� substitu�do pelo elemento passado.
	 * @param index n�mero do �ndice da posi��o que ter� o seu valor substitu�do.
	 * @param element refer�ncia do elemento que ser� inserido nesse �ndice.
	 * @return true se conseguir atualizar ou false caso o �ndice seja inv�lido.
	 */

	boolean update(int index, E element);

	/**
	 * Troca dois elementos em determinadas prioridades especificadas.
	 * @param first �ndice respectiva ao primeiro elemento da troca.
	 * @param second �ndice respectiva ao segundo elemento da troca.
	 * @return true quando for trocado ou false caso haja falha,
	 * falha pode ser prioridade inv�lida ou ent�o n�o usada.
	 */

	boolean change(int first, int second);

	/**
	 * Obt�m um determinado item de dentro da lista a partir do seu �ndice.
	 * @param index n�mero do �ndice do elemento que ser� obtido da lista.
	 * @return aquisi��o do elemento respectivo no �ndice especificado.
	 */

	E get(int index);
}
