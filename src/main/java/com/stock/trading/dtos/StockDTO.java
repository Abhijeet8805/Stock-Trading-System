package com.stock.trading.dtos;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.stock.trading.models.Stock;

public class StockDTO extends Stock {

	private MultipartFile photo;

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	
	public static Stock toEntity(StockDTO dto) {
		Stock entity=new Stock();
		BeanUtils.copyProperties(dto, entity);		
		return entity;
	}
}
