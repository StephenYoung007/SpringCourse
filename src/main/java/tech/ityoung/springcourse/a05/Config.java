package tech.ityoung.springcourse.a05;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import tech.ityoung.springcourse.a05.component.Bean2;

import javax.sql.DataSource;

@ComponentScan("tech.ityoung.springcourse.a05.component")
@Configuration
public class Config {

    @Bean
    public Bean1 bean1(){
        return new Bean1();
    };

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/vmess");
        dataSource.setUsername("root");
        dataSource.setPassword("22222222");
        return dataSource;
    }
}
