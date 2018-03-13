package anddd7.springboot.service.impl;

import anddd7.springboot.dao.SysUserMapper;
import anddd7.springboot.domain.SysUser;
import anddd7.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by AnDong on 2017/2/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser selectUser(Long id) {
        return sysUserMapper.selectByPrimaryKey(BigDecimal.valueOf(id));
    }

    @Override
    public Integer selectUserCountByCond(SysUser example) {
        return sysUserMapper.selectCountByExample(example);
    }

    @Override
    public List<SysUser> selectUserByCond(SysUser example, Integer start, Integer size) {
        return sysUserMapper.selectByExample(example, start, size);
    }
}
