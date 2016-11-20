package org.diverproject.util;

import org.diverproject.util.collection.StringExtend;
import org.diverproject.util.collection.abstraction.DynamicList;
import org.diverproject.util.lang.BooleanUtil;
import org.diverproject.util.lang.ByteUtil;
import org.diverproject.util.lang.DoubleUtil;
import org.diverproject.util.lang.FloatUtil;
import org.diverproject.util.lang.IntUtil;
import org.diverproject.util.lang.LongUtil;
import org.diverproject.util.lang.ShortUtil;
import org.diverproject.util.lang.StringUtil;

public class Format
{
	/**
	 * Como uma coluna deve ser definido para ser byte.
	 */
	public static final char TYPE_BYTE = 't';

	/**
	 * Como uma coluna deve ser definido para ser character.
	 */
	public static final char TYPE_CHAR = 'c';

	/**
	 * Como uma coluna deve ser definido para ser short.
	 */
	public static final char TYPE_SHORT = 'h';

	/**
	 * Como uma coluna deve ser definido para ser int.
	 */
	public static final char TYPE_INT = 'i';

	/**
	 * Como uma coluna deve ser definido para ser long.
	 */
	public static final char TYPE_LONG = 'l';

	/**
	 * Como uma coluna deve ser definido para ser float.
	 */
	public static final char TYPE_FLOAT = 'f';

	/**
	 * Como uma coluna deve ser definido para ser double.
	 */
	public static final char TYPE_DOUBLE = 'd';

	/**
	 * Como uma coluna deve ser definido para ser string.
	 */
	public static final char TYPE_STRING = 's';

	/**
	 * Como uma coluna deve ser definido para ser boolean.
	 */
	public static final char TYPE_BOOLEAN = 'b';

	/**
	 * Como uma coluna deve ser definido para ser vetor de tamanho fixo.
	 */
	public static final char TYPE_ARRAY_STATIC = 'a';

	/**
	 * Como uma coluna deve ser definido para ser matriz.
	 */
	public static final char TYPE_MATRIX = 'm';

	/**
	 * Como uma Coluna deve ser definido para ser vetor de tamanho vari�vel.
	 */
	public static final char TYPE_ARRAY_DINAMIC = 'n';

	/**
	 * Como o restante de uma formata��o deve ser considerada alternativa.
	 */
	public static final char TYPE_ALTERNATIVE = '?';

	/**
	 * Vetor contendo todos os tipos de dados que s�o permitidos (caracteres).
	 */

	public static final char VALID_TYPES[] = new char[]
	{
		TYPE_BYTE, TYPE_CHAR, TYPE_SHORT, TYPE_INT, TYPE_LONG, TYPE_FLOAT, TYPE_DOUBLE,
		TYPE_STRING, TYPE_BOOLEAN, TYPE_ARRAY_STATIC, TYPE_ARRAY_DINAMIC
	};

	/**
	 * C�digo para identificar o tipo de dado como desconhecido.
	 */
	public static final int CLASS_REPEAT = 0;

	/**
	 * C�digo para identificar o tipo de dado como primitivo.
	 */
	public static final int CLASS_PRIMITIVE = 1;

	/**
	 * C�digo para identificar o tipo de dado com vetor.
	 */
	public static final int CLASS_ARRAY = 2;

	/**
	 * C�digo para identificar o tipo de dado com alternativo.
	 */
	public static final int CLASS_ALTERNATIVE = 3;

	/**
	 * C�digo para identificar o tipo de dado com conjunto (parentese, colchetes ou chaves).
	 */
	public static final int CLASS_BRACE = 4;

	/**
	 * C�digo para identificar o tipo de dado como matriz.
	 */
	public static final int CLASS_MATRIX = 5;

	/**
	 * Propriedade que ir� ignorar espa�amentos no inicio e fim dos valores obtidos.
	 */
	public static final int PROPERTIE_TRIM = 0x01;

	/**
	 * Propriedade que ir� aceitar colunas que n�o possuem nem mesmo espa�os em branco.
	 */
	public static final int PROPERTIE_BLANK_VALUES = 0x02;

	/**
	 * Propriedade que ir� aceitar colunas apenas com espa�amentos.
	 */
	public static final int PROPERTIE_BLANK_COLUMNS = 0x04;

	/**
	 * Propriedade que ir� considerar espa�os largos como espa�os simples.
	 */
	public static final int PROPERTIE_TAB_AS_SPACE = 0x08;

	/**
	 * Propriedade que ir� considerar espa�os como dados nas colunas.
	 */
	public static final int PROPERTIE_SPACE_IN_COLUMN = 0x10;

	/**
	 * String contendo as regras de formata��o.
	 */
	private String format;

	/**
	 * Coluna que est� sendo atualmente processada.
	 */
	private int col;

	/**
	 * Quais as propriedades que ser�o aplicadas.
	 */
	private BitWise properties;

	/**
	 * Determina se o formato j� est� como alternativo (n�o obrigat�rio).
	 */
	private boolean alternative;

	/**
	 * �ltimo separador que foi encontrado (usado para garantir valores vazios no final do conte�do).
	 */
	private char lastSeparator;

	/**
	 * Conte�do que est� sendo considerado para fazer a formata��o.
	 */
	private StringExtend nodeFormat;

	/**
	 * Conte�do que est� sendo considerado para ser analisado de acordo com a formata��o.
	 */
	private StringExtend nodeContent;

	/**
	 * Lista que ir� armazenar todos os objetos j� analisados.
	 */
	private DynamicList<Object> objects;

	/**
	 * Constr�i uma nova formata��o iniciando a lista de objetos analisados e propriedades.
	 * Por padr�o nenhuma propriedade � ativada durante a inst�ncia sendo necess�rio definir.
	 */

	public Format()
	{
		properties = new BitWise("TRIM", "BLANK_VALUES", "BLANK_COMUNS", "TAB_AS_SPACE", "PROPERTIE_SPACE_IN_COLUMN");
		objects = new DynamicList<Object>();
	}

	/**
	 * Permite definir as regras a serem utilizadas para a formata��o.
	 * @param format string contendo as regras de formata��o.
	 * @return vetor contendo o nome de cada campo definido.
	 * @throws UtilException formato inv�lido ou j� definido.
	 */

	public String[] setFormat(String format) throws UtilException
	{
		if (this.format != null)
			throw new UtilException("formato j� definido");

		String[] types = isFormat(format);

		this.format = format;

		return types;
	}

	/**
	 * Remover espa�os no inicio e no fim do conte�do de uma coluna.
	 * @param enable true para habilitar ou false para desabilitar.
	 */

	public void setTrim(boolean enable)
	{
		if (enable)
			properties.set(PROPERTIE_TRIM);
		else
			properties.unset(PROPERTIE_TRIM);
	}

	/**
	 * Aceitar colunas que n�o contenham valores em branco (sem bytes).
	 * Nesse caso os valores ser�o obtidos como objetos em branco ou zero.
	 * @param enable true para habilitar ou false para desabilitar.
	 */

	public void setAcceptBlankValues(boolean enable)
	{
		if (enable)
			properties.set(PROPERTIE_BLANK_VALUES);
		else
			properties.unset(PROPERTIE_BLANK_VALUES);
	}

	/**
	 * Aceitar colunas que contenham apenas valores em branco (apenas espa�os).
	 * Nesse caso os valores ser�o obtidos como objetos em branco ou zero.
	 * @param enable true para habilitar ou false para desabilitar.
	 */

	public void setAcceptBlankColumns(boolean enable)
	{
		if (enable)
			properties.set(PROPERTIE_BLANK_COLUMNS);
		else
			properties.unset(PROPERTIE_BLANK_COLUMNS);
	}

	/**
	 * Considerar espa�amentos com tabs como espa�os em branco '\t' e ' '.
	 * @param enable true para habilitar ou false para desabilitar.
	 */

	public void setSpaceTab(boolean enable)
	{
		if (enable)
			properties.set(PROPERTIE_TAB_AS_SPACE);
		else
			properties.unset(PROPERTIE_TAB_AS_SPACE);
	}

	/**
	 * Permite obter objetos analisados a partir de um formato definido e conte�do de uma string.
	 * @param content conte�do do qual dever� ser analisado de acordo com a formata��o.
	 * @return vetor contendo os objetos feitos da an�lise.
	 * @throws UtilException apenas se o formato for inv�lido.
	 */

	public Object[] parse(String content) throws UtilException
	{
		objects.clear();

		StringUtil.checkParse(format);
		StringUtil.checkParse(content);

		nodeFormat = new StringExtend(format);
		nodeContent = new StringExtend(content);

		try {

			for (col = 0; !nodeFormat.finish();)
			{
				if (nodeFormat.get() == TYPE_ALTERNATIVE || nodeFormat.get(nodeFormat.offset() + 1) == TYPE_ALTERNATIVE)
					alternative = true;

				switch (checkChar(nodeFormat.get()))
				{
					case CLASS_ALTERNATIVE:
						parseAlternative();
						continue;

					case CLASS_BRACE:
					case CLASS_REPEAT:
						parseRepeatClass();
						break;

					case CLASS_PRIMITIVE:
						col++;
						parsePrimitiveClass();
						break;

					case CLASS_ARRAY:
						col++;
						parseArrayClass();
						break;

					case CLASS_MATRIX:
						col++;
						parseMatrixClass();
						break;
				}

				if (parseEndLoop())
					break;
			}

		} catch (UtilException e) {
			throw new UtilException(e, "coluna: %d", col);
		}

		if (!nodeContent.finish())
			throw new UtilException("conte�do sobrando");

		return objects.toArray();
	}

	/**
	 * Procedimento que ir� fazer a an�lise do conte�do com o formado para verificar se � alternativo.
	 * Quanto alternativo, significa que uma parte da formata��o n�o � obrigat�ria no conte�do.
	 * Caso o conte�do j� tenha sido terminado ent�o ir� encerrar a itera��o da formata��o.
	 */

	private void parseAlternative()
	{
		if (!nodeContent.finish())
		{
			nodeFormat.next();
			return;
		}

		nodeFormat.terminate();
	}

	/**
	 * Procedimento que ir� fazer a an�lise do conte�do com o formato para verificar repeti��o.
	 * Repeti��o � usada quando caracteres no conte�do devem ser iguais ao da formata��o.
	 * Aqui faz a verifica��o para identifica��o de espa�amentos como espa�os simples.
	 * @throws UtilException 
	 */

	private void parseRepeatClass() throws UtilException
	{
		if (nodeFormat.get() != nodeContent.get())
			throw new UtilException("caracter inesperado (esperado: %s, encontrado: %s)", nodeFormat.get(), nodeContent.get());

		nodeFormat.next();
		nodeContent.next();
	}

	/**
	 * Chamado sempre que detectar uma formata��o que deve ser analisado um dado primitivo.
	 * @throws UtilException ocorre apenas por falha na formata��o.
	 */

	private void parsePrimitiveClass() throws UtilException
	{
		char primitive = nodeFormat.nextChar();
		char separator = lastSeparator = nodeFormat.nextChar();

		if (separator == TYPE_ALTERNATIVE)
			separator = lastSeparator = nodeFormat.nextChar();

		Object object = parsePrimitiveContent(primitive, separator, separator);
		objects.add(object);

		nodeContent.next();
	}

	/**
	 * Chamado sempre que detectar uma formata��o que deve ser constru�do um vetor de de dados primitivos.
	 * @throws UtilException ocorre apenas por falha na formata��o.
	 */

	private void parseArrayClass() throws UtilException
	{
		char arrayType = nodeFormat.nextChar();
		char separator = lastSeparator = nodeFormat.get();

		String number = new String();

		while (nodeFormat.next() && Character.isDigit(nodeFormat.get()))
			number += nodeFormat.get();

		if (!IntUtil.isInteger(number))
			throw new UtilException("tamanho '%s' do vetor inv�lido", number);

		char primitive = nodeFormat.get();	nodeFormat.next();

		if (checkChar(primitive) != CLASS_PRIMITIVE)
			throw new UtilException("tipo de vetor '%s' desconhecido", primitive);

		char force = nodeFormat.get();

		int size = IntUtil.parse(number);

		if (arrayType == TYPE_ARRAY_DINAMIC)
			parseArrayDinamic(primitive, size, separator, force);

		else
			parseArrayStatic(primitive, size, separator, force);
	}

	/**
	 * Procedimento que ir� analisar o conte�do atualmente usado na formata��o para encontrar um vetor din�mico.
	 * Vetores din�micos permitem que os elementos encontrados n�o sejam de quantidade igual ao seu tamanho.
	 * Assim sendo, � poss�vel existir um n�mero de elementos igual ou menor que o tamanho definido.
	 * @param primitive caracter representado tipo primitivo de dados que ser� analisado.
	 * @param size n�mero limite de elementos que poder�o ser encontrados no conte�do.
	 * @param separator separador que ir� fazer a divis�o entre os elementos.
	 * @param force caracter que ser� usado para indicar o final do vetor.
	 * @throws UtilException apenas se houver muitos elementos.
	 */

	@SuppressWarnings("unchecked")
	private void parseArrayDinamic(char primitive, int size, char separator, char force) throws UtilException
	{
		DynamicList<Object> list = (DynamicList<Object>) gerDynamicList(primitive);

		Object object = parsePrimitiveContent(primitive, separator, force);
		list.add(object);

		for (int i = 0; nodeContent.get() == separator; i++)
		{
			if (!nodeContent.next())
				throw new UtilException("falta conte�do para vetor din�mico (i: %d, size: %d)", i, size);

			if (nodeContent.finish())
			{
				if (nodeFormat.offset() == nodeFormat.length() - 1)
				{
					object = getPrimitiveNullContent(nodeFormat.get());
					objects.add(object);
					nodeFormat.next();
				}

				break;
			}

			object = parsePrimitiveContent(primitive, separator, force);
			list.add(object);
		}

		if (list.size() > size)
			throw new UtilException("muitos elementos %d/%d", list.size(), size);

		Object array = list.toArray();
		objects.add(array);
	}

	@SuppressWarnings("unchecked")
	private void parseArrayStatic(char primitive, int size, char separator, char force) throws UtilException
	{
		DynamicList<Object> list = (DynamicList<Object>) gerDynamicList(primitive);

		for (int i = 0; i < size; i++)
		{
			Object object = parsePrimitiveContent(primitive, separator, force);
			list.add(object);

			if (i < size -1)
				nodeContent.next();
		}

		if (nodeContent.finish())
		{
			if (nodeFormat.offset() == nodeFormat.length() - 1 && list.size() != size)
			{
				Object object = getPrimitiveNullContent(nodeFormat.get());
				objects.add(object);
				nodeFormat.next();
			}
		}

		if (list.size() < size)
			throw new UtilException("falta de dados (size: %d, required: %d)", list.size(), size);

		Object array = list.toArray();
		objects.add(array);
	}

	/**
	 * Chamado sempre que detectar uma formata��o que deve ser constru�do uma matriz de de dados primitivos.
	 * @throws UtilException ocorre apenas por falha na formata��o.
	 */

	@SuppressWarnings("unchecked")
	private void parseMatrixClass() throws UtilException
	{
		nodeFormat.next();

		char firstSeparator = nodeFormat.get();

		String numberRows = new String();

		while (nodeFormat.next() && Character.isDigit(nodeFormat.get()))
			numberRows += nodeFormat.get();

		if (!IntUtil.isInteger(numberRows))
			throw new UtilException("tamanho '%s' do vetor inv�lido", numberRows);

		char secondSeparator = nodeFormat.get();
		String numberColumns = new String();

		while (nodeFormat.next() && Character.isDigit(nodeFormat.get()))
			numberColumns += nodeFormat.get();

		if (!IntUtil.isInteger(numberColumns))
			throw new UtilException("tamanho '%s' do vetor inv�lido", numberColumns);

		char primitive = nodeFormat.get();	nodeFormat.next();
		char force = nodeFormat.get();

		int rows = IntUtil.parse(numberRows);
		int columns = IntUtil.parse(numberColumns);

		DynamicList<DynamicList<?>> matrix = getMatrixList(primitive);
		nodeContent.back();

		for (int i = 0; i < rows && nodeContent.next(); i++)
		{
			DynamicList<Object> list = (DynamicList<Object>) gerDynamicList(primitive);

			for (int j = 0; j < columns && !nodeContent.finish(); j++)
			{
				Object object = parsePrimitiveContent(primitive, secondSeparator, firstSeparator);
				list.add(object);

				if (nodeContent.get() == secondSeparator && nodeContent.next())
					continue;
				else
					break;
			}

			matrix.add(list);

			if (nodeContent.get() == firstSeparator)
				continue;

			else if (nodeContent.get() == force)
				break;
		}

		objects.add(matrix.size());

		for (DynamicList<?> list : matrix)
			objects.add((Object) list.toArray());
	}

	/**
	 * Chamado ap�s a finaliza��o da an�lise de um caracter analisado como tipo de dado ou repeti��o.
	 * Sua finalidade � verificar se ainda h� ou n�o conte�do para ser processado na formata��o.
	 * @return true se ainda houver conte�do para ser processado, seja qual for ele.
	 * @throws UtilException ocorre apenas se a formata��o tiver um fim inesperado.
	 */

	private boolean parseEndLoop() throws UtilException
	{
		if (isContentEnd(nodeFormat, nodeContent))
		{
			if (nodeFormat.offset() == nodeFormat.length() - 1)
			{
				Object object = getPrimitiveNullContent(nodeFormat.get());
				objects.add(object);
				nodeFormat.next();

				return true;
			}

			if (!alternative)
				throw new UtilException("conte�do acabado inesperadamente");

			return true;
		}

		if (properties.is(PROPERTIE_SPACE_IN_COLUMN) || properties.is(PROPERTIE_TRIM))
		{
			while (nodeContent.get() == ' ' || (nodeContent.get() == '\t') && properties.is(PROPERTIE_TAB_AS_SPACE))
				if (!nodeContent.next())
					return true;
		}

		return false;
	}

	/**
	 * Constr�i uma nova lista de acordo com um tipo primitivo.
	 * @param primitive caracter respectivo ao tipo primitivo.
	 * @return aquisi��o da lista do tipo primitivo passado.
	 */

	public static DynamicList<?> gerDynamicList(char primitive)
	{
		DynamicList<?> list;

		switch (primitive)
		{
			case TYPE_BOOLEAN:	list = new DynamicList<Boolean>();		list.setGeneric(Boolean.class);		return list;
			case TYPE_BYTE:		list = new DynamicList<Byte>();			list.setGeneric(Byte.class);		return list;
			case TYPE_CHAR:		list = new DynamicList<Character>();	list.setGeneric(Character.class);	return list;
			case TYPE_SHORT:	list = new DynamicList<Short>();		list.setGeneric(Short.class);		return list;
			case TYPE_INT:		list = new DynamicList<Integer>();		list.setGeneric(Integer.class);		return list;
			case TYPE_LONG:		list = new DynamicList<Long>();			list.setGeneric(Long.class);		return list;
			case TYPE_FLOAT:	list = new DynamicList<Float>();		list.setGeneric(Float.class);		return list;
			case TYPE_DOUBLE:	list = new DynamicList<Double>();		list.setGeneric(Double.class);		return list;
			case TYPE_STRING:	list = new DynamicList<String>();		list.setGeneric(String.class);		return list;
		}

		return new DynamicList<Object>();
	}

	/**
	 * Constr�i uma nova matriz de acordo com um tipo primitivo.
	 * @param primitive caracter respectivo ao tipo primitivo.
	 * @return aquisi��o da matriz do tipo primitivo passado.
	 */

	public static DynamicList<DynamicList<?>> getMatrixList(char primitive)
	{
		DynamicList<DynamicList<?>> list = new DynamicList<DynamicList<?>>();

		switch (primitive)
		{
			case TYPE_BOOLEAN:	list.setGeneric(Boolean.class);		break;
			case TYPE_BYTE:		list.setGeneric(Byte.class);		break;
			case TYPE_CHAR:		list.setGeneric(Character.class);	break;
			case TYPE_SHORT:	list.setGeneric(Short.class);		break;
			case TYPE_INT:		list.setGeneric(Integer.class);		break;
			case TYPE_LONG:		list.setGeneric(Long.class);		break;
			case TYPE_FLOAT:	list.setGeneric(Float.class);		break;
			case TYPE_DOUBLE:	list.setGeneric(Double.class);		break;
			case TYPE_STRING:	list.setGeneric(String.class);		break;
		}

		return list;
	}

	/**
	 * Verifica se uma determinada formata��o � v�lida ou n�o.
	 * @param format string contendo a formata��o que ser� verificada.
	 * @return vetor contendo o nome dos tipos de dados do formato.
	 * @throws UtilException exce��o com mensagem do erro da formata��o.
	 */

	public static String[] isFormat(String format) throws UtilException
	{
		StringUtil.checkParse(format);

		StringExtend root = new StringExtend(format);
		DynamicList<String> types = new DynamicList<String>();

		while (!root.finish())
		{
			switch (checkChar(root.get()))
			{
				case CLASS_ALTERNATIVE:
					root.next();
					continue;

				case CLASS_BRACE:
				case CLASS_REPEAT:
					root.next(); // Tipo primitivo
					continue;

				case CLASS_PRIMITIVE:
					isFormatPrimitive(root, types);
					continue;

				case CLASS_ARRAY:
					isFormatArray(root, types);
					continue;

				case CLASS_MATRIX:
					isFormatMatrix(root, types);
					continue;
			}
		}

		types.setGeneric(String.class);

		return types.toArray();
	}

	/**
	 * Procedimento interno usado para fazer a an�lise de um formato de dados especificado.
	 * Essa an�lise deve ser chamada somente quando o pr�ximo dado for de um tipo primitivo.
	 * @param root refer�ncia da string estendida contendo a formata��o dos dados.
	 * @param types refer�ncia da lista contendo os tipos de dados j� analisados.
	 * @throws UtilException ocorre apenas se houver alguma irregularidade na formata��o.
	 */

	private static void isFormatPrimitive(StringExtend root, DynamicList<String> types)
	{
		root.next(); // Tipo primitivo
		root.next(); // Separador

		String type = getTypeName(root.get(root.offset() - 2));
		types.add(type);
	}

	/**
	 * Procedimento interno usado para fazer a an�lise de um formato de dados especificado.
	 * Essa an�lise deve ser chamada somente quando o pr�ximo dado for do tipo vetor.
	 * @param root refer�ncia da string estendida contendo a formata��o dos dados.
	 * @param types refer�ncia da lista contendo os tipos de dados j� analisados.
	 * @throws UtilException ocorre apenas se houver alguma irregularidade na formata��o.
	 */

	private static void isFormatArray(StringExtend root, DynamicList<String> types) throws UtilException
	{
		String type = getTypeName(root.get());		root.next(); // Separador
		String number = new String();

		while (root.next() && Character.isDigit(root.get()))
			number += root.get();

		if (!IntUtil.isInteger(number))
			throw new UtilException("tamanho de vetor incorreto pr�ximo a %s", root.fear(6));

		char primitive = root.get();	root.next(); // Separador For�ado

		type = String.format("%s<%s>[%s]", type, getTypeName(primitive), number);
		types.add(type);
	}

	/**
	 * Procedimento interno usado para fazer a an�lise de um formato de dados especificado.
	 * Essa an�lise deve ser chamada somente quando o pr�ximo dado for do tipo matriz.
	 * @param root refer�ncia da string estendida contendo a formata��o dos dados.
	 * @param types refer�ncia da lista contendo os tipos de dados j� analisados.
	 * @throws UtilException ocorre apenas se houver alguma irregularidade na formata��o.
	 */

	private static void isFormatMatrix(StringExtend root, DynamicList<String> types) throws UtilException
	{
		root.next(); // Primeiro Separador

		String rowNumber = new String();

		while (root.next() && Character.isDigit(root.get()))
			rowNumber += root.get();

		if (!IntUtil.isInteger(rowNumber))
			throw new UtilException("matriz com linha inv�lida pr�ximo a %s", root.fear(6));

		root.next(); // Segundo Separador

		String columnNumber = new String();

		while (root.next() && Character.isDigit(root.get()))
			columnNumber += root.get();

		if (!IntUtil.isInteger(columnNumber))
			throw new UtilException("matriz com coluna inv�lida pr�ximo a %s", root.fear(6));

		char primitive = root.get();	root.next(); // Separador For�ado

		if (checkChar(primitive) != CLASS_PRIMITIVE)
			throw new UtilException("tipo de matriz '%s' desconhecido", primitive);

		types.add(String.format("matrix.%s.[%s][%s]", getTypeName(primitive), rowNumber, columnNumber));
	}

	/**
	 * Verifica se o conte�do teve um fim inesperado de acordo com a formata��o.
	 * @param format string estendida respectivo ao formato que ser� considerado.
	 * @param content string estendida respectivo ao conte�do que ser� verificado.
	 * @throws UtilException conte�do finalizado mas ainda h� formata��o a ser feita.
	 */

	private boolean isContentEnd(StringExtend format, StringExtend content)
	{
		return content.finish() && !format.finish();
	}

	/**
	 * Procedimento que deve fazer a an�lise do conte�do em formata��o para obter um valor primitivo.
	 * @param primitive caracter respectivo ao tipo primitivo do dado do qual dever� ser analisado.
	 * @param separator caracter que ser� indicado como separador para encontrar o fim da coluna.
	 * @param force caracter que ir� for�ar o t�rmino do item no conte�do passado.
	 * @return objeto analisado e convertido a partir do tipo primitivo desejado.
	 * @throws UtilException falha durante a obten��o ou convers�o do conte�do.
	 */

	private Object parsePrimitiveContent(char primitive, char separator, char force) throws UtilException
	{
		int last = nodeContent.offset() - 1;

		if ((nodeContent.offset() == 0 && nodeContent.get() == separator) || // inicio do conte�do com separador (coluna vazia)
			(nodeContent.get(last) == separator && nodeContent.get() == separator) || // separador antes e agora (coluna vazia)
			(nodeContent.offset() == nodeContent.length() - 1 && nodeContent.get() == lastSeparator && separator == 0) || // fim do conte�do com separador (coluna vazia)
			((nodeContent.get(last) == ' ' || (nodeContent.get() == '\t' && properties.is(PROPERTIE_TAB_AS_SPACE))) && (nodeContent.get() == separator)) // espa�o (coluna em branco)
			)
			return getPrimitiveNullContent(primitive);

		String value = getNextItem(nodeContent, separator, force);

		if (properties.is(PROPERTIE_TRIM))
			value = value.trim();

		if (properties.is(PROPERTIE_BLANK_VALUES) && value.isEmpty())
			return getPrimitiveNullContent(primitive);

		return parsePrimititiveObject(primitive, value);
	}

	/**
	 * Faz o processamento de um determinado conte�do em string para um valor em objeto.
	 * Esse valor em objeto ser� primitivo e de acordo com o tipo desejado passado.
	 * @param primitive qual deve ser o valor primitivo retornado da convers�o.
	 * @param value conte�do em string do qual ser� convertido para o tipo primitivo.
	 * @return objeto j� convertido a partir do valor e tipo primitivo passado.
	 * @throws UtilException tipo primitivo inv�lido ou conte�do incorreto para o tipo definido.
	 */

	private Object parsePrimititiveObject(char primitive, String value) throws UtilException
	{
		switch (primitive)
		{
			case TYPE_BOOLEAN:	return parsePrimitiveBoolean(value);
			case TYPE_BYTE:		return parsePrimitiveByte(value);
			case TYPE_CHAR:		return parsePrimitiveChar(value);
			case TYPE_SHORT:	return parsePrimitiveShort(value);
			case TYPE_INT:		return parsePrimitiveInt(value);
			case TYPE_LONG:		return parsePrimitiveLong(value);
			case TYPE_FLOAT:	return parsePrimitiveFloat(value);
			case TYPE_DOUBLE:	return parsePrimitiveDouble(value);
			case TYPE_STRING:	return value;
		}

		throw new UtilException("tipo primitivo inv�lido");
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo boolean.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveBoolean(String value) throws UtilException
	{
		switch (BooleanUtil.parseString(value))
		{
			case BooleanUtil.BOOLEAN_FALSE:
				return false;

			case BooleanUtil.BOOLEAN_TRUE:
				return true;
		}

		throw new UtilException("'%s' n�o � boolean", value);
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo byte.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveByte(String value) throws UtilException
	{
		try {
			return ByteUtil.parseString(value);
		} catch (UtilException e) {
			throw new UtilException("'%s' n�o � byte, %s", value, e.getMessage());
		}
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo char.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveChar(String value) throws UtilException
	{
		if (value.length() > 1)
			throw new UtilException("'%s' n�o � um caracter", value);

		return value.charAt(0);
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo short.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveShort(String value) throws UtilException
	{
		try {
			return ShortUtil.parseString(value);
		} catch (UtilException e) {
			throw new UtilException("'%s' n�o � short, %s", value, e.getMessage());
		}
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo int.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveInt(String value) throws UtilException
	{
		try {
			return IntUtil.parseString(value);
		} catch (UtilException e) {
			throw new UtilException("'%s' n�o � int, %s", value, e.getMessage());
		}
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo long.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveLong(String value) throws UtilException
	{
		try {
			return LongUtil.parseString(value);
		} catch (UtilException e) {
			throw new UtilException("'%s' n�o � long, %s", value, e.getMessage());
		}
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo float.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveFloat(String value) throws UtilException
	{
		try {
			return FloatUtil.parseString(value);
		} catch (UtilException e) {
			throw new UtilException("'%s' n�o � float, %s", value, e.getMessage());
		}
	}

	/**
	 * Analisa uma determinada string e converte essa para um valor do tipo double.
	 * @param value string que ir� carregar o esperado valor num�rico respectivo.
	 * @return valor primitivo obtido a partir da convers�o feita da string.
	 * @throws UtilException apenas quando o valor n�o for v�lido.
	 */

	private Object parsePrimitiveDouble(String value) throws UtilException
	{
		try {
			return DoubleUtil.parseString(value);
		} catch (UtilException e) {
			throw new UtilException("'%s' n�o � double, %s", value, e.getMessage());
		}
	}

	/**
	 * De acordo com o tipo primitivo do objeto ir� retornar um conte�do "nulo".
	 * @param primitive tipo de dado primitivo que ser� obtido o valor nulo.
	 * @return zero se for do tipo num�rico, false para booleano,
	 * em branco para strings ou ent�o null se n�o for primitivo.
	 */

	private Object getPrimitiveNullContent(char primitive)
	{
		switch (primitive)
		{
			case TYPE_BOOLEAN:	return false;
			case TYPE_BYTE:		return (byte) 0;
			case TYPE_CHAR:		return (char) 0;
			case TYPE_SHORT:	return (short) 0;
			case TYPE_INT:		return 0;
			case TYPE_LONG:		return 0L;
			case TYPE_FLOAT:	return 0.0F;
			case TYPE_DOUBLE:	return 0.0D;
			case TYPE_STRING:	return "";
		}

		return null;
	}

	/**
	 * Constr�i uma string contento o conte�do completo at� o pr�ximo ponto de separa��o.
	 * Os pontos de separa��o podem ser visualizados atrav�s de VALID_SEPARATORS.
	 * Esse m�todo considera o uso de {} [] () como uma esp�cie de conjunto de dados �nicos.
	 * Portanto em quanto n�o for fechado (se aberto) n�o ir� retornar mesmo com separador.
	 * Assim � poss�vel haver um separador dentro desses conjuntos sem que retorne.
	 * Por�m se dentro deles houver um separador for�ado ir� retornar exception em conjuntos.
	 * @param content string estendida que ser� usado para obter o pr�ximo item.
	 * @param separator caracter que ser� considerado o separador desse.
	 * @param force caracter que ir� for�ar o encerramento obrigat�rio do item,
	 * caso n�o seja necess�rio for�ar, deve possuir o mesmo valor de primitive.
	 * @return string contendo o valor respectivo do pr�ximo item.
	 * @throws UtilException conjunto de dados mal inv�lido.
	 */

	public static String getNextItem(StringExtend content, char separator, char force) throws UtilException
	{
		int offset = content.offset();
		int length = -1;

		Bracket bracket = new Bracket();

		if ((checkChar(separator) == CLASS_BRACE || checkChar(force) == CLASS_BRACE) && checkChar(content.get()) == CLASS_BRACE)
			return "";

		bracket.parseOpen(content.get());
		bracket.parseClose(content.get());

		while (content.next() || bracket.has())
		{
			if (length == -1)
				length = 1;

			if( (content.get() == force && force != separator) || (content.get() == separator && !bracket.has()) )
				break;

			bracket.parseOpen(content.get());
			bracket.parseClose(content.get());
			bracket.isMuch();

			length++;
		}

		bracket.isLittle();

		return content.cut(offset, length);
	}

	/**
	 * Verifica qual a classe do qual um determinado tipo de dado determinado por um caracter �.
	 * @param c caracter do qual deve ser obtido a classe do qual representa.
	 * @return c�digo da classe do caracter analisado, pode ser encontrado por CLASS_{?}.
	 */

	public static int checkChar(char c)
	{
		if (c == TYPE_ALTERNATIVE)
			return CLASS_ALTERNATIVE;

		if (isPrimitiveChar(c))
			return CLASS_PRIMITIVE;

		if (isArrayChar(c))
			return CLASS_ARRAY;

		if (c == TYPE_MATRIX)
			return CLASS_MATRIX;

		if (isBraceChar(c))
			return CLASS_BRACE;

		return CLASS_REPEAT;
	}

	/**
	 * Chamado durante a verifica��o de um caracter de uma determinada formata��o.
	 * Essa verifica��o consiste em distinguir se � um tipo de dado ou repeti��o de caracter.
	 * @param c caracter do qual ser� verificado se � de um tipo de dado primitivo.
	 * @return true se for de um tipo de dado primitivo ou false caso contr�rio.
	 */

	private static boolean isPrimitiveChar(char c)
	{
		switch (c)
		{
			case TYPE_BOOLEAN:
			case TYPE_BYTE:
			case TYPE_CHAR:
			case TYPE_SHORT:
			case TYPE_INT:
			case TYPE_LONG:
			case TYPE_FLOAT:
			case TYPE_DOUBLE:
			case TYPE_STRING:
				return true;
		}

		return false;
	}

	/**
	 * Chamado durante a verifica��o de um caracter de uma determinada formata��o.
	 * Essa verifica��o consiste em distinguir se � um tipo de dado ou repeti��o de caracter.
	 * @param c caracter do qual ser� verificado se � de um tipo de dado em vetor.
	 * @return true se for de um tipo de dado em vetor ou false caso contr�rio.
	 */

	private static boolean isArrayChar(char c)
	{
		switch (c)
		{
			case TYPE_ARRAY_STATIC:
			case TYPE_ARRAY_DINAMIC:
				return true;
		}

		return false;
	}

	/**
	 * Chamado durante a verifica��o de um caracter de uma determinada formata��o.
	 * Essa verifica��o consiste em distinguir se � um tipo de dado ou repeti��o de caracter.
	 * @param c caracter do qual ser� verificado se � de um tipo de formata��o com brace.
	 * @return true se for de um tipo formata��o com brace ou false caso contr�rio.
	 */

	private static boolean isBraceChar(char c)
	{
		switch (c)
		{
			case '(':
			case ')':
			case '[':
			case ']':
			case '{':
			case '}':
				return true;
		}

		return false;
	}

	/**
	 * Permite obter qual o nome do tipo de dado de acordo com o seu caracter de identifica��o.
	 * @param type caracter do qual deseja saber o nome dentro de uma formata��o.
	 * @return nome do tipo de dado do qual esse caracter representa na formata��o.
	 */

	public static String getTypeName(char type)
	{
		switch (type)
		{
			case TYPE_BOOLEAN: return "boolean";
			case TYPE_BYTE: return "byte";
			case TYPE_CHAR: return "char";
			case TYPE_SHORT: return "short";
			case TYPE_INT: return "int";
			case TYPE_LONG: return "long";
			case TYPE_FLOAT: return "fload";
			case TYPE_DOUBLE: return "double";
			case TYPE_STRING: return "string";
			case TYPE_ARRAY_STATIC: return "array.static";
			case TYPE_ARRAY_DINAMIC: return "array.dinamic";
		}

		return "null";
	}

	@Override
	public String toString()
	{
		StringBuffer str = new StringBuffer("Format");

		str.append("[");
		str.append("col: " +col);
		str.append(", alternative: " +alternative);
		str.append(", properties: (" +properties.toStringProperties()+ ")");
		str.append("]");

		return str.toString();
	}
}
