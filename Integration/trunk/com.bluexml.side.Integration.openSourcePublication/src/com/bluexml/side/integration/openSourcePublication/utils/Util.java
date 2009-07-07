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
	 * Renvoi vers la bonne méthode en fonction du type (fichier ou repertoire)
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
	 * Ajout de la licence (en commentaire) au début du fichier
	 * 
	 * @param file
	 */
	public static void addLicense(File file) {
		String[] sep = file.getName().split("\\.");
		String extention = sep[sep.length - 1];
		

		if (ouvrirFichier("comment.properties").containsKey(extention)) {
			System.out.println("File: " + file);
			
			String type = ouvrirFichier("comment.properties").getProperty(extention).split(
			",")[0];
			
			try {
				PrintWriter writer = new PrintWriter(new FileWriter(file
						+ ".txt"));

				if (type.equals("multi")) {
					writer.append(getStartComment(extention) + "\n");
					writer.append(loadFile(new File("LICENSE-notices")));
					writer.append("\n" + getEndComment(extention) + "\n\n\n");
					writer.append(loadFile(file));
				} else {
					writer.append(getMonoLicense(extention) + "\n\n\n");
					writer.append(loadFile(file));
				}
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
	 * Retourne la licence avec le caractère de commentaire en début de chaque
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
				out += getStartComment(type) + " " + ligne +"\n";
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
	 * Méthode qui ouvre le fichier de proprerties
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
		return ouvrirFichier("comment.properties").getProperty(type).split(
				",")[1];
	}

	private static String getEndComment(String type) {
		return ouvrirFichier("comment.properties").getProperty(type).split(
				",")[2];
	}

	/**
	 * Retourne le contenu du fichier passé en paramètre
	 * 
	 * @param f
	 * @return
	 */
	public static String loadFile(File f) {
		StringWriter out = null;
		try {
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(f));
			out = new StringWriter();
			int b;
			while ((b = in.read()) != -1)
				out.write(b);
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
