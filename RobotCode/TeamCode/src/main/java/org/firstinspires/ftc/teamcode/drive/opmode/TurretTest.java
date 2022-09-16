package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.TurretTurn;

@TeleOp(group = "drive")
public class TurretTest extends LinearOpMode {
    @Override
    public void runOpMode()
    {
        TurretTurn myTurret = new TurretTurn();
        telemetry.addData("heading", myTurret.findDegree(50, 50, 60));
    }
}
