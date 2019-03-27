package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class TurnAction extends RobotAction
{
    double thePower;
    double theTargetHeading;

    TurnAction(double power, double targetHeading, double duration, String id, String nextAction)
    {
        thePower = power;
        theTargetHeading = targetHeading;
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
        return robot.pointTurn(thePower, theTargetHeading, timeout) || super.execute();
    }
}
