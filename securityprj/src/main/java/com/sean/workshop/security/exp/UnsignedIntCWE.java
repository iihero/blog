package com.sean.workshop.security.exp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class UnsignedIntCWE {

	public void checkWrong() throws IOException {
		// 想表达一个无符号整数
		int a = 2 << 31 + 1;
		System.out.println(a);
		System.out.println((long) a);
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		os.write(a);
		os.flush();
		byte[] buf = os.toByteArray();
		os.close();

		ByteArrayInputStream is = new ByteArrayInputStream(buf);
		DataInputStream dis = new DataInputStream(is);
		int b = dis.readInt();
		long b2 = dis.readInt() & 0xFFFFFFFFL;
		System.out.println(b);
		System.out.println(b2);
		dis.close();
	}

	public static void main(String[] args) throws IOException {
		UnsignedIntCWE foo = new UnsignedIntCWE();
		foo.checkWrong();
	}

}
