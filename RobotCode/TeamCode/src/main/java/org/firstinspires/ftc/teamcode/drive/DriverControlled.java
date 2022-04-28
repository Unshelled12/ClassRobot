package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(group = "drive")
public class DriverControlled extends LinearOpMode {
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor launch;

    private Servo hook;

    public void runOpMode() throws InterruptedException{
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        launch = hardwareMap.get(DcMotor.class, "launch");

        hook = hardwareMap.get(Servo.class, "hook");

        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftRear.setDirection(DcMotor.Direction.REVERSE);

        hook.setPosition(0.35);

        waitForStart();

        while(!isStopRequested())
        {
            //Drive Controls
            double y = gamepad1.left_stick_y;
            double strafe = -gamepad1.left_stick_x;
            double spin = gamepad1.right_stick_x;

            //set motor power to 1/2 speed
            if(gamepad1.left_bumper)
            {
                leftFront.setPower((y + strafe + spin) / 2);
                leftRear.setPower((y - strafe + spin) / 2);
                rightFront.setPower((y - strafe - spin) / 2);
                rightRear.setPower((y + strafe - spin) / 2);
            }
            //set motor power to full
            else
            {
                leftFront.setPower(y + strafe + spin);
                leftRear.setPower(y - strafe + spin);
                rightFront.setPower(y - strafe - spin);
                rightRear.setPower(y + strafe - spin);
            }

            //set hook to down position
            if(gamepad1.a)
            {
                hook.setPosition(0);
            }
            //set hook to up position
            else if(gamepad1.y)
            {
                hook.setPosition(0.35);
            }

            //spin launch motor
            if(gamepad1.b)
            {
                launch.setPower(-1);
            }
            else
            {
                launch.setPower(0);
            }
        }
    }
}
