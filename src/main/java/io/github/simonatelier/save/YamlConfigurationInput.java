package io.github.simonatelier.save;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class YamlConfigurationInput implements Input {

	private YamlConfiguration config;
	
	public YamlConfigurationInput(YamlConfiguration config) {
		this.config = config;
	}

	@Override
	public int readInt(String key) throws IOException {
		return config.getInt(key);
	}

	@Override
	public boolean readBoolean(String key) throws IOException {
		return config.getBoolean(key);
	}

	@Override
	public float readFloat(String key) throws IOException {
		return (float) config.getDouble(key);
	}

	@Override
	public double readDouble(String key) throws IOException {
		return config.getDouble(key);
	}

	@Override
	public long readLong(String key) throws IOException {
		return config.getLong(key);
	}

	@Override
	public String readString(String key) throws IOException {
		return config.getString(key);
	}

}
