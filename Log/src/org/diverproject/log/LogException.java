package org.diverproject.log;

import org.diverproject.util.UtilRuntimeException;

/**
 * <h1>Exce��o de Registro</h1>
 *
 * <p>Esse tipo Exception ocorre sempre que houver um problema no gerenciamento
 * dos registros, quando bem programado e est�tico for os processamentos, menor a
 * chance desse tipo de exce��o ocorrer. Utilizado por toda a projeto de Log.</p>
 */

public class LogException extends UtilRuntimeException
{
	/**
	 * N�mero da serializa��o dessa classe.
	 */
	private static final long serialVersionUID = 5518392083117147768L;

	/**
	 * Constr�i uma nova exce��o para gerenciamento de registros.
	 * @param message mensagem informando o que desencadeou a exception.
	 */

	public LogException(String message)
	{
		super(message);
	}

	/**
	 * Constr�i uma nova exce��o para gerenciamento de registros.
	 * Nesse caso considera a mensagem de uma exce��o.
	 * Al�m disso mostra o nome da exception (classe).
	 * @param e exce��o do qual est� sendo gerada.
	 */

	public LogException(Exception e)
	{
		super(e);
	}

	/**
	 * Constr�i uma nova exce��o para gerenciamento de registros.
	 * @param format formato que ter� a mensagem informativa.
	 * @param args argumentos que ir� completar o formato da mensagem.
	 */

	public LogException(String format, Object... args)
	{
		super(format, args);
	}

	/**
	 * Constr�i uma nova exce��o para gerenciamento de registros.
	 * Nesse caso a mensagem ser� usada de uma exce��o j� criada, por�m permite adicionar
	 * um determinado conte�do extra como dados que ser� posicionado entre aspas.
	 * @param e exce��o para usar a mensagem armazenada no mesmo como exce��o.
	 * @param format string contendo o formato do conte�do extra.
	 * @param args argumentos respectivos a formata��o da mensagem.
	 */

	public LogException(Exception e, String format, Object... args)
	{
		super(e, format, args);
	}
}
