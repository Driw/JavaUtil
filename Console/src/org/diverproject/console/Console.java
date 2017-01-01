package org.diverproject.console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.diverproject.util.collection.List;
import org.diverproject.util.collection.abstraction.DynamicList;
import javax.swing.JMenuBar;

/**
 * <h1>Console</h1>
 *
 * <p>A utiliza��o do console permite exibir uma janela que possui um campo de texto e �rea de texto.
 * Utiliza-se ainda do padr�o de projetos Singleton que determina a possibilidade uma �nica inst�ncia.</p>
 *
 * <p>O campo de texto ir� permitir executar comandos que devem ser especificado pelo desenvolvedor.
 * Os comandos podem ser interpretados de diversas formas, onde todo o conte�do digitado no campo
 * pode ser obtido atrav�s da utiliza��o da escuta do console, que ir� repassar o texto a um m�todo.</p>
 *
 * <p>A �rea de texto pode ser usada atrav�s de interface de a��es do console na escuta para console.
 * Essas a��es ir� permitir que nessa �rea de texto seja exibido mensagens relativas aos comandos.
 * podendo ainda limpar o mesmo ou definir cores para tornar as mensagens mais din�micas e n�tidas.</p>
 *
 * @see ConsoleActions
 * @see ConsoleListener
 *
 * @author Andrew
 */

@SuppressWarnings("serial")
public abstract class Console extends JFrame
{
	/**
	 * Fonte padr�o que ser� usada para exibi��o dos textos no console.
	 */
	private static final Font DEFAULT_FONT = new Font("Courier New", Font.PLAIN, 12);


	/**
	 * Painel para exibi��o de textos resultantes dos comandos do console.
	 */
	private ConsolePanel consolePanel;

	/**
	 * Campo de texto para receber as entradas referentes aos comandos.
	 */
	private JTextField input;

	/**
	 * Painel para rolagem do painel de texto que ser� usado.
	 */
	private JScrollPane scrollPane;

	/**
	 * Lista contendo as escutas que o console dever� considerar.
	 */
	private List<ConsoleListener> listeners;

	/**
	 * Usar trim para remover espa�os no come�o e fim do comando.
	 */
	private boolean useTrimInput;

	/**
	 * Limpar o conte�do da entrada (comando) ap�s ser executado.
	 */
	private boolean clearInput;

	/**
	 * Cria um novo console e deve inicializar seus componentes m�nimos.
	 * Os componentes instanciados s�o de janela, campo de texto e outros.
	 * Dever� ainda definir por padr�o que a entrada ser� limpa ap�s o comando.
	 */

	public Console()
	{
		initConsole();
	}

	/**
	 * Procedimento interno chamado pelo construtor que garante a exist�ncia do console.
	 * Inicializa a janela definindo algumas prefer�ncias de utiliza��o para o console.
	 * Como tamb�m cria o painel de texto, campo de texto painel de rolagem e afins.
	 */

	protected void initConsole()
	{
		setTitle("Console");
		setSize(1024, 480);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setFocusable(true);

		consolePanel = new ConsolePanel();
		consolePanel.setOpaque(false);
		consolePanel.setEditable(false);
		consolePanel.setFont(DEFAULT_FONT);

		input = new JTextField();
		input.setFont(DEFAULT_FONT);
		input.setOpaque(false);
		input.setForeground(ConsolePanel.DEFAULT_COLOR);
		input.setCaretColor(Color.WHITE);
		input.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyReleased(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					callListeners();
					consolePanel.callPrintMessage();
					consolePanel.callResetPreferences();
					callDefaultCommands();
					callClearInput();
				}
			}
		});
		getContentPane().add(input, BorderLayout.SOUTH);

		scrollPane = new JScrollPane(consolePanel);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		getContentPane().setBackground(Color.BLACK);

		setJMenuBar(createMenuBar());

		consolePanel.callResetPreferences();
	}

	protected abstract JMenuBar createMenuBar();

	/**
	 * Adiciona uma nova escuta ao console para que seja poss�vel executar os comandos.
	 * Sempre que um comando for detectado no console, ir� passar para esses escudas.
	 * @param listener refer�ncia do objeto que possui a implementa��o da escuta.
	 */

	public void addListener(ConsoleListener listener)
	{
		if (listeners == null)
			listeners = new DynamicList<ConsoleListener>();

		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	/**
	 * Remove um objeto que possui a implementa��o de escuta do console do mesmo.
	 * Uma vez removido os comandos aqui executados n�o ser�o repassado para este.
	 * @param listener refer�ncia do objeto que possui a implementa��o da escuta.
	 */

	public void removeListener(ConsoleListener listener)
	{
		if (listeners != null && listeners.contains(listener))
		{
			listeners.remove(listener);

			if (listeners.size() == 0)
				listeners = null;
		}
	}

	/**
	 * Permite definir se a entrada (campo de texto) que recebe os comandos ser� limpa.
	 * Se habilitado, ap�s executar o comando o campo de texto dever� ser limpo.
	 * @param enable true para habilitar ou false para desativar.
	 */

	public void setClearInput(boolean enable)
	{
		clearInput = enable;
	}

	/**
	 * Define que o console dever� usar a opera��o de trim ap�s a execu��o do comando.
	 * A opera��o trim dever� remover espa�os que estejam no come�o e fim do comando.
	 * @param enable true para habilitar ou false para desativar.
	 */

	public void useTrimInput(boolean enable)
	{
		useTrimInput = enable;
	}

	/**
	 * Ao ser chamado torna o console vis�vel (caso n�o esteja) como o tr�s para frente.
	 * Ao trazer para frente tamb�m solicita que o campo de texto do comando seja focado.
	 */

	@Override
	@SuppressWarnings("deprecation")
	public void show()
	{
		super.show();

		toFront();
		requestFocus();
		input.requestFocus();
	}

	/**
	 * Ao chamar o m�todo para esconder, ir� ocultar a janela do console.
	 * Quando oculto, n�o ser� poss�vel digitar comandos, j� que n�o estar� vis�vel.
	 */

	@Override
	@SuppressWarnings("deprecation")
	public void hide()
	{
		super.hide();
	}

	/**
	 * Procedimento interno que ir� chamar todos os listeners para processar a mensagem.
	 */

	private void callListeners()
	{
		String text = input.getText();

		if (text.length() > 0)
		{
			if (useTrimInput)
				text = text.trim();

			if (listeners != null)
				for (ConsoleListener listener : listeners)
					listener.trigger(text, consolePanel);
		}
	}

	/**
	 * Procedimento interno usado para verificar se algum comando do console foi usado.
	 * Os comandos dispon�veis s�o de <b>exit</b> para fechar e <b>clear</b> para limpar.
	 */

	private void callDefaultCommands()
	{
		switch (input.getText())
		{
			case "exit":
				hide();
				break;

			case "clear":
				consolePanel.clear();
				break;
		}
	}

	/**
	 * Procedimento interno usado para limpar todos os caracteres exibidos no console.
	 */

	private void callClearInput()
	{
		if (clearInput)
			input.setText("");
	}

	public void print(String message)
	{
		consolePanel.setMessage(message);
		consolePanel.callPrintMessage();
	}

	public ConsolePanel getConsolePanel()
	{
		return consolePanel;
	}

	public void setConsolePanel(ConsolePanel consolePanel)
	{
		this.consolePanel = consolePanel;
		this.scrollPane.setViewportView(consolePanel);
		this.repaint();
	}
}
