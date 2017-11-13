package izhibo.uestc.com.izhibo_and.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by dongfanghong on 2017/10/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseHttpModel extends BaseModel {

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;

}
