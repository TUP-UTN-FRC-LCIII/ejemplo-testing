package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.Item;
import ar.edu.utn.frc.tup.lciii.model.Tax;
import ar.edu.utn.frc.tup.lciii.model.TaxCondition;
import ar.edu.utn.frc.tup.lciii.model.TaxType;
import org.junit.jupiter.api.*;
import org.junit.platform.commons.support.ReflectionSupport;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Optional;

public class TaxServiceTest {

    private static final String ITEM_NAME = "Papas Fritas";
    private static final Integer ITEM_QUANTITY = 5;
    private static final BigDecimal ITEM_PRICE = BigDecimal.valueOf(10.50);
    private static final BigDecimal ITEM_NET_AMOUNT = BigDecimal.valueOf(52.50);

    private static final String TAX_IIBB_NAME_CF = "IIBB Consumidor Final";
    private static final String TAX_IIBB_NAME_RI = "IIBB Responsable Inscripto";
    private static final String TAX_IIBB_NAME_MONO = "IIBB Monotributista";
    private static final String TAX_IIBB_NAME_EXE = "IIBB Exento";
    private static final String TAX_IVA_NAME_CF = "IVA Consumidor Final";
    private static final String TAX_IVA_NAME_RI = "IVA Responsable Inscripto";
    private static final String TAX_IVA_NAME_MONO = "IVA Monotributista";
    private static final String TAX_IVA_NAME_EXE = "IVA Exento";

    private static final BigDecimal TAX_IIBB_AMOUNT_CF = BigDecimal.valueOf(1.3125);
    private static final BigDecimal TAX_IIBB_AMOUNT_RI = BigDecimal.valueOf(2.3625);
    private static final BigDecimal TAX_IIBB_AMOUNT_MONO = BigDecimal.valueOf(1.8375);
    private static final BigDecimal TAX_IIBB_AMOUNT_EXE = BigDecimal.valueOf(0.525);
    private static final BigDecimal TAX_IIBB_AMOUNT_DEFAULT = BigDecimal.valueOf(2.625);
    private static final BigDecimal TAX_IVA_AMOUNT_CF = BigDecimal.valueOf(11.025);
    private static final BigDecimal TAX_IVA_AMOUNT_RI = BigDecimal.valueOf(14.175);
    private static final BigDecimal TAX_IVA_AMOUNT_MONO = BigDecimal.valueOf(11.025);
    private static final BigDecimal TAX_IVA_AMOUNT_EXE = BigDecimal.valueOf(0.00).setScale(2);
    private static final BigDecimal TAX_IVA_AMOUNT_DEFAULT = BigDecimal.valueOf(11.025);

    private TaxService taxService = new TaxService();

    @Test
    @DisplayName("Test get IIBB for Consumidor Final Tax Condition")
    public void getIIBBConsumidorFinalTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IIBB_NAME_CF, TaxType.IIBB, BigDecimal.valueOf(0.025), TAX_IIBB_AMOUNT_CF);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIIBB", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.CONSUMIDOR_FINAL);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IIBB for Responsable Incripto Tax Condition")
    public void getIIBBResponsableInscriptoTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IIBB_NAME_RI, TaxType.IIBB, BigDecimal.valueOf(0.045), TAX_IIBB_AMOUNT_RI);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIIBB", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.RESPONSABLE_INSCRIPTO);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IIBB for Monotributista Tax Condition")
    public void getIIBBMonotributistaTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IIBB_NAME_MONO, TaxType.IIBB, BigDecimal.valueOf(0.035), TAX_IIBB_AMOUNT_MONO);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIIBB", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.MONOTRIBUTISTA);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IIBB for Exento Tax Condition")
    public void getIIBBExentoTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IIBB_NAME_EXE, TaxType.IIBB, BigDecimal.valueOf(0.01), TAX_IIBB_AMOUNT_EXE);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIIBB", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.EXENTO);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IIBB for Default Tax Condition")
    public void getIIBBDefaultTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IIBB_NAME_CF, TaxType.IIBB, BigDecimal.valueOf(0.05), TAX_IIBB_AMOUNT_DEFAULT);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIIBB", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.DEFAULT);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IVA for Consumidor Final Tax Condition")
    public void getIVAConsumidorFinalTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IVA_NAME_CF, TaxType.IVA, BigDecimal.valueOf(0.21), TAX_IVA_AMOUNT_CF);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIVA", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.CONSUMIDOR_FINAL);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IVA for Responsable Incripto Tax Condition")
    public void getIVAResponsableInscriptoTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IVA_NAME_RI, TaxType.IVA, BigDecimal.valueOf(0.27), TAX_IVA_AMOUNT_RI);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIVA", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.RESPONSABLE_INSCRIPTO);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IVA for Monotributista Tax Condition")
    public void getIVAMonotributistaTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IVA_NAME_MONO, TaxType.IVA, BigDecimal.valueOf(0.21), TAX_IVA_AMOUNT_MONO);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIVA", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.MONOTRIBUTISTA);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IVA for Exento Tax Condition")
    public void getIVAExentoTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IVA_NAME_EXE, TaxType.IVA, BigDecimal.valueOf(0.0).setScale(1), TAX_IVA_AMOUNT_EXE);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIVA", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.EXENTO);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }

    @Test
    @DisplayName("Test get IVA for Default Tax Condition")
    public void getIVADefaultTest() {
        // given
        Item item = new Item(ITEM_NAME, ITEM_QUANTITY, ITEM_PRICE, ITEM_NET_AMOUNT, null, null);
        Tax taxExpected = new Tax(TAX_IVA_NAME_CF, TaxType.IVA, BigDecimal.valueOf(0.21), TAX_IVA_AMOUNT_DEFAULT);
        // when
        Optional<Method> method = ReflectionSupport.findMethod(TaxService.class, "getIVA", Item.class, TaxCondition.class);
        Tax taxActual = (Tax) ReflectionSupport.invokeMethod(method.get(), taxService, item, TaxCondition.DEFAULT);
        // then
        Assertions.assertEquals(taxExpected, taxActual);
    }
}
