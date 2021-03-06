package izhibo.uestc.com.izhibo_and.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.core.ILiveLoginManager;
import com.tencent.ilivesdk.core.ILiveRoomManager;
import com.tencent.ilivesdk.view.AVRootView;
import com.tencent.livesdk.ILVLiveManager;
import com.tencent.livesdk.ILVLiveRoomOption;

import izhibo.uestc.com.izhibo_and.R;
import izhibo.uestc.com.izhibo_and.model.Constants;
import izhibo.uestc.com.izhibo_and.presenter.IOnlinePresenter;
import izhibo.uestc.com.izhibo_and.uiInterface.IOnlineView;
import izhibo.uestc.com.izhibo_and.widget.CommonUtils;

/**
 * Created by dongfanghong on 2017/10/9.
 */

public class IOnLiveActivity extends AppCompatActivity implements View.OnClickListener,IOnlineView {
    private AVRootView avRootView;
    private Button createRoomBtn;
    private EditText roomNumberEdit;
    private IOnlinePresenter iOnlinePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ionlive);
        avRootView = (AVRootView) findViewById(R.id.av_root);
        createRoomBtn = (Button) findViewById(R.id.btn_go_iOnLive);
        roomNumberEdit = (EditText) findViewById(R.id.edit_room_number);
        ILVLiveManager.getInstance().setAvVideoView(avRootView);
        iOnlinePresenter=new IOnlinePresenter(this);
        avRootView.setAutoOrientation(false);
        //打开摄像头预览
        avRootView.setSubCreatedListener(new AVRootView.onSubViewCreatedListener() {
            @Override
            public void onSubViewCreated() {
                ILiveRoomManager.getInstance().enableCamera(ILiveConstants.FRONT_CAMERA, true);
            }
        });
        createRoomBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go_iOnLive:
                createRoom();
                break;

        }
    }

    private void createRoom() {
        final int roomId = CommonUtils.getStringToInt(roomNumberEdit.getText().toString(), -1);
        if (-1 == roomId) {
            //异常处理
        }

        ILVLiveRoomOption option = new ILVLiveRoomOption(ILiveLoginManager.getInstance().getMyUserId())
                .autoCamera(ILiveConstants.NONE_CAMERA != ILiveRoomManager.getInstance().getActiveCameraId())
                .videoMode(ILiveConstants.VIDEOMODE_NORMAL)
                .controlRole(Constants.ROLE_MASTER)
                .autoFocus(true);
        ILVLiveManager.getInstance().createRoom(roomId,
                option, new ILiveCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        afterCreate(roomId);
                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {
                        if (module.equals(ILiveConstants.Module_IMSDK) && 10021 == errCode) {
                            // 被占用，改加入

                        } else {

                        }
                    }
                });
    }
    //保存相关数据
    private void afterCreate(int roomNumber) {
   iOnlinePresenter.saveRoomNumberData(roomNumber);

    }

    @Override
    public void changeUiStatue() {
        roomNumberEdit.setEnabled(false);
        createRoomBtn.setVisibility(View.INVISIBLE);
       //ui状态的改变
    }
}
