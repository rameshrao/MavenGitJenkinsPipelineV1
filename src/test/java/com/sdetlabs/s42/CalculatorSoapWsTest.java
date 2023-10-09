package com.sdetlabs.s42;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Test;

public class CalculatorSoapWsTest {

	String expResponse = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><AddResponse xmlns=\"http://tempuri.org/\"><AddResult>46</AddResult></AddResponse></soap:Body></soap:Envelope>";

	@Test
  public void getCalculatorAddOperationTest() throws MalformedURLException, IOException {
	  assertEquals(CalculatorSoapWs.getCalculatorAddOperation(23, 23), expResponse);
  }
}
