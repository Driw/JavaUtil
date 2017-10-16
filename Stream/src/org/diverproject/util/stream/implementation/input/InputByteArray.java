package org.diverproject.util.stream.implementation.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Entrada de Dados com Vetor</h1>
 *
 * <p>Implementa��o b�sica das funcionalidades de um leitor utilizando comunica��o com <i>dados em vetor</i>.
 * Trabalhar com dados em vetor, � usar um vetor especificado no construtor como fonte da comunica��o feita.
 * Por exemplo, comunica��es com arquivos escreve seus bytes consecutivamente sem pulos como fluxo de dados.
 * Nesse caso a fonte de fluxo ser� esse vetor de bytes especificado e este ser� usado diretamente ou copiado.</p>
 *
 * @see GenericInput
 *
 * @author Andrew Mello
 */

public class InputByteArray extends GenericInput
{
	/**
	 * Posi��o do ponteiro para leitura de dados.
	 */
	private int offset;

	/**
	 * Quantidade de bytes que comp�e essa entrada de dados.
	 */
	private int length;

	/**
	 * Vetor de dados (bytes) do qual ser� feito a leitura.
	 */
	private byte[] data;

	/**
	 * Cria um novo leitor de dados em vetor a partir de um vetor de dados para ser feito a leitura.
	 * Para esse caso os bytes ser�o vinculados a entrada de dados e n�o ser�o clonados (padr�o).
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para leitura.
	 */

	public InputByteArray(byte[] data)
	{
		this(data, false);
	}

	/**
	 * Cria um novo leitor de dados padr�o a partir de um vetor de dados para ser feito a leitura.
	 * @param data refer�ncia do vetor de bytes que ser� considerado como dados para leitura.
	 * @param copy permite definir se o vetor ser� clonado dentro da stream.
	 */

	public InputByteArray(byte[] data, boolean copy)
	{
		if (copy)
			this.data = data.clone();
		else
			this.data = data;

		this.length = data.length;
	}

	/**
	 * Cria um novo leitor de dados padr�o a partir de um vetor com os bytes lidos de um arquivo.
	 * @param file refer�ncia do objeto para localizar o arquivo em disco.
	 */

	public InputByteArray(File file)
	{
		try {

			FileInputStream fis = new FileInputStream(file);
			{
				fis.read((data = new byte[fis.available()]));
				length = data.length;
			}
			fis.close();

		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}

	/**
	 * Cria um novo leitor de dados padr�o a partir de uma stream para entrada de dados.
	 * Ap�s a leitura dos dados a stream <b>N�O SER� FECHADA</b> ap�s ler todos os dados.
	 * @param fis refer�ncia da stream para entrada de dados que ser� considerada.
	 */

	public InputByteArray(FileInputStream fis)
	{
		try {

			fis.read((data = new byte[fis.available()]));
			length = data.length;

		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}

	@Override
	public byte getByte()
	{
		if (space() > 0)
			return data[offset++];

		throw new StreamRuntimeException("fim do buffer");
	}

	@Override
	public byte read()
	{
		return getByte();
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
		return data == null;
	}

	@Override
	public void close()
	{
		data = null;
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
	}
}
