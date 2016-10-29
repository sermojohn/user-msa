package gr.iserm.java;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

	public static void main(String[] args) throws IOException {
//		HsqlDbServerWrapper.init();
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SpringBootApplication.class)
			.headless(false)
			.run(args);
	}

}
