package izhibo.uestc.com.izhibo_and.widget;

/**
 * Created by dongfanghong on 2017/10/10.
 */

public class CommonUtils {
    public static int getStringToInt(String data,int defaultValue){
        try{
            return Integer.parseInt(data);
        }
        catch (NumberFormatException e)
        {
            return defaultValue;
        }

    }

}
