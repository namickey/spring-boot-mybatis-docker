package com.example.demo.a2;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//@EnableBatchProcessing
//@Configuration
public class ShopConfig {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    private  ShopPros shopPros;
    @Autowired
    private ShopWriter shopWriter;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    public ShopConfig() {
    }

    @Bean
    public MyBatisCursorItemReader<Item> reader(){
        return new MyBatisCursorItemReaderBuilder<Item>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.example.demo.a2.ItemMapper.selectAll")
                .build();
    };

    @Bean
    public Job shopJob(@Qualifier("stepShop1") Step step1) {
        return jobBuilderFactory.get("shopJob")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step stepShop1(){
        return stepBuilderFactory.get("stepShop1")
                .<Item, Item>chunk(2)
                .reader(reader())
                .processor(shopPros)
                .writer(shopWriter)
                .build();
    }
}
