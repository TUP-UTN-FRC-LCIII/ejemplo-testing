package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.Bill;

import java.util.Random;

public class AfipService {

    public String getBillNumber(Bill bill) {
        Random random = new Random();
        return String.format("%04d-%08d", random.nextInt(10000), random.nextInt(100000000));
    }
}
