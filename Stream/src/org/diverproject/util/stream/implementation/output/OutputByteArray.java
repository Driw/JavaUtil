package org.diverproject.util.stream.implementation.output;

import org.diverproject.util.ObjectDescription;
import org.diverproject.util.lang.ByteUtil;
import org.diverproject.util.stream.StreamRuntimeException;
import org.diverproject.util.stream.implementation.input.InputByteArray;

/**
 * <h1>Sa�da de Dados com Vetor</h1>
 *
 * <p>Implementa��o b�sica das funcionalidades de um escritor utilizando comunica��o com <i>dados em vetor</i>.
 * Trabalhar com dados em vetor, � usar um vetor especificado no construtor como fonte da comunica��o feita.
 * Por exemplo, comunica��es com arquivos escreve seus bytes consecutivamente sem pulos como fluxo de dados.
 * Nesse caso a fonte de fluxo ser� esse vetor de bytes especificado e este ser� usado diretamente ou copiado.</p>
 *
 * @see GenericOutput
 *
 * @author Andrew Mello
 */

public class OutputByteArray extends GenericOutput
{
	/**
	 * Posicionamento do ponteiro no buffer quando dados forem escritos.
	 */
	private int offset;

	/**
	 * Quantidade de bytes que comp�e essa sa�da de dados.
	 */
	private int length;

	/**
	 * Posicionamento do ponteiro quando dados foram liberados.
	 */
	private int flushOffset;

	/**
	 * Vetor onde os dados escritos ser�o liberados.
	 */
	private byte flushed[];

	/**
	 * Vetor onde os dados escritos ser�o armazenados.
	 */
	private byte internalBuffer[];

	/**
	 * Cria um novo escritor de dados em vetor a partir de um vetor os os dados ser�o escritos.
	 * @param length quantidade de bytes que poder�o ser escritos dentro desse buffer.
	 */

	public OutputByteArray(int length)
	{
		this.internalBuffer = new byte[length];
	}

	/**
	 * Cria um novo escritor de dados em vetor a partir de um vetor os os dados ser�o escritos.
	 * Para esse caso os bytes ser�o vinculados a sa�da de dados e n�o ser�o clonados (padr�o).
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para escrita.
	 */

	public OutputByteArray(byte[] data)
	{
		this(data, false);
	}

	/**
	 * Cria um novo escritor de dados em vetor a partir de um vetor os os dados ser�o escritos.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para escrita.
	 * @param copy permite definir se o vetor ser� clonado dentro da stream.
	 */

	public OutputByteArray(byte[] data, boolean copy)
	{
		if (copy)
			this.internalBuffer = data.clone();
		else
			this.internalBuffer = data;

		this.length = data.length;
	}

	@Override
	public void flush()
	{
		if (offset() <= flushOffset)
			throw new StreamRuntimeException("n�o h� dados para liberar");

		flushed = ByteUtil.subarray(internalBuffer, flushOffset, offset());
		flushOffset = offset();
	}

	@Override
	public void write(byte b)
	{
		internalBuffer[offset++] = b;
	}

	@Override
	public int offset()
	{
		return offset;
	}

	@Override
	public int length()
	{
		return length;
	}

	@Override
	public boolean isClosed()
	{
		return internalBuffer == null;
	}

	@Override
	public void close()
	{
		internalBuffer = null;
		System.gc();
	}

	@Override
	public void skipe(int bytes)
	{
		if (space() >= bytes)
			offset += bytes;
	}

	@Override
	public void reset()
	{
		offset = 0;
		flushOffset = 0;
		flushed = null;
	}

	/**
	 * O vetor flushed � criado somente quando o m�todo flush � usado afim de liberar dados.
	 * Por ser um buffer e n�o um stream, a libera��o de dados � copiar os dados em um vetor.
	 * @return aquisi��o do vetor que contem os bytes que foram liberados do buffer.
	 */

	public byte[] getFlushedArray()
	{
		if (flushed == null)
			throw new StreamRuntimeException("sem dados liberados");

		return flushed;
	}

	/**
	 * O vetor flushed � criado somente quando o m�todo flush � usado afim de liberar dados.
	 * Por ser um buffer e n�o um stream, a libera��o de dados � copiar os dados em um vetor.
	 * @return aquisi��o de um novo buffer criado utilizando os dados liberados no flush.
	 */

	public InputByteArray newFlushedInput()
	{
		if (flushed == null)
			throw new StreamRuntimeException("sem dados liberados");

		return new InputByteArray(flushed, true);
	}

	@Override
	protected void toString(ObjectDescription description)
	{
		description.append("flushOffset", flushOffset);
		description.append("flushed", flushed != null);

		super.toString();
	}
}
