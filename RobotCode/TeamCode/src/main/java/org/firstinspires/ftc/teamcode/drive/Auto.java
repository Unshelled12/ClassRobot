package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(group = "drive")
public class Auto extends LinearOpMode {
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor launch;

    private Servo hook;

    boolean parked = false;

    @Override
    public void runOpMode() throws InterruptedException
    {
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
            if (!parked)
            {
                leftFront.setPower(-0.5);
                rightFront.setPower(0.5);
                leftRear.setPower(0.5);
                rightRear.setPower(-0.5);

                sleep(2500);

                leftFront.setPower(0);
                rightFront.setPower(0);
                leftRear.setPower(0);
                rightRear.setPower(0);

                parked = true;
            }

            return;

        }
    }
}
