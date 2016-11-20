package org.diverproject.util.stream.implementation.option;

/**
 * <p><h1>Stream para Op��es</h1></p>
 *
 * <p>Deve implementar m�todos que permite obter valores atrav�s de chaves.
 * Essas chaves s�o salvos para referenciar um determinado dado na stream.
 * Atrav�s dessas chaves � poss�vel obter-se um determinado dano.</p>
 *
 * @author Andrew
 */

public interface OptionInput extends StreamOption
{
	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo byte.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @param notfound valor que ser� retornado se n�o encontrar a op��o.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	byte getByte(String name, byte notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo char.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @param notfound valor que ser� retornado se n�o encontrar a op��o.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	char getChar(String name, char notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo short.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @param notfound valor que ser� retornado se n�o encontrar a op��o.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	short getShort(String name, short notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo int.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @param notfound valor que ser� retornado se n�o encontrar a op��o.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	int getInt(String name, int notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo long.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	long getLong(String name, long notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo float.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @param notfound valor que ser� retornado se n�o encontrar a op��o.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	float getFloat(String name, float notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo double.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @param notfound valor que ser� retornado se n�o encontrar a op��o.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	double getDouble(String name, double notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo String.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @param notfound valor que ser� retornado se n�o encontrar a op��o.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	String getString(String name, String notfound);

	/**
	 * Pega a pr�xima op��o sendo esperado que seja do tipo boolean.
	 * @param name nome da op��o seguinte escrita na stream.
	 * @return valor que foi definido a essa op��o quando salvo.
	 */

	boolean getBoolean(String name, boolean notfound);
}
