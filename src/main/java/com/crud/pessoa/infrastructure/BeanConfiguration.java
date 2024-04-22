package com.crud.pessoa.infrastructure;

import com.crud.pessoa.config.properties.ApplicationProperties;
import com.crud.pessoa.domain.domainService.DomainPessoaService;
import com.crud.pessoa.domain.repository.PessoaRepository;
import com.crud.pessoa.domain.service.PessoaService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.crud.pessoa.domain.*")
@EntityScan("com.crud.pessoa.domain.*")
@ComponentScan("com.crud.pessoa.domain.*")
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
public class BeanConfiguration { }
