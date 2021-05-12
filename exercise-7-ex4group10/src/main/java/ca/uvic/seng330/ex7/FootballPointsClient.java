package ca.uvic.seng330.ex7;

public class FootballPointsClient extends PointsClient {

  public FootballPointsClient(Points pObj) {
    pointDB = pObj;
  }

  public FootballPointsClient() {

  }

  @Override
  public void scores(int i) {
    super.scores(7*i);
  }

  /**
   * no assists in (American) football
   * @param i
   */
  @Override
  public void assists(int i) {
    super.scores(0);
  }
}
