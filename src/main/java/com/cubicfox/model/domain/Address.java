package com.cubicfox.model.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Address {

	private String street;
	private String suite;
	private String city;
	private String zipcode;

	@Embedded
	private Geo geo;

	public Address() {
	}
}
