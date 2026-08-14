package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.mechanisms.vision.Vision;

import java.util.Set;

import dev.nextftc.control.drive.MecanumKinematics;
import dev.nextftc.hardware.RobotController;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;
import dev.nextftc.robot.Telemetry;
import dev.nextftc.robot.drive.DriveCommandsKt;
import gay.zharel.fateweaver.flight.FlightRecorder;

public class Robot implements NextRobot {
    private Follower follower;
    public final Drivetrain drivetrain = new Drivetrain();
    public final Vision vision = new Vision();

    public Robot(){
        Telemetry.addBackend(FlightRecorder.INSTANCE);
    }

    public Follower getFollower() {
        if (follower == null) {
            follower = Constants.createFollower(RobotController.hardwareMap());
        }

        return follower;
    }

    public void updateFollower(){
        follower.update();
    }

    public void startDrive(Gamepad gamepad1) {
        DriveCommandsKt.mecanumDrive(
                drivetrain.frontLeft,
                drivetrain.frontRight,
                drivetrain.backLeft,
                drivetrain.backRight,
                gamepad1,
                new MecanumKinematics()
        );
    }

    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(drivetrain, vision);
    }
}
