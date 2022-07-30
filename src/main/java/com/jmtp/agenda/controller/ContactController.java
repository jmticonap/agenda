package com.jmtp.agenda.controller;

import com.jmtp.agenda.model.Contact;
import com.jmtp.agenda.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository repoContact;

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<Contact> findAll(){
        return repoContact.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Contact> save(@RequestBody(required = true) Contact contact){
        Contact result = repoContact.save(contact);
        return new ResponseEntity<Contact>(result, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Contact> remove(@PathVariable(required = true) String id){
        Contact removed_contact = repoContact.findById(id).get();
        repoContact.deleteById(id);
        return new ResponseEntity<Contact>(removed_contact, HttpStatus.OK);
    }

}
