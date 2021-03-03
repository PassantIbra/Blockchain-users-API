package com.blockchain.practicum.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
/**
 *Transaction class holds transaction details 
 *
 */
public class Transaction {

	@JsonProperty(access = Access.READ_ONLY)
	private String senderEmail;
	@NotBlank(message = "a receiver email is mandatory")
	@Email(message = "a valid receiver email is mandatory")
	private String receiverEmail;
	private Date date;
	@NotNull(message = "amount to be sent is madatory")
	@Positive(message = "amount can not be negative or 0")
	float amount;

	public Transaction() {

		// TODO Auto-generated constructor stub
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
