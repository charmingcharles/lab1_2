package pl.com.bottega.ecommerce.sales.domain.invoicing;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.util.ArrayList;
import java.util.List;

public class InvoiceBuilder {

    private ClientData client;

    private Id id;

    public InvoiceBuilder(Id id, ClientData client) {
        this.client = client;
        this.id = id;
    }

    public Invoice build(){
        return new Invoice(id, client);
    }
}
