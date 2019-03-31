package org.firstinspires.ftc.teamcode;

import android.content.Context;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@TeleOp(name="file read test", group="alpha")
/**
 * @author Andrew, Error 404: Team Name Not Found
 * @see OpMode
 */
public class FileRead extends OpMode
{
    String fileName = "MyFile";
    String content = "hellowww world";
    BufferedReader br = null;
    String line = "null";

    public void init()
    {
//        try
//        {
//            FileOutputStream outputStream = null;
//            outputStream = hardwareMap.appContext.openFileOutput(fileName, Context.MODE_PRIVATE);
//            outputStream.write(content.getBytes());
//            outputStream.close();
//
//        }
//        catch(FileNotFoundException f)
//        {
//            f.printStackTrace();
//            telemetry.addData("file not found f: ", f);
//        }
//        catch(IOException f)
//        {
//            f.printStackTrace();
//            telemetry.addData("io exception f: ", f);
//        }

        try
        {
//                     InputStream inputStream = hardwareMap.appContext.openFileInput("textTest.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            File theFile = new File("/storage/9016-4EF8/textTest.txt");
            FileReader inputStreamReader = new FileReader(theFile);

            br = new BufferedReader(inputStreamReader);
            telemetry.addData("bf: ", br);
            while((line = br.readLine()) != null)
            {
                telemetry.addData("line: ", line);
            }
            for(String f : hardwareMap.appContext.fileList())
            {
                telemetry.addData("file list: ", f);
            }
            telemetry.addData("get file dir: ", hardwareMap.appContext.getFilesDir());

        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            telemetry.addData("file not found e: ", e);
        }
        catch(IOException e)
        {
            e.printStackTrace();
            telemetry.addData("io exception: ", e);
        }
        finally
        {
            if(br != null)
            {
                try
                {
                    br.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start()
    {

    }

    public void loop()
    {

    }

    public void stop()
    {

    }
}
