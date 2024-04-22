package com.crud.pessoa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@SpringBootApplication
public class CrudPessoaApplication {
	private static final Logger log = LoggerFactory.getLogger(CrudPessoaApplication.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(CrudPessoaApplication.class);

		Environment env = app.run(args).getEnvironment();
		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		String hostAddress = "localhost";

		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			log.warn("The host name could not be determined, using `localhost` as fallback");
		}

		log.info("""

                        ----------------------------------------------------------
                        \tApplication '{}' is running! Access URLs:
                        \tLocal: \t\t{}://localhost:{}
                        \tExternal: \t{}://{}:{}
                        ----------------------------------------------------------""",
				env.getProperty("spring.application.name"),
				protocol,
				env.getProperty("server.port"),
				protocol,
				hostAddress,
				env.getProperty("server.port"));
	}

}
