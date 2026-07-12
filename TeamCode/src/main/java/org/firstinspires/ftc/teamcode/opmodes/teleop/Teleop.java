package org.firstinspires.ftc.teamcode.opmodes.teleop;

import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Putter;

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

        CommandGamepad gp1 = new CommandGamepad(Trigger.Companion.getDefaultEventLoop(), gamepad1);
        CommandGamepad gp2 = new CommandGamepad(Trigger.Companion.getDefaultEventLoop(), gamepad2);

        gp1.rightBumper().whileTrue(robot.getIntake().setSpeed(Intake.IntakeState.FORWARD));
        gp1.leftBumper().whileTrue(robot.getPutter().setPutterState(Putter.PutterState.ON));
    }

    @Override
    public void periodic() {
        telemetry.update();
    }
}