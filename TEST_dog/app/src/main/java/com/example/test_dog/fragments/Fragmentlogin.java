package com.example.test_dog.fragments;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test_dog.MainActivity;
import com.example.test_dog.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragmentlogin#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmentlogin extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FirebaseAuth mAuth;

    public Fragmentlogin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmentlogin.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmentlogin newInstance(String param1, String param2) {
        Fragmentlogin fragment = new Fragmentlogin();
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
        View view = inflater.inflate(R.layout.fragment_fragmentlogin, container, false);
        this.mAuth = FirebaseAuth.getInstance();
        Button button = view.findViewById(R.id.buttonReg);
        button.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_fragmentlogin_to_fragmentsignup));
        Button button1 = view.findViewById(R.id.buttonLog);
        button1.setOnClickListener(this::loginFunc);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mAuth.getCurrentUser() != null) {
            Navigation.findNavController(view).navigate(R.id.action_fragmentlogin_to_frontPage);
        }
    }

    public void loginFunc(View view) {
        String email = ((EditText) getView().findViewById(R.id.StartEmail)).getText().toString();
        String password = ((EditText) getView().findViewById(R.id.StartPassword)).getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getActivity(),"login failed" , Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "login ok", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.action_fragmentlogin_to_frontPage);
                    } else {
                        //Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(),"login failed" , Toast.LENGTH_LONG).show();

                        //Toast.makeText(getActivity(), "login fail", Toast.LENGTH_LONG).show();
                    }
                });
    }
}