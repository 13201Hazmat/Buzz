package org.firstinspires.ftc.teamcode.mechanisms;

import static dev.nextftc.units.Units.RotationsPerMinute;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.commands.Commands;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.AngularVelocity;

public class Putter implements Mechanism {
    private final NextMotor putterMotor = new NextMotor(RobotController.controlHub(), 0);

    public enum PutterState {
        ON,
        OFF
    }

    private PutterState putterState;

    private static final AngularVelocity ON_SPEED = RotationsPerMinute.of(2000);
    private static final AngularVelocity OFF_SPEED = RotationsPerMinute.of(0);

    public Putter() {
        putterState = PutterState.OFF;

        putterMotor.getVelocityConstants().setKP(0.0);
        putterMotor.getVelocityConstants().setKI(0.0);
        putterMotor.getVelocityConstants().setKD(0.0);

        putterMotor.getVelocityConstants().setKS(0.0);
        putterMotor.getVelocityConstants().setKV(0.0);
        putterMotor.getVelocityConstants().setKA(0.0);

        putterMotor.setVelocitySetpoint(OFF_SPEED);
    }

    private void setSpeed(AngularVelocity speed, PutterState state) {
        putterState = state;
        putterMotor.setVelocitySetpoint(speed);
    }

    public Command setPutterState(PutterState state) {
        return Commands.instant(() ->
                setSpeed(state == PutterState.ON ? ON_SPEED : OFF_SPEED, state)
        ).requiring(this);
    }

    public Command setSpeed(AngularVelocity speed) {
        return Commands.instant(() -> setSpeed(speed, PutterState.ON)).requiring(this);
    }

    public Command setSpeedRpm(double rpm) {
        return setSpeed(RotationsPerMinute.of(rpm));
    }

    public PutterState getPutterState() {
        return putterState;
    }

    @Override
    public void periodic() {
    }
}