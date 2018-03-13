package anddd7.springboot.service.impl;

import anddd7.springboot.dao.MessageMapper;
import anddd7.springboot.domain.Message;
import anddd7.springboot.service.MessageService;
import anddd7.springboot.utils.IDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by edliao on 2017/5/3.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageMapper mapper;
    @Autowired
    IDGenerator idGenerator;


    @Override
    public void save(Message message) {
        message.setMsgId(BigDecimal.valueOf(idGenerator.calId("MESSAGE_INFO").current));
        mapper.insert(message);
    }

    @Override
    public void update(Message message) {
        mapper.updateByPrimaryKeySelective(message);
    }

    @Override
    public void delete(Long msgId) {
        mapper.deleteByPrimaryKey(BigDecimal.valueOf(msgId));
    }

    @Override
    public Message selectByPrimary(Long msgId) {
        return mapper.selectByPrimaryKey(BigDecimal.valueOf(msgId));
    }

    @Override
    public List<Message> selectByExample(Message message, Long startIndex, Long pageNum) {
        return mapper.selectByExample(message, startIndex, pageNum);
    }

    @Override
    public Long selectCountByExample(Message message) {
        return mapper.selectCountByExample(message);
    }
}
