package ar.edu.utn.frc.tup.lciii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bill {

    private String billNumber;
    private String barCode;
    private Customer customer;
    private BigDecimal netAmount;
    private BigDecimal totalAmount;
    private List<Item> itemList;

}
