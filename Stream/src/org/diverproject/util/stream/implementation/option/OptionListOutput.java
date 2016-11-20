package org.diverproject.util.stream.implementation.option;

import java.util.Iterator;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.stream.Input;
import org.diverproject.util.stream.Output;

/**
 * <h1>Entrada de Op��es por Lista</h1>
 *
 * <p>Permite fazer a leitura de um determinado arquivo como listagem de op��es.
 * Esse arquivo ir� carregar todas as op��es contendo seus nomes e valores.
 * Em seguida ser� poss�vel usar os getters para obter esses valores.</p>
 *
 * @see OptionReadByInput
 * @see Input
 *
 * @author Andrew
 */

public class OptionListOutput extends OptionWriteByOutput
{
	/**
	 * Constr�i uma nova entrada para op��es de acordo com um arquivo especificado.
	 * @param output refer�ncia do objeto contendo os bytes que ser�o lidos.
	 */

	public OptionListOutput(Output output)
	{
		super(output);
	}

	/**
	 * Atrav�s de uma itera��o ir� escrever todas as op��es que deste forem iteradas.
	 * @param iteration refer�ncia do objeto iter�vel que ser� escrito.
	 */

	public void write(Iterable<StreamOptionValue<?>> iteration)
	{
		write(iteration.iterator());
	}

	/**
	 * Atrav�s de uma itera��o ir� escrever todas as op��es que deste forem iteradas.
	 * @param iterator refer�ncia do objeto com itera��o que ser� escrito.
	 */

	public void write(Iterator<StreamOptionValue<?>> iterator)
	{
		while (iterator.hasNext())
			write(iterator.next());
	}

	/**
	 * Procedimento interno chamado por cada itera��o que houver de uma op��o encontrada.
	 * @param option refer�ncia do objeto contendo o nome e valor da op��o em quest�o.
	 */

	private void write(StreamOptionValue<?> option)
	{
		if (option.getValue() instanceof Boolean)
			putByte(option.getName(), (Byte) option.getValue());

		else if (option.getValue() instanceof Character)
			putChar(option.getName(), (Character) option.getValue());

		else if (option.getValue() instanceof Short)
			putShort(option.getName(), (Short) option.getValue());

		else if (option.getValue() instanceof Integer)
			putInt(option.getName(), (Integer) option.getValue());

		else if (option.getValue() instanceof Long)
			putLong(option.getName(), (Long) option.getValue());

		else if (option.getValue() instanceof Float)
			putFloat(option.getName(), (Float) option.getValue());

		else if (option.getValue() instanceof Double)
			putDouble(option.getName(), (Double) option.getValue());

		else if (option.getValue() instanceof String)
			putString(option.getName(), (String) option.getValue());

		else if (option.getValue() instanceof Boolean)
			putBoolean(option.getName(), (Boolean) option.getValue());
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		return description.toString();
	}
}
