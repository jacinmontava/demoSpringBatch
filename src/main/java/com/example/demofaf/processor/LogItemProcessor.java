package com.example.demofaf.processor;

import com.example.demofaf.model.LogItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;

import java.sql.Time;
import java.util.Date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogItemProcessor implements ItemProcessor<FieldSet, LogItem> {

    private static final Logger LOG = LoggerFactory.getLogger(LogItemProcessor.class);

    @Override
    public LogItem process(FieldSet item) throws Exception {
        System.out.println("ITEM : " + item);
        final String regex = ".+?(?=\\[)\\[(.+?(?=\\:))\\:(.+?(?=\\s?\\+)).+?(?=\\\")\\\"(.+?(?=\\s?\\/))\\s?(.+?(?=\\\")).+?(?=\\:)\\:\\s?(.+?(?=\\]))";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(item.toString());
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                System.out.println("Group " + i + ": " + matcher.group(i));
                LogItem logItemNow = new LogItem();
                logItemNow.setJO002SERVIDOR("server_nose");
                String result = matcher.group(i);
                switch (i){
                    /*case 1: DateFormat formatterD = new SimpleDateFormat("dd/MM/yyyy");
                        Date date = (Date) formatterD.parse(result);
                        logItemNow.setJO002FECHA(date);
                        break;*/
                    /*case 2: DateFormat formatter = new SimpleDateFormat("HH:mm");
                        Time t = (Time) formatter.parse(result);
                        logItemNow.setJO002HORA(t);
                        break;*/
                    case 3: logItemNow.setJO002TIPO(result);
                        break;
                    case 4: logItemNow.setJO002OPERACION(result);
                        break;
                    case 5: double valueNumber = Double.parseDouble(result);
                        logItemNow.setJO002TIEMPOMEDIO(valueNumber);
                        break;
                }
            }
        }
        return null;
    }
}
