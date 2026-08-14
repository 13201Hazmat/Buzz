package org.firstinspires.ftc.teamcode.opmodes.teleop;

import androidx.annotation.NonNull;

import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.AutoCommands;
import org.firstinspires.ftc.teamcode.vision.Vision;

import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.robot.triggers.Trigger;

@NextTeleop(name = "test", group = "1")
public class Teleop extends NextOpMode {
    private final Robot hazmatRobot;
    private final AutoCommands autoCommands;
    private final Vision vision;

    public Teleop(@NonNull NextRobot robot, AutoCommands autoCommands){
        super(robot, BulkReadHook.INSTANCE);
        this.hazmatRobot = new Robot();
        this.autoCommands = autoCommands;
        vision = new Vision();
        this.hazmatRobot.getFollower();
        Trigger.Companion.getDefaultEventLoop().clear();
        CommandGamepad gp1 = new CommandGamepad(Trigger.Companion.getDefaultEventLoop(), gamepad1);

        //Driving
        this.hazmatRobot.startDrive(gamepad1);
        gp1.rightBumper().onTrue(autoCommands.moveToNearestBalls());

    }

    @Override
    public void periodic() {
        telemetry.update();
        hazmatRobot.updateFollower();
        Pose robotPose = hazmatRobot.getFollower().getPose();
        telemetry.addData("Robot Position", robotPose);
        telemetry.addData("Pollen Position", vision.getFinalPose(robotPose));
    }
}