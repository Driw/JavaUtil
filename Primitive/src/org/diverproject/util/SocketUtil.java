package org.diverproject.util;

import java.net.Socket;
import java.nio.ByteBuffer;

import org.diverproject.util.lang.ByteUtil;
import org.diverproject.util.lang.IntUtil;

/**
 * <p><h1>Utilit�rio para Socket</h1></p>
 *
 * Classe utilit�ria para trabalhar com objetos do tipo socket.
 *
 * @see Socket
 */

public class SocketUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private SocketUtil()
	{
		
	}

	/**
	 * Verifica se o socket existe e se h� um endere�o de internet no mesmo.
	 * @param socket conex�o socket do qual ser� obtido o endere�o de IP.
	 * @return endere�o de IP da conex�o socket se bem sucedido ou conex�o
	 * n�o definida caso contr�rio "0.0.0.0".
	 */

	public static String socketIP(Socket socket)
	{
		if (socket == null || socket.getInetAddress() == null)
			return "0.0.0.0";

		return socket.getInetAddress().getHostAddress();
	}

	/**
	 * Converte um endere�o IP salvo em um n�mero inteiro para um valor em string.
	 * @param ip endere�o de ip que foi salvo em n�mero inteiro (esperasse).
	 * @return string correspondendo ao valor do IP sendo leg�vel como "x.x.x.x".
	 */

	public static String socketIP(int ip)
	{
		byte[] buffer = ByteBuffer.allocate(4).putInt(ip).array();

		return String.format("%d.%d.%d.%d", ByteUtil.putInt(buffer[0]), ByteUtil.putInt(buffer[1]), ByteUtil.putInt(buffer[3]), ByteUtil.putInt(buffer[3]));
	}

	/**
	 * Verifica se o socket existe e se h� um endere�o de internet no mesmo.
	 * Um IP possui 4 d�gitos que v�o de 0 a 255, sendo poss�vel serem salvos
	 * em um valor num�rico inteiro onde cada valor corresponde a um byte.
	 * @param socket conex�o socket do qual ser� obtido o endere�o de IP.
	 * @return valor num�rico do IP respectivo ao socket ou 0 se inv�lido.
	 */

	public static int socketIPInt(Socket socket)
	{
		if (socket == null || socket.getInetAddress() == null)
			return 0;

		String ip = socketIP(socket);
		String[] split = ip.split("\\.");

		byte[] bytes = new byte[]
		{
				(byte) Integer.parseInt(split[0]),
				(byte) Integer.parseInt(split[1]),
				(byte) Integer.parseInt(split[2]),
				(byte) Integer.parseInt(split[3])
		};

		return ByteBuffer.wrap(bytes).getInt();
	}

	/**
	 * Um IP possui 4 d�gitos que v�o de 0 a 255, sendo poss�vel serem salvos
	 * em um valor num�rico inteiro onde cada valor corresponde a um byte.
	 * @param ipaddress endere�o do ip que deve ser convertido para int.
	 * @return valor num�rico do IP respectivo ao endere�o passado.
	 */

	public static int socketIPInt(String ipaddress)
	{
		String[] split = ipaddress.split("\\.");

		byte[] bytes = new byte[]
		{
				(byte) Integer.parseInt(split[0]),
				(byte) Integer.parseInt(split[1]),
				(byte) Integer.parseInt(split[2]),
				(byte) Integer.parseInt(split[3])
		};

		return ByteBuffer.wrap(bytes).getInt();
	}

	/**
	 * Procedimento usado para verificar se uma determinada string � um endere�o de IP.
	 * @param ipaddress refer�ncia da string contendo o endere�o de IP que ser� verificado.
	 * @return true se for v�lido como endere�o de IP ou false caso contr�rio.
	 */

	public static boolean isIP(String ipaddress)
	{
		String[] split = ipaddress.split("\\.");

		if (split.length != 4)
			return false;

		if (!IntUtil.isInteger(split[0]) || !IntUtil.isInteger(split[1]) ||
			!IntUtil.isInteger(split[2]) || !IntUtil.isInteger(split[3]))
			return false;

		return true;
	}
}
