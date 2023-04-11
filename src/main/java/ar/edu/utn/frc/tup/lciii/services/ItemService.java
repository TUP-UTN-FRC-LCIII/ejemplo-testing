package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.Item;

import java.math.BigDecimal;
import java.util.List;

public class ItemService {

    public List<Item> calculateNetAmount(List<Item> items) {
        for (Item item : items) {
            this.calculateNetAmount(item);
        }
        return items;
    }

    private void calculateNetAmount(Item item) {
        item.setNetAmount(
                item.getUnitPrice().multiply(
                        BigDecimal.valueOf(item.getQuantity())
                )
        );
    }
}
