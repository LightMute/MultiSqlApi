package fr.lightmute.MultiSqlApi;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Fichier {

	File configFile;
	Properties props;
	FileWriter writer;
	FileReader reader;

	public Fichier() {
		configFile = new File("config.properties");
	}

	public void setBaseActive(String baseactive) {
		try {
			props = new Properties();
			writer = new FileWriter(configFile);
			props.setProperty("base.active", baseactive);
			props.store(writer, "Confg de la sql !");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getBaseActive() {
		try {
			props = new Properties();
			reader = new FileReader(configFile);
			props.load(reader);
			return props.getProperty("base.active");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		return null;
	}
	}