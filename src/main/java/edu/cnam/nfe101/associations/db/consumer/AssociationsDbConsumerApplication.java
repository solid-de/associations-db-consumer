package edu.cnam.nfe101.associations.db.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.cnam.nfe101.associations.db.consumer.service.AssociationConsumer;

@SpringBootApplication
public class AssociationsDbConsumerApplication implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(AssociationsDbConsumerApplication.class);

	@Autowired
	private AssociationConsumer associationConsumer;

	public static void main(String[] args) {
		log.info("App started");
		SpringApplication.run(AssociationsDbConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting consumer");
		associationConsumer.consume();
	}

}
