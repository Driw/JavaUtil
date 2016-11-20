package org.diverproject.util.collection;

/**
 * <p><h1>Cole��o</h1></p>
 *
 * <p>Todos os tipos para constru��o das estruturas de dados dessa biblioteca s�o cole��es.
 * Esse nome � dado pelo fato de colecionarem dados e estes serem de um tipo pr�-definido.
 * Cada tipo de estrutura possui suas vantagens e desvantagens de acordo com o objetivo.</p>
 *
 * <p>Existem diversos tipos de estruturas de dados que podem ser criadas para cada situa��o.
 * Os mais comuns ser�o aplicados nessa biblioteca como cole��es (listas, filas, pilhas).
 * Tendo em vista a possibilidade em que podem ser est�ticas como tamb�m din�micas.</p>
 *
 * <p>Sua funcionalidade � a mesma em ambos os modos diferenciando apenas na sua capacidade.
 * Onde no caso das est�ticas a sua capacidade � fixa no momento em que este for criado.
 * Em quanto a din�mica permite um n�mero "infinito" de elementos (Integer.MAX_VALUE).</p>
 *
 * <p>Al�m dessas funcionalidade em comum, uma cole��o tamb�m pode sofrer ser iter�vel.
 * Quanto iter�vel permite que este possa passar pelo procedimento de <b>for each</b>.
 * Assim � poss�vel "obter todos os elementos na adicionados na cole��o de uma s� vez".</p>
 *
 * @see Iterable
 *
 * @author Andrew
 *
 * @param <E> qual ser� o tipo de dado que ser� armazenado na cole��o.
 */

public interface Collection<E> extends Iterable<E>
{
	/**
	 * Classe gen�rica de uma cole��o � utilizada pelo toArray.
	 * Onde ser� usado pra criar uma inst�ncia de acordo com tal.
	 * @return aquisi��o do tipo de dado gen�rico dessa cole��o.
	 */

	Class<?> getGeneric();

	/**
	 * Permite definir qual ser� o tipo de dado gen�rico obtido por toArray.
	 * Procedimento utilizado pelos construtores quando definido o mesmo.
	 * Deve ser definido sempre que toArray for usado e com o tipo de dado.
	 * @param generic tipo gen�rico dessa cole��o de dados (respectivo a E).
	 */

	void setGeneric(Class<?> generic);

	/**
	 * Quanto esse procedimento for chamado ir� dizer a estrutura de dados,
	 * que todos os elementos dentro desta podem ser descartados, removidos.
	 * Internamente esse procedimento � feito reconstruindo a fonte.
	 */

	void clear();

	/**
	 * Tamanho da cole��o ir� determinar quantos elementos ela possui.
	 * @return aquisi��o do n�mero de elementos armazenados atualmente.
	 */

	int size();

	/**
	 * Comprimento da cole��o ir� determinar a sua capacidade m�xima.
	 * @return aquisi��o do n�mero de elementos que podem ser armazenados.
	 */

	int length();

	/**
	 * Procedimento que ir� fazer a verifica��o do estado de vazio da cole��o.
	 * @return true se estiver vazia ou false se houver ao menos um elemento.
	 */

	boolean isEmpty();

	/**
	 * Procedimento que ir� fazer a verifica��o do estado de cheio da cole��o.
	 * @return true se estiver cheia ou false se houver ao menos um espa�o.
	 */

	boolean isFull();

	/**
	 * Procedimento que ir� verificar se a cole��o j� possui um determinado elemento.
	 * @param element refer�ncia do objeto (elemento) que ser� verificado a exist�ncia.
	 * @return true se ele j� tiver sido adicionado ou false caso n�o encontrado.
	 */

	boolean contains(E element);

	/**
	 * Constr�i um novo vetor que ir� armazenar todos os elementos da cole��o.
	 * Utiliza o procedimento de itera��o como padr�o por todas cole��es possu�rem.
	 * Para que esse procedimento funcione, deve ser utilizando setGeneric antes.
	 * Caso j� tenha sido definido uma vez ou pelo construtor n�o ter� necessidade.
	 * @return aquisi��o de um vetor contendo todos os elementos da cole��o.
	 */

	E[] toArray();
}
