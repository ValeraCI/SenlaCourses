package senla.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.cfg.Environment;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("application.properties")
public class DaoConfiguration {
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.sqlDialect}")
    private String sqlDialect;
    @Value("${db.showSql}")
    private boolean showSql;

    @Bean
    public DataSource dataSource(){
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    /*@Bean
    public LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();

        localContainerEntityManagerFactoryBean.setPackagesToScan("senla/models");
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);

        Properties jpaProperties = new Properties();

        jpaProperties.put(Environment.DIALECT, sqlDialect);
        jpaProperties.put(Environment.SHOW_SQL, showSql);

        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);

        return localContainerEntityManagerFactoryBean;
    }*/

    @Bean
    public FactoryBean<EntityManagerFactory> entityManagerFactory(DataSource dataSource) {
        final LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        localContainerEntityManagerFactoryBean.setPackagesToScan("senla/models");
        localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        Properties jpaProperties = new Properties();

        jpaProperties.put(Environment.DIALECT, sqlDialect);
        jpaProperties.put(Environment.SHOW_SQL, showSql);

        localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
        return localContainerEntityManagerFactoryBean;
    }

    @Bean
    public TransactionManager transactionManager(FactoryBean<EntityManagerFactory> entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        try {
            transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        } catch (Exception e) {
            e.printStackTrace(); //TODO сделать что-то тут
        }
        return transactionManager;
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }


    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("changelog.xml");
        return springLiquibase;
    }
}
