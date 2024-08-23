package com.ranked.converter;

import com.ranked.dto.ReaderDTO;
import com.ranked.entity.Reader;
import com.ranked.entity.Person;
import com.ranked.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReaderConverter {

    @Autowired
    private PersonRepository personRepository;

    public Reader toEntity(ReaderDTO readerDTO) {
        Reader reader = new Reader();
        reader.setId(readerDTO.getId());
        reader.setKindOfReader(readerDTO.getKindOfReader());
        reader.setEmail(readerDTO.getEmail());
        reader.setPhoneNumber(readerDTO.getPhoneNumber());

        // Check if personId exists in PersonRepository
        Optional<Person> personOptional = personRepository.findById(readerDTO.getPersonId());
        if (personOptional.isPresent()) {
            reader.setPerson(personOptional.get());
        } else {
            throw new RuntimeException("Person not found with id " + readerDTO.getPersonId());
        }

        return reader;
    }
    public ReaderDTO toDTO(Reader reader) {
        ReaderDTO readerDTO = new ReaderDTO();
        readerDTO.setId(reader.getId());
        readerDTO.setKindOfReader(reader.getKindOfReader());
        readerDTO.setEmail(reader.getEmail());
        readerDTO.setPhoneNumber(reader.getPhoneNumber());
        readerDTO.setPersonId(reader.getPerson().getId()); // Assuming you have a getId() method in Person entity
        return readerDTO;
    }
}
