package ca.uvic.seng330.ex8.observation;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class SubmitButtonBox extends HBox {
    private ObservationController controller;

    private String label = "Submit";
    private Button button;

    public SubmitButtonBox(ObservationController control) {
        controller = control;
        button = new Button(label);
        button.setTranslateY(-40);
        button.setTranslateX(5);
        button.setId("myButton");
        button.setStyle("-fx-font-size: 15px;");

        button.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent pEvent)
            {
                controller.addCurrentObservation();
            }
        });
      getChildren().add(button);
    }

}

