package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.Customer;
import ar.edu.utn.frc.tup.lciii.model.TaxCondition;

public class CustomerService {

    public Customer getDefaultCustomer() {
        return new Customer("Consumidor", "Final", TaxCondition.CONSUMIDOR_FINAL);
    }
}
