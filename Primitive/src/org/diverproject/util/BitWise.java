package org.diverproject.util;

/**
 * <p><h1>Bit Wise</h1></p>
 *
 * <p>O sistema de bit mask � usado para determinar op��es preferenciais de modo mais pr�tico.
 * Para usar op��es na programa��o onde algo � ativado ou desativado normalmente se usa o 'booleano'.
 * Valores booleanos podem assumir tanto true quando false (ativado/desativado, sim/n�o, verdadeiro/falso).
 * Na mem�ria isso ir� ocupar exatamente 1 byte para cada valor booleano o que normalmente � usado no fim.</p>
 *
 * <p>Mas algumas vezes queremos usar mais do que uma �nica op��o para determinar prefer�ncias a algo.
 * Quando encontramos uma possibilidade de usar mais de quatro op��es � recomend�vel usar o 'bit mask'.
 * Bit Mask nada mais � do que considerar um valor num�rico inteiro ou longo (int/long) para true/false.
 * Para cada bit no n�mero considerado, ser� determinado uma op��o true ou false para o �ndice do bit.</p>
 *
 * <p>Por exemplo, consideramos os seguintes n�meros abaixo e em seguida o seus bits separados.</p>
 *
 * <p>Hexa | Dec: [Bit 1] [Bit 2] [Bit 3] [Bit 4]<br>
 *<br>
 * <b>0x00 | 00:</b> [ 0 ] [ 0 ] [ 0 ] [ 0 ]<br>
 * <b>0x01 | 01:</b> [ 0 ] [ 0 ] [ 0 ] [ 1 ]<br>
 * <b>0x02 | 02:</b> [ 0 ] [ 0 ] [ 1 ] [ 0 ]<br>
 * <b>0x03 | 03:</b> [ 0 ] [ 0 ] [ 1 ] [ 1 ]<br>
 * <b>0x04 | 04:</b> [ 0 ] [ 1 ] [ 0 ] [ 0 ]<br>
 * <b>0x05 | 05:</b> [ 0 ] [ 1 ] [ 0 ] [ 1 ]<br>
 * <b>0x06 | 06:</b> [ 0 ] [ 1 ] [ 1 ] [ 0 ]<br>
 * <b>0x07 | 07:</b> [ 0 ] [ 1 ] [ 1 ] [ 1 ]<br>
 * <b>0x08 | 08:</b> [ 1 ] [ 0 ] [ 0 ] [ 0 ]<br>
 * <b>0x09 | 09:</b> [ 1 ] [ 0 ] [ 0 ] [ 1 ]<br>
 * <b>0x0A | 10:</b> [ 1 ] [ 0 ] [ 1 ] [ 0 ]<br>
 * <b>0x0B | 11:</b> [ 1 ] [ 0 ] [ 1 ] [ 1 ]<br>
 * <b>0x0C | 12:</b> [ 1 ] [ 1 ] [ 0 ] [ 0 ]<br>
 * <b>0x0D | 13:</b> [ 1 ] [ 1 ] [ 0 ] [ 1 ]<br>
 * <b>0x0E | 14:</b> [ 1 ] [ 1 ] [ 1 ] [ 0 ]<br>
 * <b>0x0F | 15:</b> [ 1 ] [ 1 ] [ 1 ] [ 1 ]</p>
 *
 * <p>Se considerarmos cada Bit (1~4) como uma op��o podemos aqui em 4bits determinar 4 op��es.
 * Onde todas as possibilidades de escolhas entre as ativar/desativar as 4 op��es podem ser consideradas.
 * Essas considera��es est�o em um intervalo entre os valores 0x00 (0) at� 0x0F (15) sem faltar combina��es.</p>
 *
 * @author Andrew
 */

public class BitWise
{
	/**
	 * Vetor que ir� guardar o nome das propriedades.
	 */
	private String propertieNames[];

	/**
	 * Valor do qual 
	 */
	private int value;

	/**
	 * Constr�i um novo BitWise permitindo definir o nome das propriedades.
	 * @param propertieNames nome das propriedades separados por v�rgula.
	 */

	public BitWise(String... propertieNames)
	{
		this.propertieNames = propertieNames;
	}

	/**
	 * As propriedades definidas a esse bitwise s�o guardados nesse valor.
	 * @return aquisi��o do valor num�rico inteiro das propriedades.
	 */

	public int getValue()
	{
		return value;
	}

	/**
	 * Permite definir um valor as propriedades atrav�s dos bits de um n�mero inteiro.
	 * @param value valor inteiro que ir� guardar as propriedades desse bitwise.
	 */

	public void setValue(int value)
	{
		this.value = value;
	}

	/**
	 * Verifica se esse bitwise atende uma ou mais propriedades com o seu valor atual.
	 * Ir� verificar cada bit do n�mero passado, considerando apenas os bin�rios 1.
	 * Pode passar mais de um valor utilizando separador | como � usado em java.
	 * @param propertie valor da propriedade que ser� verificada se cont�m.
	 * @return true se contiver todas as propriedades passadas ou false caso contr�rio.
	 */

	public boolean is(int propertie)
	{
		return (value & propertie) == propertie;
	}

	/**
	 * Define uma ou mais propriedades de acordo com a necessidade e objetivo de uso.
	 * Ir� verificar cada bit do n�mero passado, considerando apenas os bin�rios 1.
	 * Pode passar mais de um valor utilizando separador | como � usado em java.
	 * Caso a propriedade j� tenha sido definida ir� continuar como definida.
	 * @param propertie valor da propriedade que ser� definido ao bitwise.
	 */

	public void set(int propertie)
	{
		value |= propertie;
	}

	/**
	 * Desconsidera uma ou mais propriedades de acordo com a necessidade e objetivo de uso.
	 * Ir� verificar cada bit do n�mero passado, considerando apenas os bin�rios 1.
	 * Pode passar mais de um valor utilizando separador | como � usado em java.
	 * Caso a propriedade n�o tenha sido definida essa ir� continuar sem definir.
	 * @param propertie valor da propriedade que ser� removido do bitwise.
	 */

	public void unset(int propertie)
	{
		value -= value & propertie;
	}

	/**
	 * Constr�i uma string que ir� guardar todas as propriedades descritivas desse bitwise.
	 * Ir� considerar o nome das propriedades passado no construtor ou ent�o caso n�o
	 * tenha sido definido ou n�o exista ir� usar o padr�o BIT{n�mero do bit}.
	 * @return string contendo todas as propriedades definidas separadas por v�rgula.
	 */

	public String toStringProperties()
	{
		StringBuilder str = new StringBuilder();

		if (propertieNames.length == 0 || propertieNames == null)
			str.append(Integer.toBinaryString(value));

		else
		{
			String binary = Integer.toBinaryString(value);

			for (int i = 0; i < binary.length(); i++)
			{
				if (binary.charAt(binary.length() - 1 - i) == '0')
					continue;

				if (i >= propertieNames.length)
					str.append("BIT" +i);

				else
					str.append(propertieNames[i]);

				if (i < binary.length() - 1)
					str.append(", ");
			}
		}

		return str.toString();
	}

	@Override
	public String toString()
	{
		StringBuilder str = new StringBuilder("BitWise");

		str.append("[");
		str.append(toStringProperties());
		str.append("]");

		return str.toString();
	}

	/**
	 * Verifica se esse bitwise atende uma ou mais propriedades com o seu valor atual.
	 * Ir� verificar cada bit do n�mero passado, considerando apenas os bin�rios 1.
	 * Pode passar mais de um valor utilizando separador | como � usado em java.
	 * @param value valor od qual ser� considerado na verifica��o de exist�ncia.
	 * @param propertie valor da propriedade que ser� verificada se cont�m.
	 * @return true se contiver todas as propriedades passadas ou false caso contr�rio.
	 */

	public static boolean is(int value, int propertie)
	{
		return (value & propertie) == propertie;
	}

	/**
	 * Define uma ou mais propriedades de acordo com a necessidade e objetivo de uso.
	 * Ir� verificar cada bit do n�mero passado, considerando apenas os bin�rios 1.
	 * Pode passar mais de um valor utilizando separador | como � usado em java.
	 * Caso a propriedade j� tenha sido definida ir� continuar como definida.
	 * @param value valor od qual ser� alterado conforme a propriedade passada.
	 * @param propertie valor da propriedade que ser� definido ao bitwise.
	 */

	public static int set(int value, int propertie)
	{
		return (value |= propertie);
	}

	/**
	 * Desconsidera uma ou mais propriedades de acordo com a necessidade e objetivo de uso.
	 * Ir� verificar cada bit do n�mero passado, considerando apenas os bin�rios 1.
	 * Pode passar mais de um valor utilizando separador | como � usado em java.
	 * Caso a propriedade n�o tenha sido definida essa ir� continuar sem definir.
	 * @param value valor od qual ser� alterado conforme a propriedade passada.
	 * @param propertie valor da propriedade que ser� removido do bitwise.
	 */

	public static int unset(int value, int propertie)
	{
		return (value -= value & propertie);
	}
}
