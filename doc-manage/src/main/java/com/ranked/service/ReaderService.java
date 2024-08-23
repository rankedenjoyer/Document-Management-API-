package com.ranked.service;

import com.ranked.converter.ReaderConverter;
import com.ranked.dto.ReaderDTO;
import com.ranked.entity.Person;
import com.ranked.entity.Reader;
import com.ranked.repository.PersonRepository;
import com.ranked.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private ReaderConverter readerConverter;

    public ReaderDTO createReader(ReaderDTO readerDTO) {
        String personId = readerDTO.getPersonId();
        Optional<Person> personOptional = personRepository.findById(personId);
        if (personOptional.isPresent()) {
            Reader reader = readerConverter.toEntity(readerDTO);
            reader.setPerson(personOptional.get());
            Reader savedReader = readerRepository.save(reader);
            return readerConverter.toDTO(savedReader);
        } else {
            throw new RuntimeException("Person not found with id " + personId);
        }
    }

    public ReaderDTO updateReader(String id, ReaderDTO readerDTO) {
        Optional<Reader> readerOptional = readerRepository.findById(id);
        if (readerOptional.isPresent()) {
            Reader reader = readerOptional.get();
            reader.setKindOfReader(readerDTO.getKindOfReader());
            reader.setEmail(readerDTO.getEmail());
            reader.setPhoneNumber(readerDTO.getPhoneNumber());
            Reader updatedReader = readerRepository.save(reader);
            return readerConverter.toDTO(updatedReader);
        } else {
            throw new RuntimeException("Reader not found with id " + id);
        }
    }

    public void deleteReader(String id) {
        readerRepository.deleteById(id);
    }

    public List<ReaderDTO> findByPersonFullName(String fullName) {
        List<Reader> readers = readerRepository.findByPersonFullName(fullName);
        return readers.stream()
                .map(readerConverter::toDTO)
                .collect(Collectors.toList());
    }

    public List<ReaderDTO> findByKindOfReader(String kindOfReader) {
        List<Reader> readers = readerRepository.findByKindOfReader(kindOfReader);
        return readers.stream()
                .map(readerConverter::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ReaderDTO> findById(String id) {
        Optional<Reader> readerOptional = readerRepository.findById(id);
        return readerOptional.map(readerConverter::toDTO);
    }
}



