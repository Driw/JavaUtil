package org.diverproject.util;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * <p><h1>Utilit�rio para Imagem</h1></p>
 *
 * Esta classe ir� conter m�todos utilit�rios para se trabalhar com imagens.
 */

public class ImageUtil
{
	/**
	 * Imagem em branco de tamanho 1x1 pixels.
	 */
	public static final BufferedImage BLANK_IMAGE = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private ImageUtil()
	{
		
	}

	/**
	 * Cria uma nova imagem de acordo com um vetor de bytes, esses bytes podem ser obtidos
	 * atrav�s do metodo createImageBytes() que ir� realizar a opera��o inversa.
	 * @param bytes vetor contendo os bytes da imagem que ser� criada.
	 * @return uma nova imagem criada a partir dos bytes do vetor.
	 */

	public static Image create(byte[] bytes)
	{
		try {
			return ImageIO.read(new ByteArrayInputStream(bytes));
		} catch (IOException e) {
			System.out.printf("Falha ao tentar criar imagem a partir de bytes: %s.\n", e.getMessage());
			return null;
		}
	}

	/**
	 * Converte uma determinada imagem para um vetor de bytes.
	 * @param image imagem que ser� convertida.
	 * @return vetor contendo os bytes da imagem lida.
	 */

	public static byte[] toArrayByte(BufferedImage image)
	{
		byte[] bytes = new byte[]{};

		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);

			baos.flush();
			bytes = baos.toByteArray();
			baos.close();

		} catch (IOException e) {
			System.out.printf("Falha ao tentar converter uma imagem em bytes: %s.\n", e.getMessage());
		}

		return bytes;
	}

	/**
	 * Preenche uma determinada imagem com uma textura de acordo com o n�vel de opacidade do mesmo.
	 * Utiliza duas imagens, uma para se obter as cores e a outra apenas para o n�vel de opacidade.
	 * A imagem criada ser� pintada com a textura (pattern) repetidamente por�m de acordo com o
	 * de opacidade do modelo (template). A nova imagem ser� o template por�m com a textura.
	 * @param pattern textura padronizada que ser� utilizada na colora��o da nova imagem.
	 * @param template modelo que ser� utilizado para se obter o n�vel de opacidade.
	 * @return nova imagem do mesmo jeito que template (n�vel de opacidade) por�m texturizado
	 * repetidamente de acordo com pattern.
	 */

	public static BufferedImage paintTexture(BufferedImage pattern, BufferedImage template)
	{
		BufferedImage repainted = new BufferedImage(template.getWidth(), template.getHeight(), BufferedImage.TYPE_INT_ARGB);

		Graphics g = repainted.getGraphics();

		int width = pattern.getWidth();
		int height = pattern.getHeight();

		for (int x = 0; x < template.getWidth(); x++)
			for (int y = 0; y < template.getHeight(); y++)
			{
				float alpha = ((float) (template.getRGB(x, y) >>> 24) / 255F);

				int px = x % width;
				int py = y % height;

				AlphaComposite ac = java.awt.AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
				((Graphics2D) g).setComposite(ac);
				g.drawImage(pattern, x, y, x + 1, y + 1, px, py, px + 1, py + 1, null);
			}

		return repainted;
	}

	/**
	 * <p>Permit unir diversas imagens em uma �nica imagem, de modo que seja necess�rio definir
	 * alguns tipos de par�metros que permitem melhor uni�o das imagens. O processo � feito
	 * criando uma imagem em branco com o tamanho necess�rio e em seguida lendo em loops as
	 * imagens das partes que devem ser carregadas, iniciando no eixo X e em seguida o Y.</p>
	 * <p>Os pontos <b>xStart</b> e <b>yStart</b> s�o referentes aos �ndices iniciais dos eixos
	 * X e Y do qual o nome dos arquivos dever� conter, de modo que <b>format</b> seja definido
	 * com duas possibilidades num�ricos denominados pelo formato <i>%d</i>.</p>
	 * <p><b>Por exemplo:</b><br>
	 * Os pontos iniciais s�o 5 e 2 (xStart e yStart);<br>
	 * O tamanho � de 2 por 2 (partes, respectivo a width e height);<br>
	 * O formato foi definido como {"parte_%d-%d.jpg"};<br>
	 * Nesse caso os arquivos ser�o lidos como:<br>
	 * - parte_5-2.jpg<br>
	 * - parte_5-3.jpg<br>
	 * - parte_6-2.jpg<br>
	 * - parte_6-3.jpg</p>
	 * @param path caminho que se encontra as partes da imagem que ser� montada.
	 * @param part dimens�es que cada parte da imagem deve possuir.
	 * @param rect width e height determina quantas partes deve fazer a montagem,
	 * em quanto as coordenadas x e y determina o primeiro �ndice da leitura.
	 * @param format formato do nome do arquivo para ser lido durante a uni�o.
	 * @param print exibir no console qual o arquivo que est� sendo renderizado.
	 * @return imagem montada a partir das partes encontradas.
	 */

	public static BufferedImage joinParts(String path, Dimension part, Rectangle rect, String format, boolean print)
	{
		BufferedImage buffer = new BufferedImage(rect.width * part.width, rect.height * part.height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffer.getGraphics();

		int xLimit = rect.x + rect.width;
		int yLimit = rect.y + rect.height;

		for (int x = rect.x; x < xLimit; x++)
			for (int y = rect.y; y < yLimit; y++)
			{
				try {

					String file = String.format(format, x, y);
					BufferedImage coordinate = ImageIO.read(new File(path != null ? path+ "/" +file : file));
					g.drawImage(coordinate, (x * 256) - (part.width * rect.x), (y * 256) - (part.height * rect.y), null);

					if (print)
						System.out.printf("Rendering %s\n", file);

				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}

		return buffer;
	}

	/**
	 * Carrega uma imagem sem que seja necess�rio implementar o TryCatch.
	 * @param path caminho do arquivo do qual ser� carregado como imagem.
	 * @return imagem carregada ou null caso n�o seja poss�vel (try catch).
	 */

	public static BufferedImage load(String path)
	{
		try {
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * Cria uma imagem a partir das informa��es de imagem de um �cone especificado.
	 * @param icon refer�ncia do �cone do qual ser� convertido (ImageIcon � mais pr�tico).
	 * @return aquisi��o de uma nova imagem com o �cone desenhado no mesmo.
	 */

	public static Image toImage(Icon icon)
	{
		if (icon instanceof ImageIcon)
			return ((ImageIcon) icon).getImage();

		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		BufferedImage image = gc.createCompatibleImage(icon.getIconWidth(), icon.getIconHeight());

		Graphics2D g = image.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		g.dispose();

		return image;
	}
}
