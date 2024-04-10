package com.example.springbatch.job.joblistener;

import com.example.springbatch.job.validateparam.validator.FileParamValidator;
import com.sun.tools.javac.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JobListenerConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job jobListenerJob(Step jobListenerStep) {
        return jobBuilderFactory.get("jobListenerJob")
                .incrementer(new RunIdIncrementer())
                .listener(new JobLoggerListener())
                .start(jobListenerStep)
                .build();
    }

    @Bean
    public Step jobListenerStep(Tasklet jobListenerStepTasklet) {
        return stepBuilderFactory.get("jobListenerStep")
                .tasklet(jobListenerStepTasklet)
                .build();
    }

    @StepScope
    @Bean
    public Tasklet jobListenerStepTasklet() {
        return (contribution, chunkContext) -> {
            throw new Exception("Failed!!!");
        };
    }
}
