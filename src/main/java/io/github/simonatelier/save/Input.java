package io.github.simonatelier.save;

import java.io.IOException;

public interface Input {
	
	public int readInt(String key) throws IOException;
	
	public boolean readBoolean(String key) throws IOException;
	
	public float readFloat(String key) throws IOException;
	
	public double readDouble(String key) throws IOException;
	
	public long readLong(String key) throws IOException;
	
	public String readString(String key) throws IOException;

}
