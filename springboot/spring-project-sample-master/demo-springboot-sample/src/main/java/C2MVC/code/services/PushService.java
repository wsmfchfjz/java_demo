package C2MVC.code.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;

@Service
public class PushService {
    /**
     * @DeferredResult 是用来实现异步请求的(业务逻辑耗时很长)
     * 原servlet流程: request->servlet.service()->执行业务逻辑(servlet阻塞)->response
     * 新的servlet流程: request->创建子线程执行业务逻辑->servlet结束(但不反悔response)->子线程结束返回response(子线程中有req,res)
     */
    DeferredResult<String> deferredResult;

    /**
     * 方法创建了一个新的DeferredResult 并直接返回 ,servlet接受到这个result过后就结束任务并返回线程池
     * 而request和response移交到了DeferredResult内 ,待setResult后 ,才会返回
     */
    public DeferredResult<String> getAysncUpdate() {
        deferredResult = new DeferredResult();
        return deferredResult;
    }

    @Scheduled(fixedDelay = 3000)
    public void refresh() {
        if(deferredResult!=null){
            deferredResult.setResult(String.valueOf(System.currentTimeMillis()));
        }
    }
}
