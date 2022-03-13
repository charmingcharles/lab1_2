package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.util.List;

abstract class TaxCalculator {

    Invoice invoice;

    public TaxCalculator(Invoice invoice){
        this.invoice = invoice;
    }

    public Invoice calculateTaxes(List<RequestItem> items) {
        for (RequestItem item : items) {
            invoice.addItem(calculateTax(item));
        }
        return invoice;
    }

    abstract public InvoiceLine calculateTax(RequestItem item);

}
