package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.support.ReflectionSupport;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BillServiceTest {

    private static final String ITEM_NAME = "Papas Fritas";
    private static final Integer ITEM_QUANTITY = 2;
    private static final BigDecimal ITEM_PRICE = BigDecimal.valueOf(10.50);
    private static final BigDecimal ITEM_NET = BigDecimal.valueOf(21.00);
    private static final BigDecimal ITEM_FINAL = BigDecimal.valueOf(25.935);
    private static final Tax TAX_IIBB = new Tax("IIBB Consumidor Final", TaxType.IIBB, BigDecimal.valueOf(0.025), BigDecimal.valueOf(0.525));
    private static final Tax TAX_IVA = new Tax("IVA Consumidor Final", TaxType.IVA, BigDecimal.valueOf(0.21), BigDecimal.valueOf(4.41));

    private static final String ITEM_NAME_2 = "Nachos";
    private static final Integer ITEM_QUANTITY_2 = 3;
    private static final BigDecimal ITEM_PRICE_2 = BigDecimal.valueOf(23.49);
    private static final BigDecimal ITEM_NET_2 = BigDecimal.valueOf(70.47);
    private static final BigDecimal ITEM_FINAL_2 = BigDecimal.valueOf(87.03045);
    private static final Tax TAX_IIBB_2 = new Tax("IIBB Consumidor Final", TaxType.IIBB, BigDecimal.valueOf(0.025), BigDecimal.valueOf(1.76175));
    private static final Tax TAX_IVA_2 = new Tax("IVA Consumidor Final", TaxType.IVA, BigDecimal.valueOf(0.21), BigDecimal.valueOf(14.7987));

    private static final Customer CUST_CF = new Customer("Consumidor", "Final", TaxCondition.CONSUMIDOR_FINAL);

    @Mock
    private TaxService taxService;

    @Mock
    private ItemService itemService;

    @Mock
    private CustomerService customerService;

    @Mock
    private AfipService afipService;

    @Mock
    private BarCodeService barCodeService;

    @Spy
    @InjectMocks
    private BillService billService;

    @Test
    @DisplayName("Test billing a Consumidor Final Tax Condition")
    public void billingConsumidorFinalTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, null, null, null);
        Item item2 = new Item(ITEM_NAME_2, ITEM_QUANTITY_2, ITEM_PRICE_2, null, null, null);
        List<Item> itemsToBill = Arrays.asList(item, item2);
        Item itemBilled = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET, ITEM_FINAL, Arrays.asList(TAX_IIBB, TAX_IVA));
        Item itemBilled2 = new Item(ITEM_NAME_2, ITEM_QUANTITY_2, ITEM_PRICE_2, ITEM_NET_2, ITEM_FINAL_2, Arrays.asList(TAX_IIBB_2, TAX_IVA_2));
        List<Item> itemsBilled = Arrays.asList(itemBilled, itemBilled2);
        Bill billExpected = new Bill("0001-00000001", "1234567890", CUST_CF, BigDecimal.valueOf(91.47), BigDecimal.valueOf(112.96545), itemsBilled);
        // when
        when(customerService.getDefaultCustomer()).thenCallRealMethod();
        when(itemService.calculateNetAmount(itemsToBill)).thenReturn(itemsBilled);
        given(afipService.getBillNumber(Mockito.any(Bill.class))).willReturn("0001-00000001");
        given(barCodeService.generateBarCode(Mockito.any())).willReturn("1234567890");
        Bill billActual = billService.billing(itemsToBill);
        Assertions.assertEquals(billExpected, billActual);
        // then

    }
}
