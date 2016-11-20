package org.diverproject.util;

import java.util.Locale;

/**
 * <p><h1>Teste de Dura��o</h1></p>
 *
 * <p>Possui m�todos est�ticos que permitem fazer o teste de dura��o entre procedimentos.
 * Primeiramente deve ser chamado init() para determinar o inicio do teste de dura��o.
 * Por fim deve ser chamado delay() para verificar o tempo de dura��o naquele momento.</p>
 *
 * @author Andrew
 */

public class TimerTest
{
	/**
	 * Dura��o do �ltimo momento que foi feito a inicializa��o.
	 */
	private static long init;

	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private TimerTest()
	{
		
	}

	/**
	 * Determina o inicio do c�lculo do tempo em nanosegundos.
	 */

	public static void init()
	{
		init = System.nanoTime();
	}

	/**
	 * Permite obter a dura��o desde a inicializa��o feita por init().
	 * @return aquisi��o do teste de dura��o em milissegundos.
	 */

	public static double delay()
	{
		return (double) ((double) (System.nanoTime() - (double) init) / 1000000D);
	}

	/**
	 * Permite obter a dura��o desde a inicializa��o feita por init().
	 * @return aquisi��o do teste de dura��o em nanosegundos.
	 */

	public static long delayNano()
	{
		return System.nanoTime() - init;
	}

	/**
	 * Imprimi no console a dura��o desde a inicializa��o em milissegundos.
	 */

	public static void print()
	{
		System.out.printf(Locale.UK, "TimerTest: %.3f ms\n", delay());
	}

	/**
	 * Imprimi no console a dura��o desde a inicializa��o em nanosegundos.
	 */

	public static void printNano()
	{
		System.out.printf(Locale.UK, "TimerTest: %6dns\n", delayNano());
	}
}
