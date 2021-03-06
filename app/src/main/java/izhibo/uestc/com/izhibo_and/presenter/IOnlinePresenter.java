package izhibo.uestc.com.izhibo_and.presenter;

import javax.inject.Inject;

import izhibo.uestc.com.izhibo_and.component.DaggerUserInfoComponent;
import izhibo.uestc.com.izhibo_and.model.UserInfoModel;
import izhibo.uestc.com.izhibo_and.module.UserInfoModule;
import izhibo.uestc.com.izhibo_and.uiInterface.IOnlineView;

/**
 * Created by dongfanghong on 2017/10/10.
 */

public class IOnlinePresenter {
    private IOnlineView iOnlineView;
    @Inject
    UserInfoModel userInfoModel;

    public IOnlinePresenter(IOnlineView iOnlineView) {
        this.iOnlineView = iOnlineView;
       DaggerUserInfoComponent.builder().userInfoModule(new UserInfoModule()).build().inject(this);

    }

    public void saveRoomNumberData(int roomNumber) {
        userInfoModel.setRoomNumber(roomNumber);
        iOnlineView.changeUiStatue();

    }
}
