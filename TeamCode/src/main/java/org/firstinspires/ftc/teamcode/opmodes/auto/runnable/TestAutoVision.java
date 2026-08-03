package org.firstinspires.ftc.teamcode.opmodes.auto.runnable;

import androidx.annotation.NonNull;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.data.Alliance;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.AutoCommands;
import org.firstinspires.ftc.teamcode.opmodes.auto.paths.PathsAndPoses;

import java.util.List;

import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.opmode.NextAutonomous;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.OpModeHook;

@NextAutonomous(name = "Test Auto Vision", group = "Test")
public class TestAutoVision extends NextOpMode {

    private Robot robot;
    private PathsAndPoses paths;
    private AutoCommands commands;

    public TestAutoVision(@NonNull NextRobot robot, @NonNull OpModeHook... hooks) {
        super(robot, hooks);

        this.robot = new Robot(Alliance.RED);
    }
}
