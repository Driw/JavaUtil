package org.diverproject.util.collection;

/**
 * <p><h1>Pilha</h1></p>
 *
 * <p>Pilhas s�o utilizadas quando os elementos interagidos sempre s�o o do topo.
 * Utilizando o procedimento de empilhar elementos ou desempilhar elementos.</p>
 *
 * <p>Quanto utilizado a empilhagem ser� o de inserir um novo elemento na pilha.
 * Em quanto a desempilhagem ser� o de remover o elemento no topo da pilha.
 * De modo que seja seja sempre trabalhado a quest�o dos primeiros elementos.</p>
 *
 * <p>Outra caracter�stica da pilha � que o procedimento de remover (desempilhar),
 * tamb�m � ao mesmo tempo o procedimento para sele��o de um elemento na pilha.
 * Assim sendo apenas o �ltimo elemento dessa pilha poder� ser selecionado.</p>
 *
 * <p>Mesmo que ele trabalhe apenas com o �ltimo elemento da pilha (adicionar/remover),
 * ao mesmo tempo ele tamb�m � uma cole��o (estrutura de dados) como outra qualquer.
 * Portanto � poss�vel utilizar a itera��o para percorrer os elementos desta.</p>
 *
 * @see Collection
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public interface Stack<E> extends Collection<E>
{
	/**
	 * Empilha um determinado elemento especificado no topo dessa pilha.
	 * @param element refer�ncia do objeto elemento que ser� empilhado.
	 * @return true se conseguir empilhar ou false se estiver cheio.
	 */

	boolean push(E element);

	/**
	 * Desempilha o �ltimo elemento empilhado na pilha (elemento do topo).
	 * Ao fazer isso tamb�m ir� remover o mesmo da pilha se assim houver.
	 * @return aquisi��o do elemento no topo da pilha ou null se vazia.
	 */

	E pop();
}
