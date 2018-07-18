package org.diverproject.util.lang;

import java.nio.ByteBuffer;

import org.diverproject.util.UtilException;
import org.diverproject.util.collection.StringExtend;

/**
 * <p><h1>Utilit�rio para Float</h1></p>
 *
 * Classe com m�todos utilit�rios para manusear valores do tipo float.
 * Todos os m�todos envolvem retornar atributos do tipo <b>float</b>.
 * Em alguns casos h� m�todos que retornam objetos do tipo <b>float</b>.
 * Apesar de ambos serem do mesmo tipo, algumas opera��es necessitam
 * utilizar o objeto ao inv�s do atributo, e vice-versa.
 */

public class FloatUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private FloatUtil()
	{
		
	}

	/**
	 * Exibe um determinado vetor de floats no console separado por �ndice.
	 * Por padr�o esse m�todo ir� exibir apenas 20 elementos por linha.
	 * @param array vetor com os n�meros do tipo float que ser�o exibidos no console.
	 */

	public static void print(float[] array)
	{
		print((float) 20, array);
	}

	/**
	 * Exibe um determinado vetor de floats no console em linhas.
	 * @param perline quantos elementos ser�o exibidos por linha.
	 * @param array vetor com os caracteres que ser�o impressos.
	 */

	public static void print(float perline, float[] array)
	{
		for (int i = 0; i < array.length; i++)
		{
			if (i % perline == 0)
				System.out.printf("\ni%4d: ", i + 1);

			System.out.printf("[%.5f]", array[i]);
		}

		System.out.println();
	}

	/**
	 * Verifica se uma determinada string pode ser convertida para um n�mero float.
	 * Valida se a string � composta apenas por n�meros seja positivo ou negativo, e
	 * por fim verifica se est� dentro dos limites permitidos para um n�mero float.
	 * @param str string do qual deve ser verificada se pode converter.
	 * @return true se for poss�vel converter para um n�mero float.
	 */

	public static boolean isFloat(String str)
	{
		if (!StringUtil.isDecimal(str))
			return false;

		if (str.length() > (str.startsWith("-") ? 6 : 5))
			return false;

		if (str.startsWith("-") && str.length() == 6 && str.compareTo(Float.toString(Float.MIN_VALUE)) > 0)
			return false;

		else if (str.length() == 5 && str.compareTo(Float.toString(Float.MAX_VALUE)) > 0)
			return false;

		return true;
	}

	/**
	 * Percorre uma array de strings verificando a string pode ser convertida para float.
	 * @param array vetor que ser� verificado se pode ser convertido em float.
	 * @return true se todas as string podem ser convertidas ou false caso uma ou mais n�o consiga.
	 */

	public static boolean isfloat(String[] array)
	{
		for (String string : array)
			if (!isFloat(string))
				return false;

		return true;
	}

	/**
	 * M�todo que deve fazer a convers�o de uma string para valor float.
	 * Nesse caso h� a exist�ncia uma exception e deve ser tratada.
	 * @param value string contendo o valor num�rico a ser convertido.
	 * @return valor convertido a partir da string passada.
	 * @throws UtilException string nula, em branco, muito grande.
	 */

	public static float parseString(String str) throws UtilException
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

		float value = 0.0f;
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
	 * Converte uma determinada string para um valor do tipo num�rico float.
	 * @param str string do qual deve ser convertida para um n�mero float.
	 * @return valor num�rico da string passada ou 0 caso n�o seja num�rico.
	 */

	public static float parseByte(String str)
	{
		return parse(str, (float) 0);
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico float.
	 * @param str string do qual deve ser convertida para um n�mero float.
	 * @param fail valor que ser� retornado caso a string n�o seja num�rica.
	 * @return valor num�rico da string se for v�lida ou <b>fail</b> caso contr�rio.
	 */

	public static float parse(String str)
	{
		return parse(str, 0.0f);
	}

	/**
	 * Converte uma determinada string para um valor do tipo num�rico float.
	 * @param str string do qual deve ser convertida para um n�mero float.
	 * @param fail valor que ser� retornado caso a string n�o seja num�rica.
	 * @return valor num�rico da string se for v�lida ou <b>fail</b> caso contr�rio.
	 */

	public static float parse(String str, float fail)
	{
		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException e) {
			return fail;
		}
	}

	/**
	 * Um byte possui um valor de at� 8 bits que equivalem a um valor bin�rio
	 * entre 0 e 255. Esse m�todo permite obter esse valor bin�rio (decimal).
	 * @param b byte que ser� convertido para um valor num�rico float.
	 * @return valor num�rico float de 0 a 255 do byte respectivo.
	 */

	public static float parseByte(byte b)
	{
		float value = (float) b;

		return (float) (value >= 0 ? value : 256 + value);
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero float a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * @param bytes vetor contendo os bytes que deve criar o n�mero float.
	 * @return n�mero float criado a partir dos bytes passado.
	 */

	public static float parseBytes(byte[] bytes)
	{
		if (bytes == null || bytes.length == 0 || bytes.length > 4)
			return 0;

		return ByteBuffer.wrap(bytes).getFloat();
	}

	/**
	 * Analisa um vetor de bytes e tenta criar um n�mero float a partir destes bytes.
	 * Quando o vetor de bytes for nulo, branco ou mais de 4 �ndices retorna 0 (inv�lido).
	 * Esse m�todo tamb�m ir� inverter a ordem de como os bytes devem ser posicionados.
	 * @param bytes vetor contendo os bytes que deve criar o n�mero float.
	 * @return n�mero float criado a partir dos bytes passado.
	 */

	public static float parseBytesInvert(byte[] bytes)
	{
		if (bytes == null || bytes.length == 0 || bytes.length > 4)
			return 0;

		return ByteBuffer.wrap(ByteUtil.invertArray(bytes)).getFloat();
	}

	/**
	 * Converte um determinado vetor de strings para valores num�ricos
	 * do tipo float. O m�todo � feito percorrendo o vetor e passando
	 * as strings para o m�todo parse(array[i]) de modo que utiliza as
	 * regras definidas na documenta��o do mesmo.
	 * @param array vetor de strings que ser�o convertidas para float.
	 * @return vetor com n�meros do tipo float.
	 */

	public static float[] parseArray(String[] array)
	{
		float parse[] = new float[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = parseByte(array[i]);

		return parse;
	}

	/**
	 * Obt�m o c�digo ascii de todos os caracteres do vetor atrav�s
	 * de um simples cast de char para float. Os valores s�o salvos
	 * em um novo vetor do tipo float armazenando respectivamente.
	 * @param array vetor dos caracteres que ser�o convertidos.
	 * @return vetor com c�digo ascii salvos em um vetor float.
	 */

	public static float[] parseArrayChar(char[] array)
	{
		float parse[] = new float[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = (float) array[i];

		return parse;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar valores primitivos.
	 * @param array vetor de objetos do tipo float a ser usado.
	 * @return vetor de valores primitivos do tipo float criado.
	 */

	public static float[] parseArrayFloat(Float[] array)
	{
		float[] parsed = new float[array.length];

		for (int i = 0; i < parsed.length; i++)
			parsed[i] = array[i] == null ? 0 : array[i];

		return parsed;
	}

	/**
	 * Constr�i um novo vetor que ir� armazenar objetos do tipo float.
	 * @param array vetor de valores primitivos do tipo float.
	 * @return vetor de objetos do tipo float que foi criado.
	 */

	public static Float[] parseArrayFloat(float[] array)
	{
		Float parse[] = new Float[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i];

		return parse;
	}

	/**
	 * Obt�m objetos float a partir dos valores num�ricos float.
	 * @param array vetor com os valores num�ricos do tipo float.
	 * @return vetor com os objetos float dos n�meros float.
	 */

	public static float[] parseArrayInt(float[] array)
	{
		float parse[] = new float[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = array[i];

		return parse;
	}

	/**
	 * Copia uma parte de um determinado vetor de n�meros float. O modo de c�pia
	 * � feito por um ponto inicial <b>offset</b> (�ndice). A partir deste
	 * ponto ele ir� obter os pr�ximos n �ndices e inseri-los no vetor.
	 * @param array vetor que ser� copiado uma parte.
	 * @param offset ponto inicial da c�pia (�ndice).
	 * @param length quantos elementos ser�o copiados.
	 * @return vetor apenas com os elementos copiados.
	 */

	public static float[] subarray(float[] array, int offset, int length)
	{
		if (offset > array.length - 1 || offset + length > array.length)
			return new float[]{};

		float select[] = new float[length];

		for (int i = 0; i < length; i++)
			select[i] = array[offset + i];

		return select;
	}

	/**
	 * Obt�m o valor percentual float entre dois n�meros.
	 * Apesar de serem double, podem ser inseridos de quaisquer
	 * tipos. Onde um se insere o valor que seria igual a 100%
	 * e o outro o valor que deseja saber a porcentagem.
	 * @param complete valor que deseja obter a porcentagem.
	 * @param total valor que representa 100% da quantidade.
	 * @return percentual float dos valores acima.
	 */

	public static float toPorcent(double complete, double total)
	{
		return (float) ((double) (complete / total) * 100);
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

	public static float limit(float value, float min, float max)
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

	public static float min(float value, float min)
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

	public static float max(float value, float max)
	{
		return value > max ? max : value;
	}

	/**
	 * Faz uma verifica��o para retornar o maior n�mero.
	 * @param a primeiro valor flutuante.
	 * @param b segundo valor flutuante.
	 * @return maior valor flutuante.
	 */

	public static float major(float a, float b)
	{
		return a > b ? a : b;
	}

	/**
	 * Faz a compara��o entre dois n�meros flutuantes a fim de obter um resultado.
	 * @param a primeiro n�mero, quem ser� comparado com quem.
	 * @param b segundo n�mero, usado para fazer a compara��o.
	 * @return (1) se for maior, (-1) se for menor ou (0) se for igual,
	 * a compara��o � feita entre o valor A com B e n�o B com A.
	 */

	public static float compare(float a, float b)
	{
		if (a > b)
			return 1;

		else if (a < b)
			return -1;

		return 0;
	}

	/**
	 * Faz uma verifica��o para retornar o menor n�mero.
	 * @param a primeiro valor flutuante.
	 * @param b segundo valor flutuante.
	 * @return menor valor flutuante.
	 */

	public static float minor(float a, float b)
	{
		return a < b ? a : b;
	}

	/**
	 * Permite obter o valor de um objeto do tipo Float ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @return valor real do objeto ou 0 se for nulo.
	 */

	public static float valueOf(Float value)
	{
		return valueOf(value, 0L);
	}

	/**
	 * Permite obter o valor de um objeto do tipo Float ainda que nulo.
	 * @param value refer�ncia do objeto que ser� obtido seu valor.
	 * @param valueNull valor que ser� retornado cao seja nulo.
	 * @return valor real do objeto ou valueNull se for nulo.
	 */

	public static float valueOf(Float value, float valueNull)
	{
		return value == null ? valueNull : value;
	}

	/**
	 * Verifica se um determinado valor est� ou n�o dentro do limite estabelecido.
	 * @param value valor do qual deve ser verificado se est� no limite.
	 * @param min limite m�nimo do intervalo para ser v�lido.
	 * @param max limite m�ximo do intervalo para ser v�lido.
	 * @return true se estiver dentro do limite ou false caso contr�rio.
	 */

	public static boolean interval(float value, float min, float max)
	{
		return value >= min && value <= max;
	}
}
