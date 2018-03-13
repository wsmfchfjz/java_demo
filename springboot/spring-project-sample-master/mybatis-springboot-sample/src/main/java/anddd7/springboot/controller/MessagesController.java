package anddd7.springboot.controller;

import anddd7.springboot.controller.bean.ResponseListWrapper;
import anddd7.springboot.controller.bean.ResponseWrapper;
import anddd7.springboot.controller.common.ResponseEnum;
import anddd7.springboot.domain.Message;
import anddd7.springboot.service.MessageService;
import anddd7.springboot.utils.IDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edliao on 2017/5/3.
 */

@Controller
@RequestMapping("/oraclejet/messages")
public class MessagesController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageService messageService;
    @Autowired
    IDGenerator idGenerator;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    ResponseListWrapper getList(@RequestParam(name = "offset", required = false) Long startIndex,
                                @RequestParam(name = "limit", required = false) Long pageNum,
                                @RequestParam(name = "fromID", required = false) Long fromID) {

        if (fromID != null) {
            List result = new ArrayList();
            result.add(messageService.selectByPrimary(fromID));
            return new ResponseListWrapper(1L, result);
        }

        List result = messageService.selectByExample(new Message(), startIndex, pageNum);
        Long resultCount = messageService.selectCountByExample(new Message());

        return new ResponseListWrapper(resultCount, result);
    }

    @RequestMapping(value = "/get/{msgId}", method = RequestMethod.GET)
    @ResponseBody
    ResponseWrapper get(@PathVariable Long msgId) throws Exception {
        Message result = messageService.selectByPrimary(msgId);

        return new ResponseWrapper(ResponseEnum.success, result);
    }

    @RequestMapping(value = "/del/{msgId}", method = RequestMethod.GET)
    @ResponseBody
    ResponseWrapper del(@PathVariable Long msgId) throws Exception {
        messageService.delete(msgId);

        return new ResponseWrapper(ResponseEnum.success);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    ResponseWrapper save(@RequestBody Message message) throws Exception {

        if (message.getMsgId() != null) {
            messageService.update(message);
        } else {
            messageService.save(message);
        }

        return new ResponseWrapper(ResponseEnum.success, message.getMsgId());
    }

    @RequestMapping(value = "/getID", method = RequestMethod.GET)
    @ResponseBody
    Long getID() throws Exception {
        return idGenerator.calId("MESSAGE_INFO").current;
    }

}
