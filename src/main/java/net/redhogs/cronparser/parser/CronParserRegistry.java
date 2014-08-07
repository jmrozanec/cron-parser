package net.redhogs.cronparser.parser;

import net.redhogs.cronparser.CronType;

import java.util.HashMap;
import java.util.Map;

public class CronParserRegistry {
    private Map<CronType, CronParser> registry;

    private CronParserRegistry(){
        registry = new HashMap<CronType, CronParser>();
        register(CronType.CRON4J, cron4jParser());
        register(CronType.QUARTZ, quartzParser());
        register(CronType.UNIX, unixCrontabParser());
    }

    private CronParser cron4jParser(){
        return ParserDefinitionBuilder.defineParser()
                .withMinutes()
                .withHours()
                .withDayOfMonth()
                .withMonth()
                .withDayOfWeek()
                .instance();
    }

    private CronParser unixCrontabParser(){
        return ParserDefinitionBuilder.defineParser()
                .withMinutes()
                .withHours()
                .withDayOfMonth()
                .withMonth()
                .withDayOfWeek()
                .instance();
    }

    private CronParser quartzParser(){
        return ParserDefinitionBuilder.defineParser()
                .withSeconds()
                .withMinutes()
                .withHours()
                .withDayOfMonth()
                .withMonth()
                .withDayOfWeek()
                .withYear()
                .andLastFieldOptional()
                .instance();
    }

    public CronParserRegistry register(CronType cronType, CronParser parser){
        registry.put(cronType, parser);
        return this;
    }

    public CronParser retrieveParser(CronType cronType){
        return registry.get(cronType);
    }

    public static CronParserRegistry instance(){
        return new CronParserRegistry();
    }
}
