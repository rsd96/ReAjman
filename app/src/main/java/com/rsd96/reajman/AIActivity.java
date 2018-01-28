package com.rsd96.reajman;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ibm.watson.developer_cloud.android.library.camera.CameraHelper;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ImageClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassifier;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by Ramshad on 1/27/18.
 */

public class AIActivity  extends AppCompatActivity {

    private VisualRecognition vrClient;
    private CameraHelper helper;
    private JSONObject object;
    private TextView detectedObjects;
    final StringBuffer output = new StringBuffer();
    //private ImageView preview;
    //Integer imageID;

    ProgressBar progressBar1;
//    int progress;
//
//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if(msg.what == 0){
//                if(progress < 100){
//                    progress++;
//                    progressBar1.setProgress(progress);
//                    progressBar2.setProgress(progress);
//                }
//            }
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai);

        vrClient = new VisualRecognition(
                VisualRecognition.VERSION_DATE_2016_05_20,
                getString(R.string.api_key)
        );

        helper = new CameraHelper(this);

        //preview = (ImageView) findViewById(R.id.preview);
        progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        //progressBar2 = (RingProgressBar) findViewById(R.id.progressBar2);

//        progress = 0;
//
//        progressBar1.setProgress(0);
//        progressBar2.setProgress(0);

//        progressBar1.setOnProgressListener(new RingProgressBar.OnProgressListener() {
//            @Override
//            public void progressToComplete() {
//                progressBar2.setVisibility(View.INVISIBLE);
//                //preview.setImageResource(R.mipmap.watsonimage);
//                //output.append("The item is: Recyclable.").append("\n").append("The item is of category: Can");
//            }
//        });
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 100; i++) {
//                    try{
//                        Thread.sleep(90);
//                        handler.sendEmptyMessage(0);
//                    } catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
    }

    public void takePicture(View view) {
        helper.dispatchTakePictureIntent();
    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CameraHelper.REQUEST_IMAGE_CAPTURE) {
            progressBar1.setVisibility(View.VISIBLE);
            final Bitmap photo = helper.getBitmap(resultCode);
            final File photoFile = helper.getFile(resultCode);
            final ImageView preview = (ImageView) findViewById(R.id.preview);
            preview.setImageBitmap(photo);

            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    VisualClassification response =
                            vrClient.classify(
                                    new ClassifyImagesOptions.Builder()
                                            .images(photoFile)
                                            .build()
                            ).execute();

                    ImageClassification classification = response.getImages().get(0);
                    VisualClassifier classifier = classification.getClassifiers().get(0);

                    Log.d("Object", "VS"+response);
//                    String [] rOutput = {"Recyclable", "Not Recyclable"};
//                    String finalOutput;

                    try {
                        object = new JSONObject(response.toString());
                        JSONArray newObject = object.getJSONArray("images");
                        JSONObject newObject1 = newObject.getJSONObject(0);
                        JSONArray newObject2 = newObject1.getJSONArray("classifiers");
                        JSONObject newObject3 = newObject2.getJSONObject(0);
                        JSONArray newObject4 = newObject3.getJSONArray("classes");

                        //JSONObject newObject5=newObject4.getJSONObject(index:0);
                        Log.d("Object", "VS"+newObject4.getString(1));

                        if(newObject4.getString(0)=="tin"|| newObject4.getString(0)=="beer can" ||newObject4.getString(0)=="can")
                        {

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar1.setVisibility(View.GONE);
                            preview.setImageResource(R.mipmap.watsonimage);
                        }
                    });

                }
            });
        }
    }
}

