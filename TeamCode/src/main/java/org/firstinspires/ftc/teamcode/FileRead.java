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
import java.util.Arrays;

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
        try
        {
            RobotAction myAction;
            File theFile = new File("/storage/9016-4EF8/auto.csv");
            FileReader inputStreamReader = new FileReader(theFile);

            br = new BufferedReader(inputStreamReader);
            while((line = br.readLine()) != null)
            {
                // Get a line from the CSV file and split it on the commas
                String[] theItems = line.split(",");

                // Grab the first one... this will be the type of RobotAction to create
                String type = theItems[0].trim().toUpperCase();

                // Grab the reset of the items after the first one... these are the parameters to use
                // with the constructors for each of the RobotAction subclasses.
                String[] params = (Arrays.copyOfRange(theItems, 1, theItems.length));

                // Based on the type, make the specific kind of RobotAction
                if (type.equalsIgnoreCase("WAITACTION"))
                {
                    myAction = new WaitAction(params);
                }
                else if (type.equalsIgnoreCase("DRIVEACTION"))
                {
                    myAction = new DriveAction(params);
                }
                else
                {
                    myAction = new RobotAction();
                }
                telemetry.addData("TheAction: ", myAction);
             }

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
