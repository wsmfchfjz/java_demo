package C3Boot.code.beans;

/**
 * Created by edliao on 2017/5/16.
 */
public class ChatRequest {
    String sender;
    String receiver;
    String msg;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
