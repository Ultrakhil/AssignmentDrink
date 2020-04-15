package au.edu.unsw.infs3634.assignmentprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = (EditText)findViewById(R.id.username);
    }
    public void doSomething(View view){
        Intent i1 = new Intent(this, HomePage.class);
        i1.putExtra("user",e1.getText().toString());
        startActivity(i1);
    }
}
