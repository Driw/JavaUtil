package org.diverproject.util.stream.implementation;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.stream.Stream;

/**
 * <h1>Stream Gen�rica</h1>
 *
 * <p>Classe para implementa��o b�sica das funcionalidades de um stream ainda que seja pouca coisa.
 * Por existir diversos tipos de stream tanto para entrada quanto sa�da a implementa��o � simples.
 * Ter� definido um atributo para verificar se dados primitivos ser�o invertidos e o espa�o dispon�vel.</p>
 *
 * @see Stream
 *
 * @author Andrew
 */

public abstract class GenericStream implements Stream
{
	/**
	 * Quando habilitado defini que os bytes ser�o escritos ao contr�rio para os dados primitivos.
	 */
	private boolean inverted;

	@Override
	public int space()
	{
		return length() - offset();
	}

	@Override
	public boolean isEmpty()
	{
		return space() == 0;
	}

	@Override
	public boolean isInverted()
	{
		return inverted;
	}

	@Override
	public void setInvert(boolean enable)
	{
		inverted = enable;
	}

	/**
	 * M�todo interno usado para complementar o toString dos objetos.
	 * @param description refer�ncia da descri��o desse objeto.
	 */

	protected void toString(ObjectDescription description)
	{
		description.append("offset", offset());
		description.append("length", length());
		description.append("space", space());
		description.append("closed", isClosed());
		description.append("inverted", isInverted());
	}

	@Override
	public String toString()
	{
		ObjectDescription description = new ObjectDescription(getClass());

		toString(description);

		return description.toString();
	}
}
