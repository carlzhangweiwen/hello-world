package com.autumnsinger.core.service;

import com.autumnsinger.common.enums.TaskStatusEnum;
import com.autumnsinger.dal.mapper.AtomicTaskMapper;
import com.autumnsinger.dal.model.AtomicTask;
import com.autumnsinger.dal.model.AtomicTaskExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by carl on 17-7-24.
 */
@Component
public class AtomicTaskService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    AtomicTaskMapper atomicTaskMapper;

    /**
     * 開始一個任務
     * @param taskName
     * @param taskNo
     * @return
     */
    public boolean startTask(String taskName, String taskNo){
        try{
            logger.info("開始創建原子任務,taskName:{},taskNo:{}", taskName, taskNo);
            AtomicTaskExample example = new AtomicTaskExample();
            example.createCriteria().andTaskNameEqualTo(taskName)
                    .andTaskNoEqualTo(taskNo);
            if(atomicTaskMapper.countByExample(example) > 0){
                logger.info("已存在的原子任務,taskName:{},taskNo:{}", taskName, taskNo);;
                return false;
            }

            AtomicTask atomicTask = new AtomicTask();
            atomicTask.setTaskName(taskName);
            atomicTask.setTaskNo(taskNo);
            atomicTask.setStatus(TaskStatusEnum.PROCESSING.name());
            return atomicTaskMapper.insertSelective(atomicTask) == 1;
        }catch (Exception e){
            logger.error("創建原子任務失敗：{}", e.getMessage());
            return false;
        }

    }

    /**
     * 更改 任務狀態
     * @param taskName
     * @param taskNo
     * @param newStatusEnum 新狀態
     * @param oldStatusEnum 作爲數據庫乐观鎖
     * @return
     */
    public boolean updateTaskStatus(String taskName, String taskNo
            , TaskStatusEnum newStatusEnum
            , TaskStatusEnum oldStatusEnum){
        AtomicTask atomicTask = new AtomicTask();
        atomicTask.setStatus(newStatusEnum.name());
        AtomicTaskExample example = new AtomicTaskExample();
        example.createCriteria().andTaskNameEqualTo(taskName)
                .andTaskNoEqualTo(taskNo)
                .andStatusEqualTo(oldStatusEnum.name());
        return atomicTaskMapper.updateByExampleSelective(atomicTask,example) == 1;
    }
}
