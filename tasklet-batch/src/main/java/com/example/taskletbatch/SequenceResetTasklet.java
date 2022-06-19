package com.example.taskletbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SequenceResetTasklet implements Tasklet {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("*************************");
        System.out.println(itemMapper.select());
        return RepeatStatus.FINISHED;
    }
}
