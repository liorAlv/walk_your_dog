package com.example.test_dog.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test_dog.MainActivity;
import com.example.test_dog.R;
import com.example.test_dog.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonalinfoFindDgisiter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonalinfoFindDgisiter extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FirebaseAuth mAuth;

    public PersonalinfoFindDgisiter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment personalinfoFindDgisiter.
     */
    // TODO: Rename and change types and number of parameters
    public static PersonalinfoFindDgisiter newInstance(String param1, String param2) {
        PersonalinfoFindDgisiter fragment = new PersonalinfoFindDgisiter();
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
        View view = inflater.inflate(R.layout.fragment_personalinfo_find_dgisiter, container, false);

        Button button = view.findViewById(R.id.buttonToFireBase2);

        button.setOnClickListener(v -> addData2());

        this.mAuth = FirebaseAuth.getInstance();


        return view;
    }

    public void addData2() {
        String email = ((EditText) getView().findViewById(R.id.EmailUser)).getText().toString();
        String password = ((EditText) getView().findViewById(R.id.PasswordUser)).getText().toString();
        if(email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(getActivity(),"sign up failed" , Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                mAuth.signOut();
                Toast.makeText(getContext(), "sign up successful", Toast.LENGTH_SHORT).show();
                getActivity().onBackPressed();
                getActivity().onBackPressed();
            } else {
                Toast.makeText(getContext(), "sign up failed", Toast.LENGTH_SHORT).show();
            }
        });
        /*
        FirebaseDatabase database = FirebaseDatabase.getInstance();



        email = email.replace('.', '!');
        User user = new User(name, email, password);

        DatabaseReference myRef2 = database.getReference().child("user").child(user.getEmail());
        myRef2.setValue(user);

 */
    }
}