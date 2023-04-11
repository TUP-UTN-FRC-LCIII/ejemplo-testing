package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.Bill;

public class BarCodeService {

    public String generateBarCode(Bill bill) {
        return "012345" + bill.getBillNumber() + bill.getTotalAmount();
    }
}
