package com.sean.workshop.security.exp;

import java.util.ArrayList;

public class IntegerCWE {

	public void check() {
		Integer i1 = 100; // memory constant
		Integer i2 = 100;
		Integer i3 = 1000;
		// pay attention, JVM in some platform can cache till to 16384.
		// But the minimum range is [-128,127]
		Integer i4 = 1000;

		System.out.println(i1 == i2);
		System.out.println(i1 != i2);
		System.out.println(i3 == i4);
		System.out.println(i3 != i4);

	}

	public void fix() {
		Integer i1 = 100;
		Integer i2 = 100;
		Integer i3 = 1000;
		Integer i4 = 1000;

		System.out.println(i1.equals(i2));
		System.out.println(!i1.equals(i2));
		System.out.println(i3.equals(i4));
		System.out.println(!i3.equals(i4));
	}

	public void checkArrayWrong() {
		ArrayList<Integer> list1 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list1.add(1000 + i);
		}
		ArrayList<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list2.add(1000 + i);
		}

		// Are they equilative?
		int counter = 0;
		for (int i = 0; i < 10; i++) {
			if (list1.get(i) == list2.get(i)) {
				counter++;
			}
		}
		System.out.println("Equals count: " + counter);
	}

	public void checkArrayFix() {
		ArrayList<Integer> list1 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list1.add(1000 + i);
		}
		ArrayList<Integer> list2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			list2.add(1000 + i);
		}

		// Are they equilative?
		int counter = 0;
		for (int i = 0; i < 10; i++) {
			if (list1.get(i).equals(list2.get(i))) {
				counter++;
			}
		}
		System.out.println("Fix equals count: " + counter);
	}

	public static void main(String[] args) {

		IntegerCWE foo = new IntegerCWE();
		foo.check();
		foo.fix();

		foo.checkArrayWrong();
		foo.checkArrayFix();

	}

}
