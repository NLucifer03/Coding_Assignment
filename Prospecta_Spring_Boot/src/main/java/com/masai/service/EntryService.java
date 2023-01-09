package com.masai.service;

import java.util.List;

import com.masai.DTO.EntryDto;
import com.masai.DTO.ResponseDto;
import com.masai.controller.EntryController;
import com.masai.model.Entry;

public interface EntryService {

	Entry saveEntry(Entry entry);

	List<EntryDto> getAllByCategory(String category);

	ResponseDto getAll();



	
	
}
