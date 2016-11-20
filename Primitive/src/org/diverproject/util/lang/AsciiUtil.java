package org.diverproject.util.lang;

/**
 * <p><h1>Utilit�rio para Ascii</h1></p>
 *
 * Utilit�rios ASCII permite a convers�o entre caracteres de 2 bytes para valores num�ricos.
 * Atrav�s de m�todos de convers�o dos c�digos ASCII para bin�rios e de bin�rios para n�meros
 * inteiros (int/long) � poss�vel fazer estas opera��es de modo que um n�mero do tipo int,
 * possa ser salvo em no m�ximo at� 2 bytes, enquanto um valor do tipo long chega at� 4 bytes.
 */

public class AsciiUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private AsciiUtil()
	{
		
	}

	/**
	 * Atrav�s do c�digo ascii dos caracteres convertidos para bin�rios,
	 * � poss�vel se obter um valor num�rico do tipo 'long' com at� 8 bytes
	 * (4 caracteres). Caso n�o seja inserido caracteres (null) o valor
	 * retornado ser� 0, se houver mais de 4 caracteres automaticamente
	 * ser� retornado o limite dos valores do tipo 'long'.
	 * @param characters quais os caracteres que definem o valor.
	 * @return valor num�rico 'long' dos caracteres convertidos.
	 */

	public static long parseLong(char... characters)
	{
		if (characters == null || characters.length == 0)
			return 0;

		String binary = BinaryUtil.parse(characters);

		if (characters.length > 4 || BinaryUtil.removeZeros(binary) > 64)
			return Long.MAX_VALUE;

		return Long.parseLong(binary, 2);
	}

	/**
	 * Atrav�s do c�digo ascii dos caracteres convertidos para bin�rios,
	 * � poss�vel se obter um valor num�rico do tipo 'int' com at� 4 bytes
	 * (2 caracteres). Caso n�o seja inserido caracteres (null) o valor
	 * retornado ser� 0, se houver mais de 2 caracteres automaticamente
	 * ser� retornado o limite dos valores do tipo 'int'.
	 * @param characters quais os caracteres que definem o valor.
	 * @return valor num�rico 'int' dos caracteres convertidos.
	 */

	public static int parseInt(char... characters)
	{
		if (characters == null || characters.length == 0)
			return 0;

		String binary = BinaryUtil.parse(characters);

		if (characters.length > 2 || StringUtil.remInitWhile(binary, "0").length() > 32)
			return Integer.MAX_VALUE;

		return Integer.parseInt(binary, 2);
	}

	/**
	 * Para permitir que valores num�ricos sejam compactados em arquivos,
	 * este m�todo permite converter um valor do tipo 'long' em at� 8 bytes
	 * (4 caracteres), ao inv�s de salvar em dezenas de bytes (um por n�mero).
	 * Para recuperar esse valor, basta chamar o m�todo parseLong() com os
	 * caracteres gerados por este m�todo. Valores long gerados por caracteres
	 * sempre ser�o gerados em 4 caracteres independente do valor.
	 * @param value valor num�rico que ser� convertido para caracter.
	 * @return caracter convertido a partir do valor inserido.
	 */

	public static char[] toChar(long value)
	{
		String binary = Long.toBinaryString(value);
		binary = StringUtil.addStartMod(binary, "0", 64);

		String bin[] = StringUtil.split(binary, 16);

		return new char[]
		{
			(char) Integer.parseInt(bin[0]),
			(char) Integer.parseInt(bin[1]),
			(char) Integer.parseInt(bin[2]),
			(char) Integer.parseInt(bin[3])
		};
	}

	/**
	 * Para permitir que valores num�ricos sejam compactados em arquivos,
	 * este m�todo permite converter um valor do tipo 'int' em at� 4 bytes
	 * (2 caracteres), ao inv�s de salvar em dezenas de bytes (um por n�mero).
	 * Para recuperar esse valor, basta chamar o m�todo parseInt() com os
	 * caracteres gerados por este m�todo. Valores int gerados por caracteres
	 * sempre ser�o gerados em 2 caracteres independente do valor.
	 * @param value valor num�rico que ser� convertido para caracter.
	 * @return caracter convertido a partir do valor inserido.
	 */

	public static char[] toChar(int value)
	{
		String binary = Integer.toBinaryString(value);
		binary = StringUtil.addStartMod(binary, "0", 32);

		String bin[] = StringUtil.split(binary, 16);

		return new char[]
		{
			(char) Integer.parseInt(bin[0], 2),
			(char) Integer.parseInt(bin[1], 2)
		};
	}
}
