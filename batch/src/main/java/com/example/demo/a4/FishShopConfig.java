package com.example.demo.a4;

import com.amazonaws.services.s3.AmazonS3;
import com.example.demo.a3.FoodShopPros;
import com.example.demo.a3.FoodShopWriter;
import com.example.demo.a3.Item;
import io.awspring.cloud.core.io.s3.SimpleStorageProtocolResolver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.net.MalformedURLException;


@EnableBatchProcessing
@Configuration
public class FishShopConfig {

    @Autowired
    private FishShopPros fishShopPros;
    @Autowired
    private FishShopWriter fishShopWriter;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public FlatFileItemReader<com.example.demo.a3.Item> fishReader() throws MalformedURLException {

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames(new String[] { "id", "name" });
        lineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<com.example.demo.a3.Item> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(com.example.demo.a3.Item.class);

        DefaultLineMapper<com.example.demo.a3.Item> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        FlatFileItemReader<com.example.demo.a3.Item> reader = new FlatFileItemReader<>();
        reader.setLineMapper(lineMapper);
        reader.setResource(new FileSystemResource("a.csv"));
        return reader;
    };

    @Bean
    public Job fishShopJob(@Qualifier("stepFishShop1") Step step1) {
        return jobBuilderFactory.get("fishShopJob")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step stepFishShop1() throws MalformedURLException {
        return stepBuilderFactory.get("stepFishShop1")
                .<com.example.demo.a3.Item, Item>chunk(1000)
                .reader(fishReader())
                .processor(fishShopPros)
                .writer(fishShopWriter)
                .build();
    }
}
