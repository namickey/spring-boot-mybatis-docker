package com.example.demo.a5;

import com.amazonaws.services.s3.AmazonS3;
import io.awspring.cloud.core.io.s3.SimpleStorageProtocolResolver;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.net.MalformedURLException;

@EnableBatchProcessing
@Configuration
public class CatShopConfig {

    @Autowired
    private CatShopPros catShopPros;
    @Autowired
    private CatShopWriter catShopWriter;
    @Autowired
    private CatShopWriter2 catShopWriter2;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private AmazonS3 amazonS3;

    @Bean
    public FlatFileItemReader<Item> catReader() throws MalformedURLException {
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
    public ClassifierCompositeItemWriter classifierCompositeItemWriter() {
        ClassifierCompositeItemWriter compositeItemWriter = new ClassifierCompositeItemWriter();
        compositeItemWriter.setClassifier( new Classifier<Item, ItemWriter>() {
            private int i;
            @Override
            public ItemWriter classify(Item item) {
                i++;
                if (i % 2 == 0){
                    return catShopWriter2;
                }
                return catShopWriter;
            }
        });
        return compositeItemWriter;
    }

    @Bean
    public Job catShopJob(@Qualifier("stepCatShop1") Step step1) {
        return jobBuilderFactory.get("catShopJob")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step stepCatShop1() throws MalformedURLException {
        return stepBuilderFactory.get("stepCatShop1")
                .<Item, Item>chunk(4)
                .reader(catReader())
                .processor(catShopPros)
                .writer(classifierCompositeItemWriter())
                .build();
    }
}
