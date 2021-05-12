package ca.uvic.seng330.ex8.observation;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

import java.util.Map;

/**
 * A BarChart showing whale counts per Observation
 * see https://openjfx.io/javadoc/11/javafx.controls/javafx/scene/chart/BarChart.html
 */
public class ObservationChartPanel extends HBox implements Observer {
  private ObservationController controller;

  final NumberAxis xAxis = new NumberAxis();
  final CategoryAxis yAxis = new CategoryAxis();
  final BarChart<Number, String> bc = new BarChart<Number, String>(xAxis, yAxis);

  final static String orca = "Orca";
  final static String grey = "Grey";
  final static String humpback = "Humpback";
  final static String porpoise = "Porpoise";

  public BarChart<Number, String> getBarChart() {
    return bc;
  }

  public ObservationChartPanel(ObservationController control) {
    controller = control;
    controller.model.addObserver(this);

    bc.setTitle("Observation Summary");
    xAxis.setLabel("Count");
    xAxis.setId("x-axis");
    xAxis.setMinorTickVisible(false);
    yAxis.setLabel("Species");
    yAxis.setId("y-axis");
    bc.setAnimated(false);
    bc.setId("barChart");
    xAxis.setMinorTickVisible(false);
    yAxis.setLabel("Species");
    bc.setTranslateX(90);
    bc.setHorizontalGridLinesVisible(false);
    bc.setAnimated(false);
    bc.setStyle("-fx-font: normal bold 15px 'sans-serif'; -fx-border-style: solid; -fx-boarder-width: 4px; -fx-background-color: white; ");

    XYChart.Series series1 = new XYChart.Series();
    series1.setName("Observations");

    series1.getData().add(new XYChart.Data(0, orca));
    series1.getData().add(new XYChart.Data(0, grey));
    series1.getData().add(new XYChart.Data(0, humpback));
    series1.getData().add(new XYChart.Data(0, porpoise));

    bc.getData().add(series1);
    getChildren().add(bc);
  }

  @Override
  public void newObservation(Map<WhaleSpecies, Integer> obMap)
  {
    Platform.runLater(() -> {

      XYChart.Series series2 = new XYChart.Series();
      series2.setName("Observations");

      for(WhaleSpecies species : obMap.keySet()){

        Integer amount = obMap.get(species);

        series2.getData().add(new XYChart.Data(amount, species.toString()));
      }

      bc.getData().remove(0);
      bc.getData().add(series2);
    });
  }

}
