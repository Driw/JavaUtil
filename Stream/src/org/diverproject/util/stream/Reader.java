package org.diverproject.util.stream;

/**
 * <h1>Leitor</h1>
 *
 * <p>Interface com o objetivo de permitir que uma classe possa ler dados em uma comunica��o.
 * Os dados podem ser lidos em string como uma linha de um arquivo de texto simples ou ent�o
 * em bytes que normalmente � usado por arquivos "codificados", lendo dados primitivos.</p>
 *
 * <p>Com essa interface, ser� poss�vel facilitar a utiliza��o da ideia de heran�a m�ltipla.
 * No caso, ser� usado a delega��o em alguns casos, onde essa interface ser� usada como fronteira.
 * Assim, ser� poss�vel fazer a leitura de dados na comunica��o sem saber como ele ser� feita.</p>
 *
 * @see Stream
 *
 * @author Andrew Mello
 */

public interface Reader extends Stream
{
	/**
	 * Chamado para que a comunica��o fa�a a leitura do pr�ximo dado (byte) existente nela.
	 * Ir� utilizar o ponteiro de leitura para saber qual o pr�ximo dado que deve ser lido.
	 * No caso de arquivos, isso � feito internamente pela stream padr�o do java.
	 * @return aquisi��o do pr�ximo byte dispon�vel para leitura na comunica��o.
	 */

	byte read();

	/**
	 * Permite fazer a leitura de dados na comunica��o de uma forma um pouco mais din�mica.
	 * Em quanto n�o encontrar uma sequ�ncia de bytes correspondentes ir� continuar a ler.
	 * @param sequence string contendo a sequ�ncia de caracteres (bytes) que ser� o seu fim.
	 * @return aquisi��o dos bytes at� o final da sequ�ncia passada acima.
	 */

	byte[] readAt(String sequence);

	/**
	 * Utiliza o procedimento de leitura at� uma determinada sequ�ncia de caracteres.
	 * Ir� fazer uma leitura at� que se encontre a quebra de linha indicando o final desta.
	 * @return aquisi��o de uma string contendo todos os bytes lidos da atual linha.
	 */

	String readLine();
}
