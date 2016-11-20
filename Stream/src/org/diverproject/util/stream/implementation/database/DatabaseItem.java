package org.diverproject.util.stream.implementation.database;

import org.diverproject.util.stream.StreamException;

/**
 * <p><h1>Item do Banco de Dados</h1></p>
 *
 * <p>Um item do banco de dados � um conjunto de dados que forma uma informa��o �nica.
 * Por exemplo, em uma database de usu�rios um item seria um �nico usu�rio do mesmo.
 * Se olharmos a database como tabela (normal) seria o mesmo que uma �nica linha.</p>
 *
 * @author Andrew
 */

public interface DatabaseItem
{
	/**
	 * Tamanho de um item � a quantidade de colunas do mesmo.
	 * @return aquisi��o do n�mero total de colunas do item.
	 */

	int size();

	/**
	 * Permite obter um determinado dado do item que seja um boolean.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um boolean de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for boolean.
	 */

	Boolean getBoolean(int column);

	/**
	 * Permite obter um determinado dado do item que seja um byte.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um byte de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for byte.
	 */

	Byte getByte(int column);

	/**
	 * Permite obter um determinado dado do item que seja um short.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um short de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for short.
	 */

	Short getShort(int column);

	/**
	 * Permite obter um determinado dado do item que seja um int.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um int de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for int.
	 */

	Integer getInt(int column);

	/**
	 * Permite obter um determinado dado do item que seja um long.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um long de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for long.
	 */

	Long getLong(int column);

	/**
	 * Permite obter um determinado dado do item que seja um float.
	 * @param index �ndice do elemento do qual ser� obtido o dado.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um float de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for float.
	 */

	Float getFloat(int column);

	/**
	 * Permite obter um determinado dado do item que seja um double.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um double de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for double.
	 */

	Double getDouble(int column);

	/**
	 * Permite obter um determinado dado do item que seja uma string.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de uma string de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for string.
	 */

	String getString(int column);
	/**
	 * Permite obter um determinado dado do item que seja um vetor de boolean.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um boolean de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for boolean.
	 */

	Boolean[] getArrayBoolean(int column);

	/**
	 * Permite obter um determinado dado do item que seja um vetor de byte.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um byte de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for vetor de byte.
	 */

	Byte[] getArrayByte(int column);

	/**
	 * Permite obter um determinado dado do item que seja um vetor de short.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um short de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for vetor de short.
	 */

	Short[] getArrayShort(int column);

	/**
	 * Permite obter um determinado dado do item que seja um vetor de int.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um int de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for vetor de int.
	 */

	Integer[] getArrayInt(int column);

	/**
	 * Permite obter um determinado dado do item que seja um vetor de long.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um long de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for vetor de long.
	 */

	Long[] getArrayLong(int column);

	/**
	 * Permite obter um determinado dado do item que seja um vetor de float.
	 * @param index �ndice do elemento do qual ser� obtido o dado.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um float de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for vetor de float.
	 */

	Float[] getArrayFloat(int column);

	/**
	 * Permite obter um determinado dado do item que seja um vetor de double.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de um double de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for vetor de double.
	 */

	Double[] getArrayDouble(int column);

	/**
	 * Permite obter um determinado dado do item que seja um vetor de string.
	 * @param column n�mero da coluna em que ser� obtido o dado desejado.
	 * @return aquisi��o de uma string de posi��o especificada como 'column'.
	 * @throws StreamException coluna inv�lida ou ent�o se o dado n�o for vetor de string.
	 */

	String[] getArrayString(int column);

	/**
	 * Quando esse m�todo for usado significa que apenas at� uma determinada coluna � obrigat�ria.
	 * Usado para quando houver dados alternativos e esses dever�o retornar em branco se n�o houver.
	 * @param column n�mero de colunas que ser� obrigat�rio a exist�ncia no item em quest�o.
	 */

	void setMaxRequired(int column);
}
