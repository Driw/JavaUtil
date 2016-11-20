package org.diverproject.util;

/**
 * <p><h1>Pode Copiar</h1></p>
 *
 * Classes que implementam essa interface determinam que seus objetos podem copiar os dados
 * de um outro objeto. Espera-se que essa c�pia n�o fa�a uma refer�ncia dos objetos do mesmo,
 * e sim uma c�pia, gerando os mesmos dados por�m com refer�ncias diferentes em mem�ria.
 *
 * @param <E> tipo de 
 */

public interface CanCopy<E>
{
	/**
	 * Copia os dados de um outro objeto esperado ser do mesmo tipo.
	 * @param e objeto do qual deve ser copiados os dados.
	 */

	void copyFrom(E e);
}
