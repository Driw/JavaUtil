package org.diverproject.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.UIManager;

import org.diverproject.util.collection.ArrayUtil;

/**
 * <p><h1>Utilit�rios do Sistema</h1></p>
 *
 * @author Andrew
 */

public class SystemUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private SystemUtil()
	{
		
	}

	/**
	 * Deve definir toda a interface das aplica��es java como o mesmo estilo do Windows.
	 * Isso tem efeito sobre componentes como JButton, JTextField, JScrollBar e outros.
	 */

	public static void setWindowsInterface()
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			MessageUtil.showException(e);
		}
	}

	/**
	 * Deve definir toda a interface das aplica��es java como o mesmo estilo do Nimbus.
	 * Isso tem efeito sobre componentes como JButton, JTextField, JScrollBar e outros.
	 */

	public static void setNimbusInterface()
	{
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			MessageUtil.showException(e);
		}
	}

	/**
	 * Mem�ria usada � o quanto da mem�ria RAM est� sendo utilizado pela aplica��o.
	 * @return aquisi��o da quantidade em bytes total de mem�ria usada.
	 */

	public static long getUsageMemory()
	{
		return getAllocatedMemory() - getFreeMemory();
	}

	/**
	 * Mem�ria livre � o quanto da mem�ria RAM reservado para aplica��o ainda est� livre.
	 * @return aquisi��o da quantidade em bytes total de mem�ria livre.
	 */

	public static long getFreeMemory()
	{
		return Runtime.getRuntime().freeMemory();
	}

	/**
	 * Mem�ria alocada � o quanto de mem�ria RAM reservado par aplica��o foi definido.
	 * @return aquisi��o da quantidade em bytes total de mem�ria alocada.
	 */

	public static long getAllocatedMemory()
	{
		return Runtime.getRuntime().totalMemory();
	}

	/**
	 * Mem�ria m�xima � o quanto a JVM permite que a aplica��o use da mem�ria RAM.
	 * @return aquisi��o da quantidade em bytes total de mem�ria m�xima.
	 */

	public static long getMaxMemory()
	{
		return Runtime.getRuntime().maxMemory();
	}

	/**
	 * Mem�ria livre � o quanto ainda pode ser usado da mem�ria RAM para essa aplica��o.
	 * @return aquisi��o da quantidade em bytes total de mem�ria dispon�vel.
	 */

	public static long getTotalFreeMemory()
	{
		return getFreeMemory() + (getMaxMemory() - getAllocatedMemory());
	}

	/**
	 * Calcula a quantidade de mem�ria usada e formata para o maior byte poss�vel.
	 * @return aquisi��o da quantidade em bytes total de mem�ria usada.
	 */

	public static String getUsageMemoryString()
	{
		return SizeUtil.toString(getUsageMemory());
	}

	/**
	 * Calcula a quantidade de mem�ria livre e formata para o maior byte poss�vel.
	 * @return aquisi��o da quantidade em bytes total de mem�ria livre.
	 */

	public static String getFreeMemoryString()
	{
		return SizeUtil.toString(getFreeMemory());
	}

	/**
	 * Calcula a quantidade de mem�ria alocada e formata para o maior byte poss�vel.
	 * @return aquisi��o da quantidade em bytes total de mem�ria alocada.
	 */

	public static String getAllocatedMemoryString()
	{
		return SizeUtil.toString(getAllocatedMemory());
	}

	/**
	 * Calcula a quantidade de mem�ria m�xima e formata para o maior byte poss�vel.
	 * @return aquisi��o da quantidade em bytes total de mem�ria m�xima.
	 */

	public static String getMaxMemoryString()
	{
		return SizeUtil.toString(getMaxMemory());
	}

	/**
	 * Calcula a quantidade de mem�ria dispon�vel e formata para o maior byte poss�vel.
	 * @return aquisi��o da quantidade em bytes total de mem�ria dispon�vel.
	 */

	public static String getTotalFreeMemoryString()
	{
		return SizeUtil.toString(getTotalFreeMemory());
	}

	/**
	 * Imprimi no console em um total de quatro linhas os estados atuais de uso de mem�ria.
	 */

	public static void printMemoryUsage()
	{
		System.out.printf("Mem�ria Usada: %s de %s\n", getUsageMemoryString(), getAllocatedMemoryString());
	}

	/**
	 * Imprimi no console em um total de quatro linhas os estados atuais de uso de mem�ria.
	 */

	public static void printMemoryAvaiable()
	{
		System.out.printf("Mem�ria Dispon�vel: %s de %s\n", getTotalFreeMemoryString(), getMaxMemoryString());
	}

	/**
	 * Imprimi no console em um total de quatro linhas os estados atuais de uso de mem�ria.
	 */

	public static void printMemoryStatus()
	{
		printMemoryUsage();
		printMemoryAvaiable();
	}

	/**
	 * Permite obter o endere�o MAC da placa de rede utilizada no localhost, considerando o seguinte formato:
	 * <b>FF-FF-FF-FF-FF-FF</b>, o outro getMacAddress() permite determinar o separador que aqui � <b>-</b>.
	 * @return aquisi��o do endere�o MAC do localhost no formato especificado acima.
	 * @throws SocketException ocorre se n�o conseguir criar uma conex�o com o localhost.
	 * @throws UnknownHostException ocorre se o localhost n�o for encontrado.
	 */

	public static String getMacAddress() throws SocketException, UnknownHostException
	{
		return getMacAddress("-");
	}

	/**
	 * Permite obter o endere�o MAC da placa de rede utilizada no localhost, considerando o seguinte formato:
	 * <b>FF-FF-FF-FF-FF-FF</b>, isso considerando que o separador seja definido como <b>-</b> (padr�o).
	 * @param separetor string com o separador que ir� ser incrementado entre os valores hexadecimais.
	 * @return aquisi��o do endere�o MAC do localhost no formato especificado acima.
	 * @throws SocketException ocorre se n�o conseguir criar uma conex�o com o localhost.
	 * @throws UnknownHostException ocorre se o localhost n�o for encontrado.
	 */

	public static String getMacAddress(String separetor) throws SocketException, UnknownHostException
	{
		NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

		return ArrayUtil.join(ni.getHardwareAddress(), "-", true);
	}
}
