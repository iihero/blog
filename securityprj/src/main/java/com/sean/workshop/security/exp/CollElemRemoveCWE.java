package com.sean.workshop.security.exp;

import java.util.HashSet;
import java.util.Set;

public class CollElemRemoveCWE {

	// very easy to be wrong
	public void checkWrong() {
		Set<Short> a = new HashSet<>();
		for (short i = 0; i < 100; i++) {
			a.add(i);
			a.remove(i - 1);
		}

		System.out.println(a.size());
	}

	public void checkFix() {
		Set<Short> a = new HashSet<>();
		for (short i = 0; i < 100; i++) {
			a.add(i);
			a.remove((short) (i - 1));
		}

		System.out.println(a.size());
	}

	public static void main(String[] args) {
		CollElemRemoveCWE foo = new CollElemRemoveCWE();
		foo.checkWrong();
		foo.checkFix();
	}

}
