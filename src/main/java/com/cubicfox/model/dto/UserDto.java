package com.cubicfox.model.dto;

import com.cubicfox.model.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private Long id;
	private String name;
	private String username;
	private String email;
	private String phone;
	private String website;
	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private String lat;
	private String lng;
	private String companyName;
	private String catchPhrase;
	private String bs;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.website = user.getWebsite();
		this.street = user.getAddress().getStreet();
		this.suite = user.getAddress().getSuite();
		this.city = user.getAddress().getCity();
		this.zipcode = user.getAddress().getZipcode();
		this.lat = user.getAddress().getGeo().getLat();
		this.lng = user.getAddress().getGeo().getLng();
		this.companyName = user.getCompany().getName();
		this.catchPhrase = user.getCompany().getCatchPhrase();
		this.bs = user.getCompany().getBs();
	}
}
