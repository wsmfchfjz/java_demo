package anddd7.springboot.service;

import anddd7.springboot.domain.SysUser;

import java.util.List;

/**
 * Created by AnDong on 2017/2/8.
 */
public interface UserService {
    SysUser selectUser(Long id);

    Integer selectUserCountByCond(SysUser example);
    List<SysUser> selectUserByCond(SysUser example, Integer start, Integer size);
}
