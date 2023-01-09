package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.EntryDto;
import com.masai.DTO.ResponseDto;
import com.masai.model.Entry;
import com.masai.service.EntryService;

@RestController
public class EntryController {

	@Autowired
	EntryService service;
	
//	-----------------------------------------------------------------save entries--------------------------------------------------------------------------------------------------
	
	@PostMapping("/entries")
	public ResponseEntity<Entry> saveEntryHanlder(@RequestBody Entry entry)
	{
		return new ResponseEntity<Entry>(service.saveEntry(entry),HttpStatus.CREATED);
	}
	
//	-----------------------------------------------------------------get all entries by cateogry--------------------------------------------------------------------------------------------------
	
	@GetMapping("/entries/{category}")
	public ResponseEntity<List<EntryDto>> getAllByCategoryHandler(@PathVariable String  category)
	{
		return new ResponseEntity<List<EntryDto>>(service.getAllByCategory(category),HttpStatus.OK);
	}
	
//	-----------------------------------------------------------------get all entries--------------------------------------------------------------------------------------------------
	
	@GetMapping("/entries")
	public ResponseEntity<ResponseDto> getAllEntriesHanlder(@PathVariable String  category)
	{
		
		return new ResponseEntity<ResponseDto>(service.getAll(),HttpStatus.OK);
	}
	
}
