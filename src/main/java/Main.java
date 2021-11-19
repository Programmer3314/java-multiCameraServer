/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.vision.VisionThread;

public final class Main extends MainBase {

  private Main() {
  }

  /**
   * Example pipeline.
   */

  /**
   * Main.
   */
  public static void main(String... args) {

    standardInit(args);

    // Custom code to return processed image from Pipeline
    // The Pipeline class has a modified constructor that
    // takes the following CvSource so it can send frames
    // back through the following MjpegServer
    MjpegServer myStream;
    CvSource source1;

    source1 = new CvSource("myImage", VideoMode.PixelFormat.kMJPEG,640,480,30);
    myStream = new MjpegServer("processedVideo", 1184);
    myStream.setCompression(75);
    myStream.setDefaultCompression(75);
    myStream.setResolution(320, 240);
    myStream.setSource(source1);

    // if no cameras are defined cameras.get(0) will fail
    // if we have 1 or more cameras start the VisionThread
    // (might be "cameras.size()>0")
    if (cameras.size() >= 1) {
      VisionThread visionThread = new VisionThread(cameras.get(0),
              new MyPipeline(source1), pipeline -> {
        // do something with pipeline results
      });

      visionThread.start();
    }

    standardRun();

  }
}
