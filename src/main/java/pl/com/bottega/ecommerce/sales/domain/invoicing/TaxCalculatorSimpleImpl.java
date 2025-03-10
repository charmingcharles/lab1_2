package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;

public class TaxCalculatorSimpleImpl extends TaxCalculator {

    public TaxCalculatorSimpleImpl(Invoice invoice) {
        super(invoice);
    }

    @Override
    public InvoiceLine calculateTax(RequestItem item) {
        Money net = item.getTotalCost();
        BigDecimal ratio;
        String desc;

        switch (item.getProductData().getType()) {
            case DRUG:
                ratio = BigDecimal.valueOf(0.05);
                desc = "5% (D)";
                break;
            case FOOD:
                ratio = BigDecimal.valueOf(0.07);
                desc = "7% (F)";
                break;
            case STANDARD:
                ratio = BigDecimal.valueOf(0.23);
                desc = "23%";
                break;

            default:
                throw new IllegalArgumentException(item.getProductData().getType() + " not handled");
        }

        Money taxValue = net.multiplyBy(ratio);

        Tax tax = new Tax(taxValue, desc);

        return new InvoiceLine(item.getProductData(), item.getQuantity(), net, tax);
    }
}
