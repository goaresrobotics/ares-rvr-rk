package com.aresrobotics.library.hardware;

import com.aresrobotics.samples.auto.Auto;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class AresSampleRobot
{
    public DcMotor  motorLeft;
    public DcMotor  motorRight;
    public DcMotor  motorRightBack;
    public DcMotor  motorLeftBack;

//    public Servo markerRelease;
//    public Servo intake;

    HardwareMap hwMap           =  null;
    Telemetry telemetry;
    private Auto auto;
    private ElapsedTime period  = new ElapsedTime();

    public AresSampleRobot(Telemetry telemetry, Auto auto)
    {
        this.telemetry = telemetry;
        this.auto = auto;
    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;
        motorLeft  = hwMap.get(DcMotor.class, "motorLeft");
        motorRight = hwMap.get(DcMotor.class, "motorRight");
        motorLeftBack  = hwMap.get(DcMotor.class, "motorLeftBack");
        motorRightBack  = hwMap.get(DcMotor.class, "motorRightBack");
//        markerRelease = hwMap.get(Servo.class, "markerRelease");
//        intake = hwMap.get(Servo.class, "intake");
        motorLeft.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        motorRightBack.setDirection(DcMotor.Direction.FORWARD);
        motorLeftBack.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        motorRight.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors

//        intake.setPosition(1);
//        markerRelease.setPosition(1);
        motorLeft.setPower(0);
        motorRight.setPower(0);
        motorLeftBack.setPower(0);
        motorRightBack.setPower(0);


        motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorLeftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorRightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        }

    public void turn(double angle, DcMotor motorLeft, DcMotor motorRight, DcMotor motorLeftBack, DcMotor motorRightBack)
    {
        BNO055IMU imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        Orientation orientation = imu.getAngularOrientation();
        parameters.angleUnit = (BNO055IMU.AngleUnit.DEGREES);
        imu.initialize(parameters);
        double left;
        double right;
        double currentAngle;
        if(orientation.firstAngle-180.001>0)
        {
            left = -1;
            right = 1;
        }
        else
        {
            left = 1;
            right = -1;
        }
        while (orientation.firstAngle<angle && isStopRequested){
            motorLeft.setPower(left);
            motorLeftBack.setPower(left);
            motorRight.setPower(right);
            motorRightBack.setPower(right);
            orientation = imu.getAngularOrientation();
            telemetry.addData("heading", orientation.firstAngle);
            telemetry.update();
        }
        motorLeft.setPower(0);
        motorLeftBack.setPower(0);
        motorRight.setPower(0);
        motorRightBack.setPower(0);

    }


}