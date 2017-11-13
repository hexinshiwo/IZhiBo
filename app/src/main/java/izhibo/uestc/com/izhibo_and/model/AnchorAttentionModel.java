package izhibo.uestc.com.izhibo_and.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 关注的主播
 * Created by dongfanghong on 2017/10/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnchorAttentionModel extends BaseHttpModel{
    public String getAnchorName() {
        return anchorName;
    }

    public void setAnchorName(String anchorName) {
        this.anchorName = anchorName;
    }

    public int getAnchorRoomNumber() {
        return anchorRoomNumber;
    }

    public void setAnchorRoomNumber(int anchorRoomNumber) {
        this.anchorRoomNumber = anchorRoomNumber;
    }
   //主播名
    private String anchorName;
    //主播的房间号
    private int anchorRoomNumber;
}
