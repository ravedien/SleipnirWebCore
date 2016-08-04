package com.sleipnir.config;

import javax.sql.DataSource;

import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.ExecuteListenerProvider;
import org.jooq.SQLDialect;
import org.jooq.TransactionProvider;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.modelmapper.jooq.RecordValueReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.sleipnir.exception.ExceptionTranslator;
import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@Configuration
public class JooqSpringBootConfiguration {
	
	private static final int passwordStrength = 15;
	
	private static final String PROPERTY_NAME_DB_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DB_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DB_SCHEMA_SCRIPT = "db.schema.script";
    private static final String PROPERTY_NAME_DB_DATA_SCRIPT = "db.data.script";
    private static final String PROPERTY_NAME_DB_URL = "db.url";
    private static final String PROPERTY_NAME_DB_USERNAME = "db.username";
    private static final String PROPERTY_NAME_JOOQ_SQL_DIALECT = "jooq.sql.dialect";
    
    @Autowired
    private Environment env;
    
    @Bean(destroyMethod = "close")
    public HikariDataSource  dataSource() {
    	HikariDataSource  dataSource = new HikariDataSource ();
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DB_DRIVER));
        dataSource.setJdbcUrl(env.getRequiredProperty(PROPERTY_NAME_DB_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DB_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DB_PASSWORD));

        return dataSource;
    }
	
//    @Bean
//    public LazyConnectionDataSourceProxy lazyConnectionDataSource() {
//        return new LazyConnectionDataSourceProxy(dataSource());
//    }
    
//    @Bean
//    public TransactionAwareDataSourceProxy transactionAwareDataSource() {
//        return new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
//    }
    
	@Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
	
//	@Bean
//    public DataSourceConnectionProvider connectionProvider() {
//        return new DataSourceConnectionProvider(transactionAwareDataSource());
//    }
	
	@Bean
    public ExceptionTranslator exceptionTranslator() {
        return new ExceptionTranslator();
    }
	
	@Bean
    public org.jooq.Configuration jooqConfig(ConnectionProvider connectionProvider,
                                             TransactionProvider transactionProvider, ExecuteListenerProvider executeListenerProvider) {

        return new DefaultConfiguration() //
                .derive(connectionProvider) //
                .derive(transactionProvider) //
                .derive(executeListenerProvider) //
                .derive(SQLDialect.POSTGRES);
    }
	
    @Bean
    public DSLContext dsl(org.jooq.Configuration config) {
        return new DefaultDSLContext(config);
    }

    @Bean
    public ConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }
    
    @Bean
    public ModelMapper modelMapper(){
    	ModelMapper modelMapper = new ModelMapper();
    	modelMapper.getConfiguration().addValueReader(new RecordValueReader()).setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
    	return modelMapper;
    }

    @Bean
    public TransactionProvider transactionProvider() {
        return new SpringTransactionProvider();
    }

    
    @Bean
    public ExecuteListenerProvider executeListenerProvider(ExceptionTranslator exceptionTranslator) {
        return new DefaultExecuteListenerProvider(exceptionTranslator);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
    	return new BCryptPasswordEncoder(passwordStrength);
    }
}
