import java.util.ArrayList;
import java.util.Arrays;
import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.RotatedRect;
import org.opencv.core.Point;


public class Vision extends VisionModule {
    //Default, Minimum, Maximum
    public IntegerSliderVariable minHue = new IntegerSliderVariable("Min Hue", 0,  0, 255);

    public IntegerSliderVariable maxHue = new IntegerSliderVariable("Max Hue", 255, 0, 255);

    public IntegerSliderVariable Sat = new IntegerSliderVariable("Min Saturation", 0,  0, 255);

    public IntegerSliderVariable SatMax = new IntegerSliderVariable("Max Saturation", 255,  0, 255);

    public IntegerSliderVariable Val = new IntegerSliderVariable("Min Value", 0, 0, 255);

    public IntegerSliderVariable ValMax = new IntegerSliderVariable("Max Value", 255, 0, 255);


	public void run(Mat frame) {
		postImage(frame, "Sample");

		Mat converted = new Mat();

		Imgproc.cvtColor(frame,converted,Imgproc.COLOR_BGR2HSV);
    //Erosion
    Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new Size(5,5));
    Imgproc.erode(converted,converted, kernel);
		//Pixels nearby will set themselves closer to the color of the median of other pixels around it to eliminate variation
		//Imgproc.medianBlur(converted,converted,5);

		//Pixels in the center of a "window" will set themselves closer to the average of points around them
		Imgproc.GaussianBlur(converted,converted,new Size(7,7),5,5);

		//Create an ArrayList to hold the split image
		ArrayList<Mat> channels = new ArrayList<Mat>();
		//Split
		Core.split(converted, channels);
    //Binarizing the image allowing based on our IntegerSlider values
		Core.inRange(channels.get(0),new Scalar(minHue.value()), new Scalar(maxHue.value()),channels.get(0));
		Core.inRange(channels.get(1),new Scalar(Sat.value()), new Scalar(SatMax.value()),channels.get(1));
		Core.inRange(channels.get(2),new Scalar(Val.value()),new Scalar(ValMax.value()),channels.get(2));
    //Post the filtered image
		postImage(channels.get(0), "Hue Filtered");
		postImage(channels.get(1), "Saturation Filtered");
		postImage(channels.get(2), "Value Filtered");
    //Create hs to hold the interesction of H and S
		Mat hs = new Mat();
		Mat combined = new Mat();
    //Use bitwise_and to create our combined Matrix
		Core.bitwise_and(channels.get(0),channels.get(1),hs);
    //Post the hs matrix
		postImage(hs, "Hue and Saturation Filtered");
    //Use bitwise and with HS and Value in order to get fully filtered HSV image
		Core.bitwise_and(channels.get(2),hs,combined);

		postImage(combined, "Combined Filtered");
    //Contours will be saved in this container
		ArrayList<MatOfPoint> contours = new ArrayList<MatOfPoint>();
    //Clone the original image so that I can overlay a drawing of the contour over it later to compare
		Mat drawn = frame.clone();
    //Save down our contours
		Imgproc.findContours(combined,contours,new Mat(),Imgproc.RETR_EXTERNAL,Imgproc.CHAIN_APPROX_SIMPLE);

		/**** Method Draws simple contours****/
		//Imgprsoc.drawContours(drawn,contours,-1,new Scalar(0,255,0),3);

    //Check through all contours For each Matrix of points in contours
		for(MatOfPoint x: contours){

			//Draw the smallest possible rectangle
			RotatedRect rotatedRect = Imgproc.minAreaRect(new MatOfPoint2f(x.toArray()));
      double width = rotatedRect.size.width;
      double height = rotatedRect.size.height;
      float ratio = (float)width/ (float)height;
			//Store the vertices of the rectangle in a point array called vertices
			Point[] vertices = new Point[4];
      if (ratio < 1.1 && ratio > 0.9){
			rotatedRect.points(vertices);

			//New matrix of points
			MatOfPoint points = new MatOfPoint(vertices);

			//Draw the countours from a list of the points
			Imgproc.drawContours(drawn,Arrays.asList(points),-1,new Scalar(0,255,0),2);
    }
		}
		postImage(drawn,"Contoured");
	}
}
