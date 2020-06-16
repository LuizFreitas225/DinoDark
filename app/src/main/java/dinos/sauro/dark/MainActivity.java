package dinos.sauro.dark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dinos.sauro.dark.AndGraph.AGActivityGame;

public class MainActivity extends AGActivityGame {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        init( this, false);

          addScene(new TelaGame());
    }
}
