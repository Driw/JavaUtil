package org.diverproject.util.stream;

import org.diverproject.util.UtilException;

/**
 * <p><h1>Exce��o de Streams</h1></p>
 *
 * <p>Esse tipo Exception ocorre sempre que houver um problema no gerenciamento
 * das streams, quando bem programado e est�tico for os processamentos, menor a
 * chance desse tipo de exce��o ocorrer. Utilizado por toda a package.</p>
 */

public class StreamException extends UtilException
{
	/**
	 * N�mero da serializa��o dessa classe.
	 */
	private static final long serialVersionUID = 5518392083117147768L;

	/**
	 * Constr�i uma nova exce��o para gerenciamento de stream.
	 * @param message mensagem informando o que desencadeou a exception.
	 */

	public StreamException(String message)
	{
		super(message);
	}

	/**
	 * Constr�i uma nova exce��o para gerenciamento de stream.
	 * Nesse caso considera a mensagem de uma exce��o.
	 * Al�m disso mostra o nome da exception (classe).
	 * @param e exce��o do qual est� sendo gerada.
	 */

	public StreamException(Exception e)
	{
		super(e);
	}

	/**
	 * Constr�i uma nova exce��o para gerenciamento de stream.
	 * @param format formato que ter� a mensagem informativa.
	 * @param args argumentos que ir� completar o formato da mensagem.
	 */

	public StreamException(String format, Object... args)
	{
		super(format, args);
	}

	/**
	 * Constr�i uma nova exce��o para gerenciamento de stream.
	 * Nesse caso a mensagem ser� usada de uma exce��o j� criada, por�m permite adicionar
	 * um determinado conte�do extra como dados que ser� posicionado entre aspas.
	 * @param e exce��o para usar a mensagem armazenada no mesmo como exce��o.
	 * @param format string contendo o formato do conte�do extra.
	 * @param args argumentos respectivos a formata��o da mensagem.
	 */

	public StreamException(Exception e, String format, Object... args)
	{
		super(e, format, args);
	}
}
