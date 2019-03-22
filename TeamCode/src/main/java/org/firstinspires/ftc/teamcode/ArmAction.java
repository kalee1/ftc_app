package org.firstinspires.ftc.teamcode;

public class ArmAction extends RobotAction
{
    MotorArm.ArmPositions position;
    double elbPower;
    double shoulPower;
    String id;
    String nextAction;

    ArmAction(MotorArm.ArmPositions thePosition, double theElbPower, double theShoulPower, double duration, String theId, String theNextAction)
    {
        position = thePosition;
        elbPower = theElbPower;
        shoulPower = theShoulPower;
        timeout = duration;
        id = theId;

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
        return robot.goTo(position, elbPower, shoulPower) || super.execute();
    }
}
