package org.diverproject.util.stream;

/**
 * <h1>Escritor</h1>
 *
 * <p>Interface com o objetivo de permitir que uma classe possa escrever dados em uma comunica��o.
 * Os dados podem ser escritos em string como uma linha de um arquivo de texto simples ou ent�o
 * em bytes que normalmente � usado por arquivos "codificados", armazenar dados primitivos.</p>
 *
 * <p>Com essa interface, ser� poss�vel facilitar a utiliza��o da ideia de heran�a m�ltipla.
 * No caso, ser� usado a delega��o em alguns casos, onde essa interface ser� usada como fronteira.
 * Assim, ser� poss�vel fazer a escrita de dados na comunica��o sem saber como ele ser� feita.</p>
 *
 * @see Stream
 *
 * @author Andrew Mello
 */

public interface Writer extends Stream
{
	/**
	 * Permite escrever um �nico byte na comunica��o estabelecida de acordo com o ponteiro de escrita.
	 * No caso, o ponteiro de escrita sempre ser� o tamanho em que a comunica��o se encontra.
	 * Para a escrita, o ponteiro sempre ser� cont�nuo, nunca poder� retroceder na linha.
	 * @param b byte do qual dever� ser escrito no local do ponteiro de escrita.
	 */

	void write(byte b);

	/**
	 * Permite escrever uma o conte�do de uma string inteira na atual linha em que se encontra.
	 * Ir� escrever os bytes dessa string iniciando onde o ponteiro de escrita indica na comunica��o.
	 * Ap�s fazer a escrita da string ir� adicionar uma quebra de linha padr�o ao final.
	 * @param line refer�ncia da string contendo os bytes do qual ser�o escritos na linha.
	 */

	void writeLine(String line);

	/**
	 * Ao ser chamado dever� fazer uma quebra de linha na comunica��o que � denominada por '\n'.
	 */

	void writeBreakLine();
}
