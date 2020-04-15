package au.edu.unsw.infs3634.assignmentprototype;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {

    private TextView question;
    private EditText editText;
    private Button check;
    private TextView streak;
    private ImageView drink;

    public QuizFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quiz, container, false);

        question = view.findViewById(R.id.question);
        editText = view.findViewById(R.id.answer);
        check = view.findViewById(R.id.check);
        streak= view.findViewById(R.id.score);
        drink = view.findViewById(R.id.flagquestion);

        //Pull data from stored database
        DrinkDatabase db = Room.databaseBuilder(getContext(), DrinkDatabase.class , "database_name").allowMainThreadQueries().build();
        final List<Drink> drinkBank = db.drinkDao().getDrink();

        //random number generator
        final Random random = new Random();
        final int drinkRandom = random.nextInt(drinkBank.size() - 1);
        int questionRandom = random.nextInt(5 - 1) + 1;

        final String[] questions = {""};
        final String[] answers = {""};

        //Generated random numbers control random questions about random countries
        if (questionRandom == 1) {
            questions[0] = "What type of glass does a(n)" + drinkBank.get(drinkRandom).getName() + " use?";
            answers[0] = drinkBank.get(drinkRandom).getGlass();
        } else if (questionRandom == 2) {
            questions[0] = "What type of drink is a(n) " + drinkBank.get(drinkRandom).getName() + "?";
            answers[0] = drinkBank.get(drinkRandom).getType();
        } else if (questionRandom == 3) {
            questions[0] = "A(n) " + drinkBank.get(drinkRandom).getName()  + " falls under what category?";
            answers[0] = drinkBank.get(drinkRandom).getCategory();
        } else if (questionRandom == 4) {
            questions[0] = "What is the name of this drink?";

            String url = drinkBank.get(drinkRandom).getImage();

            //Load image for flag question
            GlideToVectorYou.justLoadImage(getActivity(), Uri.parse(url), drink);

            answers[0] = drinkBank.get(drinkRandom).getName();
            System.out.println(answers[0]);
        }

        question.setText(questions[0]);

        //Streak counter
        final int[] streakscore = {0};

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userAnswer = editText.getText().toString();

                int randomQuestion;
                int randomDrink;

                //Checking answer
                if(!userAnswer.equalsIgnoreCase(answers[0])) {

                    Toast.makeText(getContext(),"The answer is " + answers[0], Toast.LENGTH_SHORT).show();
                    streakscore[0] = 0;
                    streak.setText(String.valueOf(streakscore[0]));

                    drink.setImageResource(0);
                    randomDrink = random.nextInt(drinkBank.size() - 1);
                    randomQuestion = random.nextInt(5 - 1) + 1;
                    if (randomQuestion == 1) {
                        questions[0] = "What type of glass does a(n)" + drinkBank.get(randomDrink).getName() + " use?";
                        answers[0] = drinkBank.get(randomDrink).getGlass();
                    } else if (randomQuestion == 2) {
                        questions[0] = "What type of drink is a(n) " + drinkBank.get(randomDrink).getName() + "?";
                        answers[0] = drinkBank.get(randomDrink).getType();
                    } else if (randomQuestion == 3) {
                        questions[0] = "A(n) " + drinkBank.get(randomDrink).getName()  + " falls under what category?";
                        answers[0] = drinkBank.get(randomDrink).getCategory();
                    } else if (randomQuestion == 4) {
                        questions[0] = "What is the name of this drink?";
                        String url = drinkBank.get(randomDrink).getImage();
                        GlideToVectorYou.justLoadImage(getActivity(), Uri.parse(url), drink);
                        answers[0] = drinkBank.get(randomDrink).getName();
                    }

                    System.out.println(answers[0]);
                    question.setText(questions[0]);
                    editText.setText("");

                } else if (userAnswer.equalsIgnoreCase(answers[0])){
                    Toast.makeText(getContext(),"Correct!", Toast.LENGTH_SHORT).show();
                    streakscore[0] = streakscore[0] + 1;
                    if (streakscore[0] == 1) {
                        Toast toast = Toast.makeText(getContext(),"New Achievement: Sober", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();

                    } else if(streakscore[0] == 3) {
                        Toast toast = Toast.makeText(getContext(),"New Achievement: Lightweight", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else if (streakscore[0] == 6) {
                        Toast toast = Toast.makeText(getContext(),"New Achievement: Social Drinker", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else if (streakscore[0] == 11) {
                        Toast toast = Toast.makeText(getContext(),"New Achievement: Alcoholic", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    } else if (streakscore[0] == 14) {
                        Toast toast = Toast.makeText(getContext(),"Stop it. Get some help.", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
                    }

                    streak.setText(String.valueOf(streakscore[0]));

                    drink.setImageResource(0);
                    randomDrink = random.nextInt(drinkBank.size() - 1);
                    randomQuestion = random.nextInt(5 - 1) + 1;
                    if (randomQuestion == 1) {
                        questions[0] = "What type of glass does a(n)" + drinkBank.get(randomDrink).getName() + " use?";
                        answers[0] = drinkBank.get(randomDrink).getGlass();
                    } else if (randomQuestion == 2) {
                        questions[0] = "What type of drink is a(n) " + drinkBank.get(randomDrink).getName() + "?";
                        answers[0] = drinkBank.get(randomDrink).getType();
                    } else if (randomQuestion == 3) {
                        questions[0] = "A(n) " + drinkBank.get(randomDrink).getName()  + " falls under what category?";
                        answers[0] = drinkBank.get(randomDrink).getCategory();
                    } else if (randomQuestion == 4) {
                        questions[0] = "What is the name of this drink?";
                        String url = drinkBank.get(randomDrink).getImage();
                        GlideToVectorYou.justLoadImage(getActivity(), Uri.parse(url), drink);
                        answers[0] = drinkBank.get(randomDrink).getName();
                    }

                    System.out.println(answers[0]);
                    question.setText(questions[0]);
                    editText.setText("");
                }
            }
        });

        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment quiz.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}