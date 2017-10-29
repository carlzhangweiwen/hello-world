package com.autumnsinger.core.task;

import com.autumnsinger.common.enums.AtomicTaskEnum;
import com.autumnsinger.common.enums.TaskStatusEnum;
import com.autumnsinger.core.service.AtomicTaskService;
import com.autumnsinger.core.service.JijinSpiderService;
import com.autumnsinger.core.service.TianTianSpiderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Created by carl on 17-7-22.
 */
@Component
public class JijinTask {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    JijinSpiderService jijinSpiderService;

    @Autowired
    TianTianSpiderService tianTianSpiderService;

    @Autowired
    AtomicTaskService atomicTaskService;


    @Scheduled(cron="* 0/10 *  * * ? ")//每60mins执行一次
    public void getJijinDailyData(){
        logger.info("task start....");
        Random random = new Random();
        String yesterDay = LocalDate.now().minusDays(random.nextInt(100))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String taskName = AtomicTaskEnum.JIJIN_DAILY_TASK.name();
        String taskNo = yesterDay;
        if(atomicTaskService.startTask(taskName, taskNo)){
                jijinSpiderService.spider(yesterDay);
                atomicTaskService.updateTaskStatus(taskName,taskNo,
                        TaskStatusEnum.SUCCESS, TaskStatusEnum.PROCESSING);
        }

        logger.info("task end....");
    }

    @Scheduled(cron="* 0/10 *  * * ? ")//每1day执行一次
    public void getJijinMonthlyData(){
        logger.info("task start....");
        Random random = new Random();
        LocalDate date = LocalDate.now().minusMonths(random.nextInt(100));
        int year = date.getYear();
        String monthValue = String.format("%02d", date.getMonthValue());//補2位,eg.02,10
        String taskName = AtomicTaskEnum.JIJIN_MONTHLY_TASK.name();
        String taskNo = String.format("%s-%s", year, monthValue);
        if(atomicTaskService.startTask(taskName, taskNo)){
                jijinSpiderService.spiderMonth(String.valueOf(year), monthValue);
                //更改狀態爲成功
                atomicTaskService.updateTaskStatus(taskName,taskNo,
                        TaskStatusEnum.SUCCESS, TaskStatusEnum.PROCESSING);

        }
        logger.info("task end....");
    }

    @Scheduled(cron="* 0/10 *  * * ? ")//每60mins执行一次
    public void getTiantianJijinDailyData(){
        logger.info("task start....");
        Random random = new Random();
        String yesterDay = LocalDate.now().minusDays(random.nextInt(100))
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String taskName = AtomicTaskEnum.TIANTIAN_JIJIN_DAILY_TASK.name();
        String taskNo = yesterDay;
        if(atomicTaskService.startTask(taskName, taskNo)){
            tianTianSpiderService.spider(yesterDay,yesterDay);
            atomicTaskService.updateTaskStatus(taskName,taskNo,
                    TaskStatusEnum.SUCCESS, TaskStatusEnum.PROCESSING);
        }

        logger.info("task end....");
    }


}
