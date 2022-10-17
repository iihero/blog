/**
 * (c) 2022 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * No part of this publication may be reproduced or transmitted in any form or for any purpose
 * without the express permission of SAP SE. The information contained herein may be changed
 * without prior notice.
 */

package com.sean.workshop.security.mutable;

public class Parent {
	private int a;

	public Parent(int a) {
		setA(a);
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public static void main(String args[]) {
		Child child = new Child(5);
		System.out.println(child.getA());
	}
}

class Child extends Parent {

	public Child(int b) {
		super(b);
	}

	@Override
	public void setA(int b) {
		super.setA(b - 1);
	}
}
