package ru.job4j.exam;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class VacanciesBase {

    private static final Properties initData = new Properties();
    private static SiteScan siteScan;
    protected static final Logger Log = LoggerFactory.getLogger(VacanciesBase.class);

    public VacanciesBase(SiteScan siteScan) {
        this.siteScan = siteScan;
    }

    private void init() {
        try (InputStream in = VacanciesBase.class.getClassLoader().getResourceAsStream("app_v.properties")) {
            initData.load(in);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    public void fishing() {
        init();
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(VacanciesBase.WebToSQL.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(initData.getProperty("cron.time")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
        }
    }

    public static class WebToSQL implements Job {
        @Override
        public void execute(JobExecutionContext context) {
            VacanciesToSql toSql = new VacanciesToSql(initData, siteScan.getFromSite());
            toSql.transfer();
        }
    }

    public static void main(String[] args) {
        VacanciesBase vb = new VacanciesBase(new SqlRuScan());
        vb.fishing();
    }
}