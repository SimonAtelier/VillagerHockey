package io.github.simonatelier.save;

import java.io.IOException;

public class OutputMock implements Output {

	@Override
	public void write(int value, String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("write int (" + key + ":" + value + ")");
	}

	@Override
	public void write(boolean value, String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("write boolean (" + key + ":" + value + ")");
	}

	@Override
	public void write(float value, String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("write float (" + key + ":" + value + ")");
	}

	@Override
	public void write(double value, String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("write double (" + key + ":" + value + ")");
	}

	@Override
	public void write(long value, String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("write long (" + key + ":" + value + ")");
	}

	@Override
	public void write(String value, String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("write String (" + key + ":" + value + ")");
	}
	
}
