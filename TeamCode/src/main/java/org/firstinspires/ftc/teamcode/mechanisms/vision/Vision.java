package org.firstinspires.ftc.teamcode.mechanisms.vision;

import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.webcams.NextLimelight;
import dev.nextftc.robot.Mechanism;

public class Vision implements Mechanism {
    private final NextLimelight limelight = new NextLimelight("limelight");
    public Vision() {
        limelight.startReading(0, 50);
    }
    public NextLimelight getLimelight() {
        return limelight;
    }
    public Pose getFinalPose(double targetX, double targetY, Pose currentPose) {

        if (currentPose == null) {
            return null;
        }

        double cameraPitch = 10.0;
        double ballRadius = 1.4;
        double cameraHeight = 9.5;

        double cameraOffsetY = 7.5;
        double cameraOffsetX = 7.5;

        double cameraToBallAngle = Math.toRadians(cameraPitch + targetY);

        double heightDifference = cameraHeight - ballRadius;

        // Checks for impossible math
        if (Math.abs(Math.tan(cameraToBallAngle)) < 0.0001) {
            return null;
        }

        double forwardDistance = heightDifference / Math.tan(cameraToBallAngle);
        double lateralDistance = forwardDistance * Math.tan(Math.toRadians(targetX));

        forwardDistance += cameraOffsetY;
        lateralDistance += cameraOffsetX;

        // TODO FIX THIS WITH RELATION TO PEDRO COORDS
        Vector robotToBallVector = new Vector(new Pose(-forwardDistance, lateralDistance));

        robotToBallVector.rotateVector(currentPose.getHeading());

        double fieldX = currentPose.getX() + robotToBallVector.getXComponent();

        double fieldY = currentPose.getY() + robotToBallVector.getYComponent();

        return new Pose(fieldX, fieldY, currentPose.getHeading());
    }

    public Pose getFinalPose(Pose currentPose){
        return getFinalPose(limelight.getTX(), limelight.getTY(), currentPose);
    }
}