package com.example.demo.a1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.MapJobExplorerFactoryBean;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DemoConfig {

    @Autowired
    private DemoReader demoReader;
    @Autowired
    private DemoPros demoPros;
    @Autowired
    private DemoWriter demoWriter;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job demoJob() {
        return jobBuilderFactory.get("demojob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<Integer, String>chunk(2)
                .reader(demoReader)
                .processor(demoPros)
                .writer(demoWriter)
                .build();
    }

    @Bean
    DefaultBatchConfigurer batchConfigurer() {
        return new DefaultBatchConfigurer() {

            private JobRepository jobRepository;
            private JobExplorer jobExplorer;
            private JobLauncher jobLauncher;

            {
                MapJobRepositoryFactoryBean jobRepositoryFactory = new MapJobRepositoryFactoryBean();
                try {
                    this.jobRepository = jobRepositoryFactory.getObject();
                    MapJobExplorerFactoryBean jobExplorerFactory = new MapJobExplorerFactoryBean(jobRepositoryFactory);
                    this.jobExplorer = jobExplorerFactory.getObject();
                    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
                    jobLauncher.setJobRepository(jobRepository);
                    jobLauncher.afterPropertiesSet();
                    this.jobLauncher = jobLauncher;

                } catch (Exception e) {
                }
            }

            @Override
            public JobRepository getJobRepository() {
                return jobRepository;
            }

            @Override
            public JobExplorer getJobExplorer() {
                return jobExplorer;
            }

            @Override
            public JobLauncher getJobLauncher() {
                return jobLauncher;
            }
        };
    }
}
