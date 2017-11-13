package izhibo.uestc.com.izhibo_and.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by dongfanghong on 2017/11/2.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseModel implements Serializable {
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int code;
    private String msg;
}
