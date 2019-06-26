package com.kroger.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import com.datastax.driver.core.Cluster;

@Configuration
@PropertySource(value = { "classpath:cassandra.properties" })
@EnableCassandraRepositories(basePackages = { "com.kroger.repository" })
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(environment.getProperty("cassandra.contactpoints"));
        cluster.setPort(Integer.parseInt(environment.getProperty("cassandra.port")));
        cluster.setJmxReportingEnabled(false);
        return cluster;
    }

    @Override
    protected String getKeyspaceName() {
        return environment.getProperty("cassandra.keyspace");
    }

  
    
    @Bean
    public Cluster clusters() {
    	return Cluster.builder().withoutJMXReporting().addContactPoints(environment.getProperty("cassandra.contactpoints")).build();
    }
    
    
}