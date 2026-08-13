package org.firstinspires.ftc.teamcode.vision;

import com.pedropathing.geometry.Pose;
import com.pedropathing.math.Vector;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class TestVision{

    private Limelight3A limelight;

    public Limelight3A getLimelight() {
        return limelight;
    }

    public TestVision(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(0);
        limelight.start();
    }
    public LLResult getTestVisionResult(){
        LLResult result = limelight.getLatestResult();
        if(result == null || !result.isValid()){
            return null;
        }
        return result;
    }

    // Forward-distance coefficients
    private static final double AY = 0.0;
    private static final double BY = 0.0;
    private static final double CY = 0.0;

    // Lateral-distance coefficients
    private static final double AX = 0.0;
    private static final double BX = 0.0;
    private static final double CX = 0.0;

    // Forward distance
    private double getForwardDistance(double ty) {

        // Prevent division by 0
        if (Math.abs(ty) < 0.001) {
            return Double.POSITIVE_INFINITY;
        }

        return AY / ty + BY / (ty * ty) + CY;
    }

    // Lateral distance
    private double getLateralDistance(double tx) {

        return AX * tx + BX * (tx * tx) + CX;
    }

    public Pose getFinalPose(double targetX, double targetY, Pose curPose) {
        double lateralDistance = getLateralDistance(targetX);
        double forwardDistance = getForwardDistance(targetY);

        Vector robotToBallVector = new Vector(new Pose(-forwardDistance, lateralDistance));

        double fieldX = curPose.getX() + robotToBallVector.getXComponent();
        double fieldY = curPose.getY() + robotToBallVector.getYComponent();

        return new Pose(fieldX, fieldY);
    }
}

