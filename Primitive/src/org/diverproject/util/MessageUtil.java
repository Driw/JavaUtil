package org.diverproject.util;

import java.awt.Component;

import javax.swing.JOptionPane;

/**
 * <p><h1>Utilit�rio para Exibir Mensagem</h1></p>
 *
 * <p>M�todos que permitem exibir caixas de di�logos da biblioteca JOptionPane.
 * Estruturado apenas para definir t�tulo e mensagem, o �cone que ser� exibido
 * depende do m�todo do qual est� sendo chamado, aviso, informativo ou outro.</p>
 *
 * <p>Tamb�m permite definir uma vari�vel p�blica e est�tica para determinar qual
 * o componente que deve ser utilizado para ser anexado ao di�logo que deve ser
 * exibido. Quando alterado todos di�logos seguintes ir�o utilizar este.</p>
 *
 * @see JOptionPane
 */

public class MessageUtil
{
	/**
	 * Componente que deve ser anexado aos di�logos seguintes.
	 */
	public static Component MESSAGE_UTIL_COMPONENT;

	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private MessageUtil()
	{
		
	}

	/**
	 * Exibe uma caixa d di�logo do JOptionPane com o �cone "erro"
	 * exibindo o tipo de exce��o ocorrido e a mensagem da exce��o.
	 * @param e exce��o que dever� ser exibida no di�logo.
	 */

	public static void showException(Exception e)
	{
		e.printStackTrace();
		JOptionPane.showMessageDialog(MESSAGE_UTIL_COMPONENT, e.getMessage(), e.getClass().getSimpleName(), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Exibe uma caixa de di�logo do JOptionPane com um �cone "informa��o".
	 * @param title t�tulo do di�logo que ser� aberto.
	 * @param message mensagem que ser� exibida no di�logo.
	 * @param args argumento referenciados pela formata��o especificada no formato de message.
	 */

	public static void showInfo(String title, String message, Object... args)
	{
		JOptionPane.showMessageDialog(MESSAGE_UTIL_COMPONENT, String.format(message, args), title, JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Exibe uma caixa de di�logo do JOptionPane com um �cone "aviso".
	 * @param title t�tulo do di�logo que ser� aberto.
	 * @param message mensagem que ser� exibida no di�logo.
	 * @param args argumento referenciados pela formata��o especificada no formato de message.
	 */

	public static void showWarning(String title, String message, Object... o)
	{
		JOptionPane.showMessageDialog(MESSAGE_UTIL_COMPONENT, String.format(message, o), title, JOptionPane.WARNING_MESSAGE);
	}

	/**
	 * Exibe uma caixa de di�logo do JOptionPane com um �cone "erro".
	 * @param title t�tulo do di�logo que ser� aberto.
	 * @param message mensagem que ser� exibida no di�logo.
	 * @param args argumento referenciados pela formata��o especificada no formato de message.
	 */

	public static void showError(String title, String message, Object... o)
	{
		JOptionPane.showMessageDialog(MESSAGE_UTIL_COMPONENT, String.format(message, o), title, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Exibe uma caixa de di�logo do JOptionPane com duas op��es ('ok' e 'cancelar').
	 * @param title t�tulo do di�logo que ser� aberto.
	 * @param message mensagem que ser� exibida no di�logo.
	 * @param args argumento referenciados pela formata��o especificada no formato de message.
	 * @return true caso seja pressionado OK ou false se pressionado CANCELAR.
	 */

	public static boolean showYesNo(String title, String message, Object... o)
	{
		return JOptionPane.showConfirmDialog(MESSAGE_UTIL_COMPONENT, String.format(message, o), title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	}
	/**
	 * Exibe uma caixa de di�logo do JOptionPane para entrada de dados.
	 * @param title t�tulo do di�logo que ser� aberto.
	 * @param message mensagem que ser� exibida no di�logo.
	 * @param args argumento referenciados pela formata��o especificada no formato de message.
	 * @return valor inserido no campo de texto exibido.
	 */

	public static String showInput(String title, String message, Object... o)
	{
		return JOptionPane.showInputDialog(MESSAGE_UTIL_COMPONENT, String.format(message, o), title, JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Exibe uma caixa de di�logo do JOptionPane para exibir uma exce��o fatal.
	 * Nesse caso ap�s exibir a mensagem o sistema ser� encerrado automaticamente.
	 * @param e exce��o do qual geral o t�rmino da aplica��o.
	 */

	public static void die(Exception e)
	{
		showException(e);
		System.exit(0);
	}
}
