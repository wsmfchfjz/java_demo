package anddd7.springboot.dao;

import anddd7.springboot.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SysUserMapper {
    int deleteByPrimaryKey(BigDecimal id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(BigDecimal id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    //
    List<SysUser> selectByExample(@Param("example") SysUser example, @Param("start") Integer start, @Param("size") Integer size);

    Integer selectCountByExample(@Param("example") SysUser example);
}