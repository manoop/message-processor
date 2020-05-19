package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gcp.pubsub.core.*;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

	    SpringApplication.run(DemoApplication.class, args);
        System.out.println("Hit 'Enter' to terminate");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	@Bean
	public CommandLineRunner cli(PubSubTemplate pubSubTemplate) {
		return (args) -> {
			pubSubTemplate.subscribe("messages-subscription-1",
					(msg, ackConsumer) -> {
						System.out.println(msg.getData().toStringUtf8());
						ackConsumer.ack();
					});
		};
	}

}
