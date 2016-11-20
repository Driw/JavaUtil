package org.diverproject.util.stream;

/**
 * <h1>Buffer</h1>
 *
 * <p>Buffer nada mais � do que uma comunica��o de entrada e sa�da em uma massa de dados especificada.
 * Essa massa de dados s� ser� aceita como um vetor de bytes, sendo usado o mesmo para entrada e sa�da.
 * Ir� possuir tanto as fun��es de stream para entrada de dados quanto a stream para sa�da de dados.</p>
 *
 * <p>No buffer, os dois ponteiros (offset, size) ser�o separados para cada uma de suas funcionalidades.
 * Somente nesse tipo de buffer ambos ser�o de fato utilizados de acordo com o que foi especificado.</p>
 *
 * <p>Ainda adiciona algumas fun��es extras como movimentar o ponteiro de escrita e salvar dados.
 * A movimenta��o do ponteiro pode ser feita as vezes para ignorar alguns bytes ou ir direto a um ponto.
 * Em quanto o salvamento de dados ir� dizer que os bytes seguintes lidos ser�o salvos internamente.
 * Esses dados lidos que foram salvos podem ser obtidos posteriormente quando for desejado.</p>
 *
 * <p>Os buffers tamb�m, n�o ir�o fazer uma c�pia do vetor de bytes, e sim utilizado diretamente.
 * Por tanto, o mesmo vetor passado para o buffer, ter� os seus dados alterados de acordo com o buffer.</p>
 *
 * @see Input
 * @see Output
 *
 * @author Andrew Mello
 */

public interface Buffer extends Input, Output
{
	/**
	 * Cria um vetor contendo todos os bytes que foram lidos a partir da chamada de salvamento.
	 * Uma vez que este tenha sido chamado, esse salvamento ser� esquecido sendo necess�rio um novo.
	 */

	byte[] getArrayBuffer();

	/**
	 * Permite fazer a movimenta��o do ponteiro para uma posi��o especifica na comunica��o.
	 * Caso seja movido para um ponteiro inv�lido n�o poder� ser feito a leitura de dados corretamente.
	 * @param offset novo �ndice do qual o ponteiro de leitura dever� indicar na comunica��o.
	 */

	void moveTo(int offset);
}
