package io.github.simonatelier.save;

import java.io.IOException;

public interface Output {

	public void write(int value, String key) throws IOException;
	
	public void write(boolean value, String key) throws IOException;
	
	public void write(float value, String key) throws IOException;
	
	public void write(double value, String key) throws IOException;
	
	public void write(long value, String key) throws IOException;
	
	public void write(String value, String key) throws IOException;
	
}
