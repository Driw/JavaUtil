package org.diverproject.util;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 * <p><h1>Barra de Progresso</h1></p>
 *
 * Exibe uma nova janela (JFrame) apenas uma barra de progresso.
 * A barra de progresso pode ser controlado pelos m�todos nela definido.
 * - Iniciar: basta instanciar que ela ser� exibida automaticamente.
 * - Fechar: chamar o m�todo dispose() ir� remover a tela e liberar mem�ria.
 * - Progresso: atrav�s de setRate() � poss�vel determinar a taxa completada.
 *
 * @see JFrame
 * @see JProgressBar
 */

public class ProgressUtil
{
	/**
	 * Janela da barra de progresso.
	 */
	private JFrame frame;

	/**
	 * Barra de progresso que ser� exibida.
	 */
	private JProgressBar progress;

	/**
	 * Inicia a cria��o da janela definindo o tamanho padr�o.
	 * N�o permite redimensionar a janela com o mouse e deixa centralizada.
	 * Tamb�m n�o deixa fechar a janela atrav�s do fechar da janela.
	 * Exibe a janela automaticamente com a barra de progresso junto.
	 */

	public ProgressUtil()
	{
		frame = new JFrame();
		frame.setSize(520, 60);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		progress = new JProgressBar(0, 100);
		frame.setContentPane(progress);

		frame.setVisible(true);
	}

	/**
	 * Limpa a tela removendo a janela do mesmo.
	 * @return true se existir uma aberta ou false caso contr�rio.
	 */

	public boolean dispose()
	{
		if (frame == null)
			return false;

		frame.dispose();
		return true;
	}

	/**
	 * Define a taxa de progresso que a barra atingiu.
	 * Por padr�o vai de 0 a 100 (%), se definido com um valor
	 * inv�lido, o valor de progresso completo n�o ir� mudar.
	 * @param rate taxa de progresso.
	 * @return true se o valor for v�lido ou false caso contr�rio..
	 */

	public boolean setComplete(int rate)
	{
		if (rate < 0 || rate > 100)
			return false;

		progress.setValue(rate+1);
		return true;
	}

	/**
	 * Por padr�o a taxa de progresso completo pode variar de 0 a 100 (%).
	 * @return taxa de progresso completa.
	 */

	public int getComplete()
	{
		return frame == null ? 0 : progress.getValue();
	}

	/**
	 * Permite definir/redefinir a barra de t�tulos da janela que ser� exibida.
	 * @param title t�tulo que ser� exibido na barra da janela.
	 * @return true se conseguir ou false caso n�o haja nenhuma janela criada.
	 */

	public boolean setTitle(String title)
	{
		if (frame == null)
			return false;

		frame.setTitle(title);
		return true;
	}
}
