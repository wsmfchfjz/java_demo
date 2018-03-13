package anddd7.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Message {
    private BigDecimal msgId;

    private String userCode;

    private String userName;

    private String profileBgImg;

    private Date createTime;

    private String msgShort;

    public BigDecimal getMsgId() {
        return msgId;
    }

    public void setMsgId(BigDecimal msgId) {
        this.msgId = msgId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getProfileBgImg() {
        return profileBgImg;
    }

    public void setProfileBgImg(String profileBgImg) {
        this.profileBgImg = profileBgImg == null ? null : profileBgImg.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsgShort() {
        return msgShort;
    }

    public void setMsgShort(String msgShort) {
        this.msgShort = msgShort == null ? null : msgShort.trim();
    }
}