package io.github.simonatelier.save;

import org.bukkit.configuration.file.YamlConfiguration;

public class YamlConfigurationExporter implements Exporter {
	
	private YamlConfiguration config;
	
	public YamlConfigurationExporter(YamlConfiguration config) {
		this.config = config;
	}
	
	@Override
	public Output getOutput(Object object) {
		return new YamlConfigurationOutput(config);
	}

}
