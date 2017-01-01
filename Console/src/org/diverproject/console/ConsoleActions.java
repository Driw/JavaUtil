package org.diverproject.console;

/**
 * <h1>A��es do Console</h1>
 *
 * <p>Interface usada pelo console para permitir que uma escuta do console possa acess�-lo.
 * Esse acesso � limitado a algumas opera��es aqui especificadas que o console implementa.
 * As a��es consistem em permitir limpar a tela do console e trabalhar as mensagens dele.</p>
 *
 * @author Andrew
 */

public interface ConsoleActions
{
	/**
	 * Limpa a tela do console removendo todos os caracteres que j� foram exibidos no mesmo.
	 */

	void clear();

	/**
	 * Permite habilitar a utiliza��o de trace durante a exibi��o das mensagens no console.
	 * Trace ir� exibir o nome da classe que est� solicitando a exibi��o da mensagem.
	 * @param enable true para habilitar o uso do trace ou false caso contr�rio.
	 */

	void setTrace(boolean enable);

	/**
	 * Define uma mensagem para que esta seja exibida no console ap�s o fim da chamada.
	 * @param format formato do qual deve possuir a mensagem a ser exibida.
	 * @param args argumentos referentes a formata��o se assim houver.
	 */

	void setMessage(String format, Object... args);

	/**
	 * Usar esse m�todo far� com que o console use uma quebra de linha ao final da mensagem.
	 */

	void breakLine();
}
