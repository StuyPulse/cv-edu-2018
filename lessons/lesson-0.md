# Lesson 0

This lesson is reviewing of the following concepts:

* Thresholding
* Smoothing Images
* Contouring

### Functions you may want to use:

(Remember the [docs](../opencv-docs.md)!!!)

* `Imgproc.cvtColor(Mat input, Mat output, int code)`
* `Core.split(Mat frame, ArrayList<Mat> channels)`
* `Core.inRange(Mat input, Scalar lowerBound, Scalar upperBound, Mat output)`
* `Core.merge(ArrayList<Mat> channels, Mat frame)`
* `Core.bitwise_and(Mat input1, Mat input2, Mat output)`
* `Imgproc.erode(Mat input, Mat output, Mat kernel), Imgproc.dilate(Mat input, Mat output, Mat kernel)`
* `Imgproc.findContours(Mat image, ArrayList<MatOfPoint> contours, Mat hierarchy, int mode, int method)`
* `Imgproc.drawContours(Mat image, ArrayList<MatOfPoint> contours, int contourIdx, Scalar color, int thickness)`

## Task

There is a skeleton provided in `Main.java` for opening the images and GUI, and in `LessonZeroVision.java` for your processing code.
There are images provided in [this folder](../sampleImages/lesson0) that will be used for this task.

For each image in the image folder:
1. Post the original image to the GUI.
2. Convert the image to HSV.
3. Filter the images with the following values:
    * Hue: 86-93
    * Saturation: 0-255
    * Value: 83-255
4. Post each image after filtering.
5. Create a final filtered image, keeping only the images that were white in all channels.
6. Smooth the image, with erode and dilate/blur. (This may occur earlier or later than specified - up to you.)
7. Find the contours and draw them on the original image.
