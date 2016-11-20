package org.diverproject.util.stream.implementation.output;

import java.io.IOException;
import java.io.OutputStream;

import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Pacote de Sa�da</h1>
 *
 * <p>Funciona da mesma forma que uma Sa�da de Dados com Vetor Nomeada.
 * Neste caso permite usar um OutputStream para enviar os dados por flush.</p>
 *
 * @see OutputByteArrayNamed
 *
 * @author Andrew Mello
 */

public class OutputPacket extends OutputByteArrayNamed
{
	/**
	 * Stream para sa�da de dados alvo.
	 */
	private OutputStream flushTarget;

	/**
	 * Cria um novo escritor de dados em vetor a partir de um vetor os os dados ser�o escritos.
	 * Para esse caso os bytes ser�o vinculados a sa�da de dados e n�o ser�o clonados (padr�o).
	 * @param name nome que ser� vinculado a essa sa�da para um aux�lio na identifica��o.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para escrita.
	 */

	public OutputPacket(String name, byte[] data)
	{
		super(name, data);
	}

	/**
	 * Cria um novo escritor de dados em vetor a partir de um vetor os os dados ser�o escritos.
	 * @param name nome que ser� vinculado a essa sa�da para um aux�lio na identifica��o.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para escrita.
	 * @param copy permite definir se o vetor ser� clonado dentro da stream.
	 */

	public OutputPacket(String name, byte[] data, boolean copy)
	{
		super(name, data, copy);
	}

	/**
	 * Permite definir uma stream para sa�da de dados quando essa sa�da usar flush.
	 * O m�todo flush ir� funcionar de forma natural com ou sem essa defini��o.
	 * Essa funcionalidade � extra e permite que os dados daqui passe para o target.
	 * @param flushTarget stream target que ir� receber os dados ap�s o flush.
	 */

	public void setFlushTarget(OutputStream flushTarget)
	{
		this.flushTarget = flushTarget;
	}

	@Override
	public void flush()
	{
		super.flush();

		byte flushed[] = getFlushedArray();

		if (flushTarget != null)
		{
			try {

				flushTarget.write(flushed);
				flushTarget.flush();

			} catch (IOException e) {
				throw new StreamRuntimeException(e.getMessage());
			}
		}
	}
}
