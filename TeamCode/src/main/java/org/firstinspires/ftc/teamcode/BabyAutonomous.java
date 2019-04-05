package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Autonomous(name="Baby Autonomus", group="Zeta")

/** A small test autonomous with a state machine to test the new autonomous structure
 *
 * @auther Andrew, Error 404 Robotics
 * @see OpMode
 * */
public class BabyAutonomous extends OpMode
{
    ActionMaster theMaster = new ActionMaster();
    RuckusBot robot = new RuckusBot("MecanumChassis");
    BufferedReader br = null;
    String line = "null";

    public void init()
    {
        robot.init(hardwareMap, telemetry, true);
        theMaster.init(telemetry);

//        WaitAction firstStep = new WaitAction("one", "two", 3);
//        firstStep.init(telemetry, robot);
//        theMaster.addAction(firstStep);
//        theMaster.addRunAction("one");
//
//        WaitAction secondStep = new WaitAction("two", "three", 3);
//        secondStep.init(telemetry, robot);
//        theMaster.addAction(secondStep);
//
//        DriveAction thirdStep = new DriveAction("three", "", 7, .3, 0, 6);
//        thirdStep.init(telemetry, robot);
//        theMaster.addAction(thirdStep);

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
                else if(type.equalsIgnoreCase("RESETACTION"))
                {
                    myAction = new ResetHeadingAction(params);
                }
                else
                {
                    myAction = null;
                }

                if(myAction != null)
                {
                    myAction.init(telemetry, robot);
                    theMaster.addAction(myAction);
                }
                telemetry.addData("TheAction: ", myAction);
            }
            theMaster.addRunAction("One");
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
        robot.start();
        resetStartTime();
    }

    public void loop()
    {
        telemetry.addData("loop time: ", getRuntime());
        telemetry.addData("run list size: ", theMaster.getRunListSize());
        telemetry.addData("get keys: ", theMaster.keyList());
        theMaster.execute();
    }

    public void stop()
    {
        robot.stopMotors();
    }
}
