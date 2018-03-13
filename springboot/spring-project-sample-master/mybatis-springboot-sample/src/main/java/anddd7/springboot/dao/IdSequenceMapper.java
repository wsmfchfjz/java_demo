package anddd7.springboot.dao;

import anddd7.springboot.domain.IdSequence;

import java.util.List;

public interface IdSequenceMapper {
    int deleteByPrimaryKey(String tableName);

    int insert(IdSequence record);

    int insertSelective(IdSequence record);

    IdSequence selectByPrimaryKey(String tableName);

    int updateByPrimaryKeySelective(IdSequence record);

    int updateByPrimaryKey(IdSequence record);

    //
    List<IdSequence> selectAll();

}