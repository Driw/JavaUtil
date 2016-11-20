package org.diverproject.util.stream;

/**
 * <h1>Entrada</h1>
 *
 * <p>Usado para determinar uma stream como entrada de dados na aplica��o, tamb�m � um leitor na comunica��o.
 * Possui alguns procedimentos b�sicos que ir� permitir trabalhar com dados primitivos atrav�s dos bytes.
 * Classes que implementem essa interface dever�o especificar como ser� feito a convers�o dos bytes.</p>
 *
 * @see Reader
 *
 * @author Andrew Mello
 */

public interface Input extends Reader
{
	/**
	 * Funciona como uma simples leitura por read(), retornando o pr�ximo byte dispon�vel a leitura.
	 * @return aquisi��o do pr�ximo byte da comunica��o, caso n�o haja mais nenhum retorna 0.
	 */

	byte getByte();

	/**
	 * Chama repetidas vezes o m�todo getByte() a fim de ler diversos bytes de uma s� vez.
	 * @param size quantidade de bytes do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os bytes desejados de acordo com o tamanho.
	 */

	byte[] getBytes(int size);

	/**
	 * Chama repetidas vezes o m�todo getByte() a fim de ler diversos bytes de uma s� vez.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximos bytes a serem lidos.
	 */

	void getBytes(byte[] array);

	/**
	 * Faz a leitura do pr�ximo byte dispon�vel para ser lido dentro da comunica��o estabelecida.
	 * Atrav�s do calor do byte passado converte esse para um valor em caracter.
	 * @return aquisi��o do pr�ximo byte da comunica��o convertido para caracter.
	 */

	char getChar();

	/**
	 * Chama repetidas vezes o m�todo getChar() afim de ler diversos caracteres de uma s� vez.
	 * @param size quantidade de caracteres do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os caracteres desejados de acordo com o tamanho.
	 */

	char[] getChars(int size);

	/**
	 * Chama repetidas vezes o m�todo getChar() afim de ler diversos caracteres de uma s� vez.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximos caracteres a serem lidos.
	 */

	void getChars(char[] array);

	/**
	 * Faz a leitura dos pr�ximos dois bytes dispon�veis para serem lidos na comunica��o estabelecida.
	 * Utiliza da verifica��o de comunica��o invertida caso esteja habilitada, invertendo os bytes.
	 * @return aquisi��o dos pr�ximos dois bytes da comunica��o convertidos para um n�mero short.
	 */

	short getShort();

	/**
	 * Chama repetidas vezes o m�todo getShort() afim de ler diversos short de uma s� vez.
	 * @param size quantidade de shorts do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os shorts desejados de acordo com o tamanho.
	 */

	short[] getShorts(int size);

	/**
	 * Chama repetidas vezes o m�todo getShort() afim de ler diversos short de uma s� vez.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximos shorts a serem lidos.
	 */

	void getShorts(short[] array);

	/**
	 * Faz a leitura dos pr�ximos quatro bytes dispon�veis para serem lidos na comunica��o estabelecida.
	 * Utiliza da verifica��o de comunica��o invertida caso esteja habilitada, invertendo os bytes.
	 * @return aquisi��o dos pr�ximos quatro bytes da comunica��o convertidos para um n�mero int.
	 */

	int getInt();

	/**
	 * Chama repetidas vezes o m�todo getInt() afim de ler diversos inteiros de uma s� vez.
	 * @param size quantidade de inteiros do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os inteiros desejados de acordo com o tamanho.
	 */

	int[] getInts(int size);

	/**
	 * Chama repetidas vezes o m�todo getInt() afim de ler diversos inteiros de uma s� vez.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximos inteiros a serem lidos.
	 */

	void getInts(int[] array);

	/**
	 * Faz a leitura dos pr�ximos oito bytes dispon�veis para serem lidos na comunica��o estabelecida.
	 * Utiliza da verifica��o de comunica��o invertida caso esteja habilitada, invertendo os bytes.
	 * @return aquisi��o dos pr�ximos oito bytes da comunica��o convertidos para um n�mero long.
	 */

	long getLong();

	/**
	 * Chama repetidas vezes o m�todo getLong() afim de ler diversos longs de uma s� vez.
	 * @param size quantidade de longs do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os longs desejados de acordo com o tamanho.
	 */

	long[] getLongs(int size);

	/**
	 * Chama repetidas vezes o m�todo getLong() afim de ler diversos longs de uma s� vez.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximos longs a serem lidos.
	 */

	void getLongs(long[] array);

	/**
	 * Faz a leitura dos pr�ximos quatro bytes dispon�veis para serem lidos na comunica��o estabelecida.
	 * Utiliza da verifica��o de comunica��o invertida caso esteja habilitada, invertendo os bytes.
	 * @return aquisi��o dos pr�ximos quatro bytes da comunica��o convertidos para um n�mero float.
	 */

	float getFloat();

	/**
	 * Chama repetidas vezes o m�todo getFloat() afim de ler diversos floats de uma s� vez.
	 * @param size quantidade de floats do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os floats desejados de acordo com o tamanho.
	 */

	float[] getFloats(int size);

	/**
	 * Chama repetidas vezes o m�todo getFloat() afim de ler diversos float de uma s� vez.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximos floats a serem lidos.
	 */

	void getFloats(float[] array);

	/**
	 * Faz a leitura dos pr�ximos oito bytes dispon�veis para serem lidos na comunica��o estabelecida.
	 * Utiliza da verifica��o de comunica��o invertida caso esteja habilitada, invertendo os bytes.
	 * @return aquisi��o dos pr�ximos oito bytes da comunica��o convertidos para um n�mero double.
	 */

	double getDouble();

	/**
	 * Chama repetidas vezes o m�todo getFloat() afim de ler diversos doubles de uma s� vez.
	 * @param size quantidade de doubles do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os doubles desejados de acordo com o tamanho.
	 */

	double[] getDoubles(int size);

	/**
	 * Chama repetidas vezes o m�todo getFloat() afim de ler diversos double de uma s� vez.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximos doubles a serem lidos.
	 */

	void getDoubles(double[] array);

	/**
	 * Faz a leitura dos pr�ximos <b>n</b> bytes dispon�veis para serem lidos na comunica��o estabelecida.
	 * O n�mero obtido desse pr�ximo byte ir� determinar qual o tamanho da string em bytes.
	 * @return aquisi��o da pr�xima <b>n</b> bytes da comunica��o convertidos para uma string.
	 */

	String getString();

	/**
	 * Chama repetidas vezes o m�todo getStrings() afim de ler diversos strings de uma s� vez.
	 * Aqui o primeiro byte ser� considerado o tamanho da string seguido do conte�do.
	 * @param size quantidade de string do qual deseja que o vetor retornado possua.
	 * @return vetor contendo todos os string desejados de acordo com o tamanho.
	 */

	String[] getStrings(int size);

	/**
	 * Chama repetidas vezes o m�todo getStrings() afim de ler diversos strings de uma s� vez.
	 * Aqui o primeiro byte ser� considerado o tamanho da string seguido do conte�do.
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximas strings a serem lidos.
	 */

	void getStrings(String[] array);

	/**
	 * Faz a leitura dos pr�ximos bytes dispon�veis para serem lidos na comunica��o estabelecida.
	 * @param length quantos bytes ser�o lidos para formar a pr�xima string na comunica��o.
	 * @return aquisi��o de uma nova string contendo os pr�ximos <b>length</b> bytes.
	 */

	String getString(int length);

	/**
	 * Chama repetidas vezes o m�todo getStrings() afim de ler diversos strings de uma s� vez.
	 * Aqui ir� considerar os pr�ximos <b>size</b> bytes como parte do conte�do da string.
	 * Al�m disso, dever� recortar o mesmo assim que achar um byte do tipo NULL (byte: 0).
	 * @param size quantidade de string do qual deseja que o vetor retornado possua.
	 * @param length quantos caracteres dever� possuir cada string que for obtida.
	 * @return vetor contendo todos os string desejados de acordo com o tamanho.
	 */

	String[] getStrings(int size, int length);

	/**
	 * Chama repetidas vezes o m�todo getStrings() afim de ler diversos strings de uma s� vez.
	 * Aqui ir� considerar os pr�ximos <b>size</b> bytes como parte do conte�do da string.
	 * Al�m disso, dever� recortar o mesmo assim que achar um byte do tipo NULL (byte: 0).
	 * @param array vetor do qual deseja carreg�-lo com os pr�ximas strings a serem lidos.
	 * @param length quantidade de string do qual deseja que o vetor retornado possua.
	 */

	void getStrings(String[] array, int length);
}
