package org.firstinspires.ftc.teamcode.mechanisms;

import static dev.nextftc.units.Units.Degrees;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.commands.Commands;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.units.measuretypes.Angle;

public class Lift implements Mechanism {

    private static final double kP = 0;
    private static final double kI = 0;
    private static final double kD = 0;
    private static final double kV = 0;
    private static final double kA = 0;
    private static final double kS = 0;


    private static final double HIGH_TICKS = 2000;
    private static final double LOW_TICKS = 1000;
    private static final double HOME_TICKS = 0;

    public NextMotor l = new NextMotor(RobotController.controlHub(), 0);
    public NextMotor r = new NextMotor(RobotController.controlHub(), 0);

    public enum LiftState {
        HIGH,
        LOW,
        HOME
    }

    private double lastGoalPosition = 0.0;

    public Lift() {
        l.getPositionConstants().setKP(kP);
        l.getPositionConstants().setKI(kI);
        l.getPositionConstants().setKD(kD);
        l.getPositionConstants().setKV(kV);
        l.getPositionConstants().setKA(kA);
        l.getPositionConstants().setKS(kS);

        r.follow(l);

        setPos(HOME_TICKS);
    }

    private void setPos(double goalTicks) {
        lastGoalPosition = goalTicks;
        l.setPositionSetpoint(Degrees.of(goalTicks));
    }

    public Command setPosition(LiftState state) {
        return Commands.instant(() -> {
            switch (state) {
                case HIGH:
                    setPos(HIGH_TICKS);
                    break;
                case LOW:
                    setPos(LOW_TICKS);
                    break;
                case HOME:
                default:
                    setPos(HOME_TICKS);
                    break;
            }
        }).requiring(this);
    }

    public Command setPosition(double x){
        return Commands.instant(()->setPos(x)).requiring(this);
    }
}