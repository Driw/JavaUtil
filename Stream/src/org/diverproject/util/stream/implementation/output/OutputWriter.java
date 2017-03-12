package org.diverproject.util.stream.implementation.output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.diverproject.util.lang.IntUtil;
import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Sa�da com Writer</h1>
 *
 * <p>Esse tipo de stream ir� usar como base um Writer|BufferedWriter que pode ser especificado diretamente.
 * Podendo ainda ser especificado atrav�s de um objeto File representando um arquivo ou o path dele.
 * Caso seja por um arquivo ir� criar esse OutputStream para que possa ser usado internamente.</p>
 *
 * @see GenericOutput
 * @see File
 * @see BufferedWriter
 *
 * @author Andrew
 */

public class OutputWriter extends GenericOutput
{
	/**
	 * Quantidade de bytes j� escritos.
	 */
	private int offset;

	/**
	 * Stream para sa�da de dados quando bytes forem escritos.
	 */
	private BufferedWriter writer;

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados em um arquivo especifico.
	 * @param path caminho completo ou parcial do arquivo em disco que ser� usado para escrita.
	 * @throws IOException quando n�o for poss�vel estabelecer a stream de escrita.
	 */

	public OutputWriter(String path) throws IOException
	{
		this(new File(path));
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados em um arquivo especifico.
	 * @param file refer�ncia do objeto que identifica um arquivo em disco que ser� usado.
	 * @throws IOException quando n�o for poss�vel estabelecer a stream de escrita.
	 */

	public OutputWriter(File file) throws IOException
	{
		this(new FileWriter(file));
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados pr�-especificada.
	 * Para esse caso n�o ser� considerado qualquer limite de dados para se escrever.
	 * @param fw refer�ncia da stream que ser� usada para escrever os dados.
	 */

	public OutputWriter(FileWriter fw)
	{
		this(new BufferedWriter(fw));
	}

	/**
	 * Cria uma nova stream atrav�s de uma stream de sa�da de dados pr�-especificada.
	 * @param bw refer�ncia da stream que ser� usada para escrever os dados.
	 * caso n�o haja limite deve ser especificado com o valor 0 (zero).
	 */

	public OutputWriter(BufferedWriter bw)
	{
		this.writer = bw;
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

			offset++;
			writer.write(IntUtil.parseByte(b));

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
		return offset;
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
			writer.close();
			writer = null;
		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}

	@Override
	public void skipe(int bytes)
	{
		try {
			writer.write(new char[bytes]);
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
			writer.flush();
		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}
}
