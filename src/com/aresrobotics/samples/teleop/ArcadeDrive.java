package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DigitalChannel;

@TeleOp(name = "Drive")
public class ArcadeDrive extends OpMode {

    private DcMotor motorLeft;
    private DcMotor motorLeftBack;
    private DcMotor motorRight;
    private DcMotor motorRightBack;
    private DcMotor spinner;
    private DcMotor slides;
    private Servo intake;
    private Servo markerRelease;
    private DigitalChannel MLS;

    static final double INCREMENT   = 0.02;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.173;

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
        motorLeftBack = hardwareMap.dcMotor.get("motorLeftBack");
        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorRightBack = hardwareMap.dcMotor.get("motorRightBack");
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

        double h = Math.hypot(gamepad1.left_stick_x, -gamepad1.left_stick_y);
        double robotAngle = Math.atan2(-gamepad1.left_stick_y, gamepad1.left_stick_x) - Math.PI / 4;
        double rightX = gamepad1.right_stick_x;
        final double v1 = h * Math.cos(robotAngle) + rightX;
        final double v2 = h * Math.sin(robotAngle) - rightX;
        final double v3 = h * Math.sin(robotAngle) + rightX;
        final double v4 = h * Math.cos(robotAngle) - rightX;

        motorLeft.setPower(v1/1.5);
        motorRight.setPower(-v2/1.5);
        motorLeftBack.setPower(v3/1.5);
        motorRightBack.setPower(-v4/1.5);

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

