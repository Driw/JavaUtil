package org.diverproject.util.lang;

import java.util.Iterator;
import java.util.Random;

import org.diverproject.util.UtilException;

/**
 * Classe com utilit�rio para trabalhar com vari�veis do tipo string.
 * Verificar se string � alpha, num�rico, alfanum�rico, separar em
 * tamanhos espec�ficos, obter uma �nica parte da string dentre outros
 * m�todos que relacionam string. Na maioria dos m�todos envolve
 * trabalhar com uma string retornando essa string que foi trabalhada.
 */

public class StringUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private StringUtil()
	{
		
	}

	/**
	 * Verifica se uma determinada string possui apenas letras.
	 * Este m�todo ainda n�o possui valida��o para acentua��es.
	 * @param str string que ter� seus caracteres verificados.
	 * @return true se possuir apenas letras ou false caso contr�rio.
	 */

	public static boolean isAlpha(String str)
	{
		/** @TODO validar acentua��o. */

		for (char c : str.toCharArray())
			if (!Character.isAlphabetic(c))
				return false;

		return true;
	}

	/**
	 * Obtem uma determinada parte da string, este m�todo
	 * ir� pegar n caracteres desde o inicio da string.
	 * Caso um tamanho maior do que o poss�vel for definido
	 * n�o ir� causar erro, apenas ir� obter o limite permidio.
	 * @param str string que do qual deseja obter uma parte.
	 * @param length quantos caracteres ser�o pegos.
	 * @return parte da string que foi cortada.
	 */

	public static String sub(String str, int length)
	{
		return sub(str, 0, length);
	}

	/**
	 * Obtem uma determinada parte da string, este m�todo
	 * ir� pegar n caracteres desde o inicio da string.
	 * Caso um tamanho maior do que o poss�vel for definido
	 * n�o ir� causar erro, apenas ir� obter o limite permidio.
	 * @param str string que do qual deseja obter uma parte.
	 * @param offset n�mero do primeiro caracter.
	 * @param length quantos caracteres ser�o pegos.
	 * @return parte da string que foi cortada.
	 */

	public static String sub(String str, int offset, int length)
	{
		if (offset < 0)
			offset = 0;

		if (offset + length > str.length())
			length = str.length() - offset;

		char selected[] = CharUtil.subarray(str.toCharArray(), offset, length);

		return new String(selected);
	}

	/**
	 * Adiciona um determinado valor ao inicio de uma string.
	 * O valor � adicionado at� que a string obtenha um tamanho
	 * especificado, caso o tamanho seja maior ou igual cancela.
	 * @param str string do qual ter� o valor adicionado no inicio.
	 * @param value valor que ser� adicionado um valor ao inicio.
	 * @param length qual o tamanho que a string deve possuir.
	 * @return string modificada com o(s) valor(es) adicionado(s).
	 */

	public static String addStartWhile(String str, String value, int length)
	{
		while (str.length() < length)
			str = value + str;

		return str;
	}

	/**
	 * Adiciona um determinado valor ao final de uma string.
	 * O valor � adicionado at� que a string obtenha um tamanho
	 * especificado, caso o tamanho seja maior ou igual cancela.
	 * @param str string do qual ter� o valor adicionado no final.
	 * @param value valor que ser� adicionado um valor ao final.
	 * @param length qual o tamanho que a string deve possuir.
	 * @return string modificada com o(s) valor(es) adicionado(s).
	 */

	public static String addEndWhile(String str, String value, int length)
	{
		while (str.length() < length)
			str += value;

		return str;
	}

	/**
	 * Adiciona um determinado valor ao inicio de uma string.
	 * O valor � adicionado at� que o tamanho da string seja divis�vel
	 * por um determinado n�mero, onde o seu resto � zero (MOD %).
	 * @param str string do qual ser� adicionado um valor ao inicio.
	 * @param value valor que ser� adicionado um valor ao inicio.
	 * @param mod n�mero pelo qual o tamanho dever� ser div�sivel.
	 * @return string modificada com o(s) valor(es) adicionado(s).
	 */

	public static String addStartMod(String str, String value, int mod)
	{
		while (str.length() % mod != 0)
			if (str.length() + value.length() > Integer.MAX_VALUE)
				return str;
			else
				str = value + str;

		return str;
	}

	/**
	 * Adiciona um determinado valor ao final de uma string.
	 * O valor � adicionado at� que o tamanho da string seja divis�vel
	 * por um determinado n�mero, onde o seu resto � zero (MOD %).
	 * @param str string do qual ser� adicionado um valor ao final.
	 * @param value valor que ser� adicionado um valor ao final.
	 * @param mod n�mero pelo qual o tamanho dever� ser div�sivel.
	 * @return string modificada com o(s) valor(es) adicionado(s).
	 */

	public static String addEndMod(String str, String value, int mod)
	{
		while (str.length() % mod != 0)
			if (str.length() + value.length() > Integer.MAX_VALUE)
				return str;
			else
				str += value;

		return str;
	}

	/**
	 * Divide uma determinada string a cada n caracteres.
	 * @param str string que ser� divida a cada n caracteres.
	 * @param numOfChars a cada quantos caracteres dever� ser divido.
	 * @return vetor com a partes da string divida.
	 */

	public static String[] split(String str, int numOfChars)
	{
		if (numOfChars < 0)
			numOfChars = 0;

		if (numOfChars > str.length())
			return new String[] { str };

		if (str == null || str.length() < numOfChars)
			return null;

		if (str.length() == 0)
			return new String[]{ "" };

		char chars[] = str.toCharArray();
		String parse[] = new String[(int) Math.ceil((double) ((double)str.length() / (double)numOfChars))];

		for (int i = 0; i < parse.length; i++)
			parse[i] = new String(CharUtil.subarray(chars, i * numOfChars, numOfChars));

		return parse;
	}

	/**
	 * Remove o inicio de uma string enquanto iniciar com um valor.
	 * <i>Foi criado para remover zeros do inicio de valores bin�rios</i>.
	 * @param str string que ter� o seu inicio removido.
	 * @param value valor que dever� possuir o inicio.
	 * @return string com o inicio removido se assim for poss�vel.
	 */

	public static String remInitWhile(String str, String value)
	{
		while (str.startsWith(value))
			str = str.substring(1, str.length() - 1);

		return str;
	}

	/**
	 * Verifica se uma determinada string pode ser analisada por outros procedimentos.
	 * @param str qual ser� a string que deve ser verificada se pode ser analisada.
	 * @throws UtilException string com valor nulo ou ainda ent�o em branco.
	 */

	public static void checkParse(String str) throws UtilException
	{
		if (str == null)
			throw new UtilException("valor nulo");

		if (str.isEmpty())
			throw new UtilException("valor em branco");
	}

	/**
	 * Verifica se uma determinada string � composta
	 * apenas por caracteres num�ricos [0~9].
	 * @param str string que ser� verificada.
	 * @return true se for ou false caso contr�rio.
	 */

	public static boolean isNumber(String str)
	{
		if (str.startsWith("-"))
			str = str.substring(1, str.length());

		if (str == null || str.length() == 0)
			return false;

		for (char c : str.toCharArray())
			if (!Character.isDigit(c))
				return false;

		return true;
	}

	/**
	 * Verifica se uma determinada string cont�m apenas valores num�ricos e no m�ximo um ponto decimal.
	 * Nesse caso considera que o ponto decimal possa ser tanto como ponto quanto v�rgula.
	 * @param str string contendo o valor que ser� verificado se � ou n�o num�rico.
	 * @throws UtilException valor nulo, em branco, muitos pontos decimais ou n�o num�rico.
	 */

	public static void isParseNumber(String str) throws UtilException
	{
		checkParse(str);

		if (str.startsWith("-"))
			str = str.substring(1, str.length());

		int dots = 0;

		for (char c : str.toCharArray())
		{
			if (c == '.' || c == ',')
			{
				if (dots == 1)
					throw new UtilException("muitos pontos");

				dots = 1;
			}

			else if (c < '0' || c > '9')
				throw new UtilException("n�o num�rico");
		}
	}

	/**
	 * Verifica se uma determinada string � composta
	 * apenas por caracteres num�ricos [0~9] e pode
	 * possuir apenas um �nico ponto (.) para seprar
	 * valores ap�s a v�rugla (pr�prio da linguagem java).
	 * @param str string que ser� verificada.
	 * @return true se for ou false caso contr�rio.
	 */

	public static boolean isFloat(String string)
	{
		if (string == null || string.length() == 0 || string.split(".").length > 2)
			return false;

		for (char c : string.toCharArray())
			if (!Character.isDigit(c) && c != '.')
				return false;

		return true;
	}

	/**
	 * Cria um c�digo aleat�rio com caracteres alpha-num�ricos,
	 * ou seja apenas letras e n�meros. A chance de de vir uma
	 * letra ou n�mero � de 50% para cada lado.
	 * @param size quantos d�gitos deve possuir o c�digo.
	 * @return string com o c�digo gerado.
	 */

	public static String genCode(int size)
	{
		String code = "";
		Random random = new Random();

		while (code.length() < size)
		{
			if (random.nextInt(2) == 0)
				code += (char) (65 + random.nextInt(26));
			else
				code += (char) (48 + random.nextInt(10));
		}

		return code;
	}

	/**
	 * Permite adicionar um determinado caracter no meio de uma String,
	 * de modo que seja definido qual a posi��o na string que ele deve
	 * ser inserido, �ndice no vetor de caracteres da stirng. Caso o
	 * �ndice definido seja menor que 0 (zero) ou maior que o tamanho
	 * da string, nada ir� acontecer com a string (posi��o inv�lida).
	 * @param string qual a string na qual ser� inserido.
	 * @param c caracter que deve ser inserido na string.
	 * @param index �ndice da posi��o em que deve ser adicionado.
	 * @return string com o caracter adicionado na posi��o escolhida.
	 */

	public static String addIn(String string, char c, int index)
	{
		if (index < 0 || index > string.length())
			return string;

		else if (index == 0 && string.length() > 0)
			return c + string;

		else if (index == string.length())
			return string + c;

		return sub(string, 0, index) + c + sub(string, index, string.length() - index);
	}

	/**
	 * Faz a funcionalidade de apagar um caracter de uma determinada string,
	 * como o bot�o do teclado BackSpace. Atrav�s da posi��o do caracter desejado
	 * a ser apagado, esta a��o � poss�vel ser realizada. Caso a posi��o seja
	 * menor que 1 (un) ou maior que o tamanho da string nada ir� acontecer,
	 * pois estas posi��es s�o inv�lidas para realizar a opera��o.
	 * @param string qual a string que ter� um caracter apagado.
	 * @param index �ndice do caracter da string que deve ser apagado.
	 * @return string com o caracter do �ndice definido apagado.
	 */

	public static String backspace(String string, int index)
	{
		if (index > 0 && index <= string.length())
			return sub(string, 0, index - 1) + sub(string, index, string.length() - index);

		return string;
	}

	/**
	 * Faz a funcionalidade de exclu�r um caracter de uma determinada string,
	 * como o bot�o do teclado Delete. Atrav�s da posi��o do caracter desejado
	 * a ser apagado, esta a��o � poss�vel ser realizada. Caso a posi��o seja
	 * menor que 0 ou maior/igual ao tamanho da string nada ir� acontecer,
	 * pois estas posi��es s�o inv�lidas para realizar a opera��o.
	 * @param string qual a string que ter� um caracter exclu�do.
	 * @param index �ndice do caracter da string que deve ser exclu�do.
	 * @return string com o caracter do �ndice definido exclu�do.
	 */

	public static String delete(String string, int index)
	{
		if (index >= 0 && index < string.length())
			return sub(string, 0, index) + sub(string, index + 1, string.length() - index);

		return string;
	}

	/**
	 * Constr�i uma string a partir de um vetor de bytes, a diferen�a deste para o
	 * m�todo padr�o que o primeiro byte nulo (0) encontrado ser� o final da string.
	 * @param bytes vetor contendo os bytes que ser�o utilizados para criar a string.
	 * @return string criada a partir dos bytes passados at� o limite antes do byte nulo.
	 */

	public static String parseBytes(byte[] bytes)
	{
		int i = 0;

		for (; bytes[i] != 0; i++);

		if (i != bytes.length)
			return new String(ByteUtil.subarray(bytes, 0, i));

		return new String(bytes);
	}

	/**
	 * Constr�i uma string a partir dos bytes de um determinado n�mero inteiro.
	 * @param value n�mero inteiro do qual deve ser constru�do a string.
	 * @return string constru�da a partir dos bytes do n�mero inteiro.
	 */

	public static String parseInt(int value)
	{
		return new String(ByteUtil.parseInt(value));
	}

	/**
	 * Constr�i uma string contendo apenas espa�amentos de tabula��o (\t).
	 * @param length quantas tabula��es devem ser feitas na string.
	 * @return string contendo todas as tabula��es.
	 */

	public static String indent(int length)
	{
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++)
			builder.append("\t");

		return builder.toString();
	}

	/**
	 * Retorna uma string contendo um conte�do limitado de uma determinada string.
	 * Nesse caso verifica se a string e null retornando um "null", como tamb�m
	 * permite que n�o haja problema ao executar substring por length muito grande.
	 * @param str string do qual deve ser simplificada o seu conte�do.
	 * @param length quantos caracteres da string ser�o aceitos.
	 * @return string com o conte�do simplificado.
	 */

	public static Object less(String str, int length)
	{
		if (str == null)
			return "null";

		if (str.length() < length)
			length = str.length();

		return str.substring(0, length);
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param objects vetor contendo os objetos que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static String toString(Object[] objects)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < objects.length; i++)
		{
			str.append(objects[i]);

			if (i != objects.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param array vetor contendo os n�meros que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static Object toString(byte[] array)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i]);

			if (i != array.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param array vetor contendo os n�meros que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static Object toString(char[] array)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i]);

			if (i != array.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param array vetor contendo os n�meros que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static Object toString(short[] array)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i]);

			if (i != array.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param array vetor contendo os n�meros que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static Object toString(int[] array)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i]);

			if (i != array.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param array vetor contendo os n�meros que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static Object toString(long[] array)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i]);

			if (i != array.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param array vetor contendo os n�meros que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static Object toString(float[] array)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i]);

			if (i != array.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores do vetor separados por v�rgula.
	 * @param array vetor contendo os n�meros que ser�o convertidos para string.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static Object toString(double[] array)
	{
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < array.length; i++)
		{
			str.append(array[i]);

			if (i != array.length - 1)
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * Constr�i uma string para ser usada no m�todo toString de um determinado objeto.
	 * A ideia � que essa string contenha os valores da lista separados por v�rgula.
	 * @param iteration itera��o que ser� convertida para string usando toString.
	 * @return string contendo o valor dos objetos separados por v�rgula.
	 */

	public static String toString(Iterator<?> iteration)
	{
		if (iteration == null)
			return "null";

		StringBuilder str = new StringBuilder();

		while (iteration.hasNext())
		{
			str.append(iteration.next());

			if (iteration.hasNext())
				str.append(", ");
		}

		return str.toString();
	}

	/**
	 * O padr�o dos projetos DiverProject determina o seguinte: "mensagem (dados informativos)".
	 * Esse m�todo ir� fazer com que uma mensagem dessa permita inserir mais dados informativos.
	 * De modo simples, onde a mensagem j� formatada ser� juntado a uma string com dados informativos.
	 * @param message string contendo a mensagem completa j� gerada no sistema para ser modificada.
	 * @param data string contendo os dados que devem ser adicionados a essa mensagem em quest�o.
	 * @return string contendo a mensagem antiga adicionado dos dados informativos inseridos.
	 */

	public static String addStringData(String message, String data)
	{
		if (!message.contains("(") && !message.contains("("))
			return String.format("%s (%s)", message, data);

		else if (!message.contains("(") || !message.contains("("))
			return message;

		String content = message.substring(0, message.indexOf('(')).trim();
		String old = message.substring(message.indexOf('(') + 1, message.length() - 1).trim();

		return String.format("%s (%s, %s)", content, data, old);
	}

	/**
	 * O padr�o dos projetos DiverProject determina o seguinte: "mensagem [exce��o]".
	 * Esse m�todo ir� fazer com que uma mensagem j� gerada considere o uso de uma determinada classe.
	 * De modo simples, onde a mensagem j� formatada ter� a o nome da classe que a gerou renomeada.
	 * @param message string contendo a mensagem completa j� gerada no sistema para ser modificada.
	 * @param exception qual � a nova exce��o do qual ser� usado o nome da classe para substituir.
	 * @return string contendo a mensagem antiga com o nome da exce��o alterado de acordo.
	 */

	public static String addStringException(String message, Exception exception)
	{
		if (message == null)
			return exception.getMessage();

		String simpleName = exception.getClass().getSimpleName();

		if (!message.contains("[") && !message.contains("]"))
			return String.format("%s [%s]", message, simpleName);

		else if (!message.contains("[") || !message.contains("]"))
			return message;

		String content = message.substring(0, message.indexOf('[')).trim();

		return String.format("%s [%s]", content, simpleName);
	}

	/**
	 * Constr�i uma string contendo apenas espa�amentos largos conhecidos como tabs de caracter \t.
	 * @param length quantos espa�amentos dever�o ser colocados seguidamente na string.
	 * @return string constru�da a partir da quantidade de espa�amentos definido.
	 */

	public static String getTabs(int length)
	{
		char chars[] = new char[length];

		for (int i = 0; i < chars.length; i++)
			chars[i] = '\t';

		return new String(chars);
	}

	/**
	 * Converte um valor num�rico para um valor monet�rio em string separando as casas.
	 * Por exemplo, um valor num�rico de 1000000 seria repassado para "1.000.000".
	 * @param value valor do qual ser� convertido durante a formata��o para string.
	 * @return aquisi��o da string formatada conforme o par�metro passado.
	 */

	public static String money(long value)
	{
		boolean negative = value < 0;

		if (negative)
			value *= -1;

		String strv = Long.toString(value);
		String str = "";

		for (int i = 0; i < strv.length(); i++)
		{
			if (i % 3 == 0 && i > 0)
				str = "." + str;

			str = strv.charAt(strv.length() - i - 1) + str;
		}

		str = str.startsWith(".") ? str.substring(1, str.length()) : str;

		if (negative)
			str = "-" + str;

		return str;
	}

	/**
	 * Faz a contagem de quantas vezes um determinado caracter est� presente em uma string.
	 * @param str string que ser� usada para verificar a quantidade de repeti��o do caracter.
	 * @param c caracter que dever� ser considerado na contagem quando iterar a string.
	 * @return quantidade de vezes que o caracter passado por par�metro est� presente na string.
	 */

	public static int countOf(String str, char c)
	{
		int count = 0;

		for (int i = 0; i < str.length(); i++)
			if (str.charAt(i) == c)
				count++;

		return count;
	}

	/**
	 * Faz a contagem de quantas vezes um determinado caracter est� presente em uma string.
	 * @param str string que ser� usada para verificar a quantidade de repeti��o do caracter.
	 * @param c caracter que dever� ser considerado na contagem quando iterar a string.
	 * @return quantidade de vezes que o caracter passado por par�metro est� presente na string.
	 */

	public static int countOf(String str, String sequence)
	{
		int count = 0;

		for (int i = 0; i < str.length(); i++)
		{
			int repeated = 0;

			for (int j = 0; j < sequence.length(); j++, repeated++)
				if (!(i + j < str.length() && str.charAt(i + j) == sequence.charAt(j)))
				{
					repeated = 0;
					break;
				}

			if (repeated == sequence.length())
				count++;
		}

		return count;
	}

	/**
	 * Substitui diversas sequ�ncias de caracteres para uma outra determinada sequ�ncia de caracteres.
	 * @param str refer�ncia da string que ter� o valor de caracteres alterado conforme abaixo.
	 * @param replacement qual ser� o valor usado para substituir as sequ�ncias a seguir.
	 * @param targets sequ�ncia de caracteres alvos que ser�o substitu�das por <b>replacement</b>.
	 * @return aquisi��o de uma nova string contendo os caracteres atualizados conforme os par�metros.
	 */

	public static String replace(String str, String replacement, String... targets)
	{
		for (String target : targets)
			str = str.replace(target, replacement);

		return str;
	}

	/**
	 * Procura em uma determinada string uma sequ�ncia de caracteres ap�s n vezes encontrado.
	 * Por exemplo: saber quando aparecer� "0" pela segunda vez na string "012345<b>0</b>12345".
	 * @param str string contendo o conjunto de caracteres que ser� verificado no processo.
	 * @param sequence conjunto de caracteres contendo a sequ�ncia que dever� se repetir.
	 * @param position n�mero da vez que ser� considerado quando a sequ�ncia aparecer.
	 * @return �ndice da sequ�ncia na vez especificada, -1 se n�o encontrar ou -2 caso
	 * haja um par�metro inv�lido, n�mero de vezes negativa ou strings nulas.
	 */

	public static int indexOf(String str, String sequence, int time)
	{
		if (time < 0 || str == null || sequence == null)
			return -2;

		for (int i = 0, j = 0, k = 0; i < str.length(); i++)
		{
			if (j == sequence.length())
			{
				k++;

				if (k == time)
					return i;

				j = 0;
			}

			if (str.charAt(i) == sequence.charAt(j))
				j++;
			else
				j = 0;
		}

		return -1;
	}
}
