# StuyVision methods:

#### void postImage(Mat frame, String label);

`postImage` puts an image to the screen with a specified label. E.g.:

```java
postImage(frame, "Camera Frame");
```

### boolean hasGui();

Returns whether your code is being run with a GUI. Useful for writing
code that posts images to a GUI when there is one, but still works
when there is no GUI.

```java
if (hasGui()) {
    postImage(frame, "Camera Frame");
}
```

### boolean postTag(String imageLabel, String tagKey, String tagValue);

Adds a tag beneath the image labelled `imageLabel`.

`tagKey` is the label of the tag. If you call `postTag` multiple times with the
same `imageLabel` and `tagKey`, it replaces the old tag value with `tagValue`.

This is useful for displaying information about your frames. For example, if
you were calculating the distance to an object, you might want to show the
distance your code has calculated below the image.

```java
postTag("Filtered frame", "Number of contours", Integer.toString(numberOfContours));
```

