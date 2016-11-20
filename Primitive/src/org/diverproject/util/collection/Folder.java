package org.diverproject.util.collection;

/**
 * <p><h1>Pasta</h1></p>
 *
 * <p>Classes que utilizam essa interface devem implementar m�todos que permite torn�-la uma pasta virtual.
 * Uma pasta virtual n�o apenas ir� trabalhar com os elementos como tamb�m com caminho de diret�rio como chave.
 * Para que um elemento seja considerado um arquivo virtual dever� utilizar da interface adequada para tal.</p>
 *
 * <p>Espera-se que a aloca��o dos elementos sejam feitos de modo que a utiliza��o de caminhos facilite a busca.
 * Caso esse requisito n�o seja atendido na implementa��o, a utiliza��o desta interface ir� perder seu real valor.</p>
 *
 * @see Collection
 *
 * @author Andrew Mello
 *
 * @param <E> tipo de elemento do qual ser� armazenado.
 */

public interface Folder<E> extends Collection<E>
{
	/**
	 * Adiciona um novo elemento a essa pasta virtual, deve verificar o caminho do mesmo.
	 * O arquivo deve ser alocado de acordo com o seu caminho especificado no mesmo.
	 * @param element refer�ncia do elemento (arquivo) do qual deve ser inserido.
	 * @return true se conseguir adicionar ou false caso contr�rio.
	 */

	boolean add(E element);

	/**
	 * Remove um elemento (arquivo) da pasta virtual a partir do seu caminho no mesmo.
	 * @param path caminho onde est� alocado o arquivo do qual deve ser removido.
	 * @return true se conseguir remover ou false caso contr�rio.
	 */

	boolean remove(String path);

	/**
	 * Verifica se um determinado caminho existe dentro dessa pasta virtual.
	 * @param path caminho respectivo ao arquivo do qual deseja verificar.
	 * @return true se existir o caminho ou false caso contr�rio.
	 */

	boolean contains(String path);

	/**
	 * Obt�m um determinado arquivo de acordo com o caminho especificado do mesmo.
	 * @param path caminho do qual se encontra o arquivo na pasta virtual.
	 * @return aquisi��o do arquivo no caminho especificado ou null se n�o encontrar.
	 */

	E get(String path);

	/**
	 * O nome da pasta � o que indica como deve ser o inicio de todos os caminhos.
	 * @return aquisi��o do nome que foi definido a pasta ra�z principal.
	 */

	String getName();
}
