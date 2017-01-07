package org.diverproject.util.stream.implementation;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import org.diverproject.util.stream.Input;
import org.diverproject.util.stream.Output;
import org.diverproject.util.stream.StreamException;
import org.diverproject.util.stream.StreamRuntimeException;
import org.diverproject.util.stream.implementation.input.InputStreamNamed;
import org.diverproject.util.stream.implementation.output.OutputPacket;
import org.diverproject.util.stream.implementation.output.OutputStreamNamed;
import org.diverproject.util.stream.implementation.input.InputByteArrayNamed;

/**
 * <h1>Criador de Pacotes</h1>
 *
 * <p>Permite criar objetos de Input e Output conforme suas prefer�ncias para um Socket.
 * Tanto Input e Output poder�o criar pacotes de tamanho est�ticos ou din�micos.
 * Quando est�tico define um tamanho fixo e din�mico pode ler/escrever o quanto quiser.</p>
 *
 * @see Socket
 * @see Input
 * @see Output
 *
 * @author Andrew Mello
 */

public class PacketBuilder
{
	/**
	 * Refer�ncia da conex�o socket que ser� usada.
	 */
	private Socket socket;

	/**
	 * Inicia o Criador de Pacotes atrav�s de uma conex�o socket estabelecida.
	 * Atrav�s dessa conex�o ser� poss�vel criar os Input e Output necess�rios.
	 * @param socket refer�ncia da conex�o socket que ir� originar os pacotes.
	 */

	public PacketBuilder(Socket socket)
	{
		if (socket == null)
			throw new StreamRuntimeException("socket nulo");

		if (!socket.isConnected())
			throw new StreamRuntimeException("socket desconectado");

		this.socket = socket;
	}

	/**
	 * Para este caso ir� instanciar um novo pacote com tamanho din�mico.
	 * Em quanto houver dados para ler ser� poss�vel obter dados do pacote.
	 * @param name nome que ser� dado ao pacote para reconhec�-lo.
	 * @return aquisi��o do objeto que ir� receber os dados desse pacote.
	 * @throws StreamException conex�o foi fechada inesperadamente.
	 */

	public Input newInputPacket(String name) throws StreamException
	{
		try {

			while (isConnected())
			{
				InputStream stream = socket.getInputStream();

				if (stream.available() > 0)
					return new InputStreamNamed(name, socket.getInputStream());
			}

			throw new StreamException("socket encerrado inesperadamente");

		} catch (IOException e) {
			throw new StreamException(e.getMessage());
		}
	}

	/**
	 * Para este caso ir� instanciar um novo pacote com tamanho fixo.
	 * Ir� carregar todos os bytes do socket para um buffer tempor�rio.
	 * Os dados a serem lidos ser�o obtidos desse buffer tempor�rio.
	 * @param name nome que ser� dado ao pacote para reconhec�-lo.
	 * @param length quantos bytes o pacote possui para serem lidos.
	 * @return aquisi��o do objeto que ir� receber os dados desse pacote.
	 * @throws StreamException conex�o foi fechada inesperadamente.
	 */

	public Input newInputPacket(String name, int length) throws StreamException
	{
		if (length < 0)
			throw new StreamException("tamanho inv�lido");

		try {

			while (isConnected())
			{
				InputStream stream = socket.getInputStream();

				if (stream.available() > 0)
				{
					if (length == -1)
						length = stream.available();

					if (stream.available() >= length)
					{
						byte data[] = new byte[length];

						if (stream.read(data) != length)
							throw new StreamException("falta de dados inesperada");

						return new InputByteArrayNamed(name, data);
					}
				}
			}

			throw new StreamException("socket encerrado inesperadamente");

		} catch (IOException e) {
			throw new StreamException(e.getMessage());
		}
	}

	/**
	 * Para este caso cria um novo pacote com tamanho din�mico.
	 * Em quanto os dados n�o forem enviados novos dados poder�o ser escritos.
	 * @param name nome que ser� dado ao pacote para reconhec�-lo.
	 * @return aquisi��o do objeto que ir� enviar os dados desse pacote.
	 * @throws StreamException conex�o foi fechada inesperadamente.
	 */

	public Output newOutputPacket(String name) throws StreamException
	{
		try {

			if (isConnected())
				return new OutputStreamNamed(name, socket.getOutputStream());

			throw new StreamException("socket encerrado inesperadamente");

		} catch (IOException e) {
			throw new StreamException(e.getMessage());
		}
	}

	/**
	 * Para este caso cria um novo pacote com tamanho fixo.
	 * Ir� criar um buffer tempor�rio interno para alocar os dados.
	 * Os dados em buffer ser�o enviados quando assim for solicitado.
	 * @param name nome que ser� dado ao pacote para reconhec�-lo.
	 * @param length tamanho do buffer interno para alocar os dados.
	 * @return aquisi��o do objeto que ir� enviar os dados desse pacote.
	 * @throws StreamException conex�o foi fechada inesperadamente.
	 */

	public Output newOutputPacket(String name, int length) throws StreamException
	{
		if (length < 0)
			throw new StreamException("tamanho inv�lido");

		if (length == 0)
			length = 1024;

		byte data[] = new byte[length];

		try {

			OutputPacket output = new OutputPacket(name, data);
			output.setFlushTarget(socket.getOutputStream());

			return output;

		} catch (IOException e) {
			throw new StreamException(e.getMessage());
		}
	}

	/**
	 * Procedimento que permite verificar se ainda h� uma conex�o socket.
	 * @return true se houver ou false caso contr�rio.
	 */

	public boolean isConnected()
	{
		return socket != null && socket.isConnected();
	}
}
