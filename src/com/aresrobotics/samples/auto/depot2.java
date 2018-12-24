
/*
   Modified from the FIRST examples included in the examples folder in ftc_app
 */

package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Illustrates the use of the motor encoders to run to position
 */

@Autonomous(name="depot2", group="Samples")
public class depot2 extends Auto {

    @Override
    public void run() {

        encoderDrive(DRIVE_SPEED, -15, -15, 10);
        aresBot.turn(90, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -33, -33, 10);
        aresBot.turn(60, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -5, -5, 10);
        aresBot.turn(90, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -50, -50, 10);
        aresBot.turn(180, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        //aresBot.markerRelease.setPosition(0.13725490196);
        sleep(1000);
        //aresBot.markerRelease.setPosition(1);
        encoderDrive(1, -84, -84, 10);
    }
}
