package org.firstinspires.ftc.teamcode;

import android.content.Context;

import com.acmerobotics.dashboard.config.Config;
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
import com.acmerobotics.dashboard.FtcDashboard;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;


//@TeleOp(name="file read test", group="alpha")
/**
 * @author Andrew, Error 404: Team Name Not Found
 * @see OpMode
 */
@Config
public class FileRead extends OpMode
{
    String fileName = "MyFile";
    String content = "hellowww world";
    BufferedReader br = null;
    String line = "null";
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();
    public static int MAGIC_NUMBER = 32;


    public void init()
    {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        try
        {
            RobotAction myAction;
            File theFile = new File("/storage/9016-4EF8/auto.csv");
            FileReader inputStreamReader = new FileReader(theFile);

            br = new BufferedReader(inputStreamReader);

            br.readLine();  // throw away first line by reading it and not looking at it

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
                else if(type.equalsIgnoreCase("TURNACTION"))
                {
                    myAction = new TurnAction(params);
                }
                else if(type.equalsIgnoreCase("CAMERAACTION"))
                {
                    myAction = new CameraAction(params);
                }
                else if(type.equalsIgnoreCase("HANGACTION"))
                {
                    myAction = new HangAction(params);
                }
                else if(type.equalsIgnoreCase("ARMACTION"))
                {
                    myAction = new ArmAction(params);
                }
                else if(type.equalsIgnoreCase("MARKDEPLOYACTION"))
                {
                    myAction = new MarkDeployAction(params);
                }
                else if(type.equalsIgnoreCase("GYROACTION"))
                {
                    myAction = new GyroAction(params);
                }
                else
                {
                    myAction = null;
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

        telemetry.addData("Loop RunTime", getRuntime()-MAGIC_NUMBER);
    }

    public void stop()
    {

    }
}
