package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="D-RightEnter", group="Zombie")
public class D_RightEnter extends E404_Autonomous
{
    public void init()
    {
        whichFile = "D_RightEnter";
        testFile = new File("/storage/9016-4EF8/DepoRightEnter.csv");

        super.init();
    }
}
