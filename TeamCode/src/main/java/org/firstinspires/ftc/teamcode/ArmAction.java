package org.firstinspires.ftc.teamcode;

import android.content.res.Resources;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Loaded into the run map as an action that moves the arm. Each action is parameterized by the CSV file.
 *
 * @author Andrew, Error 404 Robotics
 * @see RobotAction
 *  */
public class ArmAction extends RobotAction
{
    /** An enum that is the allowed presets for the arm. */
    MotorArm.ArmPositions thePosition;
    /** A double that is the elbow power. */
    double theElbPower;
    /** A double that is the shoulder power. */
    double theShoulPower;

    /** Creates a new object from the supplied parameters. */
    ArmAction(String id, String nextAction, double duration, double elbPower, double shoulPower, String position)
    {
        super(id, nextAction, duration);

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

    /** Takes the parameters from the CSV file, converts them appropriately, and calls the
     * parameterized constructor */
    ArmAction(String[] params)
    {
        this(params[0],
             params[1],
             Double.parseDouble(params[2]),
             Double.parseDouble(params[3]),
             Double.parseDouble(params[4]),
             params[5]);
    }

    /** Placeholder for entry. Currently only calls the parent entry method.  */
    @Override
    public void init(Telemetry telem, RuckusBot theRobot)
    {
        super.init(telem, theRobot);
    }

    /** Placeholder for entry. Currently only calls the parent entry method. */
    @Override
    public void entry()
    {
        super.entry();
    }

    /** The body of the action to be executed: Calls the goTo() method in MotorArm. */
    @Override
    public boolean execute()
    {
        return robot.goTo(thePosition, theElbPower, theShoulPower) || super.execute();
    }

    /** Stops the motors and calls the parent exit method. */
    @Override
    public void exit()
    {
        robot.stopMotors();
        super.exit();
    }
}
