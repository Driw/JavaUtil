package org.diverproject.util.stream.implementation.output;

import org.diverproject.util.ObjectDescription;

/**
 * <h1>Stream de Sa�da Nomeada</h1>
 *
 * <p>Funciona da mesma forma que uma Stream de Sa�da.
 * Sua �nica diferen�a � a de possuir um nome vinculado ao mesmo.
 * Isso pode ser usado como forma de identifica��o de aux�lio.</p>
 *
 * @see OutputStream
 *
 * @author Andrew Mello
 */

public class OutputStreamNamed extends OutputStream
{
	/**
	 * Nome da sa�da.
	 */
	private String name;

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados pr�-especificada.
	 * Para esse caso n�o ser� considerado qualquer limite de dados para se escrever.
	 * @param name nome que ser� vinculado a essa sa�da para um aux�lio na identifica��o.
	 * @param os refer�ncia da stream que ser� usada para escrever os dados.
	 */

	public OutputStreamNamed(String name, java.io.OutputStream os)
	{
		super(os);

		this.name = name;
	}

	/**
	 * Nome dessa sa�da de dados � usada como aux�lio para a identifica��o do mesmo.
	 * Pode ser usada por exemplo por um pacote de dados afim de nome�-lo.
	 * @return aquisi��o do nome dessa entrada de dados.
	 */

	public String getName()
	{
		return name;
	}

	@Override
	protected void toString(ObjectDescription description)
	{
		description.append(name);

		super.toString(description);
	}
}
