package org.example.jpa.elms;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
