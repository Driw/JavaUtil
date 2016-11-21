package org.diverproject.util.stream.implementation.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Stream de Sa�da</h1>
 *
 * <p>Esse tipo de stream ir� usar como base um OutputStream que pode ser especificado diretamente.
 * Podendo ainda ser especificado atrav�s de um objeto File representando um arquivo ou o path dele.
 * Caso seja por um arquivo ir� criar esse OutputStream para que possa ser usado internamente.</p>
 *
 * @see GenericOutput
 * @see OutputStream
 *
 * @author Andrew
 */

public class OutputStream extends GenericOutput
{
	/**
	 * Quantidade de bytes j� escritos.
	 */
	private int offset;

	/**
	 * Limite de dados que poder�o ser escritos.
	 */
	private int length;

	/**
	 * Stream para sa�da de dados quando bytes forem escritos.
	 */
	private java.io.OutputStream stream;

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados em um arquivo especifico.
	 * @param path caminho completo ou parcial do arquivo em disco que ser� usado para escrita.
	 * @throws FileNotFoundException apenas se o arquivo n�o for encontrado.
	 */

	public OutputStream(String path) throws FileNotFoundException
	{
		this(new File(path));
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados em um arquivo especifico.
	 * @param file refer�ncia do objeto que identifica um arquivo em disco que ser� usado.
	 * @throws FileNotFoundException apenas se o arquivo n�o for encontrado.
	 */

	public OutputStream(File file) throws FileNotFoundException
	{
		this(new FileOutputStream(file), (int) file.length());
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados pr�-especificada.
	 * Para esse caso n�o ser� considerado qualquer limite de dados para se escrever.
	 * @param os refer�ncia da stream que ser� usada para escrever os dados.
	 */

	public OutputStream(java.io.OutputStream os)
	{
		this(os, 0);
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados pr�-especificada.
	 * @param os refer�ncia da stream que ser� usada para escrever os dados.
	 * @param length quantos bytes podem ser escritos nessa stream (limite),
	 * caso n�o haja limite deve ser especificado com o valor 0 (zero).
	 */

	public OutputStream(java.io.OutputStream os, int length)
	{
		this.stream = os;
		this.length = length;
	}

	@Override
	public void putByte(byte b)
	{
		write(b);
	}

	@Override
	public void write(byte b)
	{
		try {

			if (length > 0 && offset >= length)
				throw new StreamRuntimeException("limite de dados");

			offset++;
			stream.write(b);

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
			offset += bytes;
			stream.write(new byte[bytes]);
		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}

	@Override
	public void reset()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void flush()
	{
		try {
			stream.flush();
		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}
}
