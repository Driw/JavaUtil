package org.diverproject.util.lang;

import java.nio.ByteBuffer;

import org.diverproject.util.UtilException;

/**
 * <p><h1>Utilit�rio para Long</h1></p>
 *
 * Classe com m�todos utilit�rios para manusear valores do tipo long.
 * Todos os m�todos envolvem retornar atributos do tipo <b>long</b>.
 * Em alguns casos h� m�todos que retornam objetos do tipo <b>long</b>.
 * Apesar de ambos serem do mesmo tipo, algumas opera��es necessitam
 * utilizar o objeto ao inv�s do atributo, e vice-versa.
 */

public class LongUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private LongUtil()
	{
		
	}

	/**
	 * Exibe um determinado vetor de longs no console separado por �ndice.
	 * Por padr�o esse m�todo ir� exibir apenas 20 elementos por linha.
	 * @param array vetor com os n�meros do tipo long que ser�o exibidos no console.
	 */

	public static void print(long[] array)
	{
		print((long) 20, array);
	}

	/**
	 * Exibe um determinado vetor de longs no console em linhas.
	 * @param perline quantos elementos ser�o exibidos por linha.
	 * @param array vetor com os caracteres que ser�o impressos.
	 */

	public static void print(long perline, long[] array)
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
	 * Verifica se uma determinada string pode ser convertida para um n�mero long.
	 * Valida se a string � composta apenas por n�meros seja positivo ou negativo, e
	 * por fim verifica se est� dentro dos limites permitidos para um n�mero long.
	 * @param str string do qual deve ser verificada se pode converter.
	 * @return true se for poss�vel converter para um n�mero long.
	 */

	public static boolean islong(String str)
	{
		if (!StringUtil.isNumber(str))
			return false;

		if (str.length() > (str.startsWith("-") ? 6 : 5))
			return false;

		if (str.startsWith("-") && str.length() == 6 && str.compareTo(Long.toString(Long.MIN_VALUE)) > 0)
			return false;

		else if (str.length() == 5 && str.compareTo(Long.toString(Long.MAX_VALUE)) > 0)
			return false;

		return true;
	}

	/**
	 * Percorre uma array de strings verificando a string pode ser convertida para long.
	 * @param array vetor que ser� verificado se pode ser convertido em long.
	 * @return true se todas as string podem ser convertidas ou false caso uma ou mais n�o consiga.
	 */

	public static boolean islong(String[] array)
	{
		for (String string : array)
			if (!islong(string))
				return false;

		return true;
	}

	/**
	 * Faz toda uma verifica��o para garantir que uma determina seja convertida para um n�mero longo.
	 * Ir� considerar valores hexadecimais apenas se forem seguidos do pr�-fixo 0x que ir� determinar tal.
	 * @param value string contendo o valor num�rico ou hexadecimal do qual dever� ser convertido.
	 * @return valor num�rico contido na string passada se assim for um.
	 * @throws UtilException apenas se houver falha na convers�o.
	 */

	public static long parseString(String value) throws UtilException
	{
		if (value.startsWith("0x"))
			return HexUtil.parseLong(value);

		StringUtil.isParseNumber(value);

		boolean negative = false;

		if (value.startsWith("-"))
			negative = true;

		if (negative)
		{
			if (value.length() > Long.toString(Long.MIN_VALUE).length())
				throw new UtilException("valor muito pequeno");
		}

		else if (value.length() > Long.toString(Long.MAX_VALUE).length())
			throw new UtilException("valor muito grande");

		long parse = 0;

		for (int i = negative ? 1 : 0; i < value.length(); i++)
		{
			char c = value.charAt(i);

			if (!Character.isDigit(c))
				throw new UtilException("valor n�o num�rico");

			parse = (parse * 10) + Character.digit(c, 10);
		}

		return parse;
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico long.
	 * @param str string do qual deve ser convertida para um n�mero long.
	 * @return valor num�rico da string passada ou 0 caso n�o seja num�rico.
	 */

	public static long parse(String str)
	{
		return parse(str, 0L);
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico long.
	 * @param str string do qual deve ser convertida para um n�mero long.
	 * @param fail valor que ser� retornado caso a string n�o seja num�rica.
	 * @return valor num�rico da string se for v�lida ou <b>fail</b> caso contr�rio.
	 */

	public static long parse(String str, long fail)
	{
		if (!islong(str))
			return fail;

		return Long.parseLong(str);
	}

	/**
	 * Um byte possui um valor de at� 8 bits que equivalem a um valor bin�rio
	 * entre 0 e 255. Esse m�todo permite obter esse valor bin�rio (decimal).
	 * @param b byte que ser� convertido para um valor num�rico long.
	 * @return valor num�rico long de 0 a 255 do byte respectivo.
	 */

	public static long parseByte(byte b)
	{
		long value = (long) b;

		return (long) (value >= 0 ? value : 256 + value);
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero long a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * @param bytes vetor contendo os bytes que deve criar o n�mero long.
	 * @return n�mero long criado a partir dos bytes passado.
	 */

	public static long parseBytes(byte[] bytes)
	{
		return ByteBuffer.wrap(bytes).getLong(0);
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero long a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * Esse m�todo tamb�m ir� inverter a ordem de como os bytes devem ser posicionados.
	 * @param bytes vetor contendo os bytes que deve criar o n�mero long.
	 * @return n�mero long criado a partir dos bytes passado.
	 */

	public static long parseBytesInvert(byte[] bytes)
	{
		return ByteBuffer.wrap(ByteUtil.invertArray(bytes)).getLong(0);
	}

	/**
	 * Converte uma determinada string em valor hexadecimal em n�mero long.
	 * Caso o valor n�o seja um hexadecimal ser� retornado 0 por padr�o da convers�o.
	 * @param str valor hexadecimal salvo em uma string (com ou sem 0x no inicio).
	 * @return valor num�rico long obtido da convers�o do valor em hexadecimal.
	 */

	public static long parseHex(String str)
	{
		if (!HexUtil.isHexLong(str))
			return 0;

		return Long.parseLong(HexUtil.clearHex(str), 16);
	}

	/**
	 * Converte um determinado vetor de strings para valores num�ricos
	 * do tipo long. O m�todo � feito percorrendo o vetor e passando
	 * as strings para o m�todo parse(array[i]) de modo que utiliza as
	 * regras definidas na documenta��o do mesmo.
	 * @param array vetor de strings que ser�o convertidas para long.
	 * @return vetor com n�meros do tipo long.
	 */

	public static long[] parseArray(String[] array)
	{
		long parse[] = new long[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = parse(array[i]);

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar valores primitivos.
	 * @param array vetor de objetos do tipo long a ser usado.
	 * @return vetor de valores primitivos do tipo long criado.
	 */

	public static long[] parseArrayChar(char[] array)
	{
		long parse[] = new long[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = (long) array[i];

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar objetos do tipo long.
	 * @param array vetor de valores primitivos do tipo long.
	 * @return vetor de objetos do tipo long que foi criado.
	 */

	public static Long[] parseArrayLong(long[] array)
	{
		Long parse[] = new Long[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i];

		return parse;
	}

	/**
	 * Obt�m o valor num�rico de objetos do tipo long.
	 * Passa os valores para um vetor num�rico do tipo long.
	 * @param array vetor com os objetos do tipo long.
	 * @return vetor long com os valores dos objetos.
	 */

	public static long[] parseArrayLong(Long[] array)
	{
		long parse[] = new long[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i] == null ? 0 : array[i];

		return parse;
	}

	/**
	 * Obt�m objetos long a partir dos valores num�ricos long.
	 * @param array vetor com os valores num�ricos do tipo long.
	 * @return vetor com os objetos long dos n�meros long.
	 */

	public static long[] parseArrayInt(long[] array)
	{
		long parse[] = new long[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i];

		return parse;
	}

	/**
	 * Copia uma parte de um determinado vetor de n�meros long. O modo de c�pia
	 * � feito por um ponto inicial <b>offset</b> (�ndice). A partir deste
	 * ponto ele ir� obter os pr�ximos n �ndices e inseri-los no vetor.
	 * @param array vetor que ser� copiado uma parte.
	 * @param offset ponto inicial da c�pia (�ndice).
	 * @param length quantos elementos ser�o copiados.
	 * @return vetor apenas com os elementos copiados.
	 */

	public static long[] subarray(long[] array, int offset, int length)
	{
		if (offset > array.length - 1 || offset + length > array.length)
			return new long[]{};

		long select[] = new long[length];

		for (int i = 0; i < length; i++)
			select[i] = array[offset + i];

		return select;
	}

	/**
	 * Obt�m o valor percentual long entre dois n�meros.
	 * Apesar de serem double, podem ser inseridos de quaisquer
	 * tipos. Onde um se insere o valor que seria igual a 100%
	 * e o outro o valor que deseja saber a porcentagem.
	 * @param complete valor que deseja obter a porcentagem.
	 * @param total valor que representa 100% da quantidade.
	 * @return percentual long dos valores acima.
	 */

	public static long toPorcent(double complete, double total)
	{
		return (long) ((double) (complete / total) * 100);
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

	public static long limit(long value, long min, long max)
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

	public static long min(long value, long min)
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

	public static long max(long value, long max)
	{
		return value > max ? max : value;
	}

	/**
	 * Permite obter o valor de um objeto do tipo Long ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @return valor real do objeto ou 0 se for nulo.
	 */

	public static long valueOf(Long value)
	{
		return valueOf(value, 0L);
	}

	/**
	 * Permite obter o valor de um objeto do tipo Long ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @param valueNull valor que ser� retornado cao seja nulo.
	 * @return valor real do objeto ou valueNull se for nulo.
	 */

	public static long valueOf(Long value, long valueNull)
	{
		return value == null ? valueNull : value;
	}
}
