package com.masai.service;

import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.masai.DTO.EntryDto;
import com.masai.DTO.ResponseDto;
import com.masai.controller.EntryController;
import com.masai.model.Entry;
import com.masai.repository.EntryRepository;

@Service
public class EntryServiceImpl implements EntryService{

	@Autowired
	EntryRepository repository;
	
	@Autowired
	RestTemplate restTemplate;
	
//-----------------------------------------------------------------------------saving entry into the database--------------------------------------------------------------------
	
	@Override
	public Entry saveEntry(Entry entry) {

		return repository.save(entry);
		
}

//	---------------------------------------------------------------------------getting all by category-------------------------------------------------------------------------------
	
	@Override
	public List<EntryDto> getAllByCategory(String category) {

		System.out.println(category);
		
		return restTemplate.getForObject("https://api.publicapis.org/entries", ResponseDto.class)
				.getEntries().stream()
				.filter(entry -> entry.getCategory().equals(category))
				.map(entry -> new EntryDto(entry.getApi(), entry.getCategory()))
				.toList();

	}
	
//	-----------------------------------------------------------------get all entries--------------------------------------------------------------------------------------------------

	@Override
	public ResponseDto getAll() {
		return restTemplate.getForObject("https://api.publicapis.org/entries", ResponseDto.class);
	}

}
