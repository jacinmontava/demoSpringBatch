package com.example.demofaf.processor;

import com.example.demofaf.model.LogItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogItemProcessor implements ItemProcessor<FieldSet, LogItem> {

    private static final Logger LOG = LoggerFactory.getLogger(LogItemProcessor.class);

    @Override
    public LogItem process(FieldSet item) throws Exception {
        LOG.info("ITEM : " + item);
        final String regex = ".+?(?=\\[)\\[(.+?(?=\\:))\\:(.+?(?=\\s?\\+)).+?(?=\\\")\\\"(.+?(?=\\s?\\/))\\s?(.+?(?=\\\")).+?(?=\\:)\\:\\s?(.+?(?=\\]))";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(item.toString());
        LogItem logItemNow = new LogItem();
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                LOG.info("Group " + i + ": " + matcher.group(i));
                logItemNow.setJO002SERVIDOR("server_nosequees");
                String result = matcher.group(i);
                switch (i) {
                    case 1:
                        DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd/MMM/yyyy", Locale.ENGLISH);
                        LocalDate date = LocalDate.parse(result, formatterD);
                        ZoneId defaultZoneId = ZoneId.systemDefault();
                        Date dateConvert = Date.from(date.atStartOfDay(defaultZoneId).toInstant());
                        logItemNow.setJO002FECHA(dateConvert);
                        break;
                    case 2:
                        logItemNow.setJO002HORA(result);
                        break;
                    case 3:
                        logItemNow.setJO002TIPO(result);
                        break;
                    case 4:
                        logItemNow.setJO002OPERACION(result);
                        break;
                    case 5:
                        double valueNumber = Double.parseDouble(result);
                        logItemNow.setJO002TIEMPOMEDIO(valueNumber);
                        break;
                }
            }
            LOG.info("Object created: " + logItemNow.toString());
        }
        return logItemNow;
    }
}
