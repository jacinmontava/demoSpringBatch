package es.orizon.load.logs;

import es.orizon.load.logs.listener.JobListener;
import es.orizon.load.logs.model.LogItem;
import es.orizon.load.logs.processor.LogItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
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
    @Bean
    public FlatFileItemReader<FieldSet> reader() {
        FlatFileItemReader<FieldSet> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("data_log.csv"));
        reader.setLineMapper(new DefaultLineMapper() {{
            setLineTokenizer(new DelimitedLineTokenizer());
            setFieldSetMapper(new PassThroughFieldSetMapper() {{}});
        }});
        return reader;
    }

    @Bean
    public LogItemProcessor processor() {
        return new LogItemProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<LogItem> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<LogItem>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO JO002CPUDIARIO (JO002SERVIDOR, JO002OPERACION, JO002FECHA, " +
                        "JO002HORA, JO002TIPO, JO002TIEMPOMEDIO, JO002PETICIONES) " +
                        "VALUES (:JO002SERVIDOR, :JO002OPERACION, :JO002FECHA," +
                        ":JO002HORA, :JO002TIPO, :JO002TIEMPOMEDIO, :JO002PETICIONES)")
                .dataSource(dataSource).build();
    }

    @Bean
    public Job importJob(JobListener listener, Step step) {
        return jobBuilderFactory.get("importJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step).end().build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<LogItem> writerPerson) {
        return stepBuilderFactory.get("step")
                .<FieldSet, LogItem> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writerPerson)
                .build();
    }
}
