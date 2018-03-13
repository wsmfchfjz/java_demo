package anddd7.springboot.controller.common;

/**
 * Created by AnDong on 2017/2/17.
 */
public enum ResponseEnum {
    success(200, "成功"), exception(500, "异常"), error_param(300, "参数错误");

    Integer code;
    String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
