package org.diverproject.util.stream.implementation.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import org.diverproject.util.stream.StreamRuntimeException;

/**
 * <h1>Entrada Mapeada</h1>
 *
 * <p>A entrada de dados mapeada � feito atrav�s de um MappedByteBuffer onde os dados s�o obtidos.
 * Objetos desse tipo poder�o ser obtidos atrav�s da especifica��o de um arquivo ou stream.
 * Com a stream do arquivo ser� poss�vel obter o canal do arquivo e deste criar o mapa.</p>
 *
 * @see GenericInput
 * @see File
 * @see InputStream
 * @see FileChannel
 * @see MappedByteBuffer
 *
 * @author Andrew Mello
 */

public class InputMapped extends GenericInput
{
	/**
	 * Stream da entrada de dados para fechar se houver.
	 */
	private FileInputStream fis;

	/**
	 * Refer�ncia do mapa contendo os bytes que poder�o ser lidos nesse input.
	 */
	private MappedByteBuffer map;

	/**
	 * Cria uma nova estrutura de stream para entrada de dados no sistema atrav�s de um arquivo.
	 * @param path string contendo o caminho parcial ou completo do arquivo que ser� lido.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public InputMapped(String path) throws IOException
	{
		this(new File(path));
	}

	/**
	 * Cria uma nova estrutura de stream para entrada de dados no sistema atrav�s de um arquivo.
	 * @param file refer�ncia do objeto contendo o caminho parcial ou completo do arquivo.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public InputMapped(File file) throws IOException
	{
		this(new FileInputStream(file));
	}

	/**
	 * Cria uma nova estrutura de stream para entrada de dados no sistema atrav�s de um FileInputStream.
	 * @param fis refer�ncia da stream de entrada de dados gerados a partir de um arquivo em disco.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public InputMapped(FileInputStream fis) throws IOException
	{
		this(fis.getChannel());

		this.fis = fis;
	}

	/**
	 * Cria uma nova estrutura de stream para entrada de dados no sistema atrav�s de um canal de arquivo.
	 * Esse canal pode ser obtido quando um InputStream � instanciado a partir de um arquivo.
	 * @param channel refer�ncia do canal do arquivo que cont�m os bytes para o input.
	 * @throws IOException ocorre apenas se houver algum problema durante a leitura.
	 */

	public InputMapped(FileChannel channel) throws IOException
	{
		this(channel.map(FileChannel.MapMode.READ_ONLY, channel.position(), channel.size()));
	}

	/**
	 * Cria uma nova estrutura de stream para entrada de dados no sistema trav�s de um mapa.
	 * Esse mapa pode ser obtido atrav�s de um FileChannel de um InputStream criado.
	 * @param map refer�ncia do mapa que cont�m os bytes que poder�o ser usados como input.
	 */

	public InputMapped(MappedByteBuffer map)
	{
		this.map = map;
	}

	@Override
	public byte getByte()
	{
		return map.get();
	}

	@Override
	public byte read()
	{
		return map.get();
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
		return !map.hasRemaining();
	}

	@Override
	public void close()
	{
		if (fis != null)
		{
			try {
				fis.close();
				fis = null;
			} catch (IOException e) {
				throw new StreamRuntimeException(e);
			}
		}

		map.position(length());
	}

	@Override
	public void skipe(int bytes)
	{
		map.position(offset() + bytes);
	}

	@Override
	public void reset()
	{
		map.position(0);
	}
}
