package ar.edu.utn.frc.tup.lciii.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private String name;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal netAmount;
    private BigDecimal finalAmount;
    private List<Tax> taxList;

}
