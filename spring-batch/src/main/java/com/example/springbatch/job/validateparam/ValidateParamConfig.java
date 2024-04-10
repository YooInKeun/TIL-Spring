package com.example.springbatch.job.validateparam;

import com.example.springbatch.job.validateparam.validator.FileParamValidator;
import com.sun.tools.javac.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.CompositeJobParametersValidator;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class ValidateParamConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job validateParamJob(Step validateParamStep) {
        return jobBuilderFactory.get("validateParamJob")
                .incrementer(new RunIdIncrementer())
                .validator(multipleValidator())
                .start(validateParamStep)
                .build();
    }

    private CompositeJobParametersValidator multipleValidator() {
        CompositeJobParametersValidator validator = new CompositeJobParametersValidator();
        validator.setValidators(List.of(new FileParamValidator()));
        return validator;
    }

    @JobScope
    @Bean
    public Step validateParamStep(Tasklet validateParamStepTasklet) {
        return stepBuilderFactory.get("validateParamStep")
                .tasklet(validateParamStepTasklet)
                .build();
    }

    @StepScope
    @Bean
    public Tasklet validateParamStepTasklet(@Value("#{jobParameters['fileName']}") String fileName) {
        return (contribution, chunkContext) -> {
            System.out.println(fileName);
            System.out.println("validated param tasklet");
            return RepeatStatus.FINISHED;
        };
    }
}
