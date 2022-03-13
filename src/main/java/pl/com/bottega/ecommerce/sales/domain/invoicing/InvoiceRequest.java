package pl.com.bottega.ecommerce.sales.domain.invoicing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class InvoiceRequest {

    private ClientData client;
    private List<RequestItem> items = new ArrayList<>();

    public InvoiceRequest(ClientData client) {
        this.client = client;
    }

    public void add(RequestItem item) {
        items.add(item);
    }

    public ClientData getClient() {
        return client;
    }

    public Collection<RequestItem> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public Invoice request(){
        Invoice invoice = new Invoice(Id.generate(), client);

        for (RequestItem item : items) {
            Money net = item.getTotalCost();
            BigDecimal ratio = null;
            String desc = null;

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

            InvoiceLine invoiceLine = new InvoiceLine(item.getProductData(), item.getQuantity(), net, tax);
            invoice.addItem(invoiceLine);
        }

        return invoice;
    }
}
