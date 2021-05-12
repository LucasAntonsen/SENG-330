package ca.uvic.seng330.ex8.observation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;

public class SpeciesListPanel extends HBox {
  private ObservationController controller;

  private static final int ROW_HEIGHT = 24;
  ListView<String> list = new ListView<String>();

  public ListView<String> getList() {
    return list;
  }

  public SpeciesListPanel(ObservationController control) {
    controller = control;

    ObservableList<String> items = FXCollections.observableArrayList (
        "Orca", "Humpback", "Grey", "Porpoise");
    list.setItems(items);
    list.setPrefHeight(items.size() * ROW_HEIGHT + 2);
    list.setId("panel");
    list.setStyle("-fx-font: normal bold 12px 'sans-serif'; ");

    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        controller.prepareObservation(newValue);
      }
    });
    getChildren().add(list);
  }

}
