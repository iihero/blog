package com.sean.workshop.security.exp;

/**
 * 不要在一个表达式中对同一个变量进行多次赋值，很容易引起问题
 *
 */
public class NumberExpCWE {

	public void checkWrong() {
		int number = 17;
		int[] threshold = new int[20];
		threshold[0] = 10;
		number = (number > threshold[0] ? 0 : -2) + ((31 * ++number) * (number = get()));

		if (number == 0) {
			System.out.println("Access granted");
		} else {
			System.out.println("Access denied");
		}
	}

	private int get() {
		int number = 0;
		return number;
	}

	public void checkFix() {
		int number = 17;
		int[] threshold = new int[20];
		threshold[0] = 10;
		final int authnumber = get();
		number = (31 * (number + 1)) * authnumber + (number > threshold[0] ? 0 : -2);

		if (number == 0) {
			System.out.println("Access granted");
		} else {
			System.out.println("Access denied");
		}
	}

	public static void main(String[] args) {
		NumberExpCWE foo = new NumberExpCWE();
		foo.checkWrong();
		foo.checkFix();

	}

}
