package org.firstinspires.ftc.teamcode;

public interface RobotAction
{
    String name = "";
    double timeout = 0;
    RuckusBot robot = null;
    boolean done = false;
    String nextAction = "";

    void entry();
    void exit();
    boolean execute();

}
