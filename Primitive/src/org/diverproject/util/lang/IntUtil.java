package org.diverproject.util.lang;

import java.nio.ByteBuffer;

import org.diverproject.util.UtilException;

/**
 * <p><h1>Utilit�rio para Int</h1></p>
 *
 * Classe com m�todos utilit�rios para manusear valores do tipo int.
 * Todos os m�todos envolvem retornar atributos do tipo <b>int</b>.
 * Em alguns casos h� m�todos que retornam objetos do tipo <b>Integer</b>.
 * Apesar de ambos serem do mesmo tipo, algumas opera��es necessitam
 * utilizar o objeto ao inv�s do atributo, e vice-versa.
 */

public class IntUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private IntUtil()
	{
		
	}

	/**
	 * Exibe um determinado vetor de inteiros no console separado por �ndice.
	 * Por padr�o esse m�todo ir� exibir apenas 20 elementos por linha.
	 * @param array vetor com os n�meros do tipo long que ser�o exibidos no console.
	 */

	public static void print(int[] array)
	{
		print((short) 20, array);
	}

	/**
	 * Exibe um determinado vetor de inteiros no console em linhas.
	 * @param perline quantos elementos ser�o exibidos por linha.
	 * @param array vetor com os caracteres que ser�o impressos.
	 */

	public static void print(short perline, int[] array)
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
	 * Verifica se uma determinada string pode ser convertida para um n�mero inteiro.
	 * Valida se a string � composta apenas por n�meros seja positivo ou negativo, e
	 * por fim verifica se est� dentro dos limites permitidos para um n�mero inteiro.
	 * @param str string do qual deve ser verificada se pode converter.
	 * @return true se for poss�vel converter para um n�mero inteiro.
	 */

	public static boolean isInteger(String str)
	{
		if (!StringUtil.isNumber(str))
			return false;

		if (str.length() > (str.startsWith("-") ? 11 : 10))
			return false;

		if (str.startsWith("-") && str.length() == 11 && str.compareTo(Integer.toString(Integer.MIN_VALUE)) > 0)
			return false;

		else if (str.length() == 10 && str.compareTo(Integer.toString(Integer.MAX_VALUE)) > 0)
			return false;

		return true;
	}

	/**
	 * Percorre uma array de strings verificando a string pode ser convertida para inteiro.
	 * @param array vetor que ser� verificado se pode ser convertido em inteiro.
	 * @return true se todas as string podem ser convertidas ou false caso uma ou mais n�o consiga.
	 */

	public static boolean isInteger(String[] array)
	{
		for (String string : array)
			if (!isInteger(string))
				return false;

		return true;
	}

	/**
	 * Faz toda uma verifica��o para garantir que uma determina seja convertida para um n�mero inteiro.
	 * Ir� considerar valores hexadecimais apenas se forem seguidos do pr�-fixo 0x que ir� determinar tal.
	 * @param value string contendo o valor num�rico ou hexadecimal do qual dever� ser convertido.
	 * @return valor num�rico contido na string passada se assim for um.
	 * @throws UtilException apenas se houver falha na convers�o.
	 */

	public static int parseString(String value) throws UtilException
	{
		if (value.startsWith("0x"))
			return HexUtil.parseInt(value);

		StringUtil.isParseNumber(value);

		boolean negative = false;

		if (value.startsWith("-"))
			negative = true;

		if (negative)
		{
			if (value.length() > Integer.toString(Integer.MIN_VALUE).length())
				throw new UtilException("valor muito pequeno");
		}

		else if (value.length() > Integer.toString(Integer.MAX_VALUE).length())
			throw new UtilException("valor muito grande");

		int parse = 0;

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
	 * Converte uma determinada string para um valor do tipo num�rico inteiro.
	 * @param str string do qual deve ser convertida para um n�mero inteiro.
	 * @return valor num�rico da string passada ou 0 caso n�o seja num�rico.
	 */

	public static int parse(String str)
	{
		return parse(str, 0);
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico inteiro.
	 * @param str string do qual deve ser convertida para um n�mero inteiro.
	 * @param fail valor que ser� retornado caso a string n�o seja num�rica.
	 * @return valor num�rico da string se for v�lida ou <b>fail</b> caso contr�rio.
	 */

	public static int parse(String str, int fail)
	{
		if (!isInteger(str))
			return fail;

		return Integer.parseInt(str);
	}

	/**
	 * Um byte possui um valor de at� 8 bits que equivalem a um valor bin�rio
	 * entre 0 e 255. Esse m�todo permite obter esse valor bin�rio (decimal).
	 * @param b byte que ser� convertido para um valor num�rico inteiro.
	 * @return valor num�rico inteiro de 0 a 255 do byte respectivo.
	 */

	public static int parseByte(byte b)
	{
		int value = (int) b;

		return value >= 0 ? value : value + 256;
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero inteiro a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * @param bytes vetor contendo os bytes que deve criar o n�mero inteiro.
	 * @return n�mero inteiro criado a partir dos bytes passado.
	 */

	public static int parseBytes(byte[] bytes)
	{
		int value = 0;

		int count = IntUtil.minor(bytes.length, Integer.BYTES);

		for (int i = 0, j = 0; i < count; i++, j += 8)
		{
			int parsed = (bytes[bytes.length - i - 1] & 255) << j;
			value |= parsed;
		}

		return value;
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero inteiro a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * Esse m�todo tamb�m ir� inverter a ordem de como os bytes devem ser posicionados.
	 * @param bytes vetor contendo os bytes que deve criar o n�mero inteiro.
	 * @return n�mero inteiro criado a partir dos bytes passado.
	 */

	public static int parseBytesInvert(byte[] bytes)
	{
		int value = 0;

		int count = IntUtil.minor(bytes.length, Long.BYTES);

		for (int i = 0, j = 0; i < count; i++, j += 8)
		{
			int parsed = (bytes[i] & 255) << j;
			value |= parsed;
		}

		return value;
	}

	/**
	 * Converte uma determinada string em valor hexadecimal em n�mero inteiro.
	 * Caso o valor n�o seja um hexadecimal ser� retornado 0 por padr�o da convers�o.
	 * @param str valor hexadecimal salvo em uma string (com ou sem 0x no inicio).
	 * @return valor num�rico inteiro obtido da convers�o do valor em hexadecimal.
	 */

	public static int parseHex(String str)
	{
		if (!HexUtil.isHexInt(str))
			return 0;

		return Integer.parseInt(HexUtil.clearHex(str), 16);
	}

	/**
	 * Converte um determinado vetor de strings para valores num�ricos
	 * do tipo int. O m�todo � feito percorrendo o vetor e passando
	 * as strings para o m�todo parse(array[i]) de modo que utiliza as
	 * regras definidas na documenta��o do mesmo.
	 * @param array vetor de strings que ser�o convertidas para int.
	 * @return vetor com n�meros do tipo int.
	 */

	public static int[] parseArray(String[] array)
	{
		int parse[] = new int[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = parse(array[i]);

		return parse;
	}

	/**
	 * Obt�m o c�digo ascii de todos os caracteres do vetor atrav�s
	 * de um simples cast de char para int. Os valores s�o salvos
	 * em um novo vetor do tipo int armazenando respectivamente.
	 * @param array vetor dos caracteres que ser�o convertidos.
	 * @return vetor com c�digo ascii salvos em um vetor int.
	 */

	public static int[] parseArrayChar(char[] array)
	{
		int parse[] = new int[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = (int) array[i];

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar valores primitivos.
	 * @param array vetor de objetos do tipo int a ser usado.
	 * @return vetor de valores primitivos do tipo int criado.
	 */

	public static int[] parseArrayInteger(Integer[] array)
	{
		int parse[] = new int[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i] == null ? 0 : array[i];

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar objetos do tipo int.
	 * @param array vetor de valores primitivos do tipo int.
	 * @return vetor de objetos do tipo int que foi criado.
	 */

	public static Integer[] parseArrayInteger(int[] array)
	{
		Integer parse[] = new Integer[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i];

		return parse;
	}

	/**
	 * Copia uma parte de um determinado vetor de n�meros int. O modo de c�pia
	 * � feito por um ponto inicial <b>offset</b> (�ndice). A partir deste
	 * ponto ele ir� obter os pr�ximos n �ndices e inseri-los no vetor.
	 * @param array vetor que ser� copiado uma parte.
	 * @param offset ponto inicial da c�pia (�ndice).
	 * @param length quantos elementos ser�o copiados.
	 * @return vetor apenas com os elementos copiados.
	 */

	public static int[] subarray(int[] array, int offset, int length)
	{
		if (offset > array.length - 1 || offset + length > array.length)
			return new int[]{};

		int select[] = new int[length];

		for (int i = 0; i < length; i++)
			select[i] = array[offset + i];

		return select;
	}

	/**
	 * Inverte a ordem da posi��o dos n�meros inteiros de um vetor de int.
	 * @param array vetor de n�meros inteiros que ter� os valores invertidos.
	 * @return vetor com a ordem dos n�meros inteiros invertidos.
	 */

	public static int[] invert(int[] array)
	{
		int[] tbuff = new int[array.length];

		for (int i = 0; i < array.length; i++)
			tbuff[i] = array[array.length - i - 1];

		return tbuff;
	}

	/**
	 * Inverte a ordem dos valores dos bytes que criam este valor int.
	 * @param value n�mero inteiro que ter� os bytes invertidos.
	 * @return n�mero inteiro com os bytes invertidos.
	 */

	public static int invert(int value)
	{
		return ByteBuffer.wrap(ByteUtil.invertArray(ByteUtil.parseInt(value))).getInt();
	}

	/**
	 * Obt�m o valor percentual inteiro entre dois n�meros.
	 * Apesar de serem double, podem ser inseridos de quaisquer
	 * tipos. Onde um se insere o valor que seria igual a 100%
	 * e o outro o valor que deseja saber a porcentagem.
	 * @param complete valor que deseja obter a porcentagem.
	 * @param total valor que representa 100% da quantidade.
	 * @return percentual inteiro dos valores acima.
	 */

	public static int toPorcent(double complete, double total)
	{
		return (int) ((double) (complete / total) * 100);
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

	public static int limit(int value, int min, int max)
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

	public static int min(int value, int min)
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

	public static int max(int value, int max)
	{
		return value > max ? max : value;
	}

	/**
	 * Faz a divis�o de dois n�meros inteiros, em seguida verifica se o resultado
	 * ser� o n�mero arredondado para cima ou se for exato adiciona mais um.
	 * @param val valor do qual deve ser dividido.
	 * @param coe coeficiente da divis�o.
	 * @return resultado de acordo com as especifica��es.
	 */

	public static int div(int val, int coe)
	{
		return val/coe + (val % coe > 0 ? 1 : 0);
	}

	/**
	 * Faz uma verifica��o para retornar o maior n�mero.
	 * @param a primeiro valor inteiro.
	 * @param b segundo valor inteiro.
	 * @return maior valor inteiro.
	 */

	public static int major(int a, int b)
	{
		return a > b ? a : b;
	}

	/**
	 * Faz a compara��o entre dois n�meros inteiros a fim de obter um resultado.
	 * @param a primeiro n�mero, quem ser� comparado com quem.
	 * @param b segundo n�mero, usado para fazer a compara��o.
	 * @return (1) se for maior, (-1) se for menor ou (0) se for igual,
	 * a compara��o � feita entre o valor A com B e n�o B com A.
	 */

	public static int compare(int a, int b)
	{
		if (a > b)
			return 1;

		else if (a < b)
			return -1;

		return 0;
	}

	/**
	 * Faz uma verifica��o para retornar o menor n�mero.
	 * @param a primeiro valor inteiro.
	 * @param b segundo valor inteiro.
	 * @return menor valor inteiro.
	 */

	public static int minor(int a, int b)
	{
		return a < b ? a : b;
	}

	/**
	 * Verifica se um determinado valor est� ou n�o dentro do limite estabelecido.
	 * @param value valor do qual deve ser verificado se est� no limite.
	 * @param min limite m�nimo do intervalo para ser v�lido.
	 * @param max limite m�ximo do intervalo para ser v�lido.
	 * @return true se estiver dentro do limite ou false caso contr�rio.
	 */

	public static boolean interval(int value, int min, int max)
	{
		return value >= min && value <= max;
	}

	/**
	 * Permite obter o valor de um objeto do tipo Integer ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @return valor real do objeto ou 0 se for nulo.
	 */

	public static int valueOf(Integer value)
	{
		return valueOf(value, 0);
	}

	/**
	 * Permite obter o valor de um objeto do tipo Integer ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @param valueNull valor que ser� retornado cao seja nulo.
	 * @return valor real do objeto ou valueNull se for nulo.
	 */

	public static int valueOf(Integer value, int valueNull)
	{
		return value == null ? valueNull : value;
	}

	/**
	 * Permite obter o valor da diferen�a entre dois n�meros quaisquer.
	 * A diferen�a sempre ir� considerar um valor positivo independente da ordem.
	 * @param a primeiro valor num�rico inteiro para realizar a compara��o.
	 * @param b segundo valor num�rico inteiro para realizar a compara��o.
	 * @return valor num�rico inteiro da diferen�a entre os dois valores.
	 */

	public static int diff(int a, int b)
	{
		if (a > b)
			return a - b;

		return b - a;
	}

	/**
	 * Faz a convers�o do valor absoluto de um n�mero long para um n�mero int.
	 * @param value valor num�rico de 8 bytes que ser� convertido em int.
	 * @return valor m�ximo de um int se value exced�-lo, valor m�nimo de um
	 * int se value exced�-lo ou o seu valor absoluto convertido para int.
	 */

	public static int cast(long value)
	{
		return value > Integer.MAX_VALUE ? Integer.MAX_VALUE : value < Integer.MIN_VALUE ? Integer.MIN_VALUE : (int) value;
	}
}
