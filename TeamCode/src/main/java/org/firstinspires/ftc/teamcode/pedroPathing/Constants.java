package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.FilteredPIDFCoefficients;
import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
// GEARHEADS ARE BETTER

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
public class Constants {

    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(13.97)
            .forwardZeroPowerAcceleration(-30.87276556811365)
            .lateralZeroPowerAcceleration(-58.77714578617528)
            .useSecondaryTranslationalPIDF(true)
            .useSecondaryHeadingPIDF(true)
            .useSecondaryDrivePIDF(true)
            .translationalPIDFCoefficients(new PIDFCoefficients(0.093,0,0.0015,0.03))
            .secondaryTranslationalPIDFCoefficients(new PIDFCoefficients(0.065,0,0.007,0.04))
            .headingPIDFCoefficients(new PIDFCoefficients(1,0,0.005,0.03))
            .secondaryHeadingPIDFCoefficients(new PIDFCoefficients(2.5,0,0.06,0.01))
            .drivePIDFCoefficients(new FilteredPIDFCoefficients(0.06,0.0,0.00028,0.6,0.01))
            .secondaryDrivePIDFCoefficients(new FilteredPIDFCoefficients(0.03,0,0.00005,0.6,0.01))
            .centripetalScaling(0.00002);


    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("frontRight")
            .rightRearMotorName("backRight")
            .leftRearMotorName("backLeft")
            .leftFrontMotorName("frontLeft")
            .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
            .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
            .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
            .rightRearMotorDirection(DcMotorSimple.Direction.FORWARD)
            .xVelocity(76.76038943313237)
            .yVelocity(59.09409218134842)
            .useBrakeModeInTeleOp(true);




    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 0.9, 1);


    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .mecanumDrivetrain(driveConstants)
                .pathConstraints(pathConstraints)
                .pinpointLocalizer(localizerConstants)
                .build();



    }
    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(-6.25)
            .strafePodX(2.5)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);
}

