package com.example.test_dog.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.example.test_dog.models.DogWalkerUser;
import com.example.test_dog.R;
import com.example.test_dog.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.StreamSupport;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FrontPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FrontPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recycleView;
    private LinearLayoutManager layoutManager;
    private UserAdapter adapter;
    private FirebaseDatabase database;
    private SearchView editText;

    public FrontPage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FrontPage.
     */
    // TODO: Rename and change types and number of parameters
    public static FrontPage newInstance(String param1, String param2) {
        FrontPage fragment = new FrontPage();
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
        View inflate = inflater.inflate(R.layout.fragment_front_page, container, false);
        database = FirebaseDatabase.getInstance();
        recycleView = (RecyclerView) inflate.findViewById(R.id.my_recycler_view);
        layoutManager = new LinearLayoutManager(inflate.getContext());
        recycleView.setLayoutManager(layoutManager);
        //recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setItemAnimator(new DefaultItemAnimator());
        adapter = new UserAdapter(getContext());
        inflate.findViewById(R.id.logout_button).setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            getActivity().onBackPressed();
        });
        readData().whenComplete((t, e) -> recycleView.setAdapter(adapter));
        editText=(SearchView) inflate.findViewById(R.id.searchView);
        if(editText!=null)
        {
            editText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    search(newText);
                    return true;
                }
            });
        }

        return inflate;
    }

    private void search(String str)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("dogwalkers");
        List<DogWalkerUser> myList = new LinkedList<>();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<DogWalkerUser> list = new LinkedList<>();
                StreamSupport.stream(snapshot.getChildren().spliterator(), false)
                        .map(d -> d.getValue(DogWalkerUser.class))
                        .forEach(list::add);
                adapter.setDataSet(list);

                for (DogWalkerUser object:list)
                {
                    if(object.getCity().toLowerCase().contains(str.toLowerCase()))
                    {
                        myList.add(object);
                    }
                }
                UserAdapter userAdapter=new UserAdapter(myList);
                recycleView.setAdapter(userAdapter);
                //recycleView.setAdapter(adapter.setDataSet(myList));
//                UserAdapter userAdapter = null;
//                userAdapter.setDataSet(myList);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public CompletableFuture<Void> readData() {
        CompletableFuture<Void> future = new CompletableFuture<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("dogwalkers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<DogWalkerUser> dataSet = new LinkedList<>();
                StreamSupport.stream(snapshot.getChildren().spliterator(), false)
                        .map(d -> d.getValue(DogWalkerUser.class))
                        .forEach(dataSet::add);
                adapter.setDataSet(dataSet);
                if (!future.isDone()) {
                    future.complete(null);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return future;
    }
}