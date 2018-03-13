package anddd7.springboot.controller;

import anddd7.springboot.controller.common.ResponseEnum;
import anddd7.springboot.controller.bean.ResponseListWrapper;
import anddd7.springboot.controller.bean.ResponseWrapper;
import anddd7.springboot.domain.SysUser;
import anddd7.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by AnDong on 2017/2/8.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/welcome")
    @ResponseBody
    String welcomeUser(Long id) {
        SysUser user = userService.selectUser(id);
        if (user == null) {
            return "Sorry ,we don't find you in our white list";
        }
        return "Welcome , " + user.getName() + " !";
    }

    @RequestMapping("/selectUserByCond")
    @ResponseBody
    ResponseWrapper<ResponseListWrapper<SysUser>> selectUserByCond(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestBody SysUser user) {
        if (pageNum < 1) {
            return new ResponseWrapper<>(ResponseEnum.error_param);
        }

        Integer totalCount = 0;
        if (pageNum == 1) {
            totalCount = userService.selectUserCountByCond(user);
        }
        List<SysUser> users = userService.selectUserByCond(user, (pageNum - 1) * pageSize, pageSize);

        ResponseListWrapper<SysUser> listWrapper = new ResponseListWrapper(Long.valueOf(totalCount),users);

        return new ResponseWrapper<>(ResponseEnum.success, listWrapper);
    }



}
