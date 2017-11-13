package izhibo.uestc.com.izhibo_and.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by dongfanghong on 2017/11/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginModel extends BaseModel{
    public LoginUserDataModel getData() {
        return data;
    }

    public void setData(LoginUserDataModel data) {
        this.data = data;
    }

    public LoginUserDataModel data;
}
