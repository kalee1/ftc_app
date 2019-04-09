package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="D-OtherCrater", group="Zombie")
public class D_OtherCrater extends E404_Autonomous
{
    public void init()
    {
        whichFile = "D_OtherCrater";
        testFile = new File("/storage/9016-4EF8/DepoOtherCrater.csv");

        super.init();
    }
}
