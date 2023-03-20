package senla.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DaoConfiguration {
    @Value("${db.driverClassName}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;
    @Value("${db.sqlDialect}")
    private String sqlDialect;
    @Value("${db.showSql}")
    private Boolean showSql;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }


    // Конфигурируем адаптер поставщика jpa
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        // Установить тип базы данных
        jpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        // Устанавливаем показ генерируемого sql
        jpaVendorAdapter.setShowSql(showSql);
        // Устанавливаем не генерировать операторы DDL
        jpaVendorAdapter.setGenerateDdl(false);
        // Установить диалект
        jpaVendorAdapter.setDatabasePlatform(sqlDialect);
        return jpaVendorAdapter;
    }

    // Настройка фабрики диспетчера сущностей
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        // Вставить источник данных
        entityManagerFactoryBean.setDataSource(dataSource);
        // Внедрить адаптер производителя jpa
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        // Установить базовый пакет сканирования
        entityManagerFactoryBean.setPackagesToScan("senla.models");
        return entityManagerFactoryBean;
    }

    // Настройка менеджера транзакций jpa
    @Bean
    public PlatformTransactionManager transactionManager(LocalContainerEntityManagerFactoryBean emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        // Настройка фабрики диспетчера сущностей
        transactionManager.setEntityManagerFactory(emf.getObject());
        return transactionManager;
    }

    @Bean
    public SpringLiquibase springLiquibase(DataSource dataSource) {
        SpringLiquibase springLiquibase = new SpringLiquibase();
        springLiquibase.setDataSource(dataSource);
        springLiquibase.setChangeLog("changelog.xml");
        return springLiquibase;
    }
}
