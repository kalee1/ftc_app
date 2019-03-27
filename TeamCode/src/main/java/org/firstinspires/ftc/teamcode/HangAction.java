package org.firstinspires.ftc.teamcode;

public class HangAction extends RobotAction
{
    double thePower;
    double theDistance;
    Gen2_Hang.HangDirection theDirection;



    HangAction(double power, double distance, Gen2_Hang.HangDirection direction, double duration, String id, String nextAction)
    {
        thePower = power;
        theDistance = distance;
        theDirection = direction;
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
        return robot.hangDrive(thePower, theDistance, theDirection) || super.execute();
    }
}
