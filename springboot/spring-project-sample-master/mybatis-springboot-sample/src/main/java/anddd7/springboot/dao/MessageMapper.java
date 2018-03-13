package anddd7.springboot.dao;

import anddd7.springboot.domain.Message;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MessageMapper {
    int deleteByPrimaryKey(BigDecimal msgId);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(BigDecimal msgId);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    //
    List<Message> selectByExample(@Param("example") Message example, @Param("startIndex") Long startIndex, @Param("pageNum") Long pageNum);

    Long selectCountByExample(Message message);
}