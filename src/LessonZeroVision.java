import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfPoint;

public class LessonZeroVision extends VisionModule {
      	public IntegerSliderVariable minHue = new IntegerSliderVariable("Min Hue", 0,  0, 255);
      	//Default, Minimum, Maximum
        public IntegerSliderVariable maxHue = new IntegerSliderVariable("Max Hue", 255, 0, 255);

      	public IntegerSliderVariable Sat = new IntegerSliderVariable("Min Saturation", 0,  0, 255);
      	//Default, Minimum, Maximum

      	public IntegerSliderVariable Val = new IntegerSliderVariable("Max Value", 255, 0, 255);


      	public void run(Mat frame) {
      		postImage(frame, "Sample");

      		Mat converted = new Mat();

      		Imgproc.cvtColor(frame,converted,Imgproc.COLOR_BGR2HSV);
      		ArrayList<Mat> channels = new ArrayList<Mat>();
      		Core.split(converted, channels);
      		Core.inRange(channels.get(0),new Scalar(minHue.value()), new Scalar(maxHue.value()),channels.get(0));
      		Core.inRange(channels.get(1),new Scalar(Sat.value()), new Scalar(255),channels.get(1));
      		Core.inRange(channels.get(2),new Scalar(Val.value()),new Scalar(255),channels.get(2));

      		postImage(channels.get(0), "Hue Filtered");
      		postImage(channels.get(1), "Saturation Filtered");
      		postImage(channels.get(2), "Value Filtered");

      		Mat hs = new Mat();
      		Mat combined = new Mat();

      		Core.bitwise_and(channels.get(0),channels.get(1),hs);

      		postImage(hs, "Hue and Saturation Filtered");

      		Core.bitwise_and(channels.get(2),hs,combined);

      		postImage(combined, "Combined Filtered");

      		ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();

      		Mat drawn = frame.clone();
      		Imgproc.findContours(combined,contours,new Mat(),Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);
      		Imgproc.drawContours(drawn,contours,-1,new Scalar(0,255,0),3);
      		postImage(drawn,"Contoured");
        // Your code here
        // Remember to release your Mats before setting them to something else!
        // You may create helper functions if you wish
        // You can also create variables outside this function if necessary

    }

}
