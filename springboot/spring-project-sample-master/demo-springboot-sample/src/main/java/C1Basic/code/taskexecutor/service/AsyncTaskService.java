package C1Basic.code.taskexecutor.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Async //注解表明该方法是一个异步方法,注解在类上则表示类中所有方法都是异步的.
public class AsyncTaskService {
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务：" + i);
    }

    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("执行异步任务+1：" + (i + 1));
    }
}
