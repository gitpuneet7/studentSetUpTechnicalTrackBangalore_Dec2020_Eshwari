package com.csd.training;

public class AuthorizationService {
	
	private DAO daoObj;

	public AuthorizationService(DAO daoObj) {
		this.daoObj = daoObj;
	}

	public Boolean authorizeUser(UserInfo userInfoObj) {
		
		if(daoObj == null){
			throw new IllegalArgumentException("Dao Object cannot be null");
		}
		if(userInfoObj == null){
			throw new IllegalArgumentException("User info Object cannot be null");
		}
		//handle null cases
		if(userInfoObj.getName() == null || userInfoObj.getCountry() == null || userInfoObj.getDateOfBirth() == null){
			throw new IllegalArgumentException("User info Parameters cannot be null");
		}
		
		UserInfo daoUserInfo = daoObj.getUser_byName();
		if(daoUserInfo.getName().equals(userInfoObj.getName()) && daoUserInfo.getDateOfBirth().equals(userInfoObj.getDateOfBirth()) && daoUserInfo.getCountry().equals(userInfoObj.getCountry()) ){
			return true;
		}
		return false;
		
				
	}


}
