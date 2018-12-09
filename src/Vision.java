import java.util.ArrayList;

import stuyvision.VisionModule;
import stuyvision.gui.VisionGui;
import stuyvision.gui.IntegerSliderVariable;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class Vision extends VisionModule {
    public LessonZeroVision lesson0 = new LessonZeroVision();

    public void run(Mat frame) {
        postImage(frame, "Camera Feed");

        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_BGR2HSV);

        ArrayList<Mat> channels = new ArrayList<Mat>();

        Core.split(frame, channels);

        postImage(channels.get(0),"Hue channel");
        postImage(channels.get(1),"Saturation channel");
        postImage(channels.get(2),"Value channel");
    }
}
