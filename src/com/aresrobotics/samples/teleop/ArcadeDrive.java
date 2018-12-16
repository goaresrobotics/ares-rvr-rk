package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name = "Drive")
public class ArcadeDrive extends OpMode {

    private DcMotor motorRight;
    private DcMotor motorLeft;
    private DcMotor spinner;
    private DcMotor slides;
    private Servo intake;
    private Servo markerRelease;
    private DigitalChannel MLS;

    static final double INCREMENT   = 0.02;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.173;

    int bumperValue =0;

    double  position = MAX_POS;

    private final void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void init() {

        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        spinner = hardwareMap.dcMotor.get("spinner");
        slides = hardwareMap.dcMotor.get("slides");
        intake = hardwareMap.servo.get("intake");
        markerRelease = hardwareMap.servo.get("markerRelease");
        MLS = hardwareMap.get(DigitalChannel.class, "MLS");
        markerRelease.setPosition(1);
        intake.setPosition(position);
        MLS.setMode(DigitalChannel.Mode.INPUT);

    }

    @Override
    public void loop() {

        if(gamepad1.left_bumper)
        {

            bumperValue = bumperValue+1;

        }

        double v = gamepad1.right_stick_x;
        double omega = -gamepad1.left_stick_y;

        double right = 0.0;
        double left = 0.0;

        if(bumperValue%2 == 0)
        {
            right = v - omega;
            left = v + omega;
        }

        if(bumperValue%2 == 1)
        {
            right = (v - omega)/2;
            left = (v + omega)/2;
        }

        motorRight.setPower(right);
        motorLeft.setPower(left);

        slides.setPower(-gamepad2.left_stick_y/2);

        /*if (MLS.getState() == true) {
            if ( > 0) }
            else {
            slides.setPower(); }*/


// Servo Code
        if (gamepad2.left_bumper) {
            position += INCREMENT ;
            if (position >= MAX_POS ) {
                position = MAX_POS;
            }
        }
        if (gamepad2.right_bumper) {
            position -= INCREMENT ;
            if (position <= MIN_POS ) {
                position = MIN_POS;
            }
        }

            intake.setPosition(position);
            sleep(CYCLE_MS);

        spinner.setPower(gamepad2.right_stick_y);

        }
    }

