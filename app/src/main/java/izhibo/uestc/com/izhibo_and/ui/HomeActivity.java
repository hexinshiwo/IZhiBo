package izhibo.uestc.com.izhibo_and.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import izhibo.uestc.com.izhibo_and.R;

/**
 * Created by dongfanghong on 2017/10/8.
 */

public class HomeActivity extends AppCompatActivity {
    private Button iOnLiveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        iOnLiveBtn=(Button)findViewById(R.id.btn_go_iOnLive);
        iOnLiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,IOnLiveActivity.class);
                startActivity(intent);
            }
        });
    }
}

