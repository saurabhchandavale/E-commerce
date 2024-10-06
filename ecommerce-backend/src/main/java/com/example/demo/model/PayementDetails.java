package com.example.demo.model;

import com.example.demo.domin.PaymentStatus;

public class PayementDetails {
	
	private String paymentMethod;
	
	private PaymentStatus status;
	
	private String paymentId;
	
	private String razorpayPaymentLinkId;
	
	private String razorpayPaymentLinkRefrenceId;
	
	private String razorpayPaymentLinkStatus;
	
	private String razorpayPaymentId;

	public PayementDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PayementDetails(String paymentMethod, PaymentStatus status, String paymentId, String razorpayPaymentLinkId,
			String razorpayPaymentLinkRefrenceId, String razorpayPaymentLinkStatus, String razorpayPaymentId) {
		super();
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.paymentId = paymentId;
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
		this.razorpayPaymentLinkRefrenceId = razorpayPaymentLinkRefrenceId;
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
		this.razorpayPaymentId = razorpayPaymentId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus completed) {
		this.status = completed;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getRazorpayPaymentLinkId() {
		return razorpayPaymentLinkId;
	}

	public void setRazorpayPaymentLinkId(String razorpayPaymentLinkId) {
		this.razorpayPaymentLinkId = razorpayPaymentLinkId;
	}

	public String getRazorpayPaymentLinkRefrenceId() {
		return razorpayPaymentLinkRefrenceId;
	}

	public void setRazorpayPaymentLinkRefrenceId(String razorpayPaymentLinkRefrenceId) {
		this.razorpayPaymentLinkRefrenceId = razorpayPaymentLinkRefrenceId;
	}

	public String getRazorpayPaymentLinkStatus() {
		return razorpayPaymentLinkStatus;
	}

	public void setRazorpayPaymentLinkStatus(String razorpayPaymentLinkStatus) {
		this.razorpayPaymentLinkStatus = razorpayPaymentLinkStatus;
	}

	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}

	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
}
