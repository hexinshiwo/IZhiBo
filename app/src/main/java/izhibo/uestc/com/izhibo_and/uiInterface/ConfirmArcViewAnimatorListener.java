package izhibo.uestc.com.izhibo_and.uiInterface;

import izhibo.uestc.com.izhibo_and.widget.ConfirmArcView;

/**
 * ConfirmArcView的动画回调接口
 * Created by dongfanghong on 2017/11/3.
 */

public interface ConfirmArcViewAnimatorListener {
    //动画开始
    void onAnimationStart(ConfirmArcView confirmArcView);

    //动画结束
    void onAnimationEnd(ConfirmArcView confirmArcView);

    abstract class ConfirmViewListenerAdapter implements ConfirmArcViewAnimatorListener {
        @Override
        public void onAnimationStart(ConfirmArcView confirmArcView) {

        };

        @Override
        public void onAnimationEnd(ConfirmArcView confirmArcView) {

        };
    }
}
