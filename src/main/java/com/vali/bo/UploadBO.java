package com.vali.bo;

/**
 * Created by vali on 15-9-20.
 */
public class UploadBO {

    private static String tempPath ="/upload/tempUpload";

    private static String realPath = "/upload/realUpload";

    public static String getTempPath(){
        return tempPath;
    }

    public static String getRealPath(){
        return realPath;
    }

}
