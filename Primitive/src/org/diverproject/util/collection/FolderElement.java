package org.diverproject.util.collection;

/**
 * <p><h1>Elemento de Pasta</h1></p>
 *
 * <p>Interface usada para permitir que um objeto seja armazenado em uma pasta virtual.
 * Precisa implementar apenas um m�todo do qual � usado para obter o caminho do objeto.
 * Esse caminho ser� usado na pasta virtual para indicar onde ele deve ser alocado.</p>
 *
 * @author Andrew Mello
 */

public interface FolderElement
{
	/**
	 * A formata��o padr�o do caminho do arquivo � a utiliza��o de barras <b>/</b>.
	 * Cada barra indica um pasta e o �ltimo elemento desta o nome do arquivo.
	 * @return aquisi��o do caminho do objeto para alocar na pasta virtual.
	 */

	String getFilePath();
}
