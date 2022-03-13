package pl.com.bottega.ecommerce.sales.domain.payment;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class PaymentBuilder {

    private ClientData clientData;

    private Money amount;

    private Id aggregateId;

    public PaymentBuilder(ClientData clientData, Money amount, Id aggregateId) {
        this.clientData = clientData;
        this.amount = amount;
        this.aggregateId = aggregateId;
    }

    public PaymentBuilder withClientData(ClientData clientData){
        this.clientData = clientData;
        return this;
    }

    public PaymentBuilder withAmount(Money amount){
        this.amount = amount;
        return this;
    }

    public PaymentBuilder withAggregateId(Id aggregateId){
        this.aggregateId = aggregateId;
        return this;
    }

    public Payment build(){
        return new Payment(aggregateId, clientData, amount);
    }
}
