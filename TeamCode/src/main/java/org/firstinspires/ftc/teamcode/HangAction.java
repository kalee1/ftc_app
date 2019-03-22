package org.firstinspires.ftc.teamcode;

public class HangAction extends RobotAction
{
    double power;
    double distance;
    Gen2_Hang.HangDirection direction;
    String id;
    String nextAction;


    HangAction(double thePower, double theDistance, Gen2_Hang.HangDirection theDirection, double duration, String theId, String theNextAction)
    {
        power = thePower;
        distance = theDistance;
        direction = theDirection;
        timeout = duration;
        theId = id;

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
        return robot.hangDrive(power, distance, direction) || super.execute();
    }
}
