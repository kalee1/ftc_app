package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.ams.AMSColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

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

    public void init()
    {
        theMaster.init(telemetry);

        WaitAction firstStep = new WaitAction("one", "two", 3);
        firstStep.init(telemetry, robot);
        theMaster.addAction(firstStep);
        theMaster.addRunAction("one");

        WaitAction secondStep = new WaitAction("two", "three", 3);
        secondStep.init(telemetry, robot);
        theMaster.addAction(secondStep);

        DriveAction thirdStep = new DriveAction("three", "", 7, .3, 0, 6);
        thirdStep.init(telemetry, robot);
        theMaster.addAction(thirdStep);

        resetStartTime();
    }

    public void start(){}

    public void loop()
    {
        telemetry.addData("loop time: ", getRuntime());
        telemetry.addData("run list size: ", theMaster.getRunListSize());
        telemetry.addData("get keys: ", theMaster.keyList());
        theMaster.execute();
    }

    public void stop(){}
}
