package com.sean.workshop.security.exp;

import java.util.ArrayList;
import java.util.List;

/**
 * 断言中不要使用有副作用的表达式
 *
 */
public class AssertCWE {
	public void checkWrong() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		assert list.remove("a");
		System.out.println(list.size());
	}

	public void checkFix() {
		List<String> list = new ArrayList<>();
		list.add("a");
		list.add("b");
		boolean res = list.remove("a");
		assert res;
		System.out.println(list.size());
	}

	public static void main(String[] args) {
		AssertCWE foo = new AssertCWE();
		// 对比 assert ON和off时的不同结果
		foo.checkWrong();
		foo.checkFix();
	}

}
