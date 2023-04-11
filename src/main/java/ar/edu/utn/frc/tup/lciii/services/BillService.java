package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.Bill;
import ar.edu.utn.frc.tup.lciii.model.Customer;
import ar.edu.utn.frc.tup.lciii.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

public class BillService {

    private CustomerService customerService = new CustomerService();

    private TaxService taxService = new TaxService();

    private ItemService itemService = new ItemService();

    private AfipService afipService = new AfipService();

    private BarCodeService barCodeService = new BarCodeService();

    public Bill billing(List<Item> itemList) {
        return this.billing(itemList, customerService.getDefaultCustomer());
    }

    public Bill billing(List<Item> itemList, Customer customer) {
        Bill bill = new Bill();
        bill.setCustomer(customer);
        List<Item> itemsWithNetAmount = itemService.calculateNetAmount(itemList);
        bill.setItemList(itemsWithNetAmount);
        taxService.calculateTaxes(itemList, customer.getTaxCondition());
        bill.setBillNumber(afipService.getBillNumber(bill));
        this.calculateAmounts(bill);
        bill.setBarCode(barCodeService.generateBarCode(bill));
        return bill;
    }

    public void calculateAmounts(Bill bill) {
        this.calculateNetAmount(bill);
        this.calculateTotalAmount(bill);
    }

    private void calculateTotalAmount(Bill bill) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for(Item item : bill.getItemList()) {
            totalAmount = totalAmount.add(item.getFinalAmount());
        }
        bill.setTotalAmount(totalAmount);
    }

    private void calculateNetAmount(Bill bill) {
        BigDecimal netAmount = BigDecimal.ZERO;
        for(Item item : bill.getItemList()) {
            netAmount = netAmount.add(item.getNetAmount());
        }
        bill.setNetAmount(netAmount);
    }

}
