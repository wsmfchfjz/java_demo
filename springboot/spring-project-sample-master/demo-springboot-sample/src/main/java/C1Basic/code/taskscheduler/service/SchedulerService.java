package C1Basic.code.taskscheduler.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SchedulerService {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime(){
        System.out.println("每5秒执行,当前时间:"+sdf.format(new Date()));
    }


    @Scheduled(cron = "0 0/5 * * * ? ")
    public void fixedTimeExecution(){
        System.out.println("定时执行:"+sdf.format(new Date()));
    }
}
