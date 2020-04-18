package au.edu.unsw.infs3634.assignmentprototype;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.password);
        Login = (Button)findViewById(R.id.btn_login);

        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }
    private void validate(String userName, String userPassword){
        if((userName == "Admin") && (userPassword == "1234")){
            Intent intent = new Intent(MainActivity.this, HomePage.class);
             startActivity(intent);

        }else{
            Login.setEnabled(false);

        }

    }

}
