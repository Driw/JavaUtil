package org.diverproject.util;

/**
 * <p><h1>Descri��o do Objeto</h1></p>
 *
 * <p>Essa classe � utilizada nos m�todos toString que todos objetos possuem para visualizar.
 * Possuindo um padr�o para exibir as informa��es do objeto de acordo com Java Util.
 * Nele deve ser definido a classe que ser� descrevida e em seguida, pode ser definido
 * todos os atributos contido nele, como tamb�m o nome que ser� dado a esse na string.</p>
 *
 * @author Andrew
 */

public class ObjectDescription
{
	/**
	 * Nome da classe que ser� descrevida.
	 */
	private String className;

	/**
	 * Conte�do respectivo aos atributos definidos.
	 */
	private String attributes;

	/**
	 * Constr�i uma nova descri��o para objetos sendo necess�rio definir a classe.
	 * @param c refer�ncia da classe do qual ser� usada para descrever o objeto.
	 */

	public ObjectDescription(Class<?> c)
	{
		if (c != null)
			className = c.getSimpleName();
		attributes = "";
	}

	/**
	 * Anexar um determinado campo do objeto (atributo) para essa descri��o.
	 * @param value refer�ncia da vari�vel do qual est� querendo colocar na descri��o.
	 */

	public void append(Object value)
	{
		attributes += String.format("%s, ", value);
	}

	/**
	 * Anexar um determinado campo do objeto (atributo) para essa descri��o.
	 * @param field qual ser� o nome do campo que est� sendo anexado (nome da vari�vel).
	 * @param value refer�ncia da vari�vel do qual est� querendo colocar na descri��o.
	 */

	public void append(String field, Object value)
	{
		attributes += String.format("%s: %s, ", field, value);
	}

	@Override
	public String toString()
	{
		if (className != null)
		{
			if (attributes.length() > 2)
				return String.format("%s[%s]", className, attributes.substring(0, attributes.length() - 2));

			return String.format("%s[%s]", className, attributes);
		}

		if (attributes.length() > 2)
			return String.format("%s", attributes.substring(0, attributes.length() - 2));

		return String.format("%s", attributes);

	}
}
