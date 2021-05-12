package ca.uvic.seng330.ex7;

public abstract class PointsClient {
  protected Points pointDB;

  public void assists(int i) {
    pointDB.setAssists(i);
  }

  public void scores(int i) {
    pointDB.setGoals(i);
  }

}
