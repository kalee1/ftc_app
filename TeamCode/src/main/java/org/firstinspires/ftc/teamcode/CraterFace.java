package org.firstinspires.ftc.teamcode;

import java.io.File;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous(name="Crater Face", group="Zombie")
public class CraterFace extends E404_Autonomous
{
    public void init()
    {
        whichFile = "CraterFace";
        testFile = new File("/storage/9016-4EF8/CraterFace.csv");

        super.init();
    }
}
