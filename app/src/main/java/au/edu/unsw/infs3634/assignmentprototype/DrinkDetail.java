package au.edu.unsw.infs3634.assignmentprototype;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.gson.Gson;

import java.util.List;

//Detailed Country Activity
public class DrinkDetail extends AppCompatActivity {

    private TextView name;
    private TextView category;
    private TextView type;
    private TextView glass;
    private ImageView image;
    private ImageButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_detail);

        name = findViewById(R.id.countryName1);
        category = findViewById(R.id.category);
        type = findViewById(R.id.drink);
        glass = findViewById(R.id.glass);
        image = findViewById(R.id.drinkImage);
        backButton = findViewById(R.id.backButton);

        //Back button onclick listener that goes back to list of countries
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DrinkAdapter.class);
                v.getContext().startActivity(intent);
                ((Activity)v.getContext()).overridePendingTransition(R.anim.slide_left2, R.anim.slide_right2);
            }
        });


        //Get the country object that was passed in from the recyclerview
        final Intent intent = getIntent();
        final Drink drink = (Drink) intent.getSerializableExtra("drinkSelected");

        name.setText(drink.getName());
        category.setText(drink.getCategory());
        type.setText(drink.getType());
        glass.setText(drink.getGlass());

        String url = drink.getImage();

        //Github libary to load svg images
        GlideToVectorYou.justLoadImage(this, Uri.parse(url), image);

        //Request to api
        final String currencyRequestUrl = "hhttps://www.thecocktaildb.com/api/json/v1/1/search.php?f=a" + drink.getName();

        final RequestQueue requestQueue =  Volley.newRequestQueue(this);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                //Parsing json to list of countries
                Gson gson = new Gson();
                //currencyResponse[] currencyResponse = gson.fromJson(response, currencyResponse[].class);
                //List<currencies> currencies = currencyResponse[0].getCurrencies();

                //Setting value for currency
                //currency.setText(currencies.get(0).getCode());
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, currencyRequestUrl, responseListener,
                errorListener);

        requestQueue.add(stringRequest);

    }
}