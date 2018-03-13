package C3Boot.code.beans;

/**
 * Created by edliao on 2017/5/8.
 */
public class TransResponse {
    private String responseMsg;

    public TransResponse(String responseMsg){
        this.responseMsg = responseMsg;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }
}
