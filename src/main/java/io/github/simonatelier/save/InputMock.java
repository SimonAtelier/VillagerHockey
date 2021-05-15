package io.github.simonatelier.save;

import java.io.IOException;

public class InputMock implements Input {

	@Override
	public int readInt(String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("read int " + key);
		return 0;
	}

	@Override
	public boolean readBoolean(String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("read boolean " + key);
		return false;
	}

	@Override
	public float readFloat(String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("read float" + key);
		return 0;
	}

	@Override
	public double readDouble(String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("read double " + key);
		return 0;
	}

	@Override
	public long readLong(String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("read long " + key);
		return 0;
	}

	@Override
	public String readString(String key) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("read String " + key);
		return null;
	}

}
