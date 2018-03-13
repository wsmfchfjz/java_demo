package anddd7.springboot.dao;

import anddd7.springboot.domain.SysUserLogin;

public interface SysUserLoginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserLogin record);

    int insertSelective(SysUserLogin record);

    SysUserLogin selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUserLogin record);

    int updateByPrimaryKey(SysUserLogin record);
}