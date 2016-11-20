package org.diverproject.util.stream.implementation.option;

import org.diverproject.util.stream.Input;
import org.diverproject.util.stream.StreamException;
import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Op��es Lidas por Input</h1>
 *
 * <p>Classe com implementa��o b�sica para se ler op��es armazenadas em bytes de um input.
 * Os dados s�o lidos e se tenta criar op��es a partir destes e os despacha em seguida.
 * O despache � feito atrav�s de um m�todo abstrato que ser� por onde as op��es ser�o vistas.</p>
 *
 * @see OptionInput
 * @see Input
 *
 * @author Andrew
 */

public abstract class OptionReadByInput implements OptionInput
{
	/**
	 * Cria um novo leitor de op��es atrav�s de um objeto com entrada de dados em bytes.
	 * @param input refer�ncia da stream que ir� conter os dados das op��es a ser lidas.
	 */

	public OptionReadByInput(Input input)
	{
		while (input.space() > 0)
		{
			int offset = input.offset();
			StreamOptionValue<?> value = readNext(input);

			if (value == null)
				throw new StreamRuntimeException("op��o inv�lida (offset: %d)", offset);

			dispatch(value);
		}
	}

	/**
	 * Procedimento chamado internamente pelo construtor sempre que uma nova op��o � encontrada.
	 * Todas op��es lidas do input passado por par�metro no construtor ser� despachado aqui.
	 * @param option refer�ncia do objeto contendo as informa��es da op��o que foi lida.
	 */

	protected abstract void dispatch(StreamOptionValue<?> option);

	/**
	 * Deve fazer a leitura da pr�xima op��o na stream de entrada de dados do arquivo especificado.
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto que ir� armazenar o nome da op��o e o seu valor ou null se for inv�lido.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<?> readNext(Input input)
	{
		switch (input.getByte())
		{
			case OPTION_BYTE: return readOptionByte(input);
			case OPTION_CHAR: return readOptionChar(input);
			case OPTION_SHORT: return readOptionShort(input);
			case OPTION_INT: return readOptionInt(input);
			case OPTION_LONG: return readOptionLong(input);
			case OPTION_FLOAT: return readOptionFloat(input);
			case OPTION_DOUBLE: return readOptionDouble(input);
			case OPTION_STRING: return readOptionString(input);
			case OPTION_BOOLEAN: return readOptionBoolean(input);
		}

		return null;
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo byte contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Byte> readOptionByte(Input input)
	{
		return new StreamOptionValue<Byte>(input.getString(), input.getByte());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo char contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Character> readOptionChar(Input input)
	{
		return new StreamOptionValue<Character>(input.getString(), input.getChar());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo short contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Short> readOptionShort(Input input)
	{
		return new StreamOptionValue<Short>(input.getString(), input.getShort());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo int contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Integer> readOptionInt(Input input)
	{
		return new StreamOptionValue<Integer>(input.getString(), input.getInt());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo long contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Long> readOptionLong(Input input)
	{
		return new StreamOptionValue<Long>(input.getString(), input.getLong());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo float contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Float> readOptionFloat(Input input)
	{
		return new StreamOptionValue<Float>(input.getString(), input.getFloat());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo double contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Double> readOptionDouble(Input input)
	{
		return new StreamOptionValue<Double>(input.getString(), input.getDouble());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo string contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<String> readOptionString(Input input)
	{
		return new StreamOptionValue<String>(input.getString(), input.getString());
	}

	/**
	 * Deve fazer a leitura da pr�xima op��o dispon�vel no arquivo (stream para entrada).
	 * @param input refer�ncia da stream para entrada de dados do arquivo especificado.
	 * @return objeto op��o do tipo boolean contendo o seu valor respectivo.
	 * @throws StreamException arquivo inexistente, ser um diret�rio ou falha na leitura.
	 */

	private StreamOptionValue<Boolean> readOptionBoolean(Input input)
	{
		return new StreamOptionValue<Boolean>(input.getString(), input.getByte() == 1);
	}
}
