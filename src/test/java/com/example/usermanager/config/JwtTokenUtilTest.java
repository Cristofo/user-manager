package com.example.usermanager.config;

import java.util.Date;
import java.util.Map;

import javax.annotation.processing.Generated;

import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.function.Function;

import io.jsonwebtoken.Claims;

@Generated(value = "org.junit-tools-1.1.0")
public class JwtTokenUtilTest {

	@Before
	public void setUp() throws Exception {

	}

	private JwtTokenUtil createTestSubject() {
		return new JwtTokenUtil();
	}

	@Test
	public void testGetUsernameFromToken() throws Exception {
		JwtTokenUtil testSubject;
		String token = "";
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getUsernameFromToken(token);
	}

	@Test
	public void testGetExpirationDateFromToken() throws Exception {
		JwtTokenUtil testSubject;
		String token = "";
		Date result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getExpirationDateFromToken(token);
	}

	@Test
	public <T> void testGetClaimFromToken() throws Exception {
		JwtTokenUtil testSubject;
		String token = "";
		Function<Claims, T> claimsResolver = null;
		T result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.getClaimFromToken(token, claimsResolver);
	}

	@Test
	public void testGetAllClaimsFromToken() throws Exception {
		JwtTokenUtil testSubject;
		String token = "";
		Claims result;

		// default test
		testSubject = createTestSubject();
		result = Whitebox.invokeMethod(testSubject, "getAllClaimsFromToken", new Object[] { token });
	}

	@Test
	public void testIsTokenExpired() throws Exception {
		JwtTokenUtil testSubject;
		String token = "";
		Boolean result;

		// default test
		testSubject = createTestSubject();
		result = Whitebox.invokeMethod(testSubject, "isTokenExpired", new Object[] { token });
	}

	@Test
	public void testGenerateToken() throws Exception {
		JwtTokenUtil testSubject;
		UserDetails userDetails = null;
		String result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.generateToken(userDetails);
	}

	@Test
	public void testDoGenerateToken() throws Exception {
	JwtTokenUtil testSubject;Map<String,Object> claims = null;
	String subject = "";
	String result;
	
	// default test
//	testSubject=createTestSubject();result=Whitebox.invokeMethod(testSubject,"doGenerateToken", new Object[]{Map<String,Object>.class, subject});
	}

	@Test
	public void testValidateToken() throws Exception {
		JwtTokenUtil testSubject;
		String token = "";
		UserDetails userDetails = null;
		Boolean result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.validateToken(token, userDetails);
	}
}