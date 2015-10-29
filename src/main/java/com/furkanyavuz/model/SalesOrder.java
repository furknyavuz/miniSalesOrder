package com.furkanyavuz.model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sales_order")
public class SalesOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_number")
	private String orderNumber;

	@Column(name = "customer")
	private String customer;

	@Column(name = "total_price")
	private String totalPrice;

	@Transient
	private ArrayList<OrderLine> orderLines;

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	@Override
	public String toString() {
		return "SalesOrder [orderNumber=" + orderNumber + ", customer=" + customer + ", totalPrice=" + totalPrice
				+ ", orderLines=" + orderLines + "]";
	}

}
