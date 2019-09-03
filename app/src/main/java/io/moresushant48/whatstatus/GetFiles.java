package io.moresushant48.whatstatus;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

public class GetFiles {

    private String path = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/.Statuses/";

    public Uri[] getFiles(Context context){

        File dir = new File(path);
        File[] files = dir.listFiles();
        Uri[] fileNames = new Uri[files.length];

        if (fileNames.length > 0) {
            for(int i=0; i < files.length; i++){
                fileNames[i] = Uri.parse(path + files[i].getName());
                System.out.println(fileNames[i]);
            }
        } else
            Toast.makeText(context, "No statuses are available.", Toast.LENGTH_LONG).show();

        return fileNames;
    }

}