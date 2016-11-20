package org.diverproject.util.stream.implementation.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Stream de Entrada</h1>
 *
 * <p>Esse tipo de stream ir� usar como base um InputStream que pode ser especificado diretamente.
 * Podendo ainda ser especificado atrav�s de um objeto File representando um arquivo ou o path dele.
 * Caso seja por um arquivo ir� criar esse InputStream para que possa ser usado internamente.</p>
 *
 * @see GenericInput
 * @see InputStream
 *
 * @author Andrew
 */

public class InputStream extends GenericInput
{
	/**
	 * Quantidade de bytes j� escritos.
	 */
	private int offset;

	/**
	 * Limite de dados que poder�o ser lidos.
	 */
	private int length;

	/**
	 * Stream para entrada de dados quando bytes forem lidos.
	 */
	private java.io.InputStream stream;

	/**
	 * Cria uma nova stream atrav�s de uma stream de entrada de dados a partir de um arquivo especifico.
	 * @param path caminho completo ou parcial do arquivo em disco que ser� usado para leitura.
	 * @throws FileNotFoundException apenas se o arquivo n�o for encontrado.
	 */

	public InputStream(String path) throws FileNotFoundException
	{
		this(new File(path));
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de entrada de dados a partir de um arquivo especifico.
	 * @param file refer�ncia do objeto que identifica um arquivo em disco que ser� usado.
	 * @throws FileNotFoundException apenas se o arquivo n�o for encontrado.
	 */

	public InputStream(File file) throws FileNotFoundException
	{
		this(new FileInputStream(file), (int) file.length());
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de entrada de dados pr�-especificada.
	 * Para esse caso n�o ser� considerado qualquer limite de dados para se ler.
	 * @param is refer�ncia da stream que ser� usada para escrever os dados.
	 */

	public InputStream(java.io.InputStream is)
	{
		this(is, 0);
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de entrada de dados pr�-especificada.
	 * @param is refer�ncia da stream que ser� usada para escrever os dados.
	 * @param length quantos bytes podem ser lidos nessa stream (limite),
	 * caso n�o haja limite deve ser especificado com o valor 0 (zero).
	 */

	public InputStream(java.io.InputStream is, int length)
	{
		this.stream = is;
		this.length = length;
	}

	@Override
	public byte getByte()
	{
		return read();
	}

	@Override
	public byte read()
	{
		try {

			if (length > 0 && offset >= length)
				throw new StreamRuntimeException("limite de dados");

			offset++;
			return (byte) stream.read();

		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
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
		throw new UnsupportedOperationException();
	}

	@Override
	public void close()
	{
		try {
			stream.close();
		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}

	@Override
	public void skipe(int bytes)
	{
		try {
			stream.skip(bytes);
		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}

	@Override
	public void reset()
	{
		throw new UnsupportedOperationException();
	}
}
