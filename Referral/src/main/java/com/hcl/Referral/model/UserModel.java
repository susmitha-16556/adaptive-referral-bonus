package com.hcl.Referral.model;

public class UserModel {

	private int userId;
	private String userName;

	public UserModel(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "ReferralModel [userId=" + userId + ", userName=" + userName + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}