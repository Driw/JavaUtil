package org.diverproject.util.collection;

import java.lang.reflect.Array;

import org.diverproject.util.lang.HexUtil;
import org.diverproject.util.lang.ShortUtil;
import org.diverproject.util.lang.StringUtil;

/**
 * <p><h1>Utilit�rio para Vetores</h1></p>
 *
 * <p>Classe do tipo utilit�rio, portanto deve possuir apenas procedimentos est�ticos.
 * Seu construtor � privado de modo que n�o seja poss�vel construir inst�ncias de tal.
 * Todos os procedimentos ir�o trabalhar com vetores ou algo similar e s�o p�blicos.</p>
 *
 * <p>Esse utilit�rio est� na parte de cole��es pois � o �nico lugar da biblioteca,
 * do qual ir� trabalhar com vetores, que s�o usados internamente pelas cole��es
 * para armazenar os dados nesta inseridos como deve ser feito as estruturas.</p>
 *
 * @author Andrew
 */

public class ArrayUtil
{
	/**
	 * Construtor privado para que n�o haja inst�ncias de ArrayUtil.
	 */

	private ArrayUtil()
	{
		
	}

	/**
	 * <p>Procedimento do qual deve fazer com que todos os elementos de um vetor,
	 * passam uma �nica casa (�ndice) para o lado esquerdo de modo que assim,
	 * o �ndice especificado seja considerado como exclu�do do vetor.</p>
	 * <p>No final desse procedimento o �ltimo elemento restante da lista estar�
	 * duplicado devido a movimenta��o for�ada dos elementos, por tanto este
	 * ser� definido sempre como null de modo que d� a impress�o de movimenta��o
	 * de todos os elementos do vetor para a esquerda de uma �nica vez.</p>
	 * @param array vetor do qual ter� um determinado �ndice removido.
	 * @param index n�mero do �ndice do qual ser� perdido no vetor.
	 * @return true se conseguir mover ou false por vetor ou �ndice inv�lido.
	 */

	public static boolean moveLeft(Object[] array, int index)
	{
		if (index < 0 || index >= array.length)
			return false;

		for (int i = index; i < array.length - 1; i++)
			array[i] = array[i + 1];

		try {
			array[array.length - 1] = null;
		} catch (Exception e) {
			
		}

		return true;
	}

	/**
	 * <p>Procedimento do qual deve fazer com que todos os elementos de um vetor,
	 * passam uma �nica casa (�ndice) para o lado direito de modo que assim,
	 * o �ndice especificado seja considerado como adicionado no vetor.</p>
	 * <p>Relembrando de que o �ndice especificado ser� preenchido com o
	 * valor null para que seja visto como um espa�o livre no vetor trabalhado.</p>
	 * @param array vetor do qual ter� um determinado �ndice adicionado.
	 * @param index n�mero do �ndice do qual ser� inserido no vetor.
	 * @return true se conseguir mover ou false por vetor ou �ndice inv�lido.
	 */

	public static boolean moveRight(Object[] array, int index)
	{
		if (index < 0 || index >= array.length)
			return false;

		for (int i = array.length - 1; i > index; i--)
			array[i] = array[i - 1];

		try {
			array[index] = null;
		} catch (Exception e) {
			
		}

		return true;
	}

	/**
	 * <p>Procedimento do qual ir� redimensionar um determinado vetor especificado.
	 * Esse redimensionamento � a constru��o de um novo vetor com tamanho definido.
	 * Onde os elementos do vetor antigo ser�o passados para o novo vetor criado.</p>
	 * <p>Caso falte espa�o �ltimos elementos ser�o perdidos, em quanto se faltar,
	 * os �ndices ser�o preenchidos com valores nulos ou zerado de acordo com o tipo.</p>
	 * @param array vetor que ser� redimensionado e copiado os elementos
	 * @param length comprimento do qual o novo vetor dever� possuir.
	 * @return vetor constru�do com o comprimento e elementos passados.
	 */

	public static Object[] resizeTo(Object[] array, int length)
	{
		Object old[] = array;
		array = new Object[length];

		for (int i = 0; i < old.length && i < array.length; i++)
			array[i] = old[i];

		return array;
	}

	/**
	 * <p>Procedimento do qual ir� redimensionar um determinado vetor especificado.
	 * Esse redimensionamento � a constru��o de um novo vetor com tamanho maior.
	 * Onde os elementos do vetor antigo ser�o passados para o novo vetor criado.</p>
	 * <p>Neste caso como sempre ser� aumentado o tamanho, todos os �ndices novos
	 * ser�o preenchidos como valores autom�ticos do java (nulo ou zero).</p>
	 * @param array vetor que ser� redimensionado e copiado os elementos
	 * @param length comprimento do qual o novo vetor dever� possuir.
	 * @return vetor constru�do com o comprimento e elementos passados.
	 */

	public static Object[] increseIn(Object[] array, int length)
	{
		if (array == null || (long) (array.length + length) > Integer.MAX_VALUE)
			return null;

		Object old[] = array;
		Class<?> generic = Object.class;

		length += array.length;
		array = (Object[]) Array.newInstance(generic, length);

		for (int i = 0; i < old.length && i < array.length; i++)
			array[i] = old[i];

		return array;
	}

	/**
	 * <p>Procedimento do qual ir� redimensionar um determinado vetor especificado.
	 * Esse redimensionamento � a constru��o de um novo vetor com tamanho maior.
	 * Onde os elementos do vetor antigo ser�o passados para o novo vetor criado.</p>
	 * <p>Neste caso como sempre ser� aumentado o tamanho, todos os �ndices novos
	 * ser�o preenchidos como valores autom�ticos do java (nulo ou zero).</p>
	 * @param generic tipo de dados que ser� usado para criar o vetor.
	 * @param array vetor que ser� redimensionado e copiado os elementos
	 * @param length comprimento do qual o novo vetor dever� possuir.
	 * @return vetor constru�do com o comprimento e elementos passados.
	 */

	@SuppressWarnings("unchecked")
	public static <T> T[] increseIn(Class<T> generic, T[] array, int length)
	{
		if (array == null || (long) (array.length + length) > Integer.MAX_VALUE)
			return null;

		T old[] = array;

		length += array.length;
		array = (T[]) Array.newInstance(generic, length);

		for (int i = 0; i < old.length && i < array.length; i++)
			array[i] = old[i];

		return array;
	}

	/**
	 * <p>Procedimento do qual ir� redimensionar um determinado vetor especificado.
	 * Esse redimensionamento � a constru��o de um novo vetor com tamanho menor.
	 * Onde os elementos do vetor antigo ser�o passados para o novo vetor criado.</p>
	 * <p>Neste caso como sempre ser� reduzido o tamanho em caso de falta de espa�o,
	 * ou seja, o novo tamanho � menor que a quantidade de elementos do antigo,
	 * esses elementos ser�o perdidos do �ltimo �ndice at� o tamanho do novo vetor.</p>
	 * @param array vetor que ser� redimensionado e copiado os elementos
	 * @param length comprimento do qual o novo vetor dever� possuir.
	 * @return vetor constru�do com o comprimento e elementos passados.
	 */

	public static Object[] decreaseIn(Object[] array, int length)
	{
		if (array == null || length > array.length)
			return null;

		Object old[] = array;
		Class<?> generic = Object.class;

		length = array.length - length;
		array = (Object[]) Array.newInstance(generic, length);

		for (int i = 0; i < old.length && i < array.length; i++)
			array[i] = old[i];

		return array;
	}

	/**
	 * <p>Procedimento do qual ir� redimensionar um determinado vetor especificado.
	 * Esse redimensionamento � a constru��o de um novo vetor com tamanho menor.
	 * Onde os elementos do vetor antigo ser�o passados para o novo vetor criado.</p>
	 * <p>Neste caso como sempre ser� reduzido o tamanho em caso de falta de espa�o,
	 * ou seja, o novo tamanho � menor que a quantidade de elementos do antigo,
	 * esses elementos ser�o perdidos do �ltimo �ndice at� o tamanho do novo vetor.</p>
	 * @param generic tipo de dados que ser� usado para criar o vetor.
	 * @param array vetor que ser� redimensionado e copiado os elementos
	 * @param length comprimento do qual o novo vetor dever� possuir.
	 * @return vetor constru�do com o comprimento e elementos passados.
	 */

	@SuppressWarnings("unchecked")
	public static <T> T[] decreaseIn(Class<T> generic, T[] array, int length)
	{
		if (array == null || length > array.length)
			return null;

		T old[] = array;

		length = array.length - length;
		array = (T[]) Array.newInstance(generic, length);

		for (int i = 0; i < old.length && i < array.length; i++)
			array[i] = old[i];

		return array;
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de String com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de String � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(String[] array, String separator)
	{
		if (array == null || array.length == 0)
			return "";

		String join = array[0];

		for (int i = 1; i < array.length; i++)
			join += separator + array[i];

		return join;
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de byte com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de byte � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(byte[] array, String separator)
	{
		return join(array, separator, false);
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de byte com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de byte � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @param hex determina que os valores em bytes devem ficar no formado hexadecimal.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(byte[] array, String separator, boolean hex)
	{
		if (array == null || array.length == 0)
			return "";

		String join = hex ? joinByteHex(array[0]) : Byte.toString(array[0]);

		for (int i = 1; i < array.length; i++)
			join += separator + (hex ? joinByteHex(array[i]) : Byte.toString(array[i]));

		return join;
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de short com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de short � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(short[] array, String separator)
	{
		if (array == null || array.length == 0)
			return "";

		String join = Short.toString(array[0]);

		for (int i = 1; i < array.length; i++)
			join += separator + Short.toString(array[i]);

		return join;
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de int com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de int � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(int[] array, String separator)
	{
		if (array == null || array.length == 0)
			return "";

		String join = Integer.toString(array[0]);

		for (int i = 1; i < array.length; i++)
			join += separator + Integer.toString(array[i]);

		return join;
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de long com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de long � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(long[] array, String separator)
	{
		if (array == null || array.length == 0)
			return "";

		String join = Long.toString(array[0]);

		for (int i = 1; i < array.length; i++)
			join += separator + Long.toString(array[i]);

		return join;
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de float com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de float � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(float[] array, String separator)
	{
		if (array == null || array.length == 0)
			return "";

		String join = Float.toString(array[0]);

		for (int i = 1; i < array.length; i++)
			join += separator + Float.toString(array[i]);

		return join;
	}

	/**
	 * Cria uma string concatenando todos os valores de um vetor de double com um separador.
	 * O separador � colocado entre dois valores concatenados, n�o utilizado no fim e inicio.
	 * @param array vetor contendo todos os valores de double � concatenar.
	 * @param separator String contendo o separador que ser� usando entre os valores.
	 * @return aquisi��o da string contendo os valores concatenados com separador.
	 */

	public static String join(double[] array, String separator)
	{
		if (array == null || array.length == 0)
			return "";

		String join = Double.toString(array[0]);

		for (int i = 1; i < array.length; i++)
			join += separator + Double.toString(array[i]);

		return join;
	}

	/**
	 * M�todo interno para converter um valor de byte em um valor hexadecimal de 00 at� FF.
	 * @param b valor do byte que ser� convertido para um valor hexadecimal.
	 * @return aquisi��o da string contendo o formato do byte em hexadecimal.
	 */

	private static String joinByteHex(byte b)
	{
		String hex = HexUtil.parseShort(ShortUtil.parseByte(b));
		hex = StringUtil.addStartWhile(hex, "0", 2);

		return hex;
	}
}
