package com.androidatc.accountmanager;

import java.io.IOException;

/**
 * Created by LAB-INV-07 on 11/01/2017.
 */

public class GetNameInforeground extends AbstractGetNameTask {
    public GetNameInforeground(SplashActivity activity,String email,String scope){
        super(activity,email,scope);
    }
    protected  String fetchToken() throws IOException{
        try{
            return GoogleAuthUtil.getToken(mActivity,mEmail,mScope);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
