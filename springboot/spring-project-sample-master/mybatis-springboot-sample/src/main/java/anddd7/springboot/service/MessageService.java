package anddd7.springboot.service;

import anddd7.springboot.domain.Message;

import java.util.List;

/**
 * Created by edliao on 2017/5/3.
 */
public interface MessageService {
    void save(Message message);

    void update(Message message);

    void delete(Long msgId);

    Message selectByPrimary(Long msgId);

    List<Message> selectByExample(Message message, Long startIndex, Long pageNum);

    Long selectCountByExample(Message message);
}
