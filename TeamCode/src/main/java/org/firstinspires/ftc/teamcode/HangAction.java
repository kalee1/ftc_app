package org.firstinspires.ftc.teamcode;

/**
 *
 * @author Andrew, Error 404: Team Name Not Found
 * @see RobotAction
 * */
public class HangAction extends RobotAction
{
    double thePower;
    double theDistance;
    Gen2_Hang.HangDirection theDirection;


    /** Creates a new object from the supplied parameters. */
    HangAction(String id, String nextAction, double duration, double power, Gen2_Hang.HangDirection direction, double distance )
    {
        super( id, nextAction, duration);
        thePower = power;
        theDistance = distance;
        theDirection = direction;
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
    HangAction(String[] params)
    {
        this(params[0],
             params[1],
             Double.parseDouble(params[2]),
             Double.parseDouble(params[3]),
             params[4].equals("IN") ? Gen2_Hang.HangDirection.IN : Gen2_Hang.HangDirection.OUT,
             Double.parseDouble(params[5]));
    }

    /** The body of the action to be executed: Calls the hangDrive() method in MotorArm. */
    @Override
    public boolean execute()
    {
        return robot.hangDrive(thePower, theDistance, theDirection) || super.execute();
    }

    /** Stops all the motors and calls the parent exit method. */
    @Override
    public void exit()
    {
        robot.stopMotors();
        super.exit();
    }


}
