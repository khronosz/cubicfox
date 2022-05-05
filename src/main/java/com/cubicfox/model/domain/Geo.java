package com.cubicfox.model.domain;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Geo {

	private String lat;
	private String lng;

	public Geo() {
	}
}
