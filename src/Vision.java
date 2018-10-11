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

public class Vision extends VisionModule {

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

		//Imgproc.


		/*
		Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2HSV);
		//Convert the weird BGR stuff into HSV

		ArrayList<Mat> channels = new ArrayList<Mat>();
		//I want an array list to hold Hue, Saturation, Value

		Core.split(frame, channels);
		//Splits into three different HSV from frame into the ArrayList

		Core.inRange(channels.get(0), new Scalar(minHue.value()), new Scalar(maxHue.value()), channels.get(0));
		//For the hue I need to convert numbers into Scalars. The repeated stuff is input then output Input filter Output Has limiters

		postImage(channels.get(0), "Hue-Filtered Frame");
		//Post the Hue filter frame
		postImage(channels.get(2), "grayscale");
		//Post the Value, which is only brightness. The brightest there is is white.

		ArrayList<Mat> hueChannels = new ArrayList<Mat>();

		hueChannels.add(channels.get(0));

		hueChannels.add(channels.get(0));

		hueChannels.add(channels.get(0));

		Mat hueFilterColor = new Mat();

		Core.merge(hueChannels, hueFilterColor);

		Imgproc.cvtColor(frame, frame, Imgproc.COLOR_HSV2BGR);

		Core.bitwise_and(frame, hueFilterColor, hueFilterColor);
		postImage(hueFilterColor, "Hue-Filtered Frame (color)");

		Core.bitwise_not(channels.get(0), channels.get(0));
		postImage(channels.get(0), "not(Hue-Filtered Frame)");

		Core.bitwise_and(channels.get(0), channels.get(2), channels.get(2));
		postImage(channels.get(2), "value but there's a hole");

		ArrayList<Mat> chaseBankChannels = new ArrayList<Mat>();
		chaseBankChannels.add(channels.get(2));
		chaseBankChannels.add(channels.get(2));
		chaseBankChannels.add(channels.get(2));

		Mat chaseBankChannel = new Mat();

		Core.merge(chaseBankChannels, chaseBankChannel);

		Core.bitwise_or(chaseBankChannel, hueFilterColor, chaseBankChannel);

		postImage(chaseBankChannel, "chaseBankChannel xd");
		*/
	}
}
