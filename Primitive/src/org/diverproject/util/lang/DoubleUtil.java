package org.diverproject.util.lang;

import java.nio.ByteBuffer;

import org.diverproject.util.UtilException;
import org.diverproject.util.collection.StringExtend;

/**
 * <p><h1>Utilit�rio para Double</h1></p>
 *
 * Classe com m�todos utilit�rios para manusear valores do tipo double.
 * Todos os m�todos envolvem retornar atributos do tipo <b>double</b>.
 * Em alguns casos h� m�todos que retornam objetos do tipo <b>double</b>.
 * Apesar de ambos serem do mesmo tipo, algumas opera��es necessitam
 * utilizar o objeto ao inv�s do atributo, e vice-versa.
 */

public class DoubleUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private DoubleUtil()
	{
		
	}

	/**
	 * Exibe um determinado vetor de doubles no console separado por �ndice.
	 * Por padr�o esse m�todo ir� exibir apenas 20 elementos por linha.
	 * @param array vetor com os n�meros do tipo double que ser�o exibidos no console.
	 */

	public static void print(double[] array)
	{
		print((double) 20, array);
	}

	/**
	 * Exibe um determinado vetor de doubles no console em linhas.
	 * @param perline quantos elementos ser�o exibidos por linha.
	 * @param array vetor com os caracteres que ser�o impressos.
	 */

	public static void print(double perline, double[] array)
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
	 * Verifica se uma determinada string pode ser convertida para um n�mero double.
	 * Valida se a string � composta apenas por n�meros seja positivo ou negativo, e
	 * por fim verifica se est� dentro dos limites permitidos para um n�mero double.
	 * @param str string do qual deve ser verificada se pode converter.
	 * @return true se for poss�vel converter para um n�mero double.
	 */

	public static boolean isdouble(String str)
	{
		if (!StringUtil.isNumber(str))
			return false;

		if (str.length() > (str.startsWith("-") ? 6 : 5))
			return false;

		if (str.startsWith("-") && str.length() == 6 && str.compareTo(Double.toString(Double.MIN_VALUE)) > 0)
			return false;

		else if (str.length() == 5 && str.compareTo(Double.toString(Double.MAX_VALUE)) > 0)
			return false;

		return true;
	}

	/**
	 * Percorre uma array de strings verificando a string pode ser convertida para double.
	 * @param array vetor que ser� verificado se pode ser convertido em double.
	 * @return true se todas as string podem ser convertidas ou false caso uma ou mais n�o consiga.
	 */

	public static boolean isdouble(String[] array)
	{
		for (String string : array)
			if (!isdouble(string))
				return false;

		return true;
	}

	/**
	 * M�todo que deve fazer a convers�o de uma string para valor double.
	 * Nesse caso h� a exist�ncia uma exception e deve ser tratada.
	 * @param value string contendo o valor num�rico a ser convertido.
	 * @return valor convertido a partir da string passada.
	 * @throws UtilException string nula, em branco, muito grande.
	 */

	public static double parseString(String str) throws UtilException
	{
		StringUtil.isParseNumber(str);

		boolean negative = false;

		if (str.startsWith("-"))
		{
			str = str.substring(1, str.length());
			negative = true;
		}

		String splited[] = str.split("\\.|\\,");

		if (splited.length == 0)
			throw new UtilException("n�o � um float");

		if (splited.length > 2)
			throw new UtilException("muitos pontos decimais");

		if (splited[0].length() > 6)
			throw new UtilException("valor muito grande");

		double value = 0.0f;
		StringExtend root = new StringExtend(splited[0]);

		while (root.next())
			value = (value * 10) + Character.digit(root.get(), 10);

		if (splited.length == 2)
		{
			root = new StringExtend(splited[1]);

			for (int i = 1; root.next(); i++)
				value = value + (Character.digit(root.get(), 10) / (float) Math.pow(10, i));
		}

		return negative ? -value : value;
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico double.
	 * @param str string do qual deve ser convertida para um n�mero double.
	 * @return valor num�rico da string passada ou 0 caso n�o seja num�rico.
	 */

	public static double parseByte(String str)
	{
		return parse(str, (double) 0);
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico double.
	 * @param str string do qual deve ser convertida para um n�mero double.
	 * @param fail valor que ser� retornado caso a string n�o seja num�rica.
	 * @return valor num�rico da string se for v�lida ou <b>fail</b> caso contr�rio.
	 */

	public static double parse(String str, double fail)
	{
		if (!isdouble(str))
			return fail;

		return Double.parseDouble(str);
	}

	/**
	 * Um byte possui um valor de at� 8 bits que equivalem a um valor bin�rio
	 * entre 0 e 255. Esse m�todo permite obter esse valor bin�rio (decimal).
	 * @param b byte que ser� convertido para um valor num�rico double.
	 * @return valor num�rico double de 0 a 255 do byte respectivo.
	 */

	public static double parseByte(byte b)
	{
		double value = (double) b;

		return (double) (value >= 0 ? value : 256 + value);
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero double a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * @param bytes vetor contendo os bytes que deve criar o n�mero double.
	 * @return n�mero double criado a partir dos bytes passado.
	 */

	public static double parseBytes(byte[] bytes)
	{
		if (bytes == null || bytes.length == 0 || bytes.length > 4)
			return 0;

		return ByteBuffer.wrap(bytes).getDouble();
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero double a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * Esse m�todo tamb�m ir� inverter a ordem de como os bytes devem ser posicionados.
	 * @param bytes vetor contendo os bytes que deve criar o n�mero double.
	 * @return n�mero double criado a partir dos bytes passado.
	 */

	public static double parseBytesInvert(byte[] bytes)
	{
		if (bytes == null || bytes.length == 0 || bytes.length > 4)
			return 0;

		return ByteBuffer.wrap(ByteUtil.invertArray(bytes)).getDouble();
	}

	/**
	 * Converte um determinado vetor de strings para valores num�ricos
	 * do tipo double. O m�todo � feito percorrendo o vetor e passando
	 * as strings para o m�todo parse(array[i]) de modo que utiliza as
	 * regras definidas na documenta��o do mesmo.
	 * @param array vetor de strings que ser�o convertidas para double.
	 * @return vetor com n�meros do tipo double.
	 */

	public static double[] parseArray(String[] array)
	{
		double parse[] = new double[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = parseByte(array[i]);

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar valores primitivos.
	 * @param array vetor de objetos do tipo double a ser usado.
	 * @return vetor de valores primitivos do tipo double criado.
	 */

	public static double[] parseArrayChar(char[] array)
	{
		double parse[] = new double[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = (double) array[i];

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar objetos do tipo double.
	 * @param array vetor de valores primitivos do tipo double.
	 * @return vetor de objetos do tipo double que foi criado.
	 */

	public static Double[] parseArrayDouble(double[] array)
	{
		Double[] parsed = new Double[array.length];

		for (int i = 0; i < parsed.length; i++)
			parsed[i] = array[i];

		return parsed;
	}

	/**
	 * Obt�m o valor num�rico de objetos do tipo double.
	 * Passa os valores para um vetor num�rico do tipo double.
	 * @param array vetor com os objetos do tipo double.
	 * @return vetor double com os valores dos objetos.
	 */

	public static double[] parseArrayDouble(Double[] array)
	{
		double[] parsed = new double[array.length];

		for (int i = 0; i < parsed.length; i++)
			parsed[i] = array[i] == null ? 0 : array[i];

		return parsed;
	}

	/**
	 * Obt�m objetos double a partir dos valores num�ricos double.
	 * @param array vetor com os valores num�ricos do tipo double.
	 * @return vetor com os objetos double dos n�meros double.
	 */

	public static double[] parseArrayInt(double[] array)
	{
		double parse[] = new double[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i];

		return parse;
	}

	/**
	 * Copia uma parte de um determinado vetor de n�meros double. O modo de c�pia
	 * � feito por um ponto inicial <b>offset</b> (�ndice). A partir deste
	 * ponto ele ir� obter os pr�ximos n �ndices e inseri-los no vetor.
	 * @param array vetor que ser� copiado uma parte.
	 * @param offset ponto inicial da c�pia (�ndice).
	 * @param length quantos elementos ser�o copiados.
	 * @return vetor apenas com os elementos copiados.
	 */

	public static double[] subarray(double[] array, int offset, int length)
	{
		if (offset > array.length - 1 || offset + length > array.length)
			return new double[]{};

		double select[] = new double[length];

		for (int i = 0; i < length; i++)
			select[i] = array[offset + i];

		return select;
	}

	/**
	 * Obt�m o valor percentual double entre dois n�meros.
	 * Apesar de serem double, podem ser inseridos de quaisquer
	 * tipos. Onde um se insere o valor que seria igual a 100%
	 * e o outro o valor que deseja saber a porcentagem.
	 * @param complete valor que deseja obter a porcentagem.
	 * @param total valor que representa 100% da quantidade.
	 * @return percentual double dos valores acima.
	 */

	public static double toPorcent(double complete, double total)
	{
		return (double) ((double) (complete / total) * 100);
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

	public static double limit(double value, double min, double max)
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

	public static double min(double value, double min)
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

	public static double max(double value, double max)
	{
		return value > max ? max : value;
	}

	/**
	 * Permite obter o valor de um objeto do tipo Double ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @return valor real do objeto ou 0 se for nulo.
	 */

	public static double valueOf(Double value)
	{
		return valueOf(value, 0L);
	}

	/**
	 * Permite obter o valor de um objeto do tipo Double ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @param valueNull valor que ser� retornado cao seja nulo.
	 * @return valor real do objeto ou valueNull se for nulo.
	 */

	public static double valueOf(Double value, double valueNull)
	{
		return value == null ? valueNull : value;
	}
}
