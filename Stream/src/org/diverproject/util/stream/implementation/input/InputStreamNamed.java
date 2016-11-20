package org.diverproject.util.stream.implementation.input;

import org.diverproject.util.ObjectDescription;

/**
 * <h1>Stream de Entrada Nomeada</h1>
 *
 * <p>Funciona da mesma forma que uma Stream de Entrada.
 * Sua �nica diferen�a � a de possuir um nome vinculado ao mesmo.
 * Isso pode ser usado como forma de identifica��o de aux�lio.</p>
 *
 * @see InputStream
 *
 * @author Andrew Mello
 */

public class InputStreamNamed extends InputStream
{
	/**
	 * Nome da entrada.
	 */
	private String name;

	/**
	 * Cria uma nova stream atrav�s de uma stream de entrada de dados pr�-especificada.
	 * Para esse caso n�o ser� considerado qualquer limite de dados para se ler.
	 * @param name nome que ser� vinculado a essa entrada para um aux�lio na identifica��o.
	 * @param is refer�ncia da stream que ser� usada para escrever os dados.
	 */

	public InputStreamNamed(String name, java.io.InputStream is)
	{
		super(is);

		this.name = name;
	}

	/**
	 * Nome dessa entrada de dados � usada como aux�lio para a identifica��o do mesmo.
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
