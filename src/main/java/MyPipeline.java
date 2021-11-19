import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSource;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;

public class MyPipeline implements VisionPipeline {
    public int val;

    // Network Table variables 
    NetworkTableInstance nt;
    NetworkTable table; 

    // image source used by the Streaming Server
    CvSource source1;

    public MyPipeline(CvSource source1){  
      this.source1 = source1;

      /* Setup Network Table variables
        Get the default instance of Netowrk Tables
        and a pointer to the "Retroreflective Tape Target"
        entry in that table. This entry is typically used for
        side to side aiming. Additional entries could be used 
        for other parameters such as vertical/distance/angle 
        values. 
        */
      nt = NetworkTableInstance.getDefault();
      table = nt.getTable("Retroreflective Tape Target");
    }

    /* main processing code
    This is the function for vision processing
    Processed images are returned by putting them in 
    source1. 
    Targetting information should be returned via 
    network tables using nt and table. 
    */
    @Override
    public void process(Mat mat) {
      val += 1;

      // Draw some lines. 
      Point pt1 = new Point(mat.width() / 2 , mat.height());
      Point pt2 = new Point(mat.width() / 2, 0);//original line
      Imgproc.line(mat, pt1, pt2, new Scalar(0,0,255),15);
      Point pt3 = new Point(mat.width() , mat.height()/6);//horizontal line
      Point pt4 = new Point(0, mat.height()/6);
      Imgproc.line(mat, pt3, pt4, new Scalar(255,0,0),6);
       pt3 = new Point(mat.width()*3/8 , 0);//vertical line left
       pt4 = new Point(mat.width()*3/8, mat.height());
      Imgproc.line(mat, pt3, pt4, new Scalar(0,255,0),6);
       pt3 = new Point(mat.width()*5/8 , 0);//vertical line right
       pt4 = new Point(mat.width()*5/8, mat.height());
      Imgproc.line(mat, pt3, pt4, new Scalar(0,255,0),6);

      // send this image back through the Streaming Server
      source1.putFrame(mat);
    }
  }

