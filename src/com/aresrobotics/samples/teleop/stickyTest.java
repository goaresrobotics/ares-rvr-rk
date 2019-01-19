package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;


@TeleOp(name = "stickyTest")
public class stickyTest extends OpMode{
    private static boolean lastState;

    @Override
     public void init(){}

    @Override
    public void loop(){

    boolean dad = false;
    boolean dad2 = true;
            if (gamepad2.dpad_up == true && dad == false) {
        dad2 = dad;
        dad = true;
    } else {
        if (gamepad2.dpad_up == true && dad == true)
        {
            dad2 = dad;
        dad = false;
        } else {



        }
    }

    telemetry.addData("D-Pad", dad2);



    }

}
