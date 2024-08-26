package com.shop.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity 
@NoArgsConstructor @AllArgsConstructor
@Setter @Getter @ ToString
@EqualsAndHashCode
@Table(name="Order_Tbl")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long oId;

	private LocalDate date;

	@ManyToOne
	@JoinColumn(name="cId")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name="pId")
	private Product product;
}