package com.example.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button add=(Button)findViewById(R.id.AddMovie);
        Button show=(Button)findViewById(R.id.ShowMovies);

        final EditText name=(EditText)findViewById(R.id.MovieNameText);
        final EditText desc=(EditText)findViewById(R.id.MovieDescriptionText);
        final MovieDBHelper newMovie =new MovieDBHelper(this);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values from the text fields
                String movieName = name.getText().toString();
                String movieDesc = desc.getText().toString();

                // Check if the input values are empty or not
                if (movieName.isEmpty() || movieDesc.isEmpty()) {
                    // Show an error message if any of the input values are empty
                    Toast.makeText(getApplicationContext(), "Movie name and description cannot be empty", Toast.LENGTH_LONG).show();
                } else {
                    // Create a new movie object with the input values
                    newMovie.createNewMovie(movieName, movieDesc);
                    // Show a success message
                    Toast.makeText(getApplicationContext(), "Movie Added", Toast.LENGTH_LONG).show();
                }
            }






        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent allMovies=new Intent(MainActivity.this,ShowMovies.class);
                startActivity(allMovies);


            }
        });





    }
}