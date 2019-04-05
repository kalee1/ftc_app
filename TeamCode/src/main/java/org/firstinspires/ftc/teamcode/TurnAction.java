package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class TurnAction extends RobotAction
{
    double thePower;
    double theTargetHeading;

    TurnAction(String id, String nextAction, double duration, double power, double targetHeading)
    {
        super(id, nextAction, duration);
        thePower = power;
        theTargetHeading = targetHeading;
//        timeout = duration;
//        theId = id;
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

    TurnAction(String[] params)
    {
        this(params[0],
             params[1],
             Double.parseDouble(params[2]),
             Double.parseDouble(params[3]),
             Double.parseDouble(params[4]));
    }

//    params[4].equals("IN") ? Gen2_Hang.HangDirection.IN : Gen2_Hang.HangDirection.OUT,
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
        return robot.pointTurn(thePower, theTargetHeading, timeout) || super.execute();
    }

    @Override
    public void exit()
    {
        robot.stopMotors();
        super.exit();
    }
}
