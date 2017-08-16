/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.gms.samples.vision.ocrreader;

import android.app.Activity;
import android.content.Context;
import android.media.RingtoneManager;
import android.os.Vibrator;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.samples.vision.ocrreader.ui.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import static android.R.id.button1;

/**
 * A very simple Processor which receives detected TextBlocks and adds them to the overlay
 * as OcrGraphics.
 */
public class OcrDetectorProcessor extends Activity implements Detector.Processor<TextBlock> {


    private GraphicOverlay<OcrGraphic> mGraphicOverlay;
    //Button button = (Button) findViewById(R.id.button3);
    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        mGraphicOverlay = ocrGraphicOverlay;


    }

    /**
     * Called by the detector to deliver detection results.
     * If your application called for it, this could be a place to check for
     * equivalent detections by tracking TextBlocks that are similar in location and content from
     * previous frames, or reduce noise by eliminating TextBlocks that have not persisted through
     * multiple detections.
     */
    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        mGraphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();

        /*for (int k = 0; k < items.size(); k++) {
            TextBlock iitem = items.valueAt(k);
            if (iitem.getValue().equalsIgnoreCase("stop")) {
                Toast.makeText(this, "Stop Sign Detected", Toast.LENGTH_SHORT).show();
            }
        }*/

        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            /*if(items.valueAt(i).getValue().equalsIgnoreCase("stop")){
                Toast.makeText(this, "Stop Sign Detected", Toast.LENGTH_SHORT).show();
                *//*Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(400);*//*
            }*/
            OcrGraphic graphic = new OcrGraphic(mGraphicOverlay, item);
            mGraphicOverlay.add(graphic);



        }
    }



    /**
     * Frees the resources associated with this detection processor.
     */
    @Override
    public void release() {
        mGraphicOverlay.clear();
    }
}
