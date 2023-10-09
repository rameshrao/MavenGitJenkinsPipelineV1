package com.sdetlabs.s42;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Add {
	static Number valA;
	static Number[] valB;

	public static <N extends Number> Number add(Number... valA) {

		String type = checkType(valA);

		if (type == "Double") {
			double sum = 0;
			for (var num : valA) {
				sum += num.doubleValue();
			}
			return Double.parseDouble(String.valueOf(sum));
		}
		if (type == "Float") {
			float sum = 0;
			for (Number num : valA) {
				sum += num.floatValue();
			}
			return Float.parseFloat(String.valueOf(sum));
		}
		if (type == "Long") {
			long sum = 0L;
			BigInteger bg = null;
			BigInteger sum_bg = null;
			for (Number num : valA) {
				bg = BigInteger.valueOf(num.longValue());
				sum_bg = BigInteger.valueOf(sum);
				if (checkAdditionOverflow(sum, num.longValue())) {
					sum_bg = sum_bg.add(bg);
				} else {
					sum += num.longValue();
				}
			}
			if (sum_bg.toString().compareTo(String.valueOf(sum)) > 0) {
				return sum_bg;
			}
			return Long.parseLong(String.valueOf(sum));
		}
		if (type == "Integer") {
			int sum = 0;
			BigInteger bg = null;
			BigInteger sum_bg = null;
			for (Number num : valA) {
				bg = BigInteger.valueOf(num.intValue());
				sum_bg = BigInteger.valueOf(sum);
				if (checkAdditionOverflow(sum, num.intValue())) {
					sum_bg = sum_bg.add(bg);
				} else {
					sum += num.intValue();
				}
			}
			if (sum_bg.toString().compareTo(String.valueOf(sum)) > 0) {
				return sum_bg;
			}
			return Integer.parseInt(String.valueOf(sum));
		}
		return Integer.parseInt(String.valueOf(0));
	}

	public static final String checkType(Number... iterator) {
		String type = null;
		List<String> typeList = new ArrayList<String>(Arrays.asList(type));
		for (int iCnt = 0; iCnt < iterator.length; iCnt++) {
			if (iterator[iCnt] instanceof Double) {
				typeList.add("Double");
				continue;
			}
			if (iterator[iCnt] instanceof Float) {
				typeList.add("Float");
				continue;
			}
			if (iterator[iCnt] instanceof Long) {
				typeList.add("Long");
				continue;
			}
			if (iterator[iCnt] instanceof Integer) {
				typeList.add("Integer");
				continue;
			}
		}

		if (typeList.contains("Double")) {
			type = "Double";
		} else if (typeList.contains("Float")) {
			type = "Float";
		} else if (typeList.contains("Long")) {
			type = "Long";
		} else if (typeList.contains("Integer")) {
			type = "Integer";
		} else {
			type = "NaN";
		}
		return type;
	}

	public static boolean checkAdditionOverflow(long left, long right) {
		try {
			Math.addExact(left, right);
			return false;
		} catch (ArithmeticException e) {
			return true;
		}
	}

	public static boolean checkAdditionOverflow(int left, int right) {
		try {
			Math.addExact(left, right);
			return false;
		} catch (ArithmeticException e) {
			return true;
		}
	}

	public static void main(String[] args) {
		System.out.println(add(Double.MAX_VALUE, 2) + ", " + checkType(Double.MAX_VALUE, 2));
		System.out.println(add(Float.MAX_VALUE, 2.1f) + ", " + checkType(Float.MAX_VALUE, 2.1f));
		System.out.println(add(Long.MAX_VALUE, 2) + ", " + checkType(Long.MAX_VALUE, 2));
		System.out.println(add(Integer.MAX_VALUE, 2) + ", " + checkType(Integer.MAX_VALUE, 2));
		/*
		 * 9223372036854775809, Long <= 9223372036854775807 + 2 2147483679, Integer <=
		 * 2147483647 + 2
		 */
	}
}
