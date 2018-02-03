# Lesson 1

This lesson will be about how to use a processed image to find information about the position of your robot relative to the target.

We will be looking at what was done in DEStiny's year. If you want to take a look yourself, here's a [link](https://github.com/Team694/DEStiny/blob/2018-compatible/src/edu/stuy/robot/cv/StuyVision.java).

## DEStiny

In DEStiny's year, the target was a rectangle, missing a side on the top.
It was wrapped around the bottom of the high goal, which was about 7 feet above the ground and 2 feet high.
You can find a diagram of the tower [here](https://firstfrc.blob.core.windows.net/frc2016manuals/GameManual/FRC-2016-game-manual.pdf) on page 23-24.

In short terms, when CV was run, this was the sequence of events that occurred:
1. Take a picture
2. Filter the image
3. Find the largest contour in the filtered images that passes specific requirements:
    * Contour area
    * Area ratio (width / height)
4. Using a RotatedRect, return the x- and y-distance from the center of the image and the angle that the rotated rect is.
5. Using this information, calculate the distance and angle the robot needs to move to be lined up for the goal.

Let's clarify this logic.

Given the x-distance from the center of the frame, the viewing angle and the dimensions of the image can be used in a proportion to calculate the approximate angle that the object is at relative to the center, or 0 degrees.
The same can be done for the y-direction.

