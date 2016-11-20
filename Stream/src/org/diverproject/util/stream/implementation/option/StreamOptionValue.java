package org.diverproject.util.stream.implementation.option;

import org.diverproject.util.ObjectDescription;

/**
 * <p><h1>Op��o em Stream</h1></p>
 *
 * <p>Objetos desse tipo s�o criados para que possam salvar dados de op��es para stream.
 * Quando uma stream de entrada � criada, esses objetos podem ser adquiridos da stream.
 * De modo que seja poss�vel obter op��es carregadas ao inv�s de sequenciais.</p>
 *
 * @author Andrew
 *
 * @param <E> tipo de objeto que ser� armazenado.
 */

public class StreamOptionValue<E>
{
	/**
	 * Nome que foi dado a essa op��o.
	 */
	private String name;

	/**
	 * Refer�ncia do valor que foi dado a essa op��o.
	 */
	private E value;

	/**
	 * Constr�i uma nova op��o onde deve ser definido o seu nome e valor.
	 * @param name nome da op��o � utilizado para localiz�-la na stream.
	 * @param value valor da op��o � respectivo ao valor definido ao mesmo.
	 */

	public StreamOptionValue(String name, E value)
	{
		this.name = name;
		this.value = value;
	}

	/**
	 * Utilizado para que possa ser localizado na stream.
	 * @return aquisi��o do nome da a��o carregada da stream.
	 */

	public String getName()
	{
		return this.name;
	}

	/**
	 * Utilizado para salvar o valor na stream ou us�-lo na aplica��o.
	 * @return aquisi��o do valor carregado da stream dessa op��o.
	 */

	public E getValue()
	{
		return value;
	}

	/**
	 * Permite definir um novo valor para que essa op��o passe a assumir.
	 * @param value novo valor do qual a preferencia deve considerar.
	 */

	public void setValue(E value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		description.append("name", name);
		description.append("value", value);
		description.append("type", value != null ? value.getClass() : null);

		return description.toString();
	}
}
