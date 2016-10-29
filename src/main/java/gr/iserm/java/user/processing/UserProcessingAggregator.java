package gr.iserm.java.user.processing;

import gr.iserm.java.user.User;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class UserProcessingAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        User.Builder userBuilder;
        if(oldExchange == null) {
            userBuilder = User.Builder.create();
        } else {
            userBuilder = oldExchange.getIn().getBody(User.Builder.class);
        }
        newExchange.getOut().setBody(callBuilder(userBuilder, newExchange.getIn().getBody()));
        return newExchange;
    }

    private User.Builder callBuilder(User.Builder builder, Object o) {
        if(UserValidity.class.isInstance(o)) {
            builder.setValidity(UserValidity.class.cast(o));
        } else if(UserEnrichment.class.isInstance(o)) {
            builder.setEnhancement(UserEnrichment.class.cast(o));
        } else if(User.class.isInstance(o)) {
            builder.copyUser(User.class.cast(o));
        }
        return builder;
    }

}
