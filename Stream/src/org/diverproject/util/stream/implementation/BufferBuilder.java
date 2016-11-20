package org.diverproject.util.stream.implementation;

import org.diverproject.util.stream.Buffer;
import org.diverproject.util.stream.implementation.buffer.BufferArrayData;

/**
 * <h1>Construtor de Buffer</h1>
 *
 * <p>Criado para funcionar como meio intermedi�rio entre a cria��o da comunica��o e a f�brica.
 * Possuindo apenas os m�todos que por nome a par�metros � poss�vel entender sua funcionalidade.
 * Assim, ser� poss�vel "esconder" a real origem das comunica��es que foram usadas por ele.</p>
 *
 * <p>Interface que possui todos os m�todos poss�veis para a cria��o de comunica��o de dados.
 * A �nica forma de se criar um buffer � atrav�s de um vetor de bytes permitindo escrita e leitura.
 * A forma como � feito a escrita dos dados ser� respectiva de acordo com a f�brica que gerou este.</p>
 *
 * @see Buffer
 *
 * @author Andrew Mello
 */

public class BufferBuilder
{
	/**
	 * Cria uma nova comunica��o buffer a partir de um vetor de bytes para entrada e sa�da de dados.
	 * @param data refer�ncia do vetor que ser� usado tanto para a leitura quando escrita dos dados.
	 * @return aquisi��o de uma nova comunica��o buffer usando o vetor especificado.
	 */

	public Buffer newBuffer(byte[] data)
	{
		return new BufferArrayData(data);
	}
}
