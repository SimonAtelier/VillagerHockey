package io.github.simonatelier.save;

import org.bukkit.configuration.file.YamlConfiguration;

public class YamlConfigurationImporter implements Importer {

	private YamlConfiguration config;

	public YamlConfigurationImporter(YamlConfiguration config) {
		this.config = config;
	}

	@Override
	public Input getInput(Object object) {
		return new YamlConfigurationInput(config);
	}

}
