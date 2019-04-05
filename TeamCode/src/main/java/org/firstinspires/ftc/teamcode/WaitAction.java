package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.ams.AMSColorSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class WaitAction extends RobotAction
{


    WaitAction(String id, String nextAction, double duration)
    {
        super( id, nextAction, duration);
//        theId = id;
//        timeout = duration;
//
//        if(nextAction.isEmpty())
//        {
//            theNextAction = null;
//        }
//        else
//        {
//            theNextAction = nextAction;
//        }

    }

    WaitAction(String[] params)
    {
        this(params[0], params[1], Double.parseDouble(params[2]));
    }


    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    @Override
    public void entry()
    {
        super.entry();
    }

    @Override
    public boolean execute()
    {
        telemetry.addData("timeout: ", timeout);
        telemetry.addData("get run time: ", getRuntime());
        return super.execute();
    }

    public void exit()
    {
        robot.stopMotors();
        robot.collectorStop();
        super.exit();
    }

}
