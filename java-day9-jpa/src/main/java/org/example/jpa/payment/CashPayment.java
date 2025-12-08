package org.example.jpa.payment;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
//@DiscriminatorValue("CASH")
public class CashPayment extends Payment {

    private String cashierName;
}
