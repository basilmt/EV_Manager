package me.basilmt.evmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class UsageActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage);

        final GraphView graph = findViewById(R.id.graph);


        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(100);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(12);

        try {
            LineGraphSeries<DataPoint> series = new LineGraphSeries < > (new DataPoint[] {
                    new DataPoint(1, 74),
                    new DataPoint(2, 65),
                    new DataPoint(3, 72),
                    new DataPoint(4, 63),
                    new DataPoint(5, 75),
                    new DataPoint(6, 60),
                    new DataPoint(7, 66),
                    new DataPoint(8, 71),
                    new DataPoint(9, 73),
                    new DataPoint(10, 59),
                    new DataPoint(11, 62),
                    new DataPoint(12, 72),
            });
            graph.addSeries(series);
        } catch (IllegalArgumentException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}