package org.diverproject.util;


/**
 * <p><h1>Temporizador por Tick</h1></p>
 *
 * <p>Com um temporizador � poss�vel determinar um intervalo em milissegundos para cada tick.
 * Utiliza um m�todo que permite obter quantos ticks podem ser obtidos deste a �ltima tentativa.
 * Possuindo dois modos de ticks, onde um determina um limite de ticks enquanto o outro usa o m�ximo.</p>
 *
 * <p>Isso � utilizado quando � necess�rio fazer uma contagem de tempos em tempos (ticks).
 * Assim sendo, os dois construtores permitem definir o intervalo em milissegundos para cada tick.
 * Quando o m�todo for chamado ir� atualizar o �ltimo tick obtido para um c�lculo seguinte.
 * Al�m disso, n�o permite que esse possa obter mais ticks do que o definido no limite.</p>
 *
 * @author Andrew
 */

public class TimerTick
{
	/**
	 * Contagem de quantos ticks j� foram obtidos.
	 */
	private long count;

	/**
	 * �ltima vez que foi poss�vel obter-se um ou mais ticks.
	 */
	private long last;

	/**
	 * Limite de ticks que podem ser obtidos desse temporizador.
	 */
	private final long limit;

	/**
	 * Dura��o do intervalo para cada tick em milissegundos.
	 */
	private final long interval;

	/**
	 * Constr�i um novo temporizador para ticks onde define o limite m�ximo permitido.
	 * @param interval dura��o que ser� considerado por cada tick em milissegundos.
	 */

	public TimerTick(long interval)
	{
		this(interval, Integer.MAX_VALUE);
	}

	/**
	 * Constr�i um novo temporizador para ticks onde permite definir um limite de ticks.
	 * @param interval dura��o que ser� considerado por cada tick em milissegundos.
	 * @param limit quantos ticks poder�o ser obtidos desse temporizador.
	 */

	public TimerTick(long interval, long limit)
	{
		this.last = System.currentTimeMillis();
		this.interval = interval;
		this.limit = limit;
		this.count = 0;
	}

	/**
	 * Principal m�todo utilizado pelo temporizador, feito para obter os ticks.
	 * Quando nenhum tick � obtido n�o atualiza o tempo de �ltimo tick (last).
	 * @return aquisi��o de quantos ticks foram feitos desde a �ltima tentativa.
	 */

	public long getTicks()
	{
		long delay = System.currentTimeMillis() - last;
		long count = delay / interval;

		if (count == 0)
			return 0;

		last = System.currentTimeMillis();

		if (count > howMany())
			count = howMany();

		this.count += count;

		return count;
	}

	/**
	 * Para que o temporizador n�o passe do limite permitido, � feito uma contagem de ticks obtidos.
	 * @return aquisi��o de quantos ticks j� foram obtidos desse temporizador.
	 */

	public long getTicksCount()
	{
		return count;
	}

	/**
	 * M�todo usado por getTicks() para que a contagem de ticks n�o ultrapasse o limite permitido.
	 * @return quantos ticks ainda podem ser obtidos desse temporizador.
	 */

	public long howMany()
	{
		return limit - count;
	}

	/**
	 * Verifica se o temporizador ainda pode ou n�o obter ticks.
	 * @return true se for poss�vel ou false caso contr�rio.
	 */

	public boolean has()
	{
		return howMany() > 0;
	}

	@Override
	public String toString()
	{
		return String.format("TimerTick[interval: %dms, count: %d/%d, last: %s]", interval, count, limit, new Time(last));
	}
}
