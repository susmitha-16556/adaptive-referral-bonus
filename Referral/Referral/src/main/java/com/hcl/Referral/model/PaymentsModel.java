package com.hcl.Referral.model;

public class PaymentsModel {
	private int userId;
	private String paymentType;
	private double paymentAmount;

	public PaymentsModel(int userId, String paymentType, double paymentAmount) {
		super();
		this.userId = userId;
		this.paymentType = paymentType;
		this.paymentAmount = paymentAmount;
	}

	@Override
	public String toString() {
		return "PaymentsModel [userId=" + userId + ", paymentType=" + paymentType + ", paymentAmount=" + paymentAmount
				+ "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
}