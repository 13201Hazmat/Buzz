package org.firstinspires.ftc.teamcode.opmodes.teleop;

import androidx.annotation.NonNull;


import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.data.Alliance;

import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.opmode.NextOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.opmode.OpModeHook;

@NextTeleop(name = "NextTeleop", group = "Test")
public class Teleop extends NextOpMode {

    private Robot robot;
    public Teleop(@NonNull NextRobot robot, @NonNull OpModeHook... hooks) {
        super(robot, hooks);
    }

    @Override
    public void onInit() {
        robot = new Robot(Alliance.BLUE);
    }

    @Override
    public void onStart() {
        //Gamepad logic goes here says claude

    }

    @Override
    public void periodic() {
        super.periodic();
    }
}
