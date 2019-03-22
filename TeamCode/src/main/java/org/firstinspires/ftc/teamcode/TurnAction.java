package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class TurnAction extends RobotAction
{
    double power;
    double targetHeading;
    String id;
    String nextAction;

    TurnAction(double thePower, double theTargetHeading, double duration, String theId, String theNextAction)
    {
        power = thePower;
        targetHeading = theTargetHeading;
        timeout = duration;
        id = theId;
        nextAction = theNextAction;

        if(theNextAction.isEmpty())
        {
            nextAction = null;
        }
        else
        {
            nextAction = theNextAction;
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
        return robot.pointTurn(power, targetHeading, timeout) || super.execute();
    }
}
