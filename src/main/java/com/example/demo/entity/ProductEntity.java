package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="Product")

public class ProductEntity {
	
	@Id
	@GeneratedValue
	private long id;
	private String productName;
	private String madeIn;
	private String brand;
	private double price;
	private int quantity;
	private double discountrate;
	private double taxprice;;
	private double discountprice;
	private  double finalprice;
	private double offerprice;
	private double stockvalue;

}
