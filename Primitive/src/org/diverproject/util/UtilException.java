package org.diverproject.util;

import org.diverproject.util.lang.StringUtil;

/**
 * <p><h1>Exce��o de Utilit�rios</h1></p>
 *
 * <p>Essa exce��o n�o possui nenhuma funcionalidade extra al�m a de permitir mensagem formatada.
 * Utilizado pelas classes utilit�rias ou outras classes do conjunto de bibliotecas utilit�rias.</p>
 *
 * @author Andrew
 */

@SuppressWarnings("serial")
public class UtilException extends Exception
{
	/**
	 * Mensagem que foi gerada quando a exce��o foi criada.
	 */
	private String message;

	/**
	 * Constr�i uma nova exce��o de utilit�rio sendo necess�rio definir uma mensagem.
	 * @param message mensagem que ser� exibida quando a exce��o for gerada.
	 */

	public UtilException(String message)
	{
		this.message = message;
	}

	/**
	 * Constr�i uma nova exce��o de utilit�rio sendo necess�rio definir uma mensagem.
	 * @param format string contendo o formato da mensagem que ser� exibida.
	 * @param args argumentos respectivos a formata��o da mensagem.
	 */

	public UtilException(String format, Object... args)
	{
		this.message = String.format(format, args);
	}

	/**
	 * Constr�i uma nova exce��o de utilit�rio sendo necess�rio definir a exce��o.
	 * Nesse caso ir� construir uma nova exce��o a partir de uma exce��o existente.
	 * Utilizando a mensagem dessa exce��o como mensagem desta.
	 * @param e exce��o do qual ser� considerada para criar uma nova.
	 */

	public UtilException(Exception e)
	{
		if (e.getClass() == this.getClass())
			message = e.getMessage();
		else
			message = StringUtil.addStringException(e.getMessage(), e);

		this.setStackTrace(e.getStackTrace());
	}

	/**
	 * Constr�i uma nova exce��o de utilit�rio sendo necess�rio definir uma mensagem.
	 * Nesse caso a mensagem ser� usada de uma exce��o j� criada, por�m permite adicionar
	 * um determinado conte�do extra como dados que ser� posicionado entre aspas.
	 * @param e exce��o para usar a mensagem armazenada no mesmo como exce��o.
	 * @param format string contendo o formato do conte�do extra.
	 * @param args argumentos respectivos a formata��o da mensagem.
	 */

	public UtilException(Exception e, String format, Object... args)
	{
		message = String.format(format, args);
		message = StringUtil.addStringData(e.getMessage(), message);

		if (e.getClass() != this.getClass())
			message = StringUtil.addStringException(message, this);

		this.setStackTrace(e.getStackTrace());
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());
		description.append(message);

		return description.toString();
	}
}
