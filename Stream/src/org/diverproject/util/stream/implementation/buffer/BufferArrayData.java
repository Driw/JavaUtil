package org.diverproject.util.stream.implementation.buffer;

import org.diverproject.util.stream.implementation.input.InputByteArray;
import org.diverproject.util.stream.implementation.output.OutputByteArray;

/**
 * <h1>Buffer com Dados Padr�o</h1>
 *
 * <p>Implementa��o � uma extens�o para um Buffer Padr�o, implementando as funcionalidades restantes.
 * Essas funcionalidades s�o implementadas a parte de modo que seja poss�vel usufruir de seus atributos.
 * Isso tamb�m permite que outros tipos de buffers futuros possam ser criados tendo a mesma ideia.</p>
 *
 * <p>As funcionalidades de responsabilidade dessa classe � determinar como � feito a leitura e escrita de
 * um �nico byte (principal procedimento das comunica��es), permitindo atribuir adequadamente os seus
 * atributos que ir�o fazer o controle, tamb�m � aqui que � guardado o vetor com os dados da comunica��o.</p>
 *
 * @see GenericBuffer
 *
 * @author Andrew Mello
 */

public class BufferArrayData extends GenericBuffer
{
	/**
	 * Refer�ncia dos dados (vetor de bytes) usado no buffer.
	 */
	private byte internalBuffer[];

	/**
	 * Cria um novo buffer padr�o sendo necess�rio definir qual ser� o vetor de bytes usado para tal.
	 * O vetor n�o ser� copiado e sim ligado ao buffer estabelecendo assim a comunica��o de dados.
	 * @param data refer�ncia do vetor que ser� usado na comunica��o para sa�da e entrada de dados.
	 */

	public BufferArrayData(byte[] data)
	{
		super(new InputByteArray(data), new OutputByteArray(data));

		internalBuffer = data;
	}

	@Override
	public byte[] getArrayBuffer()
	{
		return internalBuffer.clone();
	}

	@Override
	public void moveTo(int offset)
	{
		int difOffset = offset - offset();

		input.skipe(difOffset);
		output.skipe(difOffset);
	}

	@Override
	public byte read()
	{
		output.skipe(1);

		return input.getByte();
	}

	@Override
	public void write(byte b)
	{
		output.write(b);
		input.skipe(1);
	}

	@Override
	public boolean isClosed()
	{
		return input.isClosed() || output.isClosed();
	}

	@Override
	public void close()
	{
		input.close();
		output.close();

		internalBuffer = null;
		System.gc();
	}

	@Override
	public void skipe(int bytes)
	{
		input.skipe(bytes);
		output.skipe(bytes);
	}

	@Override
	public void reset()
	{
		input.reset();
		output.reset();
	}
}
