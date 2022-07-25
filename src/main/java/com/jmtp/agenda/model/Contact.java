package com.jmtp.agenda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contact")
public class Contact {
    private String _id;
    private String firstname;
    private String lastname;
    private LocalDate birthday;
    private List<PhoneSchema> phones;
    private List<SocialSchema> socials;
}
