package ar.edu.utn.frc.tup.lciii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tax {

    private String name;
    private TaxType taxType;
    private BigDecimal rate;
    private BigDecimal amount;
}
