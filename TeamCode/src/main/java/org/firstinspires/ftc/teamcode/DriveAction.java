package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class DriveAction extends RobotAction
{
    double thePower;
    double theDirection;
    double theGain;
    double theDistance;

    DriveAction(String id, String nextAction, double duration, double power, double direction, double distance)
    {
        super(id, nextAction, duration);

        thePower = power;
        theDirection = direction;
        theGain = .01;
        theDistance = distance;
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

    DriveAction(String[] params)
    {
        this(params[0],
             params[1],
             Double.parseDouble(params[2]),
             Double.parseDouble(params[3]),
             Double.parseDouble(params[4]),
             Double.parseDouble(params[5]));
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
        telemetry.addData("distance ", theDistance);
        return robot.drive(thePower, theDirection, theGain, theDistance, timeout);
    }


    @Override
    public void exit()
    {
        robot.stopMotors();
        super.exit();
    }

}
