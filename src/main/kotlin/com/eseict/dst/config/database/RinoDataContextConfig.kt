package com.eseict.dst.config.database

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(
    basePackages = ["com.eseict.dst.repository.rino",
                   ],
    entityManagerFactoryRef = "rinoEntityManager",
    transactionManagerRef = "rinoTransactionManager"
)
class RinoDataContextConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-rino")
    fun rinoDataSource(): DataSource = DataSourceBuilder.create().build()

    @Bean
    fun rinoEntityManager(): LocalContainerEntityManagerFactoryBean =
        (LocalContainerEntityManagerFactoryBean()).apply {
            dataSource = rinoDataSource()
            setPackagesToScan("com.eseict.dst.entity")
            jpaVendorAdapter = HibernateJpaVendorAdapter()
        }

    @Bean
    fun rinoTransactionManager() = JpaTransactionManager(rinoEntityManager().`object`!!)

}