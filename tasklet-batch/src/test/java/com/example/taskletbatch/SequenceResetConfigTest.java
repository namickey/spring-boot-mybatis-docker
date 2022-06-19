package com.example.taskletbatch;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@DBRider
class SequenceResetConfigTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private SequenceResetConfig sequenceResetConfig;

	@Test
	@DataSet(value= "item.yml", executeStatementsBefore={"drop sequence abc", "create sequence abc"})
	@ExpectedDataSet(value = "expected-item.yml")
	void test1() throws Exception {
		JobExecution jobExecution = jobLauncher.run(sequenceResetConfig.job1(),
				new JobParametersBuilder().addString(
						"key", UUID.randomUUID().toString()).toJobParameters());
	}

	@Test
	@DataSet(value= "item.yml", executeStatementsBefore={"drop sequence abc", "create sequence abc"})
	@ExpectedDataSet(value = "expected-item.yml")
	void test2() throws Exception {
		JobExecution jobExecution = jobLauncher.run(sequenceResetConfig.job1(),
				new JobParametersBuilder().addString(
						"key", UUID.randomUUID().toString()).toJobParameters());
	}
}
