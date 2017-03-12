package org.diverproject.util.jzip;

import static org.diverproject.util.lang.IntUtil.limit;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.diverproject.jzip.DataFormatException;
import org.diverproject.jzip.Deflater;
import org.diverproject.jzip.Inflater;
import org.diverproject.util.SizeUtil;

/**
 * A biblioteca de utilit�rios tamb�m permite gerenciar objetos conhecidos pela biblioteca JZIP (diverproject).
 * As duas principais funcionalidades dele utilit�rio � compactar bytes em zip e descompactar bytes "zipados".
 */

public class ZipUtil
{
	/**
	 * Tamanho dos buffers internos que ser�o criados.
	 */
	public static final int BUFFER_SIZE = 1024 * 8;

	/**
	 * Determina se dever� ser exibido uma mensagem dos dados quando compactados ou descompactados.
	 */
	public static boolean LOG = false;

	/**
	 * Procedimento que realiza a compacta��o de um conjunto de bytes especificados no formato ZIP.
	 * � permitido definir o tamanho do buffer e o tipo de compacta��o (influenciam na velocidade).
	 * Neste caso <b>enableException</b> � desativado, ou seja n�o causa exception em caso de falha,
	 * tamb�m � definido um tamanho padr�o para o buffer interno utilizado (<code>BUFFER_SIZE</code>)
	 * e o n�vel de compress�o � o m�ximo dispon�vel para este formato (9, BEST_COMPRESSION).
	 * @param input vetor contendo os bytes do qual deseja compactar no formato ZIP.
	 * @param compressionLevel n�vel de compress�o, influencia na redu��o de bytes (1 a 9).
	 * @param bufferSize tamanho do buffer interno que ser� usado para compactar os dados.
	 * @return retorna o vetor contendo os bytes compactados em ZIP ou null se falhar.
	 */

	public static byte[] zip(byte input[])
	{
		return zip(input, Deflater.BEST_COMPRESSION, BUFFER_SIZE, false);
	}

	/**
	 * Procedimento que realiza a compacta��o de um conjunto de bytes especificados no formato ZIP.
	 * � permitido definir o tamanho do buffer e o tipo de compacta��o (influenciam na velocidade).
	 * Neste caso <b>enableException</b> � desativado, ou seja n�o causa exception em caso de falha,
	 * tamb�m � definido um tamanho padr�o para o buffer interno utilizado (<code>BUFFER_SIZE</code>).
	 * @param input vetor contendo os bytes do qual deseja compactar no formato ZIP.
	 * @param compressionLevel n�vel de compress�o, influencia na redu��o de bytes (1 a 9).
	 * @param bufferSize tamanho do buffer interno que ser� usado para compactar os dados.
	 * @return retorna o vetor contendo os bytes compactados em ZIP ou null se falhar.
	 */

	public static byte[] zip(byte input[], int compressionLevel)
	{
		return zip(input, compressionLevel, BUFFER_SIZE, false);
	}

	/**
	 * Procedimento que realiza a compacta��o de um conjunto de bytes especificados no formato ZIP.
	 * � permitido definir o tamanho do buffer e o tipo de compacta��o (influenciam na velocidade).
	 * Neste caso <b>enableException</b> � desativado, ou seja n�o causa exception em caso de falha.
	 * @param input vetor contendo os bytes do qual deseja compactar no formato ZIP.
	 * @param compressionLevel n�vel de compress�o, influencia na redu��o de bytes (1 a 9).
	 * @param bufferSize tamanho do buffer interno que ser� usado para compactar os dados.
	 * @return retorna o vetor contendo os bytes compactados em ZIP ou null se falhar.
	 */

	public static byte[] zip(byte input[], int compressionLevel, int bufferSize)
	{
		return zip(input, compressionLevel, bufferSize, false);
	}

	/**
	 * Procedimento que realiza a compacta��o de um conjunto de bytes especificados no formato ZIP.
	 * � permitido definir o tamanho do buffer e o tipo de compacta��o (influenciam na velocidade).
	 * @param input vetor contendo os bytes do qual deseja compactar no formato ZIP.
	 * @param compressionLevel n�vel de compress�o, influencia na redu��o de bytes (1 a 9).
	 * @param bufferSize tamanho do buffer interno que ser� usado para compactar os dados.
	 * @param enableException true para causar exception em caso de falha ou false caso contr�rio.
	 * @return retorna o vetor contendo os bytes compactados em ZIP ou null se falhar.
	 */

	public static byte[] zip(byte input[], int compressionLevel, int bufferSize, boolean enableException)
	{
		Deflater deflater = new Deflater(limit(compressionLevel, Deflater.BEST_SPEED, Deflater.BEST_COMPRESSION));
		deflater.setStrategy(Deflater.FILTERED);
		deflater.setInput(input);
		deflater.finish();

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] buffer = new byte[bufferSize];

		while (!deflater.finished())
		{
			int compressed = deflater.deflate(buffer);
			output.write(buffer, 0, compressed);
		}

		try {
			output.close();
		} catch (IOException e) {
			if (enableException)
				throw new RuntimeException(e.getMessage());
		}

		if (LOG)
			System.out.printf("Compactado %s em %s.\n", SizeUtil.toString(deflater.getTotalIn()), SizeUtil.toString(deflater.getTotalOut()));

		return output.toByteArray();
	}

	/**
	 * Procedimento que realiza a descompacta��o de um conjunto de bytes especificados no formato ZIP.
	 * � permitido definir o tamanho do buffer e se este ir� causar exception por m� forma��o.
	 * Neste caso <b>enableException</b> � desativado, ou seja n�o causa exception em caso de falha,
	 * tamb�m � definido um tamanho padr�o para o buffer interno utilizado (<code>BUFFER_SIZE</code>).
	 * @param input vetor contendo os bytes do qual deseja descompactar do formato ZIP.
	 * @return retorna o vetor contendo os bytes descompactados do formato ZIP ou null se falhar.
	 */

	public static byte[] unzip(byte input[])
	{
		return unzip(input, BUFFER_SIZE, false);
	}

	/**
	 * Procedimento que realiza a descompacta��o de um conjunto de bytes especificados no formato ZIP.
	 * � permitido definir o tamanho do buffer e se este ir� causar exception por m� forma��o.
	 * Neste caso <b>enableException</b> � desativado, ou seja n�o causa exception em caso de falha.
	 * @param input vetor contendo os bytes do qual deseja descompactar do formato ZIP.
	 * @param bufferSize tamanho do buffer interno que ser� usado para descompactar os dados.
	 * @return retorna o vetor contendo os bytes descompactados do formato ZIP ou null se falhar.
	 */

	public static byte[] unzip(byte input[], int bufferSize)
	{
		return unzip(input, bufferSize, false);
	}

	/**
	 * Procedimento que realiza a descompacta��o de um conjunto de bytes especificados no formato ZIP.
	 * � permitido definir o tamanho do buffer e se este ir� causar exception por m� forma��o.
	 * @param input vetor contendo os bytes do qual deseja descompactar do formato ZIP.
	 * @param bufferSize tamanho do buffer interno que ser� usado para descompactar os dados.
	 * @param enableException true para causar exception em caso de falha ou false caso contr�rio.
	 * @return retorna o vetor contendo os bytes descompactados do formato ZIP ou null se falhar.
	 */

	public static byte[] unzip(byte input[], int bufferSize, boolean enableException)
	{
		Inflater inflater = new Inflater();
		inflater.setInput(input);

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte buffer[] = new byte[bufferSize];

		try {

			while (!inflater.finished())
			{
				int decompress = inflater.inflate(buffer);
				output.write(buffer, 0, decompress);
			}

			output.close();

		} catch (DataFormatException | IOException e) {
			if (enableException)
				throw new RuntimeException(e.getMessage());
			return null;
		}

		if (LOG)
			System.out.printf("Descompactado %s em %s.\n", SizeUtil.toString(inflater.getTotalIn()), SizeUtil.toString(inflater.getTotalOut()));

		return output.toByteArray();
	}
}
