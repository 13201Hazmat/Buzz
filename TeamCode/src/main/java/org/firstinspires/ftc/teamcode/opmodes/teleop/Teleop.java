package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.pedropathing.ivy.commands.Commands;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Robot;

import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "test", group = "1")
public class Teleop extends NextOpMode {
    private final Robot robot;

    public Teleop(Robot robot){
        super(robot);
        this.robot = robot;
        robot.getFollower();
        Trigger.Companion.getDefaultEventLoop().clear();
        CommandGamepad gp1 = new CommandGamepad(Trigger.Companion.getDefaultEventLoop(), gamepad1);

        //Driving
        robot.startDrive(gamepad1);

    }

    @Override
    public void periodic() {
        telemetry.update();
        robot.updateFollower();
    }
}