package com.heima.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.heima.apis.schedule.IScheduleClient;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.TaskTypeEnum;
import com.heima.model.schedule.dtos.Task;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.utils.common.JdkSerializeUtil;
import com.heima.utils.common.ProtostuffUtil;
import com.heima.wemedia.service.WmNewsAutoScanService;
import com.heima.wemedia.service.WmNewsTaskService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@Service
public class WmNewsTaskServiceImpl implements WmNewsTaskService {

    @Autowired
    private IScheduleClient scheduleClient;

    @Autowired
    private WmNewsAutoScanService wmNewsAutoScanService;

    /**
     * 添加任务到延迟队列中
     * @param id 文章id
     * @param publishTime 发布时间——任务执行时间
     */
    @Async
    @Override
    public void addNewsToTask(Integer id, Date publishTime) {
        log.info("添加任务到延迟服务中----begin");
        Task task = new Task();
        task.setExecuteTime(publishTime.getTime());
        task.setTaskType(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType());
        task.setPriority(TaskTypeEnum.NEWS_SCAN_TIME.getPriority());

        WmNews wmNews = new WmNews();
        wmNews.setId(id);
        task.setParameters(JdkSerializeUtil.serialize(wmNews));
        scheduleClient.addTask(task);
        log.info("添加任务到延迟服务中----end");
    }

    /**
     * 消费延迟队列数据——固定频率
     */
    @Scheduled(fixedRate = 5000)
    @Override
    @SneakyThrows
    public void scanNewsByTask() {
        log.info("文章审核---消费任务执行---begin---");

        ResponseResult responseResult = scheduleClient.poll(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType(), TaskTypeEnum.NEWS_SCAN_TIME.getPriority());
        log.info("文章审核---消费任务执行---responseResult:"+responseResult.getCode()+"---"+responseResult.getData());
        if(responseResult.getCode().equals(200) && responseResult.getData() != null){
            String json_str = JSON.toJSONString(responseResult.getData());
            Task task = JSON.parseObject(json_str, Task.class);
            byte[] parameters = task.getParameters();

            WmNews wmNews = JdkSerializeUtil.deserialize(parameters, WmNews.class);
            wmNewsAutoScanService.autoScanWmNews(wmNews.getId());
        }
        log.info("文章审核---消费任务执行---end---");
    }
}
