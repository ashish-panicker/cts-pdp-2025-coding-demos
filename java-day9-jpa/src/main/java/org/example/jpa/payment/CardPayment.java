package org.example.jpa.payment;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@DiscriminatorValue("CARD")
public class CardPayment extends Payment {

    @Column(length = 16)
    private String cardNumber;
}
