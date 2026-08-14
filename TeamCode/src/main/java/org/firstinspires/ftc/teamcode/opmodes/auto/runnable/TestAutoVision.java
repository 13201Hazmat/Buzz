package org.firstinspires.ftc.teamcode.opmodes.auto.runnable;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.AutoCommands;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;

import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.opmode.BulkReadHook;
import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.OpModeHook;

@NextAutonomous(name = "Test Auto Vision", group = "Test")
public class TestAutoVision extends NextOpMode {

    private final Robot robot;
    private final PathsAndPoses paths;
    private final AutoCommands commands;

    public TestAutoVision(@NonNull NextRobot robot) {
        super(robot, BulkReadHook.INSTANCE);
        this.robot = new Robot();
        this.paths = new PathsAndPoses(this.robot);
        this.commands = new AutoCommands(this.robot, this.paths);
    }

    @Override
    public void start() {
        commands.moveToNearestBalls();
    }

}