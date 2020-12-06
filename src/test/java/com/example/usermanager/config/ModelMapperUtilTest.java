package com.example.usermanager.config;

import javax.annotation.processing.Generated;

import org.junit.Test;
import org.modelmapper.ModelMapper;

@Generated(value = "org.junit-tools-1.1.0")
public class ModelMapperUtilTest {

	private ModelMapperUtil createTestSubject() {
		return new ModelMapperUtil();
	}

	@Test
	public void testModelMapper() throws Exception {
		ModelMapperUtil testSubject;
		ModelMapper result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.modelMapper();
	}
}