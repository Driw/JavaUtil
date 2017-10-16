package org.diverproject.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.diverproject.util.lang.StringUtil;

/**
 * <p><h1>Utilit�rio para Arquivo</h1></p>
 *
 * Utilit�rios de gerenciamento de arquivos, permite fazer an�lises com alguns tipos
 * de opera��es relacionadas a arquivos como contagem de linhas, convers�o de bytes
 * para uma string leg�vel dentre outros aspectos �teis para melhorar as aplica��es.
 */

public class FileUtil
{
	/**
	 * Construtor privado pois � um utilit�rio est�tico (apenas m�todos est�ticos).
	 */

	private FileUtil()
	{
		
	}

	/**
	 * Conta quantas linhas existem nos arquivos dentro de um determinado diret�rio.
	 * @param path caminho da pasta do qual ser� analisada.
	 * @return n�mero total de linhas encontradas nos arquivos.
	 */

	public static int countLineIn(String path)
	{
		int count = 0;

		File file = new File(path);

		if (file.isFile())
			try {

				Scanner scan = new Scanner(file);

				while (scan.hasNext())
				{
					scan.nextLine();
					count++;
				}

				scan.close();

				return count;

			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
			}

		for (String s : file.list())
			count += countLineIn(path + "\\" + s);

		return count;
	}

	/**
	 * Corrige caminhos de  de um arquivo ou pasta para o formato padr�o do java.
	 * Alguns caminhos de arquivos s�o definidos com "\" (barra invertida), estes por sua
	 * vez s�o utilizados para express�es regulares e coisas do g�nero.
	 * Assim sendo, este m�todo ir� substitui quaisquer barras ou barras duplas
	 * para o tipo de barra padr�o utilizado para definir caminhos no java: <b>/</b>.
	 * @param path caminho que deve ser corrigido (em  ou local).
	 * @return caminho corrigido com as normas acima.
	 */

	public static String adaptPath(String path)
	{
		if (path == null)
			return null;

		while (path.startsWith("/") || path.startsWith("\\"))
			path = StringUtil.sub(path, 2, path.length() - 1);

		while (path.contains("//"))
			path = path.replace("//", "/");

		while (path.contains("\\\\"))
			path = path.replace("\\\\", "\\");

		return path.replace("\\", "/");
	}

	/**
	 * An�lise o caminho passado a fim de obter apenas a extens�o do arquivo.
	 * <b>Exemplo:</b> [C:\Windows\system32\cmd.exe] : [exe]
	 * @param path caminho do qual deseja obter apenas a extens�o do arquivo.
	 * @return aquisi��o da string contendo apenas a extens�o do arquivo.
	 */

	public static String getExtension(String path)
	{
		path = adaptPath(path);

		if (path.lastIndexOf('.') == -1)
			return "";

		return path.substring(path.lastIndexOf('.') + 1, path.length());
	}

	/**
	 * An�lise o caminho passado a fim de obter apenas o nome do arquivo.
	 * <b>Exemplo:</b> [C:\Windows\system32\cmd.dll.exe] : [cmd.dll]
	 * @param path caminho do qual deseja obter apenas o nome do arquivo.
	 * @return aquisi��o da string contendo apenas o nome do arquivo.
	 */

	public static String getFileName(String path)
	{
		path = adaptPath(path);
		path = getFileNameExtension(path);

		if (path.contains("."))
			path = path.substring(0, path.lastIndexOf("."));

		return path;
	}

	/**
	 * An�lise o caminho passado a fim de obter o nome completo do arquivo.
	 * <b>Exemplo:</b> [C:\Windows\system32\cmd.exe] : [cmd.exe]
	 * @param path caminho do qual deseja obter o nome do arquivo com extens�o.
	 * @return aquisi��o da string contendo o nome do arquivo com extens�o.
	 */

	public static String getFileNameExtension(String path)
	{
		path = adaptPath(path);

		if (path.contains("/"))
			return path.substring(path.lastIndexOf('/') + 1, path.length());

		return path;
	}

	/**
	 * An�lise o caminho passado a fim de obter o caminho completo da pasta ra�z.<br>
	 * <b>Exemplo:</b> [C:\Windows\system32\cmd.exe] : [C:\Windows\system32]
	 * @param path caminho do qual deseja obter o diret�rio externo.
	 * @return aquisi��o da string contendo o caminho do diret�rio externo.
	 */

	public static String getParentPath(String path)
	{
		path = adaptPath(path);

		while (path.endsWith("/"))
			path = StringUtil.sub(path, 0, path.length() - 1);

		if (path.contains("/"))
			return path.substring(0, path.lastIndexOf("/"));

		return path + "/";
	}

	/**
	 * An�lise o caminho passado a fim de obter o caminho virtual da ra�z.<br>
	 * <b>Exemplo:</b> [C:\Windows\system32\cmd.exe] : [Windows\system32]
	 * @param path caminho do qual deseja obter o diret�rio interno.
	 * @return aquisi��o da string contendo o caminho do diret�rio interno.
	 */

	public static String getVirtualPath(String path)
	{
		path = adaptPath(path);

		if (path.contains("/"))
		{
			path = path.substring(path.indexOf("/") + 1, path.length());

			if (path.contains("/"))
				path = path.substring(0, path.lastIndexOf("/"));

			return path;
		}

		return path;
	}

	/**
	 * An�lise o caminho passado a fim de obter o caminho virtual completo.<br>
	 * <b>Exemplo:</b> [C:\Windows\system32\cmd.exe] : [Windows\system32\cmd.exe]
	 * @param path caminho do qual deseja obter o diret�rio interno.
	 * @return aquisi��o da string contendo o caminho do diret�rio interno.
	 */

	public static String getInnerPath(String path)
	{
		path = adaptPath(path);

		if (path.contains("/"))
			return path.substring(path.indexOf("/") + 1, path.length());

		return path;
	}

	/**
	 * An�lise o caminho passado a fim de obter apenas o nome da ra�z principal.<br>
	 * <b>Exemplo:</b> [C:\Windows\system32\cmd.exe] : [Windows]
	 * @param path caminho do qual deseja obter o diret�rio interno.
	 * @return aquisi��o da string contendo o caminho do diret�rio interno.
	 */

	public static String getRootName(String path)
	{
		if (path.contains(":"))
			path = path.substring(path.indexOf(":") + 2, path.length());

		path = adaptPath(path);

		return path.substring(0, path.indexOf("/"));
	}

	/**
	 * <p>An�lise o caminho passado a fim de obter apenas o nome do parente.<p>
	 * <p><b>Exemplo:</b> [C:\Windows\system32\cmd.exe] : [system32]</p>
	 * @param path caminho do qual deseja obter o diret�rio interno.
	 * @return aquisi��o da string contendo o caminho do diret�rio interno.
	 */

	public static String getParentName(String path)
	{
		path = getVirtualPath(path);

		return path.substring(path.indexOf("/") + 1, path.length());
	}

	/**
	 * Cria todos os diret�rios de um objeto file especificado caso n�o existam.
	 * @param file refer�ncia do objeto contendo o caminho do arquivo.
	 */

	public static void makeDirs(File file)
	{
		file = new File(file.getAbsolutePath());
		File parent = file.getParentFile();

		if (!parent.exists())
			makeDirs(parent);

		parent.mkdir();
	}
}
