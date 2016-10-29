package gr.iserm.java.user.processing;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.MemoryAggregationRepository;
import org.springframework.stereotype.Component;

@Component
public class UserRouteBuilder extends RouteBuilder {

    public static String USER_NAME_HEADER = "USER_ID";

    @Override
    public void configure() throws Exception {

        from("seda:init")
            .setHeader(USER_NAME_HEADER, simple("${body.name}"))
            .log("Processing new user with id ${header."+ USER_NAME_HEADER +"}.")
            .multicast()
                .to("seda:aggregate")
                .to("seda:validate")
                .to("seda:enrich");

        from("seda:validate")
            .bean("userService", "validate")
            .to("seda:aggregate");

        from("seda:enrich")
            .bean("userService", "enrich")
            .to("seda:aggregate");

        from("seda:aggregate")
            .aggregate().expression(header(USER_NAME_HEADER)).completionSize(3).aggregationStrategy(new UserProcessingAggregator())
            .log("Processed new user with id ${header."+ USER_NAME_HEADER +"}.")
            .transform(simple("${body.build()}"))
            .to("bean:userDao?method=insert");
    }
}
