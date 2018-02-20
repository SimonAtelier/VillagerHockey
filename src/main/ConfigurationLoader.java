package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.bukkit.configuration.file.YamlConfiguration;

import gateways.configuration.Configuration;
import gateways.configuration.ConfigurationYaml;

public class ConfigurationLoader {

	private Configuration configuration;

	private String resourcePath;
	private String pluginFolderPath;
	
	public boolean exists() {
		File file = new File(pluginFolderPath);
		return file.exists();
	}

	private void loadFromPluginFolder() {
		File file = new File(pluginFolderPath);
		YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
		configuration = new ConfigurationYaml(yamlConfiguration);
	}

	private void createConfigurationFromResource() throws IOException {
		File file = new File(pluginFolderPath);
		file.createNewFile();
		InputStream is = null;
		FileOutputStream os = null;
		try {
			is = getClass().getResource(resourcePath).openStream();
			os = new FileOutputStream(file);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
	}

	public Configuration loadConfiguration() {
		if (!exists()) {
			try {
				createConfigurationFromResource();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		loadFromPluginFolder();
		return configuration;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public String getPluginFolderPath() {
		return pluginFolderPath;
	}

	public void setPluginFolderPath(String pluginFolderPath) {
		this.pluginFolderPath = pluginFolderPath;
	}

}
