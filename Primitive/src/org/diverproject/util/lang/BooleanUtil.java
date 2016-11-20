package org.diverproject.util.lang;

/**
 * <p><h1>Utilit�rio para Boolean</h1></p>
 *
 * Classe com m�todos utilit�rios para manusear valores do tipo boolean.
 * Todos os m�todos envolvem retornar atributos do tipo <b>boolean</b>.
 * Em alguns casos h� m�todos que retornam objetos do tipo <b>boolean</b>.
 * Apesar de ambos serem do mesmo tipo, algumas opera��es necessitam
 * utilizar o objeto ao inv�s do atributo, e vice-versa.
 */

public class BooleanUtil
{
	/**
	 * C�digo para string analisada com valor falso.
	 */
	public static final int BOOLEAN_FALSE = 0;

	/**
	 * C�digo para string analisada com valor verdadeiro.
	 */
	public static final int BOOLEAN_TRUE = 1;

	/**
	 * C�digo para string analisada com valor inv�lido.
	 */
	public static final int BOOLEAN_ERROR = 2;

	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private BooleanUtil()
	{
		
	}

	/**
	 * Verifica se uma determinada string � booleana ou n�o.
	 * @param value valor que ser� verificado se � verdadeiro.
	 * @return true se for verdadeiro ou false caso contr�rio.
	 */

	public static boolean parse(String value)
	{
		return parseString(value) == 1;
	}

	/**
	 * Faz a an�lise de uma determinada string para verificar se o seu valor � verdadeiro ou falso.
	 * Nesse caso considera os seguintes valores como verdadeiro e falso: 1|0, yes|no, true|false.
	 * @param value string contendo o valor do qual ser� analisado e validado.
	 * @return 1 se for verdadeiro, 0 se for falso e -1 caso n�o seja v�lido.
	 */

	public static int parseString(String value)
	{
		if (value.equals("1") || value.equals("yes") || value.equals("true"))
			return BOOLEAN_TRUE;

		if (value.equals("0") || value.equals("no") || value.equals("false"))
			return BOOLEAN_FALSE;

		return BOOLEAN_ERROR;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar valores primitivos.
	 * @param array vetor de objetos do tipo boolean a ser usado.
	 * @return vetor de valores primitivos do tipo boolean criado.
	 */

	public static boolean[] parseArrayBoolean(Boolean[] array)
	{
		boolean[] parsed = new boolean[array.length];

		for (int i = 0; i < parsed.length; i++)
			parsed[i] = array[i] == null ? false : array[i];

		return parsed;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar objetos do tipo boolean.
	 * @param array vetor de valores primitivos do tipo boolean.
	 * @return vetor de objetos do tipo boolean que foi criado.
	 */

	public static Boolean[] parseArrayBoolean(boolean[] array)
	{
		Boolean[] parsed = new Boolean[array.length];

		for (int i = 0; i < parsed.length; i++)
			parsed[i] = array[i];

		return parsed;
	}

	/**
	 * Deve verificar se uma determinada string possui um valor booleano.
	 * Os valores aceitos como booleano s�o: 0/1, false/true, no/yes, nao/sim.
	 * @param value string do qual ser� avaliado se o conte�do pode ou n�o ser boolean.
	 * @return true se possuir um conte�do considerado booleano ou false caso contr�rio.
	 */

	public static boolean isBoolean(String value)
	{
		return	value != null && (
				value.equals("0")		|| value.equals("1")	||
				value.equals("false")	|| value.equals("true")	||
				value.equals("no")		|| value.equals("yes")	||
				value.equals("nao")		|| value.equals("sim")	);
	}
}
