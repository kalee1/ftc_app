package org.firstinspires.ftc.teamcode;

import android.content.res.Resources;

public class ArmAction extends RobotAction
{
    MotorArm.ArmPositions thePosition;
    double theElbPower;
    double theShoulPower;


    ArmAction(String id, String nextAction, double duration, double elbPower, double shoulPower, String position )
    {
        if(position.equals(MotorArm.ArmPositions.ARM_HOME.toString()))
        {
            thePosition = MotorArm.ArmPositions.ARM_HOME;
        }
        else if(position.equals(MotorArm.ArmPositions.CRATER_EXTEND.toString()))
        {
            thePosition = MotorArm.ArmPositions.CRATER_EXTEND;
        }
        else if(position.equals(MotorArm.ArmPositions.DRIVING_EXTEND.toString()))
        {
            thePosition = MotorArm.ArmPositions.DRIVING_EXTEND;
        }
        else if(position.equals(MotorArm.ArmPositions.LANDER_EXTEND.toString()))
        {
            thePosition = MotorArm.ArmPositions.LANDER_EXTEND;
        }
        else
        {
            thePosition = MotorArm.ArmPositions.CRATER_EXTEND;
        }

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

    ArmAction(String[] params)
    {
        this(params[0],
             params[1],
             Double.parseDouble(params[2]),
             Double.parseDouble(params[3]),
             Double.parseDouble(params[4]),
             params[5]);
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
