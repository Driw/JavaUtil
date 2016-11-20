package org.diverproject.util.lang;

import org.diverproject.util.UtilException;

/**
 * Classe que permite trabalhar com valores em hexadecimal.
 * A linguagem java n�o possui um atributo especifico para determinar
 * valores em hexadecimal, utilizamos strings para manuse�-los.
 * Os utilit�rios aqui permitem validar e converter valores para
 * um valor em string no formato hexadecimal e a opera��o inversa.
 */

public class HexUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private HexUtil()
	{
		
	}

	/**
	 * Verifica se uma determinada string est� em um formato hexadecimal ou n�o.
	 * Valida tanto strings que possui 0x na frente ou n�o, basta conter valores
	 * num�ricos e letras entre A e F para serem considerados hexadecimais.
	 * @param str string do qual dever� ser validada se � ou n�o hexadecimal.
	 * @param bytes quantos bytes pode ter (caracteres hexadecimal).
	 * @return true se for um valor hexadecimal ou false caso contr�rio.
	 */

	public static boolean isHex(String str, int bytes)
	{
		if (str == null || str.length() == 0)
			return false;

		if (str.startsWith("0x"))
			str = str.substring(2, str.length());

		if (str.length() > bytes * 2 || (str.length() == bytes * 2 && str.charAt(0) > 55))
			return false;

		for (char c : str.toLowerCase().toCharArray())
			if (c < '0' || c > '9')
				if (c < 'a' || c > 'f')
					return false;

		return true;
	}

	/**
	 * Verifica se uma determinada string est� em um formato hexadecimal ou n�o.
	 * Tamb�m valida se esse valor hexadecimal pode ser convertido para um n�mero short.
	 * @param str string do qual dever� ser validada se � um hexadecimal short.
	 * @return true se for um valor hexadecimal short ou false caso contr�rio.
	 */

	public static boolean isHexShort(String str)
	{
		return isHex(str, Short.BYTES);
	}

	/**
	 * Verifica se uma determinada string est� em um formato hexadecimal ou n�o.
	 * Tamb�m valida se esse valor hexadecimal pode ser convertido para um n�mero int.
	 * @param str string do qual dever� ser validada se � um hexadecimal int.
	 * @return true se for um valor hexadecimal int ou false caso contr�rio.
	 */

	public static boolean isHexInt(String str)
	{
		return isHex(str, Integer.BYTES);
	}

	/**
	 * Verifica se uma determinada string est� em um formato hexadecimal ou n�o.
	 * Tamb�m valida se esse valor hexadecimal pode ser convertido para um n�mero long.
	 * @param str string do qual dever� ser validada se � um hexadecimal long.
	 * @return true se for um valor hexadecimal long ou false caso contr�rio.
	 */

	public static boolean isHexLong(String str)
	{
		return isHex(str, Long.BYTES);
	}

	/**
	 * Verifica se uma determina string � hexadecimal, caso n�o seja retorna '0'.
	 * Quando � um hexadecimal remove o '0x' do inicio da string se existir.
	 * @param str string do qual deve ser verificada e removido pr�-fixo.
	 * @return string limpa, ou seja, sem o pr�-fixo 0x no inicio.
	 */

	public static String clearHex(String str)
	{
		return str.startsWith("0x") ? str.substring(2, str.length()) : str;
	}

	/**
	 * Obt�m o valor hexadecimal em string de um determinado byte.
	 * @param value byte que ser� obtido o valor em hexadecimal.
	 * @return valor hexadecimal em string do byte.
	 */

	public static String parseByte(byte value)
	{
		return Integer.toHexString((int) value).toUpperCase();
	}

	/**
	 * Obt�m o valor num�rico que seja considerado um valor hexadecimal.
	 * @param value string contendo o valor hexadecimal que ser� convertido.
	 * @return n�mero obtido ap�s a analise e convers�o do hexadecimal.
	 * @throws UtilException apenas no caso de n�o ser um valor hexadecimal.
	 */

	public static byte parseByte(String value) throws UtilException
	{
		if (HexUtil.isHex(value, 1))
		{
			if (value.startsWith("0x"))
				return Byte.parseByte(value.substring(2, value.length()), 16);

			return Byte.parseByte(value, 16);
		}

		throw new UtilException("hexadecimal inv�lido");
	}

	/**
	 * Obt�m o valor hexadecimal em string do c�digo ascii de um caracter.
	 * @param character que ser� obtido o valor em hexadecimal.
	 * @return valor hexadecimal em string do caracter.
	 */

	public static String parseChar(char character)
	{
		return Integer.toHexString((int) character).toUpperCase();
	}

	/**
	 * Obt�m o valor hexadecimal em string de um valor num�rico curto.
	 * @param value n�mero do qual ser� obtido o valor em hexadecimal.
	 * @return valor hexadecimal em string do n�mero curto.
	 */

	public static String parseShort(short value)
	{
		return Integer.toHexString(value).toUpperCase();
	}

	/**
	 * Obt�m o valor hexadecimal em string de um valor num�rico curto.
	 * @param value n�mero do qual ser� obtido o valor em hexadecimal.
	 * @param lenght quantas casas hexadecimais deve ter no m�nimo.
	 * @return valor hexadecimal em string do n�mero curto.
	 */

	public static String parseShort(short value, int lenght)
	{
		return StringUtil.addStartWhile(parseShort(value), "0", lenght);
	}

	/**
	 * Obt�m o valor num�rico que seja considerado um valor hexadecimal.
	 * @param value string contendo o valor hexadecimal que ser� convertido.
	 * @return n�mero obtido ap�s a analise e convers�o do hexadecimal.
	 * @throws UtilException apenas no caso de n�o ser um valor hexadecimal.
	 */

	public static short parseShort(String value) throws UtilException
	{
		if (HexUtil.isHex(value, 2))
		{
			if (value.startsWith("0x"))
				return Short.parseShort(value.substring(2, value.length()), 16);

			return Short.parseShort(value, 16);
		}

		throw new UtilException("hexadecimal inv�lido");
	}

	/**
	 * Obt�m o valor hexadecimal em string de um valor num�rico inteiro.
	 * @param value n�mero do qual ser� obtido o valor em hexadecimal.
	 * @return valor hexadecimal em string do n�mero inteiro.
	 */

	public static String parseInt(int value)
	{
		return Integer.toHexString(value);
	}

	/**
	 * Obt�m o valor hexadecimal em string de um valor num�rico inteiro.
	 * @param value n�mero do qual ser� obtido o valor em hexadecimal.
	 * @param lenght quantas casas hexadecimais deve ter no m�nimo.
	 * @return valor hexadecimal em string do n�mero inteiro.
	 */

	public static String parseInt(int value, int lenght)
	{
		return StringUtil.addStartWhile(parseInt(value), "0", lenght).toUpperCase();
	}

	/**
	 * Obt�m o valor num�rico que seja considerado um valor hexadecimal.
	 * @param value string contendo o valor hexadecimal que ser� convertido.
	 * @return n�mero obtido ap�s a analise e convers�o do hexadecimal.
	 * @throws UtilException apenas no caso de n�o ser um valor hexadecimal.
	 */

	public static int parseInt(String value) throws UtilException
	{
		if (HexUtil.isHex(value, 4))
		{
			if (value.startsWith("0x"))
				return Integer.parseInt(value.substring(2, value.length()), 16);

			return Integer.parseInt(value, 16);
		}

		throw new UtilException("hexadecimal inv�lido");
	}

	/**
	 * Obt�m o valor hexadecimal em string de um valor num�rico longo.
	 * @param value n�mero do qual ser� obtido o valor em hexadecimal.
	 * @return valor hexadecimal em string do n�mero long.
	 */

	public static String parseLong(long value)
	{
		return Long.toHexString(value).toUpperCase();
	}

	/**
	 * Obt�m o valor hexadecimal em string de um valor num�rico longo.
	 * @param value n�mero do qual ser� obtido o valor em hexadecimal.
	 * @param lenght quantas casas hexadecimais deve ter no m�nimo.
	 * @return valor hexadecimal em string do n�mero longo.
	 */

	public static String parseLong(long value, int lenght)
	{
		return StringUtil.addStartWhile(parseLong(value), "0", lenght).toUpperCase();
	}

	/**
	 * Obt�m o valor num�rico que seja considerado um valor hexadecimal.
	 * @param value string contendo o valor hexadecimal que ser� convertido.
	 * @return n�mero obtido ap�s a analise e convers�o do hexadecimal.
	 * @throws UtilException apenas no caso de n�o ser um valor hexadecimal.
	 */

	public static long parseLong(String value) throws UtilException
	{
		if (HexUtil.isHex(value, 8))
		{
			if (value.startsWith("0x"))
				return Long.parseLong(value.substring(2, value.length()), 16);

			return Long.parseLong(value, 16);
		}

		throw new UtilException("hexadecimal inv�lido");
	}
}
