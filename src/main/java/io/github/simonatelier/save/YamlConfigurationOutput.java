package io.github.simonatelier.save;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class YamlConfigurationOutput implements Output {
	
	private YamlConfiguration config;

	public YamlConfigurationOutput(YamlConfiguration config) {
		this.config = config;
	}

	@Override
	public void write(int value, String key) throws IOException {
		config.set(key, value);
	}

	@Override
	public void write(boolean value, String key) throws IOException {
		config.set(key, value);
	}

	@Override
	public void write(float value, String key) throws IOException {
		config.set(key, value);
	}

	@Override
	public void write(double value, String key) throws IOException {
		config.set(key, value);
	}

	@Override
	public void write(long value, String key) throws IOException {
		config.set(key, value);
	}

	@Override
	public void write(String value, String key) throws IOException {
		config.set(key, value);
	}
	
}
