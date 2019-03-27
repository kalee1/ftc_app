package org.firstinspires.ftc.teamcode;

import android.content.res.Resources;

public class ArmAction extends RobotAction
{
    MotorArm.ArmPositions thePosition;
    double theElbPower;
    double theShoulPower;


    ArmAction(MotorArm.ArmPositions position, double elbPower, double shoulPower, double duration, String id, String nextAction)
    {
        thePosition = position;
        theElbPower = elbPower;
        theShoulPower = shoulPower;
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
        return robot.goTo(thePosition, theElbPower, theShoulPower) || super.execute();
    }
}
