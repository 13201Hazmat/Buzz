package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.pedropathing.geometry.Pose;
import com.pedropathing.ivy.commands.Commands;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.mechanisms.Lift;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.AutoCommands;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;

import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "test", group = "1")
public class Teleop extends NextOpMode {
    private final Robot robot;

    public Teleop (Robot robot){
        super(robot);
        this.robot = robot;

        Trigger.Companion.getDefaultEventLoop().clear();
    }

    @Override
    public void start() {
        this.robot.getFollower();


        CommandGamepad gp1 = new CommandGamepad(gamepad1);
        this.robot.startDrive(gamepad1);

        //Intake
        gp1.leftBumper().onTrue(Commands.instant(robot.intake::cycle));

        //Lift
        gp1.dpadUp().onTrue(robot.lift(Lift.LiftState.HIGH));
        gp1.dpadRight().onTrue(robot.lift(Lift.LiftState.LOW));
        gp1.dpadDown().onTrue(robot.lift.setPosition(Lift.LiftState.HOME));

        //Drop
        gp1.rightBumper().onTrue(robot.drop());
    }

    @Override
    public void periodic() {
        telemetry.update();
        robot.updateFollower();
        Pose robotPose = robot.getFollower().getPose();
        telemetry.addData("Robot Position", robotPose);
    }
}