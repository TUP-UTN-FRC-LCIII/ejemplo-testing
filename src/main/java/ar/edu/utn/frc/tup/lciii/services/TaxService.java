package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.Item;
import ar.edu.utn.frc.tup.lciii.model.Tax;
import ar.edu.utn.frc.tup.lciii.model.TaxCondition;
import ar.edu.utn.frc.tup.lciii.model.TaxType;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class TaxService {

    private static final BigDecimal IVA_CF = BigDecimal.valueOf(0.21);
    private static final BigDecimal IVA_MONO = BigDecimal.valueOf(0.21);
    private static final BigDecimal IVA_RESP = BigDecimal.valueOf(0.27);
    private static final BigDecimal IVA_EXE = BigDecimal.valueOf(0.0);

    private static final BigDecimal IIBB_CF = BigDecimal.valueOf(0.025);
    private static final BigDecimal IIBB_MONO = BigDecimal.valueOf(0.035);
    private static final BigDecimal IIBB_RESP = BigDecimal.valueOf(0.045);
    private static final BigDecimal IIBB_EXE = BigDecimal.valueOf(0.01);
    private static final BigDecimal IIBB_OTHER = BigDecimal.valueOf(0.05);

    private static final TaxCondition DEFAULT_TAX_CONDITION = TaxCondition.CONSUMIDOR_FINAL;

    public void calculateTaxes(List<Item> items) {
        for (Item item : items) {
            this.calculateTaxes(item);
        }
    }

    public void calculateTaxes(Item item) {
        List<Tax> taxes = this.getTaxes(item, DEFAULT_TAX_CONDITION);
        BigDecimal taxesAmount = this.getTaxesAmount(taxes);
        item.setFinalAmount(item.getNetAmount().add(taxesAmount));
    }

    public void calculateTaxes(List<Item> items, TaxCondition taxCondition) {
        if(taxCondition == null) {
            this.calculateTaxes(items);
        }
        for (Item item : items) {
            this.calculateTaxes(item, taxCondition);
        }
    }

    public void calculateTaxes(Item item, TaxCondition taxCondition) {
        List<Tax> taxes = this.getTaxes(item, taxCondition);
        BigDecimal taxesAmount = this.getTaxesAmount(taxes);
        item.setFinalAmount(item.getNetAmount().add(taxesAmount));
    }

    private BigDecimal getTaxesAmount(List<Tax> taxes) {
        BigDecimal totalTaxes = BigDecimal.ZERO;
        for(Tax tax : taxes) {
            totalTaxes = totalTaxes.add(tax.getAmount());
        }
        return  totalTaxes;
    }

    private List<Tax> getTaxes(Item item, TaxCondition taxCondition) {
        List<Tax> taxes = new ArrayList<>();
        taxes.add(getIVA(item,taxCondition));
        taxes.add(getIIBB(item, taxCondition));
        return taxes;
        // Other way to return a List of elements
        // return Arrays.asList(getIVA(item,taxCondition), getIIBB(item, taxCondition));
    }

    private Tax getIVA(Item item, TaxCondition taxCondition) {
        switch (taxCondition) {
            case RESPONSABLE_INSCRIPTO:
                return new Tax("IVA Responsable Inscripto", TaxType.IVA, IVA_RESP, item.getNetAmount().multiply(IVA_RESP));
            case EXENTO:
                return new Tax("IVA Exento", TaxType.IVA, IVA_EXE, item.getNetAmount().multiply(IVA_EXE));
            case MONOTRIBUTISTA:
                return new Tax("IVA Monotributista", TaxType.IVA, IVA_MONO, item.getNetAmount().multiply(IVA_MONO));
            default:
                return new Tax("IVA Consumidor Final", TaxType.IVA, IVA_CF, item.getNetAmount().multiply(IVA_CF));
        }
    }

    private Tax getIIBB(Item item, TaxCondition taxCondition) {
        switch (taxCondition) {
            case CONSUMIDOR_FINAL:
                return new Tax("IIBB Consumidor Final", TaxType.IIBB, IIBB_CF, item.getNetAmount().multiply(IIBB_CF));
            case RESPONSABLE_INSCRIPTO:
                return new Tax("IIBB Responsable Inscripto", TaxType.IIBB, IIBB_RESP, item.getNetAmount().multiply(IIBB_RESP));
            case EXENTO:
                return new Tax("IIBB Exento", TaxType.IIBB, IIBB_EXE, item.getNetAmount().multiply(IIBB_EXE));
            case MONOTRIBUTISTA:
                return new Tax("IIBB Monotributista", TaxType.IIBB, IIBB_MONO, item.getNetAmount().multiply(IIBB_MONO));
            default:
                return new Tax("IIBB Consumidor Final", TaxType.IIBB, IIBB_OTHER, item.getNetAmount().multiply(IIBB_OTHER));
        }
    }

}
