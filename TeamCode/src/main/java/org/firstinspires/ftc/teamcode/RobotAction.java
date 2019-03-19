package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.concurrent.TimeUnit;

public class RobotAction
{
    String name = "";
    double timeout = 0;
    RuckusBot robot = null;
    boolean done = false;
    String nextAction = "";
    Telemetry telemetry;
    // internal time tracking
    private long startTime = 0; // in nanoseconds

    public void init(Telemetry telem, RuckusBot theRobot)
    {
        telemetry = telem;
        robot = theRobot;
    }

    public void entry()
    {
        startTime = System.nanoTime();
    }

    public void exit()
    {

    }

    public boolean execute()
    {
        return (getRuntime() >= timeout);
    }

    /**
     * Get the number of seconds this op mode has been running
     * <p>
     * This method has sub millisecond accuracy.
     * @return number of seconds this op mode has been running
     */
    public double getRuntime() {
        final double NANOSECONDS_PER_SECOND = TimeUnit.SECONDS.toNanos(1);
        return (System.nanoTime() - startTime) / NANOSECONDS_PER_SECOND;
    }

    /**
     * Reset the start time to zero.
     */
    public void resetStartTime() {
        startTime = System.nanoTime();
    }

}
