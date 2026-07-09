package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

import java.util.Set;

import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;

public class Robot implements NextRobot {
    public final Intake i = new Intake();
    public final Lift l = new Lift();


    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(i, l);
    }
}
