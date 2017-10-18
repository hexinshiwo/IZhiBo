package izhibo.uestc.com.izhibo_and.modle;

/**
 * Created by dongfanghong on 2017/10/17.
 */

public class BaseHttpModel {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int code;
    private String data;
    private String msg;
}
