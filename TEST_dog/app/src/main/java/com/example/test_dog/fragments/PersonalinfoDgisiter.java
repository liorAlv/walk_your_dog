package com.example.test_dog.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test_dog.MainActivity;
import com.example.test_dog.R;
import com.example.test_dog.models.DogWalkerUser;
import com.example.test_dog.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalinfoDgisiter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalinfoDgisiter extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;

    public PersonalinfoDgisiter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment personalinfoDgisiter.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalinfoDgisiter newInstance(String param1, String param2) {
        PersonalinfoDgisiter fragment = new PersonalinfoDgisiter();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personalinfo_dgisiter, container, false);
        Button button = view.findViewById(R.id.buttonToFirebase1);
        this.mAuth = FirebaseAuth.getInstance();
        button.setOnClickListener(v -> addData());
        return view;

    }

    public void addData() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String phone = ((EditText) getView().findViewById(R.id.Phone)).getText().toString();
        String name = ((EditText) getView().findViewById(R.id.Name)).getText().toString();
        String email = ((EditText) getView().findViewById(R.id.Email)).getText().toString();
        String age = ((EditText) getView().findViewById(R.id.Age)).getText().toString();
        String city = ((EditText) getView().findViewById(R.id.City)).getText().toString();
        String price = ((EditText) getView().findViewById(R.id.PricePerHour)).getText().toString();
        String TellMeAboutYourSelf = ((EditText) getView().findViewById(R.id.TellMeAboutYourself)).getText().toString();
        String LinkForInstagramOrFacebook = ((EditText) getView().findViewById(R.id.instagramOrFacebook)).getText().toString();
        if(phone.isEmpty() || name.isEmpty()|| email.isEmpty()|| age.isEmpty() || city.isEmpty() || price.isEmpty() ||TellMeAboutYourSelf.isEmpty()|| LinkForInstagramOrFacebook.isEmpty())
        {
            Toast.makeText(getActivity(),"sign up failed" , Toast.LENGTH_LONG).show();
            return;
        }


        DogWalkerUser userRegistrationToDogmen = new DogWalkerUser(name, email, phone, age, city, price, TellMeAboutYourSelf, LinkForInstagramOrFacebook);
        /*User user=new User(name,email,password) ;*/
        DatabaseReference myRef = database.getReference("dogwalkers").child(userRegistrationToDogmen.getEmail().replaceAll("\\.", "!"));
        //DatabaseReference myRef2 = database.getReference().child("user").child(User.getEmail());
        myRef.setValue(userRegistrationToDogmen)
                .addOnCompleteListener(t -> {
                    if (t.isSuccessful()) {
                        Toast.makeText(getContext(), "sign up successful", Toast.LENGTH_SHORT).show();
                        FragmentActivity activity = getActivity();
                        activity.onBackPressed();
                        activity.onBackPressed();
                    } else {
                        Toast.makeText(getContext(), t.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}