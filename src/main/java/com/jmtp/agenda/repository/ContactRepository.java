package com.jmtp.agenda.repository;

import com.jmtp.agenda.model.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<Contact, String> {
}
