package org.diverproject.console;

/**
 * <h1>Escuta para Console</h1>
 *
 * <p>Esse tipo de escuta � chamado a todo momento em que algo for digitado e confirmado no Console.
 * Ir� chamar o �nico m�todo que a escuta implementa e para este ser� passado a entrada do console.
 * Nesse m�todo cada aplica��o dever� especificar como os comandos ser�o identificados e aplicados.</p>
 *
 * @author Andrew
 *
 */

public interface ConsoleListener
{
	/**
	 * Procedimento �nico da escuta que ser� desencadeado no momento em que o console receber o enter.
	 * @param text texto que foi entrado no campo de texto do console para executar comandos.
	 * @param actions refer�ncia da interface que permite interagir com o console.
	 */

	void trigger(String text, ConsoleActions actions);
}
