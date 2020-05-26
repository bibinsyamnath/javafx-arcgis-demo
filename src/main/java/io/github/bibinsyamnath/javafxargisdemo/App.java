
package io.github.bibinsyamnath.javafxargisdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.esri.arcgisruntime.geometry.Point;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.view.Graphic;
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.symbology.SimpleLineSymbol;
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol;

public class App extends Application {
    private final static Retrofit retrofit = new Retrofit.Builder().baseUrl("https://disease.sh/v2/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    private final static DataService service = retrofit.create(DataService.class);

    private final static MapView mapView = new MapView();
    private final static GraphicsOverlay overlay = new GraphicsOverlay();

    private final static int hexRed = 0xFFFF0000;
    private final static int hexBlue = 0xFF00FF00;

    private ProgressIndicator progressIndicator = new ProgressIndicator(ProgressIndicator.INDETERMINATE_PROGRESS);

    @Override
    public void start(Stage stage) {
        var pane = new StackPane(mapView, progressIndicator);
        VBox.setVgrow(pane, Priority.ALWAYS);

        var root = new VBox(pane);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        stage.setTitle("My Map App");
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();

        setupMap();

        service.getTotal().enqueue(new Callback<Report>() {

            @Override
            public void onResponse(Call<Report> call, Response<Report> response) {
                if (response.isSuccessful()) {
                    var report = response.body();

                    var labelTotalCases = new Label(report.getTotalCases().toString());
                    labelTotalCases.setStyle("-fx-text-fill: goldenrod");

                    var labelTotalDeaths = new Label(report.getTotalDeaths().toString());
                    labelTotalDeaths.setStyle("-fx-text-fill: crimson");

                    var labelTotalRecovered = new Label(report.getTotalRecovered().toString());
                    labelTotalRecovered.setStyle("-fx-text-fill: #36f836");

                    var labelAffectedCountries = new Label(report.getAffectedCountries().toString());
                    labelAffectedCountries.setStyle("-fx-text-fill: purple");

                    var dashboard = new HBox(10, new Label("Total Cases:"), labelTotalCases, new Label("Total Deaths:"),
                            labelTotalDeaths, new Label("Total Recovered:"), labelTotalRecovered,
                            new Label("Affected Countries:"), labelAffectedCountries);

                    dashboard.setStyle("-fx-font-size: 18;-fx-font-weight: bold;");
                    dashboard.setAlignment(Pos.CENTER);
                    dashboard.setPrefHeight(75);

                    Platform.runLater(() -> root.getChildren().add(dashboard));

                }
            }

            @Override
            public void onFailure(Call<Report> call, Throwable t) {
                System.out.println(t.getMessage());
                progressIndicator.setVisible(false);
            }

        });

        service.getAffectedCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful()) {
                    response.body().forEach(App::createPont);
                    progressIndicator.setVisible(false);
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    private static void createPont(Country country) {
        if (overlay != null) {
            var pointSymbol = new SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, hexRed, 20.0f);
            pointSymbol.setOutline(new SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, hexBlue, 2.0f));
            var point = new Point(country.getCountryInfo().getLongitude(), country.getCountryInfo().getLatitude(),
                    SpatialReferences.getWgs84());
            overlay.getGraphics().add(new Graphic(point, pointSymbol));
        }
    }

    private void setupMap() {
        if (mapView != null) {
            // var basemapType = Basemap.Type.IMAGERY_WITH_LABELS;
            var basemapType = Basemap.Type.TERRAIN_WITH_LABELS;
            var levelOfDetail = 1;
            var latitude = 34.02700;
            var longitude = -118.80543;
            var map = new ArcGISMap(basemapType, latitude, longitude, levelOfDetail);
            map.setReferenceScale(.05);
            mapView.setMap(map);

            mapView.getGraphicsOverlays().add(overlay);
        }
    }

    @Override
    public void stop() {
        if (mapView != null) {
            mapView.dispose();
        }

        System.exit(0);
    }
}
