package com.example.demo.service;
import com.example.demo.model.Character;
import com.example.demo.repository.CharacterRepository;

public class CharacterUploadService {

	private CharacterRepository characterRepository;
	public Character createCharacter(Character character) {
		return characterRepository.save(character);
	}
}
