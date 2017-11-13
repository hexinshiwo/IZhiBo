package izhibo.uestc.com.izhibo_and.model;

import java.util.List;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class UserInfoModel {
    private String userAccount;

    private int roomNumber;

    private String userName;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    private List<AnchorAttentionModel> attentionAnchorList;//关注的主播列表

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<AnchorAttentionModel> getAttentionAnchorList() {
        return attentionAnchorList;
    }

    public void setAttentionAnchorList(List<AnchorAttentionModel> attentionAnchorList) {
        this.attentionAnchorList = attentionAnchorList;
    }

}
