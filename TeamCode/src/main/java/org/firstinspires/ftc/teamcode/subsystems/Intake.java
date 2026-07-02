package org.firstinspires.ftc.teamcode.subsystems;

import static com.pedropathing.ivy.commands.Commands.infinite;
import static com.pedropathing.ivy.commands.Commands.instant;
import static dev.nextftc.units.Units.Inches;

import com.pedropathing.ivy.Command;

import dev.nextftc.hardware.actuators.NextMotor;

public class Intake {
    NextMotor i = new NextMotor("intakeMotor", Inches.of(1.0), 0.2);
    private IntakeState intakeState;

    public enum IntakeState {
        FORWARD,
        REVERSE,
        OFF
    }

    private double power;

    private final double forward = 1.0;
    private final double reverse = -1.0;
    private final double off = 0.0;

    public Intake() {
        intakeState = IntakeState.OFF;
        power = off;
    }

    private void setState(IntakeState intakeState) {
        this.intakeState = intakeState;
    }

    public Command setSpeed(IntakeState s) {
        return instant(() -> this.setState(s));
    }

    public void cycle() {
        if (intakeState == IntakeState.REVERSE) power = forward;
        else if (intakeState == IntakeState.FORWARD) power = reverse;
        else power = forward;
    }

    public Command periodic() {
        return infinite(() -> {
            switch (intakeState) {
                case FORWARD:
                    i.setThrottle(forward);
                    break;
                case REVERSE:
                    i.setThrottle(reverse);
                    break;
                case OFF:
                    i.setThrottle(off);
                    break;
            }
        });
    }


}
