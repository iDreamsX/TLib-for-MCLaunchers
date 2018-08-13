package fr.trxyy.launcher.fxutil;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TRectangle
  extends Rectangle
{
  public TRectangle(int x, int y, int sX, int sY)
  {
    setX(x);
    setY(y);
    setWidth(sX);
    setHeight(sY);
  }
  
  public TRectangle(int width, int height)
  {
    setWidth(width);
    setHeight(height);
  }
  
  public void setColor(Color color)
  {
    setStroke(color);
  }
}
