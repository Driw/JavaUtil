package org.diverproject.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Locale;

import org.diverproject.util.lang.StringUtil;

/**
 * <p><h1>Utilit�rio para Tamanho</h1></p>
 *
 * <p>Possui procedimentos que tem como finalidade definir o tamanho de objetos.
 * Dentre esses existem m�todos que obt�m o tamanho dos valores primitivos.
 * Como tamb�m analisa o conte�do de um objeto e calcula o seu tamanho em bytes.</p>
 *
 * <p>Vale lembrar que apenas vari�veis dos tipos primitivos ser�o calculados aqui.
 * Por tanto qualquer outro valor que n�o seja primitivo n�o ter� um resultado.
 * Por�m se este possuir valores primitivos ir� considerar estes e assim em diante.</p>
 *
 * @author Andrew
 */

public class SizeUtil
{
	private static final Class<?> PRIMITIVE_CLASS[] = new Class[]
	{
		Byte.class,
		Short.class,
		Integer.class,
		Long.class,
		Float.class,
		Double.class,
		Character.class,
		Boolean.class
	};

	private static final int PRIMITIVE_SIZE[] = new int[] { 1, 2, 4, 8, 4, 8, 1, 1 };

	/**
	 * Procedimento que ir� calcular o tamanho de um determinado objeto em bytes, considera apenas atributos do objeto.
	 * Assim sendo, atributos est�ticos n�o ser�o calculados nesse, n�o diferencia public, private, protected (...).
	 * @param object refer�ncia do objeto do qual dever� ser analisado seus atributos ou tipo para calcular o tamanho.
	 * @return tamanho total dos atributos ou do objeto em si (se primitivo) em bytes.
	 */

	public static long sizeOf(Object object)
	{
		return sizeOf(object, 0, false, new HashMap<Object, Object>());
	}

	/**
	 * Procedimento que ir� calcular o tamanho de um determinado objeto em bytes, considera apenas atributos do objeto.
	 * Assim sendo, atributos est�ticos n�o ser�o calculados nesse, n�o diferencia public, private, protected (...).
	 * @param object refer�ncia do objeto do qual dever� ser analisado seus atributos ou tipo para calcular o tamanho.
	 * @param debug true para exibir a analise hier�rquica de profundidade dos atributos do objeto se houver.
	 * @return tamanho total dos atributos ou do objeto em si (se primitivo) em bytes.
	 */

	public static long sizeOf(Object object, boolean debug)
	{
		long size = sizeOf(object, 0, debug, new HashMap<Object, Object>());

		if (debug)
			System.out.printf("-> %d bytes totais\n", size);

		return size;
	}

	/**
	 * Procedimento que ir� calcular o tamanho de um determinado objeto em bytes, considera apenas atributos do objeto.
	 * Assim sendo, atributos est�ticos n�o ser�o calculados nesse, n�o diferencia public, private, protected (...).
	 * @param object refer�ncia do objeto do qual dever� ser analisado seus atributos ou tipo para calcular o tamanho.
	 * @param depth n�vel de profundidade que esse objeto est� sendo analisado, se n�o interno deve ser 0 (recomendado).
	 * @param debug true para exibir a analise hier�rquica de profundidade dos atributos do objeto se houver.
	 * @param map mapeamento dos objetos que j� foram analisados para evitar loops infinitos.
	 * @return tamanho total dos atributos ou do objeto em si (se primitivo) em bytes.
	 */

	private static long sizeOf(Object object, int depth, boolean debug, HashMap<Object, Object> map)
	{
		if (object instanceof Class || object.getClass() == Object.class || object == null || map.containsValue(object))
			return 0;

		if (isPrimitive(object))
			return map.put(map.size(), object) == null ? sizeOfPrimitive(object) : 0;

		if (isPrimitveArray(object))
			return map.put(map.size(), object) == null ? sizeOfPrimitiveArray(object) : 0;

		long size = 0L;

		if (object instanceof Object[])
			return sizeOfObjectArray((Object[]) object, depth, debug, "", map);

		for (Class<?> c = object.getClass(); c != Object.class; c = c.getSuperclass(), depth++)
		{
			if (debug)
				System.out.printf("%s-> [%s]\n", StringUtil.getTabs(depth), c.getSimpleName());

			long sizeOfClass = sizeOfClass(c, object, depth, debug, map);

			size += sizeOfClass;
		}

		return size;
	}

	/**
	 * Esse procedimento pode ser chamado tanto por sizeOf ou por sizeOfClass de acordo com o momento da analise.
	 * No caso de sizeOf ser� chamado apenas se o objeto passado for um vetor de objetos caso contr�rio � um atributo deste.
	 * @param array refer�ncia do vetor contendo os objetos dos quais ser�o calculados os seus tamanhos.
	 * @param depth qual o n�vel de profundidade que a an�lise do objeto j� est� sendo passada (apenas para debug).
	 * @param debug true para exibir um escada hier�rquica dos atributos com seus tamanhos ou false caso contr�rio.
	 * @param fieldName nome que ser� exibido no debug para identificar qual � o campo que est� sendo calculado.
	 * @param map mapeamento dos objetos que j� foram analisados para evitar loops infinitos.
	 * @return tamanho total de todos os objetos inseridos nesse vetor de objetos em bytes.
	 */

	private static long sizeOfObjectArray(Object[] array, int depth, boolean debug, String fieldName, HashMap<Object, Object> map)
	{
		if (map.containsValue((Object) array))
			return 0;

		map.put(map.size(), array);

		if (debug)
			System.out.printf("%s-> [Object[]] (%s)\n", StringUtil.getTabs(depth), fieldName);

		long size = 0L;

		for (int i = 0; i < ((Object[]) array).length; i++)
		{
			Object obj = ((Object[]) array)[i];

			if (obj == null)
				continue;

			long sizeOf = sizeOf(obj, depth + 1, debug, map);
			size += sizeOf;

			if (debug)
				System.out.printf("%s-> [%d] %s: %d bytes\n", StringUtil.getTabs(depth + 1), i, obj.getClass().getSimpleName(), sizeOf);
		}

		return size;
	}

	/**
	 * Esse procedimento � chamado por sizeOf quanto identifica que h� heran�as, e essas podem conter atributos no mesmo.
	 * Assim � necess�rio passar o objeto em quest�o, a classe (heran�a ou n�o) para que permita o acesso destes atributos.
	 * @param c refer�ncia da classe do qual ir� garantir que o objeto permita obter os atributos para calcular o tamanho.
	 * @param object refer�ncia do objeto que ser� usado para se obter os atributos a fim de obter seus tamanhos.
	 * @param depth qual o n�vel de profundidade que a an�lise do objeto j� est� sendo passada (apenas para debug).
	 * @param debug true para exibir um escada hier�rquica dos atributos com seus tamanhos ou false caso contr�rio..
	 * @param map mapeamento dos objetos que j� foram analisados para evitar loops infinitos.
	 * @return tamanho total dos atributos contidos na classe passada de acordo com o objeto passado em bytes.
	 */

	private static long sizeOfClass(Class<?> c, Object object, int depth, boolean debug, HashMap<Object, Object> map)
	{
		if (map.containsValue(object))
			return 0L;

		long total = 0L;
		Field fields[] = c.getDeclaredFields();

		for (Field field : fields)
		{
			if ((field.getModifiers() & Modifier.STATIC) == Modifier.STATIC)
				continue;

			field.setAccessible(true);

			try {

				Object value = field.get(object);

				if (map.containsValue(value))
					continue;

				if (value instanceof Object[])
				{
					total += sizeOfObjectArray((Object[]) value, depth + 1, debug, field.getName(), map);
					continue;
				}

				long size = sizeOf(value, depth, debug, map);

				if (debug)
					System.out.printf("%s-> %s: %d bytes (%s)\n", StringUtil.getTabs(depth + 1), value.getClass().getSimpleName(), size, field.getName());

				total += size;

				field.setAccessible(false);

			} catch (Exception e) {
			}
		}

		return total;
	}

	/**
	 * Verifica se o objeto � uma inst�ncia de um dado de tipo primitivo.
	 * @param object objeto do qual ser� verificar se est� dentro do esperado.
	 * @return true se for um dado de tipo primitivo ou false caso contr�rio.
	 */

	private static boolean isPrimitive(Object object)
	{
		return	object instanceof Byte ||
				object instanceof Short ||
				object instanceof Integer ||
				object instanceof Long ||
				object instanceof Float ||
				object instanceof Double ||
				object instanceof Character ||
				object instanceof String ||
				object instanceof Boolean;
	}

	/**
	 * Verifica se o objeto � uma inst�ncia de vetor para valores primitivos.
	 * @param object objeto do qual ser� verificar se est� dentro do esperado.
	 * @return true se for um vetor primitivo ou false caso contr�rio.
	 */

	private static boolean isPrimitveArray(Object object)
	{
		return	object instanceof Byte[] ||
				object instanceof Short[] ||
				object instanceof Integer[] ||
				object instanceof Long[] ||
				object instanceof Float[] ||
				object instanceof Double[] ||
				object instanceof Character[] ||
				object instanceof String[] ||
				object instanceof Boolean[];
	}

	/**
	 * Verifica se um objeto � uma inst�ncia de um tipo de dado primitivo da linguagem java e calcula o seu tamanho.
	 * Cada tipo de dado possui um tamanho de acordo com sua capacidade de op��es de valores armazen�veis.
	 * @param object objeto do qual � esperado que seja um dado primitivo para que seja calculado seu tamanho.
	 * @return tamanho do dado primitivo de acordo com o seu tipo (padr�o) ou 0 se n�o for um primitivo.
	 */

	private static long sizeOfPrimitive(Object object)
	{
		if (object instanceof String)
			return ((String) object).getBytes().length;

		for (int i = 0; i < PRIMITIVE_CLASS.length; i++)
			if (object.getClass() == PRIMITIVE_CLASS[i])
				return PRIMITIVE_SIZE[i];

		return 0;
	}

	/**
	 * Verifica se o objeto � uma inst�ncia de vetor para valores primitivos e calcula a o tamanho a partir deste.
	 * Ir� identificar qual o tipo do vetor (dado primitivo) e ir� verificar se este n�o � nulo e ent�o definir o tamanho.
	 * @param object objeto do qual � esperado que seja um vetor de um dado primitivo para que seja calculado seu tamanho.
	 * @return tamanho dos dados primitivos ocupados no vetor (n�o considera null: 0) ou 0 se n�o for um vetor primitivo.
	 */

	private static long sizeOfPrimitiveArray(Object object)
	{
		long total = 0L;

		if (object instanceof Byte[])
		{
			for (Byte b : (Byte[]) object)
				if (b != null)
					total += 1;
		}

		else if (object instanceof Short[])
		{
			for (Short s : (Short[]) object)
				if (s != null)
					total += 2;
		}

		else if (object instanceof Integer[])
		{
			for (Integer i : (Integer[]) object)
				if (i != null)
					total += 4;
		}

		else if (object instanceof Long[])
		{
			for (Long l : (Long[]) object)
				if (l != null)
					total += 1;
		}

		else if (object instanceof Float[])
		{
			for (Float f : (Float[]) object)
				if (f != null)
					total += 4;
		}

		else if (object instanceof Double[])
		{
			for (Double d : (Double[]) object)
				if (d != null)
					total += 8;
		}

		else if (object instanceof Character[])
		{
			for (Character c : (Character[]) object)
				if (c != null)
					total += c < 255 ? 1 : 2;
		}

		else if (object instanceof String[])
		{
			for (String s : (String[]) object)
				if (s != null)
					total += s.getBytes().length;
		}

		else if (object instanceof Boolean[])
		{
			for (Boolean b : (Boolean[]) object)
				if (b != null)
					total += 1;
		}

		return total;
	}

	/**
	 * Diferente dos n�meros decimais, bytes trocam a denomina��o a cada 1024 unidades.
	 * Por exemplo, 1kb n�o equivalem a 1000bytes e sim a 1024bytes, esse m�todo ir� fazer
	 * a convers�o do n�mero de bytes para um valor em string.<p>
	 * <b>Exemplo:</b><br>
	 * 1000 bytes: 1000B<br>
	 * 1050 bytes: 1.02KiB<br>
	 * 1048576 bytes: 1.00MiB<br>
	 * @param value valor em bytes que ser� convertido.
	 * @return string em modo leg�vel e definido o tipo de aproxima��o (KB, MB, GB).
	 */

	public static String toString(long value)
	{
		if (value > 1073741823)
			return String.format(Locale.US, "%.2fGiB", (((float)value) / ((float)1073741824)));

		if (value > 1048575)
			return String.format(Locale.US, "%.2fMiB", (((float)value) / ((float)1048576)));

		if (value > 1023)
			return String.format(Locale.US, "%.2fKiB", (((float)value) / ((float)1024)));

		return String.format(Locale.US, "%dB", value);
	}
}
