package io.github.simonatelier.save;

import java.io.IOException;

public interface Savable {
	
	void write(Exporter exporter) throws IOException;
	
	void read(Importer importer) throws IOException;

}
