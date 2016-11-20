package org.diverproject.util.stream.implementation.output;

import org.diverproject.util.ObjectDescription;

/**
 * <h1>Sa�da de Dados com Vetor Nomeada</h1>
 *
 * <p>Funciona da mesma forma que uma Sa�da de Dados com Vetor.
 * Sua �nica diferen�a � a de possuir um nome vinculado ao mesmo.
 * Isso pode ser usado como forma de identifica��o de aux�lio.</p>
 *
 * @see OutputByteArray
 *
 * @author Andrew Mello
 */

public class OutputByteArrayNamed extends OutputByteArray
{
	/**
	 * Nome da sa�da.
	 */
	private String name;

	/**
	 * Cria um novo escritor de dados em vetor a partir de um vetor os os dados ser�o escritos.
	 * Para esse caso os bytes ser�o vinculados a sa�da de dados e n�o ser�o clonados (padr�o).
	 * @param name nome que ser� vinculado a essa sa�da para um aux�lio na identifica��o.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para escrita.
	 */

	public OutputByteArrayNamed(String name, byte[] data)
	{
		this(name, data, false);
	}

	/**
	 * Cria um novo escritor de dados em vetor a partir de um vetor os os dados ser�o escritos.
	 * @param name nome que ser� vinculado a essa sa�da para um aux�lio na identifica��o.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para escrita.
	 * @param copy permite definir se o vetor ser� clonado dentro da stream.
	 */

	public OutputByteArrayNamed(String name, byte[] data, boolean copy)
	{
		super(data, copy);

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
