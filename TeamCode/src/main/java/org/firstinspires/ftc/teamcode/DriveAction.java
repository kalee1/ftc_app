package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class DriveAction extends RobotAction
{
    double power;
    double direction;
    double gain;
    double distance;

    DriveAction(double thePower, double theDirection, double theGain, double theDistance, double duration)
    {
        power = thePower;
        direction = theDirection;
        gain = theGain;
        distance = theDistance;
        timeout = duration;

    }

    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
    }

    @Override
    public void entry()
    {
    }

    @Override
    public void exit()
    {
    }

    @Override
    public boolean execute()
    {
        return false;
    }
}
