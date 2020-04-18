package au.edu.unsw.infs3634.assignmentprototype;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity implements Learn.OnFragmentInteractionListener, QuizFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Logic to swap fragment
        Fragment fragment = new Learn();
        swapFragment(fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_search) {
                    Fragment fragment = new Learn();
                    swapFragment(fragment);
                    return true;
                } else if (menuItem.getItemId() == R.id.nav_quiz) {
                    Fragment fragment = new QuizFragment();
                    swapFragment(fragment);
                    return true;
                }
                return false;
            }

        });
    }

    //Fragment swap helper method
    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    //Used to hide keyboard when user clicks screen
    public void hide(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}



/*
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomePage extends Fragment implements View.OnClickListener {

    public HomePage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_home_page, container, false);

        ImageButton learnBtn = (ImageButton) rootView.findViewById(R.id.learn);
        ImageButton playBtn = (ImageButton) rootView.findViewById(R.id.playBtn);

        learnBtn.setOnClickListener(this);
        playBtn.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.learn:
                fragment = new Learn();
                replaceFragment(fragment);
                break;

            case R.id.playBtn:
                fragment = new QuizFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_slot, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


*/