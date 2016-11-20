package org.diverproject.util.lang;

import org.diverproject.util.UtilException;

/**
 * <p><h1>Utilit�rio para Short</h1></p>
 *
 * Classe com m�todos utilit�rios para manusear valores do tipo short.
 * Todos os m�todos envolvem retornar atributos do tipo <b>short</b>.
 * Em alguns casos h� m�todos que retornam objetos do tipo <b>Short</b>.
 * Apesar de ambos serem do mesmo tipo, algumas opera��es necessitam
 * utilizar o objeto ao inv�s do atributo, e vice-versa.
 */

public class ShortUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private ShortUtil()
	{
		
	}

	/**
	 * Exibe um determinado vetor de curtos no console separado por �ndice.
	 * Por padr�o esse m�todo ir� exibir apenas 20 elementos por linha.
	 * @param array vetor com os n�meros do tipo long que ser�o exibidos no console.
	 */

	public static void print(short[] array)
	{
		print((short) 20, array);
	}

	/**
	 * Exibe um determinado vetor de curtos no console em linhas.
	 * @param perline quantos elementos ser�o exibidos por linha.
	 * @param array vetor com os caracteres que ser�o impressos.
	 */

	public static void print(short perline, short[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (i % perline == 0)
				System.out.printf("\ni%4d: ", i + 1);

			System.out.printf("[%5d]", array[i]);
		}

		System.out.println();
	}

	/**
	 * Verifica se uma determinada string pode ser convertida para um n�mero curto.
	 * Valida se a string � composta apenas por n�meros seja positivo ou negativo, e
	 * por fim verifica se est� dentro dos limites permitidos para um n�mero curto.
	 * @param str string do qual deve ser verificada se pode converter.
	 * @return true se for poss�vel converter para um n�mero curto.
	 */

	public static boolean isShort(String str)
	{
		if (!StringUtil.isNumber(str))
			return false;

		if (str.length() > (str.startsWith("-") ? 6 : 5))
			return false;

		if (str.startsWith("-") && str.length() == 6 && str.compareTo(Short.toString(Short.MIN_VALUE)) > 0)
			return false;

		else if (str.length() == 5 && str.compareTo(Short.toString(Short.MAX_VALUE)) > 0)
			return false;

		return true;
	}

	/**
	 * Percorre uma array de strings verificando a string pode ser convertida para curto.
	 * @param array vetor que ser� verificado se pode ser convertido em curto.
	 * @return true se todas as string podem ser convertidas ou false caso uma ou mais n�o consiga.
	 */

	public static boolean isShort(String[] array)
	{
		for (String string : array)
			if (!isShort(string))
				return false;

		return true;
	}

	/**
	 * Faz toda uma verifica��o para garantir que uma determina seja convertida para um n�mero curto.
	 * Ir� considerar valores hexadecimais apenas se forem seguidos do pr�-fixo 0x que ir� determinar tal.
	 * @param value string contendo o valor num�rico ou hexadecimal do qual dever� ser convertido.
	 * @return valor num�rico contido na string passada se assim for um.
	 * @throws UtilException apenas se houver falha na convers�o.
	 */

	public static short parseString(String value) throws UtilException
	{
		if (value.startsWith("0x"))
			return HexUtil.parseShort(value);

		StringUtil.isParseNumber(value);

		boolean negative = false;

		if (value.startsWith("-"))
			negative = true;

		if (negative)
		{
			if (value.length() > Short.toString(Short.MIN_VALUE).length())
				throw new UtilException("valor muito pequeno");
		}

		else if (value.length() > Short.toString(Short.MAX_VALUE).length())
			throw new UtilException("valor muito grande");

		short parse = 0;

		for (int i = negative ? 1 : 0; i < value.length(); i++)
		{
			char c = value.charAt(i);

			if (!Character.isDigit(c))
				throw new UtilException("valor n�o num�rico");

			parse = (short) ((parse * 10) + Character.digit(c, 10));
		}

		return parse;
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico curto.
	 * @param str string do qual deve ser convertida para um n�mero curto.
	 * @return valor num�rico da string passada ou 0 caso n�o seja num�rico.
	 */

	public static short parse(String str)
	{
		return parse(str, (short) 0);
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico curto.
	 * @param str string do qual deve ser convertida para um n�mero curto.
	 * @param fail valor que ser� retornado caso a string n�o seja num�rica.
	 * @return valor num�rico da string se for v�lida ou <b>fail</b> caso contr�rio.
	 */

	public static short parse(String str, short fail)
	{
		if (!isShort(str))
			return fail;

		return Short.parseShort(str);
	}

	/**
	 * Um byte possui um valor de at� 8 bits que equivalem a um valor bin�rio
	 * entre 0 e 255. Esse m�todo permite obter esse valor bin�rio (decimal).
	 * @param b byte que ser� convertido para um valor num�rico curto.
	 * @return valor num�rico curto de 0 a 255 do byte respectivo.
	 */

	public static short parseByte(byte b)
	{
		short value = (short) b;

		return (short) (value >= 0 ? value : 256 + value);
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero curto a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * @param bytes vetor contendo os bytes que deve criar o n�mero curto.
	 * @return n�mero curto criado a partir dos bytes passado.
	 */

	public static short parseBytes(byte[] bytes)
	{
		short value = 0;

		int count = IntUtil.minor(bytes.length, Short.BYTES);

		for (int i = 0, j = 0; i < count; i++, j += 8)
		{
			int parsed = (bytes[bytes.length - i - 1] & 255) << j;
			value |= parsed;
		}

		return value;
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero curto a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * Esse m�todo tamb�m ir� inverter a ordem de como os bytes devem ser posicionados.
	 * @param bytes vetor contendo os bytes que deve criar o n�mero curto.
	 * @return n�mero curto criado a partir dos bytes passado.
	 */

	public static short parseBytesInvert(byte[] bytes)
	{
		short value = 0;

		int count = IntUtil.minor(bytes.length, Short.BYTES);

		for (int i = 0, j = 0; i < count; i++, j += 8)
		{
			int parsed = (bytes[i] & 255) << j;
			value |= parsed;
		}

		return value;
	}

	/**
	 * Converte uma determinada string em valor hexadecimal em n�mero curto.
	 * Caso o valor n�o seja um hexadecimal ser� retornado 0 por padr�o da convers�o.
	 * @param str valor hexadecimal salvo em uma string (com ou sem 0x no inicio).
	 * @return valor num�rico curto obtido da convers�o do valor em hexadecimal.
	 */

	public static short parseHex(String str)
	{
		if (!HexUtil.isHexShort(str))
			return 0;

		return Short.parseShort(HexUtil.clearHex(str), 16);
	}

	/**
	 * Converte um determinado vetor de strings para valores num�ricos
	 * do tipo short. O m�todo � feito percorrendo o vetor e passando
	 * as strings para o m�todo parse(array[i]) de modo que utiliza as
	 * regras definidas na documenta��o do mesmo.
	 * @param array vetor de strings que ser�o convertidas para short.
	 * @return vetor com n�meros do tipo short.
	 */

	public static short[] parseArray(String[] array)
	{
		short parse[] = new short[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = parse(array[i]);

		return parse;
	}

	/**
	 * Obt�m o c�digo ascii de todos os caracteres do vetor atrav�s
	 * de um simples cast de char para short. Os valores s�o salvos
	 * em um novo vetor do tipo short armazenando respectivamente.
	 * @param array vetor dos caracteres que ser�o convertidos.
	 * @return vetor com c�digo ascii salvos em um vetor short.
	 */

	public static short[] parseArrayChar(char[] array)
	{
		short parse[] = new short[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = (short) array[i];

		return parse;
	}

	public static Short[] parseArrayByte(short[] array)
	{
		Short[] parsed = new Short[array.length];

		for (int i = 0; i < parsed.length; i++)
			parsed[i] = array[i];

		return parsed;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar valores primitivos.
	 * @param array vetor de objetos do tipo short a ser usado.
	 * @return vetor de valores primitivos do tipo short criado.
	 */

	public static short[] parseArrayShort(Short[] array)
	{
		short parse[] = new short[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i] == null ? 0 : array[i];

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar objetos do tipo short.
	 * @param array vetor de valores primitivos do tipo short.
	 * @return vetor de objetos do tipo short que foi criado.
	 */

	public static Short[] parseArrayInt(short[] array)
	{
		Short parse[] = new Short[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i];

		return parse;
	}

	/**
	 * Copia uma parte de um determinado vetor de n�meros short. O modo de c�pia
	 * � feito por um ponto inicial <b>offset</b> (�ndice). A partir deste
	 * ponto ele ir� obter os pr�ximos n �ndices e inseri-los no vetor.
	 * @param array vetor que ser� copiado uma parte.
	 * @param offset ponto inicial da c�pia (�ndice).
	 * @param length quantos elementos ser�o copiados.
	 * @return vetor apenas com os elementos copiados.
	 */

	public static short[] subarray(short[] array, int offset, int length)
	{
		if (offset > array.length - 1 || offset + length > array.length)
			return new short[]{};

		short select[] = new short[length];

		for (int i = 0; i < length; i++)
			select[i] = array[offset + i];

		return select;
	}

	/**
	 * Obt�m o valor percentual curto entre dois n�meros.
	 * Apesar de serem double, podem ser inseridos de quaisquer
	 * tipos. Onde um se insere o valor que seria igual a 100%
	 * e o outro o valor que deseja saber a porcentagem.
	 * @param complete valor que deseja obter a porcentagem.
	 * @param total valor que representa 100% da quantidade.
	 * @return percentual curto dos valores acima.
	 */

	public static short toPorcent(double complete, double total)
	{
		return (short) ((double) (complete / total) * 100);
	}

	/**
	 * Verifica se o valor est� dentro do limite permitido que foi definido.
	 * Caso o valor ultrapasse os limites permitidos ser� definidos com os mesmos.
	 * @param value valor que ser� verificado se est� dentro dos limites.
	 * @param min limite m�nimo permitido que value pode ter.
	 * @param max limite m�ximo permitido que value pode ter.
	 * @return valor se estive dentro dos limites definido, limite m�nimo se for
	 * menor que o permitido ou limite m�ximo se for maior que o permitido.
	 */

	public static short limit(short value, short min, short max)
	{
		return value < min ? min : value > max ? max : value;
	}

	/**
	 * Verifica se o valor � menor que o limite determinado por par�metro.
	 * @param value valor que ser� verificado se est� ou n�o dentro do m�nimo permitido.
	 * @param min valor m�nimo que deve ser permitido retornar ap�s verificar.
	 * @return se o valor for menor que o permitido, esse por sua vez (min)
	 * ser� retornado, caso contr�rio ir� retornar o valor normal.
	 */

	public static short min(short value, short min)
	{
		return value < min ? min : value;
	}

	/**
	 * Verifica se o valor � maior que o limite determinado por par�metro.
	 * @param value valor que ser� verificado se est� ou n�o dentro do m�ximo permitido.
	 * @param max valor m�ximo que deve ser permitido retornar ap�s verificar.
	 * @return se o valor for maior que o permitido, esse por sua vez (max)
	 * ser� retornado, caso contr�rio ir� retornar o valor normal.
	 */

	public static short max(short value, short max)
	{
		return value > max ? max : value;
	}

	/**
	 * Verifica se um determinado valor est� ou n�o dentro do limite estabelecido.
	 * @param value valor do qual deve ser verificado se est� no limite.
	 * @param min limite m�nimo do intervalo para ser v�lido.
	 * @param max limite m�ximo do intervalo para ser v�lido.
	 * @return true se estiver dentro do limite ou false caso contr�rio.
	 */

	public static boolean interval(short value, short min, short max)
	{
		return value >= min && value <= max;
	}

	/**
	 * Permite obter o valor de um objeto do tipo Short ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @return valor real do objeto ou 0 se for nulo.
	 */

	public static short valueOf(Short value)
	{
		return valueOf(value, (short) 0);
	}

	/**
	 * Permite obter o valor de um objeto do tipo Short ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @param valueNull valor que ser� retornado cao seja nulo.
	 * @return valor real do objeto ou valueNull se for nulo.
	 */

	public static short valueOf(Short value, short valueNull)
	{
		return value == null ? valueNull : value;
	}

	/**
	 * Faz a convers�o do valor absoluto de um n�mero int para um n�mero short.
	 * @param value valor num�rico de 4 bytes que ser� convertido em short.
	 * @return valor m�ximo de um short se value exced�-lo, valor m�nimo de um
	 * short se value exced�-lo ou o seu valor absoluto convertido para short.
	 */

	public static short cast(int value)
	{
		return value > Short.MAX_VALUE ? Short.MAX_VALUE : value < Short.MIN_VALUE ? Short.MIN_VALUE : (short) value;
	}

	/**
	 * Faz a convers�o do valor absoluto de um n�mero long para um n�mero short.
	 * @param value valor num�rico de 8 bytes que ser� convertido em short.
	 * @return valor m�ximo de um short se value exced�-lo, valor m�nimo de um
	 * short se value exced�-lo ou o seu valor absoluto convertido para short.
	 */

	public static short cast(long value)
	{
		return value > Short.MAX_VALUE ? Short.MAX_VALUE : value < Short.MIN_VALUE ? Short.MIN_VALUE : (short) value;
	}
}
