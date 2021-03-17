package com.example.demofaf.listener;

import com.example.demofaf.model.LogItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobListener extends JobExecutionListenerSupport {

    private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JobListener(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        super.afterJob(jobExecution);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("Finalizó el job");
            jdbcTemplate
                    .query("SELECT JO002SERVIDOR, JO002OPERACION, JO002FECHA, JO002HORA, " +
                                    "JO002TIPO, JO002TIEMPOMEDIO, JO002PETICIONES FROM JO002CPUDIARIO",
                            (rs, row) -> new LogItem(rs.getString(1),
                                    rs.getString(2),
                                    rs.getDate(3),
                                    rs.getString(4),
                                    rs.getString(5),
                                    rs.getDouble(6),
                                    rs.getInt(7)))
                    .forEach(logItem -> LOG.info("Registro: " + logItem));
        }
    }
}
