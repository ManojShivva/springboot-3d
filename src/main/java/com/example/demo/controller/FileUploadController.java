package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.example.demo.payload.Response;
import com.example.demo.service.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;

import com.example.demo.model.Character;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime; 
//import com.example.demo.service.CharacterUploadService;
import com.example.demo.repository.CharacterRepository;



@RestController
@CrossOrigin(origins = "http://localhost:4200/")
public class FileUploadController {
	
	 @Autowired
	 private FileStorageService fileStorageService;
	 private Character character;
	 private int id = 4;
	 
	 @Autowired
	 private CharacterRepository characterRepository;
	 
	 @PostMapping("/uploadFile")
	    public Response uploadFile(@RequestParam("file") MultipartFile file) {
	        String fileName = fileStorageService.storeFile(file);

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	            .path("/downloadFile/")
	            .path(fileName)
	            .toUriString();
	        
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); 
	        LocalDateTime now = LocalDateTime.now();  
	        System.out.println(dtf.format(now));  
	        
	        //name of the character
	        String fileNameWithoutExt = fileName.substring(0, fileName.lastIndexOf('.'));;
	        
	        character = new Character(id++, fileNameWithoutExt, dtf.format(now) ,file.getSize(), 100, fileDownloadUri);
//	        this.character.setDate(dtf.format(now));
//	        this.character.setId(id);
//	        this.character.setName(fileNameWithoutExt);
//	        this.character.setPath(fileDownloadUri);
//	        this.character.setSize(file.getSize());
//	        this.character.setCost(100);
	        System.out.println(characterRepository.save(character));
	        
	        return new Response(fileName, fileDownloadUri,
	            file.getContentType(), file.getSize());
	    }
	 
	 @CrossOrigin(origins = "http://localhost:4200/")
	 @GetMapping("/characterFind/{characterName:.+}")
		public List <Character> getCharacter(@PathVariable String characterName, HttpServletRequest request){
			return characterRepository.findByName(characterName);
		}
	 
	 
	 @PostMapping("/uploadMultipleFiles")
	    public List < Response > uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
	        return Arrays.asList(files)
	            .stream()
	            .map(file-> uploadFile(file))
	            .collect(Collectors.toList());
	    }

}
