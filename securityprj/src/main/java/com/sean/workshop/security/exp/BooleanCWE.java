package com.sean.workshop.security.exp;

public class BooleanCWE {
	public void checkWong() {
		Boolean b1 = new Boolean("true");
		Boolean b2 = new Boolean("true");
		if (b1 == b2) {
			System.out.println("Never printed....");
		}
	}

	public void checkFix() {
		Boolean b1 = new Boolean("true");
		Boolean b2 = new Boolean("true");

		if (b1.equals(b2)) {
			System.out.println("1.Always printed....");
		}

		b1 = true;
		b2 = true;
		if (b1.equals(b2)) {
			System.out.println("2.Always printed....");
		}

		b1 = Boolean.TRUE;
		b2 = Boolean.TRUE;
		if (b1.equals(b2)) {
			System.out.println("3.Always printed....");
		}
	}

	public static void main(String[] args) {
		BooleanCWE foo = new BooleanCWE();
		foo.checkWong();
		foo.checkFix();
	}

}
