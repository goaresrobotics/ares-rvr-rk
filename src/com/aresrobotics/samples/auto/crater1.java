package com.aresrobotics.samples.auto;

import com.aresrobotics.library.hardware.AresSampleRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="crater1", group="Samples")
public class crater1 extends LinearOpMode {

    /* Declare OpMode members. */
    AresSampleRobot aresBot   = new AresSampleRobot();   // Use defined ARES hardware
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 560 ;    // eg: REV Core Hex
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP - 1 : 1 for my bot
    static final double     WHEEL_DIAMETER_INCHES   = 4 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
                                                      (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * See the init() method in the AresSampleRobot.java class file
         */
        aresBot.init(hardwareMap);

        // Send telemetry message to signify the bot is waiting for the operator press;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        aresBot.motorLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        aresBot.motorRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        aresBot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        aresBot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d",
                          aresBot.motorLeft.getCurrentPosition(),
                          aresBot.motorRight.getCurrentPosition());
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)
        encoderDrive(DRIVE_SPEED,  -23,  -23, 10);
        aresBot.turn(90, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED, -25, -25, 10);
        aresBot.turn(60, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        encoderDrive(DRIVE_SPEED,  -60,  -60, 10);
        aresBot.turn(180, aresBot.motorLeft, aresBot.motorRight, aresBot.motorLeftBack, aresBot.motorRightBack);
        //aresBot.markerRelease.setPosition(0.13725490196);
        sleep(1000);
        //aresBot.markerRelease.setPosition(1);
        encoderDrive(1,  -87,  -87, 10);
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = aresBot.motorLeft.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = aresBot.motorRight.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            aresBot.motorLeft.setTargetPosition(newLeftTarget);
            aresBot.motorRight.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            aresBot.motorLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            aresBot.motorRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            aresBot.motorLeft.setPower(Math.abs(speed));
            aresBot.motorRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (aresBot.motorLeft.isBusy() && aresBot.motorRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            aresBot.motorLeft.getCurrentPosition(),
                                            aresBot.motorRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            aresBot.motorLeft.setPower(0);
            aresBot.motorRight.setPower(0);

            // Turn off RUN_TO_POSITION
            aresBot.motorLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            aresBot.motorRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }
}
