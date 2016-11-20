package org.diverproject.util.collection;

/**
 * <p><h1>Fila</h1></p>
 *
 * <p>Fias s�o utilizadas sempre que os primeiros elementos adicionados,
 * ser�o os primeiros a serem selecionados tamb�m como de fato � uma fila.</p>
 *
 * <p>Conforme os elementos forem sendo adicionados v�o sendo posicionados ao
 * fim da fila, sendo necess�rio esperar que os primeiros sejam selecionados.
 * Quando estes forem selecionados os �ltimos come�am a passar para frente.</p>
 *
 * <p>Como forma de pensamento para a utiliza��o de uma fila, devemos pensar que os
 * elementos que forem adicionados a esta dever�o esperar uma sequ�ncia para que
 * possam ser selecionado, tendo de esperar os elementos a frente serem chamados.</p>
 *
 * @see Collection
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public interface Queue<E> extends Collection<E>
{
	/**
	 * Deve enfileirar um novo elemento na fila de acordo com suas especifica��es.
	 * @param element refer�ncia do elemento que ser� enfileirado.
	 * @return true se consegui enfileirar ou false caso contr�rio.
	 */

	boolean offer(E element);

	/**
	 * Ir� analisar a fila e buscar o elemento mais adequado para ser retirado.
	 * @return aquisi��o do elemento mais antigo na fila.
	 */

	E poll();
}
