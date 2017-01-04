package org.diverproject.log;

/**
 * <h1>Registro</h1>
 *
 * <p>Classe criada internamente no servi�o para guardar as informa��es de um registro gerado.
 * Registros contem um tipo e uma mensagem que � sua informa��o ou ocorrido a ser registrado.</p>
 *
 * <p>Al�m disso atrav�s dele � poss�vel saber a origem do mesmo atrav�s de um StackTraceElement.
 * Nele podemos saber qual a linha e arquivo que cont�m a chamada tal como o nome do m�todo
 * onde se encontra o chamado, podendo ainda se saber o nome da classe que pode divergir do arquivo.</p>
 *
 * @see StackTraceElement
 *
 * @author Andrew
 */

public class Log
{
	/**
	 * Tipo de mensagem que est� sendo gerada.
	 */
	private String type;

	/**
	 * Mensagem contendo as informa��es/ocorrido a ser registrado.
	 */
	private String message;

	/**
	 * Objeto que ir� permitir localizar a origem de onde foi feito o registro da mensagem.
	 */
	private Throwable throwable;

	/**
	 * Refer�ncia do Trace contendo informa��es da origem da chamada.
	 */
	private StackTraceElement stackTraceElement;

	/**
	 * Construtor em visibilidade package para evitar inst�ncias fora do projeto.
	 * Essa classe deve ser gerada exclusivamente pelo projeto conform necess�rio.
	 */

	Log(Throwable throwable)
	{
		this.throwable = throwable;
	}

	/**
	 * O tipo da mensagem pode ser de um padr�o pr�-definido pelo sistema de registro.
	 * Mas pode ainda ser o nome de uma classe quando for o caso de uma Exception.
	 * @return aquisi��o do tipo de mensagem que ser� gerada pelo registro.
	 */

	public String getType()
	{
		return type;
	}

	/**
	 * @return aquisi��o do objeto que identifica a origem do registro.
	 */

	public Throwable getThrowable()
	{
		return throwable;
	}

	/**
	 * O tipo da mensagem pode ser de um padr�o pr�-definido pelo sistema de registro.
	 * Mas pode ainda ser o nome de uma classe quando for o caso de uma Exception.
	 * @param type tipo de mensagem que dever� ser gerada pelo registro.
	 */

	void setType(String type)
	{
		this.type = type;
	}

	/**
	 * Um registro possui uma mensagem referente a uma informa��o ou ocorrido.
	 * Essa mensagem possui um conte�do valioso ao projeto por isso ser� registrado.
	 * @return aquisi��o da mensagem do qual dever� ser registrada.
	 */

	public String getMessage()
	{
		return message;
	}

	/**
	 * M�todo com vis�o de pacote para evitar que seja alterada por fora.
	 * Permite definir uma mensagem ao registro, definindo um ocorrido/informa��o.
	 * @param message refer�ncia da string contendo a mensagem a ser registrada.
	 */

	void setMessage(String message)
	{
		this.message = message;
	}

	/**
	 * Permite definir um objeto que ir� conter informa��es da origem da chamada do log.
	 * Atrav�s desse m�todo � poss�vel saber o nome do arquivo, classe, m�todo e linha.
	 * @param stackTraceElement refer�ncia do trace obtido de um throwable.
	 */

	void setStackTrace(StackTraceElement stackTraceElement)
	{
		this.stackTraceElement = stackTraceElement;
	}

	/**
	 * Atrav�s de um StrackTraceElement ser� poss�vel saber onde o m�todo log foi chamado.
	 * Aqui ser� poss�vel saber o nome do arquivo que possui a classe que chamou o log.
	 * @return aquisi��o do nome do arquivo que cont�m a classe do chamado.
	 */

	public String getFileName()
	{
		return stackTraceElement.getFileName();
	}

	/**
	 * Atrav�s de um StrackTraceElement ser� poss�vel saber onde o m�todo log foi chamado.
	 * Aqui ser� poss�vel saber o nome da classe que possui o m�todo que chamou o log.
	 * @return aquisi��o do nome da classe que gerou a mensagem.
	 */

	public String getClassName()
	{
		String name = stackTraceElement.getClassName();

		return name.substring(name.lastIndexOf('.') + 1);
	}

	/**
	 * Atrav�s de um StrackTraceElement ser� poss�vel saber onde o m�todo log foi chamado.
	 * Aqui ser� poss�vel saber o nome do m�todo em uma classe que chamou o log.
	 * @return nome do m�todo em uma classe que gerou a mensagem.
	 */

	public String getMethodName()
	{
		return stackTraceElement.getMethodName();
	}

	/**
	 * Atrav�s de um StrackTraceElement ser� poss�vel saber onde o m�todo log foi chamado.
	 * Aqui ser� poss�vel saber o n�mero da linha do arquivo JAVA do chamado log.
	 * @return aquisi��o do n�mero da linha de uma arquivo que gerou a mensagem.
	 */

	public int getLineNumber()
	{
		return stackTraceElement.getLineNumber();
	}

	@Override
	public String toString()
	{
		if (type != null)
			return String.format("[%s] %s.%s: %s", getType(), getClassName(), getMethodName(), getMessage());

		return String.format("%s.%s: %s", getClassName(), getMethodName(), getMessage());
	}
}
