package izhibo.uestc.com.izhibo_and.modle;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class UserInfoModle {
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    private String userAccount;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    private int roomNumber;

}
