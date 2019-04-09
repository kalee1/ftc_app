package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class HangAction extends RobotAction
{
    double thePower;
    double theDistance;
    Gen2_Hang.HangDirection theDirection;



    HangAction(String id, String nextAction, double duration, double power, Gen2_Hang.HangDirection direction, double distance )
    {
        super( id, nextAction, duration);
        thePower = power;
        theDistance = distance;
        theDirection = direction;
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

    HangAction(String[] params)
    {
        this(params[0],
             params[1],
             Double.parseDouble(params[2]),
             Double.parseDouble(params[3]),
             params[4].equals("IN") ? Gen2_Hang.HangDirection.IN : Gen2_Hang.HangDirection.OUT,
             Double.parseDouble(params[5]));
    }

    @Override
    public void exit()
    {
        robot.stopMotors();
        super.exit();
    }

    @Override
    public boolean execute()
    {
        return robot.hangDrive(thePower, theDistance, theDirection) || super.execute();
    }
}
