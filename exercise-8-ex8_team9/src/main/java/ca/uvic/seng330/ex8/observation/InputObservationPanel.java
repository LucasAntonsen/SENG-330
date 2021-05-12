package ca.uvic.seng330.ex8.observation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class InputObservationPanel extends HBox {
  private ObservationController controller;
  private Slider aSlider = createSlider();

  public InputObservationPanel(ObservationController control) {
    controller = control;

    aSlider.setValue(0);
    aSlider.setId("slider");
    getChildren().add(aSlider);

    aSlider.valueProperty().addListener(new ChangeListener<Number>()
    {
      public void changed(ObservableValue<? extends Number> pValue, Number pOld, Number pNew)
      {
        try{
          Integer num = (int)aSlider.getValue();
          controller.prepareObservation(num);
        }catch(Exception e){
        }
      }
    });
  }

  private static Slider createSlider() {
    Slider slider = new Slider(1, 10, 5);
    slider.setShowTickMarks(true);
    slider.setShowTickLabels(true);
    slider.setMinWidth(ObservationApp.WIDTH);
    slider.setMajorTickUnit(1);
    slider.setBlockIncrement(1);
    slider.setMinorTickCount(0);
    slider.setSnapToTicks(true);
    slider.layout();
    return slider;
  }

}

