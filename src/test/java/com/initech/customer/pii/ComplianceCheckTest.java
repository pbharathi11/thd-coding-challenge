package com.initech.customer.pii;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class ComplianceCheckTest {

	@Test
	public void test1() throws Exception {
//		Input: "bharathipalanivel@gmail.com"
//		Output: "p*****l@gmail.com"
//		Explanation: All names are converted to lowercase, and the letters between the
//		             first and last letter of the first name is replaced by 5 asterisks.
//		             Therefore, "bharathipalanivel" -> "b*****l".
		String S = "bharathipalanivel@gmail.com";
		String result = new ComplianceCheck().maskPII(S);
		Assert.assertEquals("b*****l@gmail.com", result);
	}

	@Test
	public void test2() throws Exception{
//		Input: "BP@gmail.com"
//		Output: "b*****p@gmail.com"
//		Explanation: There must be 5 asterisks between the first and last letter
//		             of the first name "BP". Therefore, "bp" -> "b*****p".
		String S = "BP@gmail.com";
		String result = new ComplianceCheck().maskPII(S);

		Assert.assertEquals("b*****p@gmail.com", result);
	}

	@Test
	public void test3() throws Exception{
//		Input: "1(470)258-0816"
//		Output: "470-***-0816"
//		Explanation: 10 digits in the phone number, which means all digits make up the local number.
		String S = "(470)258-0816";
		String result = new ComplianceCheck().maskPII(S);

		Assert.assertEquals("470-***-0816", result);
	}

	@Test
	public void test4() throws Exception{
//		Input: "+1(470)258-0816"
//		Output: "+1470-***-0816"
//		Explanation: 12 digits, 2 digits for country code and 10 digits for local number.
		String S = "+1(470)258-0816";
		String result = new ComplianceCheck().maskPII(S);

		Assert.assertEquals("+1470-***-0816", result);
	}

}
