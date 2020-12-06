package com.csd.training;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/*import com.tutorial.sample.authenticate.AuthenticationService;
import com.tutorial.sample.authenticate.UserInfo;
*/


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class AuthorizationTestClass {
	
	
	AuthorizationService  authorizer;
	UserInfo userInfoObj;
	DAO daoObj;
	UserInfo storeduserinfo;
	

	private void createMockito(){
		daoObj = mock(DAO.class);
		when(daoObj.getUser_byName()).thenReturn((UserInfo) storeduserinfo);

	}
	
	private void create_authorised(){
		storeduserinfo =new UserInfo();
		storeduserinfo.setName("CSD");
		storeduserinfo.setDateOfBirth("12/05/2020");
		storeduserinfo.setCountry("India");
		
	}
	
	@Before
	public void beforeStartSetUp (){
		create_authorised();
		createMockito();
		authorizer = new AuthorizationService(daoObj);
	}
	
	@Test
	public void authorized_user(){
		Boolean expected = true;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName("CSD");
		userInfoObj.setDateOfBirth("12/05/2020");
		userInfoObj.setCountry("India");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		Mockito.verify(daoObj).getUser_byName();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void unauthorized_userName(){
		Boolean expected = false;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName("ABC");
		userInfoObj.setDateOfBirth("12/05/2020");
		userInfoObj.setCountry("India");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		Mockito.verify(daoObj).getUser_byName();
		
		assertEquals(expected, actual);
		
	}	
	
	@Test
	public void unauthorized_dob(){
		Boolean expected = false;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName("CSD");
		userInfoObj.setDateOfBirth("12/10/2020");
		userInfoObj.setCountry("India");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		Mockito.verify(daoObj).getUser_byName();
		
		assertEquals(expected, actual);
		
	}	
	
	@Test
	public void unauthorized_country(){
		Boolean expected = false;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName("CSD");
		userInfoObj.setDateOfBirth("12/05/2020");
		userInfoObj.setCountry("india");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		Mockito.verify(daoObj).getUser_byName();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void blank_name(){
		Boolean expected = false;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName("");
		userInfoObj.setDateOfBirth("12/05/2020");
		userInfoObj.setCountry("India");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		Mockito.verify(daoObj).getUser_byName();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void blank_dob(){
		Boolean expected = false;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName("CSD");
		userInfoObj.setDateOfBirth("");
		userInfoObj.setCountry("India");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		Mockito.verify(daoObj).getUser_byName();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void blank_country(){
		Boolean expected = false;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName("CSD");
		userInfoObj.setDateOfBirth("12/05/2020");
		userInfoObj.setCountry("");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		Mockito.verify(daoObj).getUser_byName();
		
		assertEquals(expected, actual);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void null_name(){
		Boolean expected = false;
		
		userInfoObj = new UserInfo();
		userInfoObj.setName(null);
		userInfoObj.setDateOfBirth("12/05/2020");
		userInfoObj.setCountry("India");
		
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(userInfoObj);
		//Mockito.verify(daoObj).getUser_byName();
		
		//assertEquals(expected, actual);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void null_setUser(){
		//Boolean expected = false;
		
		/*userInfoObj = new UserInfo();
		userInfoObj.setName("CSD");
		userInfoObj.setDateOfBirth(null);
		userInfoObj.setCountry("India");
		*/
		//create_authorised();
		//createMockito();
		
		Boolean actual = authorizer.authorizeUser(null);
		//Mockito.verify(daoObj).getUser_byName();
		
		//assertEquals(expected, actual);
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void dao_is_null(){
		//Boolean expected = true;
		AuthorizationService auth = new AuthorizationService(null);
		userInfoObj = new UserInfo();
		userInfoObj.setName("CSD");
		userInfoObj.setDateOfBirth("12/05/2020");
		userInfoObj.setCountry("India");
		
		Boolean actual = auth.authorizeUser(userInfoObj);
		
		//assertEquals(expected, actual);
		
		
	}
	
	@After
	public void tearDown(){
		authorizer =null;
	}

}
