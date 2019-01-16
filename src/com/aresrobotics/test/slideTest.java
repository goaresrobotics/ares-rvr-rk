package com.aresrobotics.test;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;


@TeleOp(name = "Test: Slides")
@Disabled
public class slideTest extends OpMode{

    ///DigitalChannel touchMe;
    //DigitalChannel touchMeToo;

    private DcMotor slides;

    @Override
    public void init()
    {

       slides = hardwareMap.dcMotor.get("slides");

        //touchMe.setMode(DigitalChannel.Mode.INPUT);
        //touchMeToo.setMode(DigitalChannel.Mode.INPUT);

    }
    @Override
    public void loop()
    {

        double intakeSlide = gamepad2.left_stick_y;

        slides.setPower(-intakeSlide/5);
        /*if (touchMe.getState() == true) {
            slides.setPower(-0.1); }
        else {
            slides.setPower(intakeSlide/15); }


        if (touchMeToo.getState() == true) {
            slides.setPower(0.1); }
        else {
            slides.setPower(intakeSlide/15); }*/

    }
}
