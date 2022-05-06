package com.cubicfox.mapper;

import org.springframework.stereotype.Component;

import com.cubicfox.model.domain.Address;
import com.cubicfox.model.domain.Company;
import com.cubicfox.model.domain.Geo;
import com.cubicfox.model.domain.User;
import com.cubicfox.model.dto.UserDto;

@Component
public class UserMapper {

	public UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());
		dto.setPhone(user.getPhone());
		dto.setWebsite(user.getWebsite());
		dto.setStreet(user.getAddress() != null ? user.getAddress().getStreet() : null);
		dto.setSuite(user.getAddress() != null ? user.getAddress().getSuite() : null);
		dto.setCity(user.getAddress() != null ? user.getAddress().getCity() : null);
		dto.setZipcode(user.getAddress() != null ? user.getAddress().getZipcode() : null);
		dto.setLat(user.getAddress() != null && user.getAddress().getGeo() != null ? user.getAddress().getGeo().getLat() : null);
		dto.setLng(user.getAddress() != null && user.getAddress().getGeo() != null ? user.getAddress().getGeo().getLng() : null);
		dto.setCompanyName(user.getCompany() != null ? user.getCompany().getName() : null);
		dto.setCatchPhrase(user.getCompany() != null ? user.getCompany().getCatchPhrase() : null);
		dto.setBs(user.getCompany() != null ? user.getCompany().getBs() : null);
		return dto;
	}

	public User toEntity(UserDto dto) {
		Geo geo = new Geo(dto.getLat(), dto.getLng());
		Address address = new Address(dto.getStreet(), dto.getSuite(), dto.getCity(), dto.getZipcode(), geo);
		Company company = new Company(dto.getCompanyName(), dto.getCatchPhrase(), dto.getBs());
		return new User(dto.getId(), dto.getName(), dto.getUsername(), dto.getEmail(), dto.getPhone(), dto.getWebsite(), address, company);
	}
}
