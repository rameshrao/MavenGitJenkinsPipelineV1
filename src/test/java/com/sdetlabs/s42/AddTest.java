package com.sdetlabs.s42;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class AddTest {

	@Test
	public void addTestDouble() {
		assertEquals(String.valueOf(Add.add(Double.MAX_VALUE, 2)), "1.7976931348623157E308");
	}

	@Test
	public void addTestFloat() {
		assertEquals(String.valueOf(Add.add(Float.MAX_VALUE, 2)), "3.4028235E38");
	}

	@Test
	public void addTestLong() {
		assertEquals(String.valueOf(Add.add(Long.MAX_VALUE, 2)), "9223372036854775809");
	}

	@Test
	public void addTestInteger() {
		assertEquals(String.valueOf(Add.add(Integer.MAX_VALUE, 2)), "2147483649");
	}
}
