package com.example.demo.a3;

import com.amazonaws.services.s3.AmazonS3;
import io.awspring.cloud.core.io.s3.SimpleStorageProtocolResolver;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.net.MalformedURLException;

@EnableBatchProcessing
@Configuration
public class FoodShopConfig {

    @Autowired
    private  FoodShopPros foodShopPros;
    @Autowired
    private FoodShopWriter foodShopWriter;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private AmazonS3 amazonS3;

    @Bean
    public FlatFileItemReader<Item> foodReader() throws MalformedURLException {
        SimpleStorageProtocolResolver simpleStorageProtocolResolver = new SimpleStorageProtocolResolver(amazonS3);
        Resource aResource = resourceLoader.getResource("s3://" + "batch-567" + "/" + "a.csv");
        if (!aResource.getClass().getName().endsWith("SimpleStorageResource") &&
                resourceLoader instanceof DefaultResourceLoader) {
            aResource = simpleStorageProtocolResolver.resolve("s3://" + "batch-567" + "/" + "a.csv", resourceLoader);
        }

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("id", "name", "code");
        lineTokenizer.setStrict(false);

        BeanWrapperFieldSetMapper<Item> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Item.class);

        DefaultLineMapper<Item> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);

        FlatFileItemReader<Item> reader = new FlatFileItemReader<>();
        reader.setLineMapper(lineMapper);
        reader.setResource(aResource);
        return reader;
    };

    @Bean
    public Job foodShopJob(@Qualifier("stepFoodShop1") Step step1) {
        return jobBuilderFactory.get("foodShopJob")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step stepFoodShop1() throws MalformedURLException {
        return stepBuilderFactory.get("stepFoodShop1")
                .<Item, Item>chunk(1000)
                .reader(foodReader())
                .processor(foodShopPros)
                .writer(foodShopWriter)
                .build();
    }
}
