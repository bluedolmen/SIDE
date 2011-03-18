/*
    Copyright (C) 2007-2011  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/


package com.bluexml.side.integration.openSourcePublication.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

public class Util {

	/**
	 * Renvoi vers la bonne m�thode en fonction du type (fichier ou repertoire)
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void doTreatment(File file) throws IOException {
		if (file.isFile()) {
			addLicense(file);
		} else if (file.isDirectory()) {
			if (!file.getName().equals(".svn")) {
				doDirectory(file);
			}
		} else {
			throw new FileNotFoundException(file.toString() + " does not exist");
		}
	}

	/**
	 * Execute le traitement sur tous les fichier (ou dossier) que contient le
	 * repertoire
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void doDirectory(File file) throws IOException {
		File[] inDir = file.listFiles();
		for (int i = 0; i < inDir.length; i++) {
			File fileTemp = inDir[i];
			doTreatment(fileTemp);
		}
	}

	/**
	 * Ajout de la licence (en commentaire) au d�but du fichier
	 * 
	 * @param file
	 */
	public static void addLicense(File file) {
		String[] sep = file.getName().split("\\.");
		String extention = sep[sep.length - 1];

		if (ouvrirFichier("comment.properties").containsKey(extention)) {
			//System.out.println("File: " + file);

			String type = ouvrirFichier("comment.properties").getProperty(
					extention).split(",")[0];

			try {
				PrintWriter writer = new PrintWriter(new FileWriter(file
						+ ".txt"));

				writer.append(loadFile(file, extention, type));
				
				// fermeture des flux
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// Suppression de l'ancien fichier
			file.delete();
			// Renomage du nouveau
			new File(file + ".txt").renameTo(file);
		}
	}

	/**
	 * Retourne la licence avec le caract�re de commentaire en d�but de chaque
	 * ligne
	 * 
	 * @param type
	 * @return
	 */
	private static String getMonoLicense(String type) {
		String licensePath = "LICENSE-notices";

		String out = "";
		String ligne = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					licensePath));
			while ((ligne = reader.readLine()) != null) {
				out += getStartComment(type) + " " + ligne + "\n";
			}
			// fermeture des flux
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out;
	}

	/**
	 * M�thode qui ouvre le fichier de proprerties
	 * 
	 */
	public static Properties ouvrirFichier(String fichier) {
		FileInputStream fileStream = null;
		Properties properties = null;

		try {
			fileStream = new FileInputStream(fichier);

			properties = new Properties();

			properties.load(fileStream);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return properties;
	}

	private static String getStartComment(String type) {
		return ouvrirFichier("comment.properties").getProperty(type).split(",")[1];
	}

	private static String getEndComment(String type) {
		return ouvrirFichier("comment.properties").getProperty(type).split(",")[2];
	}

	/**
	 * Retourne le contenu du fichier pass� en param�tre
	 * 
	 * @param f
	 * @return
	 */
	public static String loadFile(File f, String extention, String type) {
		StringWriter out = null;
		boolean license = false;
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			out = new StringWriter();
			String line;

			if ("LICENSE-notices".equals(f.getName())) {
				while ((line = in.readLine()) != null) {
					out.append(line+"\n");
				}
			} else {
				while ((line = in.readLine()) != null) {
					if (line.indexOf("<?xml version=") != -1 && !license) {
						if (type.equals("multi")) {
							out.append(line+"\n");
							out.append(getStartComment(extention) + "\n");
							out.append(loadFile(new File("LICENSE-notices"),
									null, null));
							out.append("\n" + getEndComment(extention)
									+ "\n\n\n");
						} else {
							out.append(line+"\n");
							out.append(getMonoLicense(extention) + "\n\n\n");
						}

						license = true;
					} else if(!license && !line.equals("")){
						if (type.equals("multi")) {
							out.append(getStartComment(extention) + "\n");
							out.append(loadFile(new File("LICENSE-notices"),
									null, null));
							out.append("\n" + getEndComment(extention)
									+ "\n\n\n");
						} else {
							out.append(getMonoLicense(extention) + "\n\n\n");
						}
						out.append(line+"\n");
						license = true;
					} else {
						out.append(line+"\n");
					}
				}

			}
			out.flush();
			out.close();
			in.close();
			return out.toString();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		return out.toString();
	}

}
