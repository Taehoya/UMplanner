package com.cth.myapplication.presentation;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;

import com.cth.myapplication.R;
import com.cth.myapplication.application.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        copyDatabaseToDevice();
    }//onCreate

    public void buttonLookUpCourseOnClick(View view){
        HomeActivity.this.startActivity(new Intent(HomeActivity.this, CourseActivity.class));
    }//showLookUpCourseActivity

    public void buttonPlanCourseOnClick(View view){
        HomeActivity.this.startActivity(new Intent(HomeActivity.this, PlanOptionsActivity.class));
    }//showCompleteCourseActivity

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";
        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();
        try {
            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }
            copyAssetsToDirectory(assetNames, dataDirectory);
            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
    }//copyDatabaseToDevice

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();
        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;
            File outFile = new File(copyPath);
            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);
                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }//while
                out.close();
                in.close();
            }//if
        }//for
    }//copyAsetToDirectory


}//HomeActivity