import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class LessonZeroVision extends VisionModule {

  public IntegerSliderVariable minHue = new IntegerSliderVariable("Min Hue", 68,  0, 255);
  public IntegerSliderVariable maxHue = new IntegerSliderVariable("Max Hue", 98, 0, 255);

  public void run(Mat frame) {
    postImage(frame, "Camera");

    Mat hsv = new Mat();
    Imgproc.cvtColor(frame, hsv, Imgproc.COLOR_BGR2HSV);

    ArrayList<Mat> channels = new ArrayList<Mat>();
    Core.split(hsv, channels);

    postImage(channels.get(0), "Start Hue");

    Core.inRange(channels.get(0), new Scalar(minHue.value()), new Scalar(maxHue.value()), channels.get(0));

    ArrayList<Mat> hueChannels = new ArrayList<Mat>();
		hueChannels.add(channels.get(0));
		hueChannels.add(channels.get(0));
		hueChannels.add(channels.get(0));

    Mat hue = new Mat();
    Core.merge(hueChannels, hue);

    postImage(hue, "Filtered Hue");

    Mat inverse = new Mat();
    Core.bitwise_not(hue, inverse);

    postImage(inverse, "Inverse Hue");

    Core.bitwise_and(frame, hue, hue);
    postImage(hue, "Only Folder");

    Core.split(frame, channels);
    ArrayList<Mat> oriHue = new ArrayList<Mat>();

		oriHue.add(channels.get(0));
	  oriHue.add(channels.get(0));
	  oriHue.add(channels.get(0));
    Core.merge(oriHue, hsv);

    postImage(hsv, "HSV");

    Core.bitwise_and(hsv, inverse, hsv);
    postImage(hsv, "HSV Background w/o Folder");

    Core.bitwise_or(hsv, hue, hsv);
    postImage(hsv, "Final");

  }

}
