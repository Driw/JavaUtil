package org.diverproject.util.stream.implementation.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Sa�da Mapeada</h1>
 *
 * <p>A entrada de dados mapeada � feito atrav�s de um MappedByteBuffer onde os dados s�o obtidos.
 * Objetos desse tipo poder�o ser obtidos atrav�s da especifica��o de um arquivo ou stream.
 * Com a stream do arquivo ser� poss�vel obter o canal do arquivo e deste criar o mapa.</p>
 *
 * @see GenericOutput
 * @see File
 * @see OutputStream
 * @see FileChannel
 * @see MappedByteBuffer
 *
 * @author Andrew Mello
 */

public class OutputMapped extends GenericOutput
{
	/**
	 * Stream da sa�da de dados para libera��o dos dados.
	 */
	private FileOutputStream fos;

	/**
	 * Buffer com bytes mapeados do arquivo que ser� escrito.
	 */
	private MappedByteBuffer map;

	/**
	 * Cria um novo escritor de dados em vetor a partir de um arquivo os os dados ser�o escritos.
	 * @param path caminho completo ou parcial do arquivo do qual ser� escrito por essa sa�da.
	 * @param length quantos bytes dever�o ser poss�veis escrever no mapa para alocar espa�o.
	 * @throws IOException apenas se houver algum problema para iniciar a stream do arquivo.
	 */

	public OutputMapped(String path, long length) throws IOException
	{
		this(new File(path), length);
	}

	/**
	 * Cria um novo escritor de dados em vetor a partir de um arquivo os os dados ser�o escritos.
	 * @param file refer�ncia de um objeto que represente um arquivo em disco no Java.
	 * @param length quantos bytes dever�o ser poss�veis escrever no mapa para alocar espa�o.
	 * @throws IOException apenas se houver algum problema para iniciar a stream do arquivo.
	 */

	public OutputMapped(File file, long length) throws IOException
	{
		this(new FileOutputStream(file), length);
	}

	/**
	 * Cria um novo escritor de dados em vetor a partir de um arquivo os os dados ser�o escritos.
	 * @param fos refer�ncia do objeto que possui a stream para sa�da de dados em um arquivo.
	 * @param length quantos bytes dever�o ser poss�veis escrever no mapa para alocar espa�o.
	 * @throws IOException apenas se houver algum problema para iniciar a stream do arquivo.
	 */

	public OutputMapped(FileOutputStream fos, long length) throws IOException
	{
		this(fos.getChannel().map(FileChannel.MapMode.PRIVATE, 0, length));

		this.fos = fos;
	}

	/**
	 * Cria um novo escritor de dados em vetor a partir de um arquivo os os dados ser�o escritos.
	 * @param mapa refer�ncia do buffer de bytes mapeados que ser� usado para escrever os dados.
	 * @throws IOException apenas se houver algum problema para iniciar a stream do arquivo.
	 */

	public OutputMapped(MappedByteBuffer map)
	{
		this.map = map;
	}

	@Override
	public void flush()
	{
		try {
			fos.flush();
		} catch (IOException e) {
			throw new StreamRuntimeException(e);
		}
	}

	@Override
	public void write(byte b)
	{
		map.put(b);
	}

	@Override
	public int offset()
	{
		return map.position();
	}

	@Override
	public int length()
	{
		return map.capacity();
	}

	@Override
	public boolean isClosed()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void close()
	{
		if (fos != null)
			try {
				fos.close();
			} catch (IOException e) {
				throw new StreamRuntimeException(e);
			}

		throw new UnsupportedOperationException("n�o usou um FileOutputStream");
	}

	@Override
	public void skipe(int bytes)
	{
		map.position(map.position() + bytes);
	}

	@Override
	public void reset()
	{
		map.position(0);
	}
}
