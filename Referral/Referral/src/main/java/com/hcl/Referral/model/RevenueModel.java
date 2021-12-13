package com.hcl.Referral.model;

public class RevenueModel {

	private int userId;

	public RevenueModel(int userId) {
		super();
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "RevenueModel [userId=" + userId + "]";
	}
}