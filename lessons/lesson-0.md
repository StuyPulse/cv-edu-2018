# Lesson 0

This lesson is reviewing of the following concepts:

* Thresholding
* Smoothing Images
* Contouring

### Functions you may want to use:

* `Imgproc.cvtColor()`
* `Core.split()`
* `Core.inRange()`
* `Core.merge()`
* `Core.bitwise_and`
* `Imgproc.erode(), Imgproc.dilate()`
* `Imgproc.findContours()`
* `drawContours()`

## Task

There is a skeleton provided in `Main.java` for opening the images and GUI, and in `LessonZeroVision.java` for your processing code.
There are images provided in [this folder](../sampleImages/lesson0) that will be used for this task.

For each image in the image folder:
1. Post the original image to the GUI.
2. Convert the image to HSV.
3. Filter the images with the following values:
    * Hue: 0-255 [CHANGE]
    * Saturation: 0-255 [CHANGE]
    * Value: 0-255 [CHANGE]
4. Post each image after filtering.
5. Create a final filtered image, keeping only the images that were white in all channels.
6. Smooth the image, with erode and dilate/blur. (This may occur earlier or later than specified - up to you.)
7. Find the contours and draw them on the original image.
