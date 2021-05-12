package ca.uvic.seng330.ex8;
import ca.uvic.seng330.ex8.observation.*;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.testfx.matcher.control.ListViewMatchers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

@ExtendWith(ApplicationExtension.class)
public class Tests {

  private static GridPane createPane() {
    GridPane root = new GridPane();
    root.setHgap(10);
    root.setVgap(10);
    root.setPadding(new Insets(20));
    return root;
  }

  /**
   * Will be called with {@code @Before} semantics, i. e. before each test method.
   *
   * @param stage - Will be injected by the test runner.
   */
  @Start

  private void start(Stage stage) {
    ObservationApp app = new ObservationApp();
    app.start(stage);
  }

  @Test
  void Test_Button(FxRobot robot) {
    // Test that button exists, can be clicked and has correct label
    robot.clickOn("#myButton");
    FxAssert.verifyThat("#myButton", LabeledMatchers.hasText("Submit"));
  }

  @Test
  void Test_Panel(FxRobot robot) {
    // Test that panel exists, can be clicked and has correct items
    robot.clickOn("#panel");
    FxAssert.verifyThat("#panel", ListViewMatchers.hasItems(4));
    FxAssert.verifyThat("#panel", ListViewMatchers.hasListCell("Orca"));
    FxAssert.verifyThat("#panel", ListViewMatchers.hasListCell("Humpback"));
    FxAssert.verifyThat("#panel", ListViewMatchers.hasListCell("Grey"));
    FxAssert.verifyThat("#panel", ListViewMatchers.hasListCell("Porpoise"));
  }

  @Test
  void Test_Chart(FxRobot robot) {
    // Test that bar chart exist and has x-axis and y-axis and they can be clicked
    robot.clickOn("#barChart");
    robot.clickOn("#x-axis");
    robot.clickOn("#y-axis");
  }

  @Test
  void Test_Slider(FxRobot robot) {
    // Test that slider exists and can be clicked
    robot.clickOn("#slider");
  }

  @Test
  void Initial_Chart_Values() {
    ObservationController controller = new ObservationController();
    ObservationChartPanel chart = new ObservationChartPanel(controller);
    XYChart.Series series1 = chart.getBarChart().getData().get(0);
    ArrayList<String> results = new ArrayList<>();
    results.add("Data[0,Orca,null]");
    results.add("Data[0,Grey,null]");
    results.add("Data[0,Humpback,null]");
    results.add("Data[0,Porpoise,null]");
    int i = 0;
    for (Object d : series1.getData()) {
      assertEquals(d.toString(), results.get(i++));
    }
  }

  @Test
  void Panel_List_Values() throws NoSuchFieldException {
    Field list_field = SpeciesListPanel.class.getDeclaredField("list");
    list_field.setAccessible(true);
    System.out.println(list_field);
    ObservationController controller = new ObservationController();
    SpeciesListPanel panel = new SpeciesListPanel(controller);
    ListView<String> listTest = new ListView<String>();
    ObservableList<String> items = FXCollections.observableArrayList(
            "Orca", "Humpback", "Grey", "Porpoise");
    listTest.setItems(items);
    assertEquals(panel.getList().getItems(), listTest.getItems());
  }

  @Test
  void Add_Observation_To_Data_Model() throws Exception {
    // Create new Controller
    ObservationController controller = new ObservationController();

    // Allow direct access to the data model for testing.
    Field model_field = ObservationController.class.getDeclaredField("model");
    model_field.setAccessible(true);
    ObservationData data_model = new ObservationData();
    model_field.set(controller, data_model);

    // Pass new data to the controller.
    controller.prepareObservation("Orca");
    controller.prepareObservation(2);
    controller.addCurrentObservation();

    // Check that the data model was updated properly.
    Observation o = data_model.getObservations().get(0);
    assertEquals(o.getSpecies(), WhaleSpecies.ORCA);
    assertEquals(o.getWhaleQuantity(), 2);
  }
}
