package com.aresrobotics.samples.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="depot1", group="Samples")
public class depot1 extends Auto {

    @Override
    public void run() {

        telemetry.addLine("Starting encoder drive");
        encoderDrive(DRIVE_SPEED,  25,  25, 10);
        turn(90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, 33, 33, 10);
        turn(60,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  5,  5, 10);
        turn(90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  50,  50, 10);
        turn(90,0.5,  aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        //aresBot.markerRelease.setPosition(0.13725490196);
        //sleep(1000);
        //aresBot.markerRelease.setPosition(1);
        encoderDrive(1,  -100,  -100, 10);
    }
}
