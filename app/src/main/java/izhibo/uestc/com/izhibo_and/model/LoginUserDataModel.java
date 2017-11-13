package izhibo.uestc.com.izhibo_and.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 登陆者的数据，有用户名和所关注的主播的列表数据
 * Created by dongfanghong on 2017/10/19.
 */

public class LoginUserDataModel implements Serializable {
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<AnchorAttentionModel> getAnchorAttentionList() {
        return anchorAttentionList;
    }

    public void setAnchorAttentionList(ArrayList<AnchorAttentionModel> anchorAttentionList) {
        this.anchorAttentionList = anchorAttentionList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
    private String userName;
    private ArrayList<AnchorAttentionModel> anchorAttentionList;

}
