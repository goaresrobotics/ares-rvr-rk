package com.aresrobotics.samples.teleop;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.aresrobotics.library.hardware.AresSampleRobot;

@TeleOp(name = "Drive")
public class ArcadeDrive extends OpMode {

    private DcMotor motorRight;
    private DcMotor motorLeft;
    private DcMotor spinner;
    private DcMotor slides;
    Servo intake;

    static final double INCREMENT   = 0.02;
    static final int    CYCLE_MS    =   50;
    static final double MAX_POS     =  1.0;
    static final double MIN_POS     =  0.0;

    double  position = MAX_POS;
    boolean rampUp = true;

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
 //       spinner = hardwareMap.dcMotor.get("spinner");
 //       slides = hardwareMap.dcMotor.get("slides");
        intake = hardwareMap.servo.get("intake");


    }

    @Override
    public void loop() {

        double v = gamepad1.right_stick_x;
        double omega = -gamepad1.left_stick_y;

        double right = v - omega;
        double left = v + omega;

        motorRight.setPower(right);
        motorLeft.setPower(left);

      //  double intakeSlide = gamepad2.left_stick_y;

      //  slides.setPower(intakeSlide);

        if (rampUp && gamepad2.left_bumper) {
            position += INCREMENT ;
            if (position >= MAX_POS ) {
                position = MAX_POS;
                rampUp = false;  // Switch ramp direction
            }
        }
        if (!rampUp && gamepad2.right_bumper) {
            position -= INCREMENT ;
            if (position <= MIN_POS ) {
                position = MIN_POS;
                rampUp = true;  // Switch ramp direction
            }
        }

            intake.setPosition(position);
            sleep(CYCLE_MS);


    }

}