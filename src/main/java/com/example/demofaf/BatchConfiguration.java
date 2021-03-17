package com.example.demofaf;

import com.example.demofaf.listener.JobListener;
import com.example.demofaf.model.LogItem;
import com.example.demofaf.model.Persona;
import com.example.demofaf.processor.LogItemProcessor;
import com.example.demofaf.processor.PersonaItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.PassThroughFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /*** PERSONA FLOW ***/
//    @Bean
//    public FlatFileItemReader<Persona> readerPerson() {
//        return new FlatFileItemReaderBuilder<Persona>().name("personaItemReader")
//                .resource(new ClassPathResource("data.csv")).delimited()
//                .names(new String[] { "nombre", "apellido", "telefono" })
//                .fieldSetMapper(new BeanWrapperFieldSetMapper<Persona>() {
//                    {
//                        setTargetType(Persona.class);
//                    }
//                }).build();
//    }
//
//    @Bean
//    public PersonaItemProcessor processorPerson() {
//        return new PersonaItemProcessor();
//    }
//
//    @Bean
//    public JdbcBatchItemWriter<Persona> writerPerson(DataSource dataSource) {
//        return new JdbcBatchItemWriterBuilder<Persona>()
//                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
//                .sql("INSERT INTO persona (nombre, apellido, telefono) VALUES (:nombre, :apellido, :telefono)")
//                .dataSource(dataSource).build();
//    }
//
//    @Bean
//    public Job importPersonaJob(JobListener listener, Step step) {
//        return jobBuilderFactory.get("importPersonaJob")
//                .incrementer(new RunIdIncrementer())
//                .listener(listener)
//                .flow(step).end().build();
//    }
//
//    @Bean
//    public Step step(JdbcBatchItemWriter<Persona> writerPerson) {
//        return stepBuilderFactory.get("step")
//                .<Persona, Persona> chunk(10)
//                .reader(readerPerson())
//                .processor(processorPerson())
//                .writer(writerPerson)
//                .build();
//    }

    /*** LOG-ITEM FLOW ***/

    @Bean
    public FlatFileItemReader<FieldSet> readerLog() {

        FlatFileItemReader<FieldSet> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data_log.csv"));
        reader.setLineMapper(new DefaultLineMapper() {{
            setLineTokenizer(new DelimitedLineTokenizer());
            setFieldSetMapper(new PassThroughFieldSetMapper() {{}});
        }});
        return reader;
    }

    @Bean
    public LogItemProcessor processorLog() {
        return new LogItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<LogItem> writerLog(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<LogItem>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO persona (nombre, apellido, telefono) VALUES (:nombre, :apellido, :telefono)")
                .dataSource(dataSource).build();
    }

    @Bean
    public Job importLogJob(JobListener listener, Step step) {
        return jobBuilderFactory.get("importLogJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step).end().build();
    }

    @Bean
    public Step stepLog(JdbcBatchItemWriter<LogItem> writerLog) {
        return stepBuilderFactory.get("stepLog")
                .<FieldSet, LogItem> chunk(10)
                .reader(readerLog())
                .processor(processorLog())
                .writer(writerLog)
                .build();
    }
}
