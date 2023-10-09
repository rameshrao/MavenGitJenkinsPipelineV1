package com.sdetlabs.s42;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* 
 * Test WSDL location: for Calculator WSDL
 * 
 *"https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx";
 */

public class CalculatorSoapWs {

	static String wsURL = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx";
	static URL url = null;
	static URLConnection connection = null;
	static HttpURLConnection httpConn = null;

	static String expResponseBody = "";
	static long responseTime = 0;

	public static void setHttpConn() throws MalformedURLException, IOException {
//		String wsURL = "https://ecs.syr.edu/faculty/fawcett/Handouts/cse775/code/calcWebService/Calc.asmx";
		url = new URL(wsURL);

		connection = url.openConnection();
		httpConn = (HttpURLConnection) connection;
	}

	public static String getCalculatorAddOperation(int valA, int valB) throws MalformedURLException, IOException {

		// Code to make a web service HTTP request
		String responseString = "";
		String outputString = "";

		setHttpConn();

		ByteArrayOutputStream bout = new ByteArrayOutputStream();

		String xmlInput = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" //
				+ "  <soap:Body>\n" //
				+ "    <Add xmlns=\"http://tempuri.org/\">\n" //
				+ "      <a>int</a>\n" //
				+ "      <b>int</b>\n" //
				+ "    </Add>\n" //
				+ "  </soap:Body>\n" //
				+ "</soap:Envelope>";

		String strToFind = ">int</";
		int strCount = xmlInput.split(strToFind, -1).length - 1;

		for (int iCnt = 0; iCnt < strCount; iCnt++) {
			// To Do
		}

		String xmlWithData = xmlInput.replaceAll(strToFind, ">23</");

		byte[] buffer = new byte[xmlWithData.length()];
		buffer = xmlWithData.getBytes();

		bout.write(buffer);
		byte[] b = bout.toByteArray();

		String SOAPAction = "http://tempuri.org/Add";

		// Set the appropriate HTTP parameters.
		httpConn.setRequestProperty("Content-Length", "length");
		httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
		httpConn.setRequestProperty("SOAPAction", SOAPAction);
		httpConn.setRequestMethod("POST");

		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);

		OutputStream out = httpConn.getOutputStream();

		// Write the content of the request to the output stream of the HTTP Connection.
		out.write(b);
		out.close();

		// Ready with sending the request.

		// Read the response.
		InputStreamReader isr = new InputStreamReader(httpConn.getInputStream());
		BufferedReader in = new BufferedReader(isr);

		// Write the SOAP message response to a String.
		while ((responseString = in.readLine()) != null) {
			outputString = outputString + responseString;
		}

		in.close();

		return outputString;
	}


	public static void main(String[] args) throws MalformedURLException, IOException {
		System.out.println("response: " + getCalculatorAddOperation(23, 23));
	}

}
