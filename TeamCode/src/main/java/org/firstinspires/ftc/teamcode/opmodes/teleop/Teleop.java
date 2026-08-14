package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Robot;
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
    private final AutoCommands autoCommands;

    public Teleop (Robot robot){
        super(robot, BulkReadHook.INSTANCE);
        this.robot = robot;
        PathsAndPoses paths = new PathsAndPoses(robot);
        this.autoCommands = new AutoCommands(robot, paths);
    }

    @Override
    public void start() {
        this.robot.getFollower();
        Trigger.Companion.getDefaultEventLoop().clear();
        CommandGamepad gp1 = new CommandGamepad(Trigger.Companion.getDefaultEventLoop(), gamepad1);
        this.robot.startDrive(gamepad1);
        gp1.rightBumper().onTrue(autoCommands.moveToNearestBalls());
    }

    @Override
    public void periodic() {
        telemetry.update();
        robot.updateFollower();
        Pose robotPose = robot.getFollower().getPose();
        telemetry.addData("Robot Position", robotPose);
        telemetry.addData("Pollen Position", robot.vision.getFinalPose(robotPose));
    }
}