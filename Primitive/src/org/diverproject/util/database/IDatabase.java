package org.diverproject.util.database;

/**
 * <p><h1>Database</h1></p>
 *
 * <p>Interface que possui alguns m�todos que permitem trabalhar como forma de banco de dados.
 * Esses m�todos devem ser implementados para fazer o gerenciamento dos elementos no banco.
 * Assim sendo permitindo adicionar, atualizar, remover e selecionar elementos no mesmo.
 * Tamb�m permite obter o n�mero de elementos inseridos e tamanho limite do banco de dados.</p>
 *
 * @author Andrew
 *
 * @param <E> tipo de da dado que ser� armazenado no banco de dados.
 */

interface IDatabase<E>
{
	/**
	 * Verifica se o banco de dados est� ou n�o vazio.
	 * @return true se estiver ou false caso contr�rio.
	 */

	boolean isEmpety();

	/**
	 * Verifica se o banco de dados est� ou n�o cheio.
	 * @return true se estiver ou false caso contr�rio.
	 */

	boolean isFully();

	/**
	 * Permite atualizar um determinado �ndice do banco de dados com um novo elemento.
	 * Caso o �ndice esteja ocupado ser� substituido pelo elemento passado.
	 * @param index �ndice do elemento no banco para ser substitu�do.
	 * @param element refer�ncia do elemento do qual dever� substituir.
	 * @return true se atualizar ou false se �ndice inv�lido ou em branco.
	 */

	boolean update(int index, E element);

	/**
	 * Permite remover um determinado elemento em um determinado �ndice.
	 * @param index �ndice do elemento do qual deseja remover do banco.
	 * @return true se conseguir remover ou false caso n�o exista
	 */

	boolean remove(int index);

	/**
	 * Permite selecionar um �nico elemento do banco de dados de acordo com o seu �ndice.
	 * @param index �ndice do elemento que deseja obter de dentro do banco de dados.
	 * @return aquisi��o do elemento no �ndice ou null com �ndice inv�lido ou livre para elemento.
	 */

	E select(int index);

	/**
	 * Constr�i um vetor com os elementos selecionados dos �ndices respectivos.
	 * Caso se um �ndice estiver em branco ou inv�lido ser� preenchido com null.
	 * @param index �ndices separados por v�rgula dos elementos desejados.
	 * @return vetor contendo os elementos respectivos dos �ndices.
	 */

	E[] select(int... index);

	/**
	 * Tamanho do banco de dados determina o seu espa�o interno ocupado.
	 * @return aquisi��o do n�mero de elementos inseridos no banco.
	 */

	int size();

	/**
	 * Comprimento do banco de dados determina o seu espa�o interno m�ximo.
	 * @return aquisi��o do n�mero m�ximo de elementos permitidos.
	 */

	int length();
}
