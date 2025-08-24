package com.api.payments;

import com.sun.istack.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PaymentsApplication {

	private static final Logger logger = Logger.getLogger(PaymentsApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(PaymentsApplication.class, args);
		logger.info(
				"\n----------------------------------------------------------\n\t"
						+ "Payments API is running in default port: 8080"
						+ "\n----------------------------------------------------------"
		);
	}
}
