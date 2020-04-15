package au.edu.unsw.infs3634.assignmentprototype;

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


}