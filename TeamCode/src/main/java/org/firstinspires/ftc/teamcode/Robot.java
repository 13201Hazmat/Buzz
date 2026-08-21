package org.firstinspires.ftc.teamcode;

import static com.pedropathing.ivy.commands.Commands.waitUntil;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;

import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.commands.Commands;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.mechanisms.Bucket;
import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.Intake;
import org.firstinspires.ftc.teamcode.mechanisms.Putter;
import org.firstinspires.ftc.teamcode.mechanisms.Lift;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

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
    public final Bucket bucket = new Bucket();
    public final Drivetrain drivetrain = new Drivetrain();
    public final Intake intake = new Intake();
    public final Lift lift = new Lift();
    public final Putter putter = new Putter();


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

    public Command lift(Lift.LiftState liftState){
        return parallel(
            lift.setPosition(liftState),
            intake.setSpeed(Intake.IntakeState.OFF)
        );
    }
    public Command drop(){
        return sequential(
            bucket.setDrop(),
            waitUntilDisplaced(8),
            lift.setPosition(Lift.LiftState.HOME)
        );
    }

    private Command waitUntilDisplaced(double distance) {
        double startX = getFollower().getPose().getX();
        double startY = getFollower().getPose().getY();

        return waitUntil(() -> {
            double dx = getFollower().getPose().getX() - startX;
            double dy = getFollower().getPose().getY() - startY;
            return Math.sqrt(dx * dx + dy * dy) >= distance;
        });
    };
    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(bucket, drivetrain, intake, lift, putter);
    }
}
