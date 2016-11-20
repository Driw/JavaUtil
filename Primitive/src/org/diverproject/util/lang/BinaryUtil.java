package org.diverproject.util.lang;

/**
 * <p><h1>Utilit�rio para Bin�rios</h1></p>
 *
 * Os utilit�rios em UBinary permitem trabalhar com valores do tipo bin�rio.
 * A linguagem java n�o possui atributos do tipo bin�rio, eles s�o salvos em strings,
 * assim sendo os valores bin�rios quando utilizados por determinados m�todos,
 * ir�o verificar a consist�ncia dos dados validando-os. � poss�vel transformar
 * diversos outros tipos de vari�veis em n�meros bin�rios como int, long e char.
 */

public class BinaryUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private BinaryUtil()
	{
		
	}

	/**
	 * Verifica se uma determinada string � de fato um num�rico bin�rio.
	 * N�meros bin�rios ir�o conter somente os n�meros 0 e/ou 1, quaisquer
	 * outros valores sejam letras ou n�meros, n�o s�o aceitos por este tipo.
	 * @param binary string contendo o valor bin�rio que ser� verificado.
	 * @return true se for v�lido ou false caso contr�rio.
	 */

	public static boolean isBinary(String binary)
	{
		if (binary == null || binary.length() == 0)
			return false;

		for (char c : binary.toCharArray())
			if (!(c == '0' || c == '1'))
				return false;

		return true;
	}

	/**
	 * Este m�todo serve apenas para vis�o dos dados bin�rios, em alguns casos os dados
	 * bin�rios v�em em um n�mero exato de casas bin�rias, sendo preenchidos com v�rios
	 * zeros no inicio da string. Com este m�todo qualquer zero no inicio da string ser�
	 * removido (apenas aqueles que n�o alterem o verdadeiro valor do n�mero bin�rio).
	 * @param binary valor bin�rio do qual ser� removido os zeros desnecess�rios.
	 * @return valor bin�rio limpo sem os zeros na frente.
	 */

	public static int removeZeros(String binary)
	{
		return StringUtil.remInitWhile(binary, "0").length();
	}

	/**
	 * Converte um determinado caracter em um n�mero bin�rio. A convers�o � feita
	 * a partir do c�digo ascii do caracter que permite obter um n�mero inteiro
	 * que pode ser convertido para um n�mero bin�rio.
	 * @param character que ser� convertido para bin�rio.
	 * @return valor bin�rio do caracter convertido.
	 */

	public static String parse(char character)
	{
		return Integer.toBinaryString((int) character);
	}

	/**
	 * <p>Converte v�rios caracteres seguidos em n�meros bin�rios. Caso valor bin�rio
	 * dos caracteres ir�o ocupar at� 16 casas bin�rias, totalizando um valor m�ximo
	 * de 65535 por caracter. Os valores bin�rios s�o unidos de acordo com a ordem dos
	 * caracteres inseridos por par�metro.</p>
	 * <i>Este m�todo � utilizado para complementar convers�es.</i>
	 * @param characters que ser�o convertidos para bin�rio.
	 * @return valor bin�rio dos caracteres convertidos.
	 */

	public static String parse(char[] characters)
	{
		String binary = "";

		for (int i = 0; i < characters.length; i++)
			binary += StringUtil.addStartWhile(parse(characters[i]), "0", 16);

		return binary;
	}

	/**
	 * <p>Analisa um determinado byte e o converte para um valor bin�rio.
	 * Cada byte pode possuir no m�ximo 8 bits (8 casas bin�rias).</p>
	 * <i>N�o retorna complementa��o com 0 (zeros) no valor retornado.</i>
	 * @param value byte que ser� convertido para bin�rio.
	 * @return valor bin�rio do byte convertido.
	 */

	public static String parse(byte value)
	{
		return Integer.toBinaryString((int) value);
	}

	/**
	 * Analisa v�rios bytes seguidos e converte todos eles para valores bin�rios.
	 * Onde cada byte poder� possuir no m�ximo 8 bits (8 casa bin�rias)>.
	 * Os valores bin�rios obtidos s�o todos salvos dentro de uma �nica
	 * string, cada byte ir� ocupar 8 casas bin�rias para inserir seu valor.
	 * @param array vetor de bytes que ser�o convertidos para bin�rios.
	 * @return string �nica com o valor bin�rio dos bytes inseridos.
	 */

	public static String parse(byte[] array)
	{
		String binary = "";

		for (int i = 0; i < array.length; i++)
			binary += StringUtil.addStartWhile(parse(array[i]), "0", 8);

		return binary;
	}

	/**
	 * Transforma um valor bin�rio para um valor num�rico 'long'.
	 * Caso o valor n�o seja num�rico, ser� retornado 0 e se o
	 * valor bin�rio ultrapassar as 63 casas bin�rias limites, ser�
	 * considerado automaticamente como o valor m�ximo de um 'long'.
	 * @param string valor bin�rios que ser� convertido para long.
	 * @return valor num�rico do tipo 'long' convertido.
	 */

	public static long toLong(String string)
	{
		if (!isBinary(string))
			return 0;

		if (string.length() > 63)
			return Long.MAX_VALUE;

		return Long.parseLong(string, 2);
	}

	/**
	 * Transforma um valor bin�rio para um valor num�rico 'int'.
	 * Caso o valor n�o seja num�rico, ser� retornado 0 e se o
	 * valor bin�rio ultrapassar as 31 casas bin�rias limites, ser�
	 * considerado automaticamente como o valor m�ximo de um 'int'.
	 * @param string valor bin�rios que ser� convertido para int.
	 * @return valor num�rico do tipo 'int' convertido.
	 */

	public static int toInt(String value)
	{
		if (!isBinary(value))
			return 0;

		if (value.length() > 31)
			return Integer.MAX_VALUE;

		return Integer.parseInt(value, 2);
	}

	/**
	 * Transforma um valor bin�rio para um valor num�rico 'short'.
	 * Caso o valor n�o seja num�rico, ser� retornado 0 e se o
	 * valor bin�rio ultrapassar as 7 casas bin�rias limites, ser�
	 * considerado automaticamente como o valor m�ximo de um 'short'.
	 * @param string valor bin�rios que ser� convertido para short.
	 * @return valor num�rico do tipo 'short' convertido.
	 */

	public static short toShort(String value)
	{
		if (!isBinary(value))
			return 0;

		if (value.length() > 7)
			return 127;

		return (short) Integer.parseInt(value, 2);
	}

	/**
	 * Transforma um determinado valor bin�rio em um caracter.
	 * Esse m�todo deve ser utilizado apenas para valores bin�rios
	 * que sejam menor do que 65535, caso contr�rio o caracter
	 * n�o ir� corresponder corretamente. A tabela ascii agrega
	 * um limite de bytes por caracter, resultando em 65535 slots.
	 * @param string valor bin�rio que ser� convertido para caracter.
	 * @return caracter convertido a partir do valor bin�rio.
	 */

	public static char toChar(String string)
	{
		return (char) toInt(string);
	}

	/**
	 * Transforma um determinado valor bin�rio em um byte.
	 * Esse m�todo deve ser utilizado apenas para valores bin�rios
	 * que sejam menor do que 127, caso contr�rio o byte gerado
	 * ser� retornado como -1 pois n�o o limite � de 127 possibilitadas.
	 * @param string valor bin�rio que ser� convertido para um byte.
	 * @return byte convertido a partir do valor bin�rio.
	 */

	public static byte toByte(String string)
	{
		return (byte) toInt(string);
	}

	/**
	 * Dado um determinado vetor de n�meros do tipo 'long',
	 * este m�todo ir� converter respectivamente os valores
	 * para um vetor de string contendo seus valores bin�rios.
	 * @param array vetor num�rico que ser� convertido.
	 * @return vetor dos bin�rios obtidos a partir de array.
	 */

	public static String[] parseArray(long[] array)
	{
		if (array == null)
			return null;

		String parse[] = new String[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = Long.toBinaryString(array[i]);

		return parse;
	}

	/**
	 * Dado um determinado vetor de n�meros do tipo 'int',
	 * este m�todo ir� converter respectivamente os valores
	 * para um vetor de string contendo seus valores bin�rios.
	 * @param array vetor num�rico que ser� convertido.
	 * @return vetor dos bin�rios obtidos a partir de array.
	 */

	public static String[] parseArray(int[] array)
	{
		if (array == null)
			return null;

		String parse[] = new String[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = Integer.toBinaryString(array[i]);

		return parse;
	}

	/**
	 * Dado um determinado vetor de n�meros do tipo 'short',
	 * este m�todo ir� converter respectivamente os valores
	 * para um vetor de string contendo seus valores bin�rios.
	 * @param array vetor num�rico que ser� convertido.
	 * @return vetor dos bin�rios obtidos a partir de array.
	 */

	public static String[] parseArray(short[] array)
	{
		if (array == null)
			return null;

		String parse[] = new String[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = Integer.toBinaryString(array[i]);

		return parse;
	}

	/**
	 * Dado um determinado vetor de caracteres �nicos,
	 * este m�todo ir� converter respectivamente os valores
	 * para um vetor de string contendo seus valores bin�rios.
	 * @param array vetor de caracteres que ser� convertido.
	 * @return vetor dos bin�rios obtidos a partir de array.
	 */

	public static String[] parseArray(char[] array)
	{
		if (array == null)
			return null;

		String parse[] = new String[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = parse(array[i]);

		return parse;
	}

	/**
	 * Dado um determinado vetor de bytes,
	 * este m�todo ir� converter respectivamente os valores
	 * para um vetor de string contendo seus valores bin�rios.
	 * @param array vetor de bytes que ser� convertido.
	 * @return vetor dos bin�rios obtidos a partir de array.
	 */

	public static String[] parseArray(byte[] array)
	{
		if (array == null)
			return null;

		String parse[] = new String[array.length];

		for (int i = 0; i < array.length; i++)
			parse[i] = parse(array[i]);

		return parse;
	}
}
