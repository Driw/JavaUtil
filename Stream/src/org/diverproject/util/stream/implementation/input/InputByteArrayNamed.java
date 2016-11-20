package org.diverproject.util.stream.implementation.input;

import org.diverproject.util.ObjectDescription;

/**
 * <h1>Entrada de Dados com Vetor Nomeada</h1>
 *
 * <p>Funciona da mesma forma que uma Entrada de Dados com Vetor.
 * Sua �nica diferen�a � a de possuir um nome vinculado ao mesmo.
 * Isso pode ser usado como forma de identifica��o de aux�lio.</p>
 *
 * @see InputByteArray
 *
 * @author Andrew Mello
 */

public class InputByteArrayNamed extends InputByteArray
{
	/**
	 * Nome da entrada.
	 */
	private String name;

	/**
	 * Cria um novo leitor de dados em vetor a partir de um vetor de dados para ser feito a leitura.
	 * Para esse caso os bytes ser�o vinculados a entrada de dados e n�o ser�o clonados (padr�o).
	 * @param name nome que ser� vinculado a essa entrada para um aux�lio na identifica��o.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para leitura.
	 */

	public InputByteArrayNamed(String name, byte data[])
	{
		this(name, data, false);
	}

	/**
	 * Cria um novo leitor de dados padr�o a partir de um vetor de dados para ser feito a leitura.
	 * @param name nome que ser� vinculado a essa entrada para um aux�lio na identifica��o.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para leitura.
	 * @param copy permite definir se o vetor ser� clonado dentro da stream.
	 */

	public InputByteArrayNamed(String name, byte[] data, boolean copy)
	{
		super(data, copy);

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
