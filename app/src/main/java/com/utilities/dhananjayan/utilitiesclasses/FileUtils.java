package com.utilities.dhananjayan.utilitiesclasses;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by dhananjayan on 11/20/2015.
 */
public class FileUtils {

    //** Writing  file to Internal Storage **/

    public static void writeFileInternalStorage(String strWrite, Context context, String fileName) {
        try {
            // Check if Storage is Readable
            if (isSdReadable())   // isSdReadable()e method is define at bottom of the post
            {
                String smsfilename = fileName;
                FileOutputStream fos = context.openFileOutput(smsfilename, Context.MODE_PRIVATE);
                fos.write(strWrite.getBytes());
                fos.flush();
                fos.close();

            }
        } catch (Exception e) {
            // Your Code
        }
    }

    //** Write File to SD Card **/

    public static void writeFileOnSDCard(String strWrite, Context context, String fileName) {
        try {
            if (isSdReadable())   // isSdReadable()e method is define at bottom of the post
            {
                String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File myFile = new File(fullPath + File.separator + "/" + fileName);

                FileOutputStream fOut = new FileOutputStream(myFile);
                OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
                myOutWriter.append(strWrite);
                myOutWriter.close();
                fOut.close();
            }
        } catch (Exception e) {
            //do your stuff here
        }
    }

    //** Read file from Internal Stoarge */
    public static String readFileFromSDCard(String fileName, Context context) {
        String stringToReturn = "";
        try {
            if (isSdReadable())    // isSdReadable()e method is define at bottom of the post
            {
                String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "/" + fileName;
                InputStream inputStream = context.openFileInput(fullPath);
                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((receiveString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(receiveString);
                    }
                    inputStream.close();
                    stringToReturn = stringBuilder.toString();
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("TAG", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("TAG", "Can not read file: " + e.toString());
        }
        return stringToReturn;
    }


    //** Read File from SD Card **/
    public static String readFileInternalStorage(String fileName, Context context) {
        String stringToReturn = " ";
        try {
            if (isSdReadable())   // isSdReadable()e method is define at bottom of the post
            {
                String sfilename = fileName;
                InputStream inputStream = context.openFileInput(sfilename);

                if (inputStream != null) {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();

                    while ((receiveString = bufferedReader.readLine()) != null) {
                        stringBuilder.append(receiveString);
                    }
                    inputStream.close();
                    stringToReturn = stringBuilder.toString();
                }
            }
        } catch (FileNotFoundException e) {
            Log.e("TAG", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("TAG", "Can not read file: " + e.toString());
        }

        return stringToReturn;
    }

    //** Method to Check whether Storage is Readable */
    public static boolean isSdReadable() {
        boolean mExternalStorageAvailable = false;
        try {
            String state = Environment.getExternalStorageState();

            if (Environment.MEDIA_MOUNTED.equals(state)) {
                // We can read and write the media
                mExternalStorageAvailable = true;
                Log.i("isSdReadable", "External storage card is readable.");
            } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                // We can only read the media
                Log.i("isSdReadable", "External storage card is readable.");
                mExternalStorageAvailable = true;
            } else {
                // Something else is wrong. It may be one of many other
                // states, but all we need to know is we can neither read nor
                // write
                mExternalStorageAvailable = false;
            }
        } catch (Exception ex) {

        }
        return mExternalStorageAvailable;
    }
}
