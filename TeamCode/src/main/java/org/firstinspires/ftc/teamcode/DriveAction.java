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

    DriveAction(double power, double direction, double gain, double distance,
                double duration, String id, String nextAction)
    {
        thePower = power;
        theDirection = direction;
        theGain = gain;
        theDistance = distance;
        timeout = duration;
        theId = id;

        if(nextAction.isEmpty())
        {
            theNextAction = null;
        }
        else
        {
            theNextAction = nextAction;
        }
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
        return robot.drive(thePower, theDirection, theGain, theDistance, timeout) || super.execute();
    }
}
