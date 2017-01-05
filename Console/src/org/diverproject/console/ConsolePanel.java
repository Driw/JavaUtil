package org.diverproject.console;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import org.diverproject.util.UtilRuntimeException;
import org.diverproject.util.collection.StringExtend;

/**
 * <h1>Painel para Console</h1>
 *
 * <p>Esse painel � constitu�do por um painel de texto (JTextPane) que ir� possuir as mensagens registradas em log.
 * Ser� usado este componente devido a possibilidade de estiliza��o conforme o tipo de mensagem (colora��o).
 * Por padr�o o console poder� exibir apenas um painel deste por vez, sendo poss�vel adicionar outros para troca.</p>
 *
 * @author Andrew
 */

@SuppressWarnings("serial")
public class ConsolePanel extends JTextPane implements ConsoleActions
{
	/**
	 * Fonte padr�o que ser� usada para exibi��o dos textos no console.
	 */
	public static final Font DEFAULT_FONT = new Font("Courier New", Font.PLAIN, 12);

	/**
	 * Cor padr�o que ser� usada para exibi��o dos textos no console.
	 */
	public static final Color DEFAULT_COLOR = Color.LIGHT_GRAY;


	/**
	 * Documento referente ao painel de texto para estilizar (colorir).
	 */
	private Document document;

	/**
	 * Estiliza��o do painel de texto que foi usado.
	 */
	private Style style;

	/**
	 * Utilizar quebra de linha ap�s exibir a mensagem do comando.
	 */
	private boolean breakLine;

	/**
	 * Mostrar o nome da classe que executou as opera��es do comando.
	 */
	private boolean trace;

	/**
	 * Mensagem que ser� exibida ap�s o final da execu��o do comando.
	 */
	private String message;

	/**
	 * Determina se uma mensagem foi imprimida no painel de console.
	 */
	private boolean printed;

	/**
	 * Cria uma nova inst�ncia de um painel para console que � encarregado de exibir as mensagens no console.
	 * Atrav�s deste painel � que poder� ser especificado as mensagens e como elas dever�o ser exibidas.
	 */

	public ConsolePanel()
	{
		style = addStyle("default", style);
		document = getDocument();

		setOpaque(false);
		setEditable(false);
		setFont(DEFAULT_FONT);
		setColor(Color.WHITE);
	}

	@Override
	public boolean getScrollableTracksViewportWidth()
	{
		return true;
	}

	@Override
	public void clear()
	{
		try {
			document.remove(0, document.getLength());
		} catch (BadLocationException e) {
			throw new UtilRuntimeException("falha ao limpar console");
		}
	}

	@Override
	public void setTrace(boolean enable)
	{
		trace = enable;
	}

	@Override
	public void breakLine()
	{
		breakLine = true;
	}

	@Override
	public void setMessage(String format, Object... args)
	{
		String caller = getClass().getSimpleName();
		String message = String.format(format, args);

		if (trace)
		{
			Throwable throwable = new Throwable();
			StackTraceElement elements[] = throwable.getStackTrace();

			caller = elements[1].getClassName();
			caller = caller.substring(caller.lastIndexOf('.') + 1, caller.length());

			this.message = String.format("%s: %s", caller, message);
		}

		else
			this.message = message;

		printed = true;
	}

	/**
	 * Procedimento interno usado para imprimir todos os caracteres da mensagem no console.
	 * Ir� processar caracter por caracter afim de identificar poss�veis comandos.
	 * Caso seja definido o uso de quebra de linhas ser� quebrada a linha ao final.
	 */

	public void callPrintMessage()
	{
		StringExtend se = new StringExtend(message);

		if (se.length() == 0)
			return;

		do {

			processCharacter(se);

		} while (se.next() && !se.finish());
		
		if (breakLine)
			printCharacter('\n');

		message = "";
	}

	/**
	 * Procedimento interno usado para processar um �nico caracter exibindo-o no console.
	 * Verifica se � uma caracter de escape para acionar uma fun��o ou n�o.
	 * @param se refer�ncia do objeto contendo a itera��o da string.
	 */

	private void processCharacter(StringExtend se)
	{
		switch (se.get())
		{
			case '\f':
				processFunction(se);
				break;

			default:
				printCharacter(se.get());
		}
	}

	/**
	 * Procedimento interno chamado quando o caracter processado for de escape para fun��o.
	 * @param se refer�ncia do objeto contendo a itera��o da string.
	 */

	private void processFunction(StringExtend se)
	{
		se.next();

		switch(se.get())
		{
			case 'c':
				processChangeColor(se);
				break;
		}
	}

	/**
	 * Procedimento interno chamado para realizar a fun��o de altera��o na cor do console.
	 * @param se refer�ncia do objeto contendo a itera��o da string.
	 */

	private void processChangeColor(StringExtend se)
	{
		se.next();

		switch (se.get())
		{
			case 'b': setColor(Color.BLUE); break;
			case 'n': setColor(Color.CYAN); break;
			case 'k': setColor(Color.DARK_GRAY); break;
			case 'a': setColor(Color.GRAY); break;
			case 'g': setColor(Color.GREEN); break;
			case 'l': setColor(Color.LIGHT_GRAY); break;
			case 'm': setColor(Color.MAGENTA); break;
			case 'o': setColor(Color.ORANGE); break;
			case 'p': setColor(Color.PINK); break;
			case 'r': setColor(Color.RED); break;
			case 'w': setColor(Color.WHITE); break;
			case 'y': setColor(Color.YELLOW); break;
			default: setColor(null);
		}
	}

	/**
	 * Procedimento interno usado para definir qual ser� a cor usada para exibir o texto.
	 * @param color refer�ncia do objeto contendo os valores da cor em RGB.
	 */

	private void setColor(Color color)
	{
		if (color != null)
			StyleConstants.setForeground(style, color);
		else
			StyleConstants.setForeground(style, DEFAULT_COLOR);
	}

	/**
	 * Procedimento interno usado para imprimir um �nico caracter no console.
	 * @param c caracter do qual dever� ser imprimido na �rea do console.
	 */

	private void printCharacter(char c)
	{
		try {
			document.insertString(document.getLength(), Character.toString(c), style);
		} catch (BadLocationException e) {
			throw new UtilRuntimeException("falha ao escrever no console");
		}
	}

	/**
	 * Procedimento interno usado para restabelecer as configura��es do console ao padr�o.
	 * Esse padr�o inclui em usar DEFAULT_COLOR na cor e quebra de linhas e trace false.
	 */

	public void callResetPreferences()
	{
		StyleConstants.setForeground(style, DEFAULT_COLOR);
		breakLine = false;
	}

	/**
	 * M�todo usado pelo console para saber quando deve mover o ScrollBar para seu limite.
	 * Sempre que este m�todo for chamado a atribui��o de printed ser� definido como false.
	 * @return true se tiver imprimido alguma coisa ou false caso contr�rio.
	 */

	public boolean wasPrinted()
	{
		boolean has = printed;
		printed = false;

		return has;
	}
}
