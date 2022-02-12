package com.example.test_dog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_dog.models.DogWalkerUser;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private List<DogWalkerUser> dataSet;
    private LayoutInflater layoutInflater;

    public UserAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }
    public UserAdapter(List<DogWalkerUser> dataSet) {
        this.dataSet = dataSet;
    }

    public void setDataSet(List<DogWalkerUser> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_layout,viewGroup,false);
        //View view = layoutInflater.inflate(R.layout.cards_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int listPosition) {


        TextView nameFront = viewHolder.nameFront;
        TextView cityFront = viewHolder.cityFront;
        TextView phoneFront = viewHolder.phoneFront;
        TextView ageFront = viewHolder.ageFront;
        TextView informationFront = viewHolder.informationFront;
        TextView priceFront = viewHolder.priceFront;
        TextView linkFront = viewHolder.linkFront;
        

        DogWalkerUser dogWalkerUser = dataSet.get(listPosition);
        nameFront.setText(dogWalkerUser.getName());
        cityFront.setText(dogWalkerUser.getCity());
        phoneFront.setText(dogWalkerUser.getPhone());
        ageFront.setText(dogWalkerUser.getAge());
        informationFront.setText(dogWalkerUser.getTellMeAboutYourSelf());
        priceFront.setText(dogWalkerUser.getPrice());
        linkFront.setText(dogWalkerUser.getLinkForInstagramOrFacebook());


    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameFront;
        TextView cityFront;
        TextView phoneFront;
        TextView informationFront;
        TextView ageFront;
        TextView priceFront;
        TextView linkFront;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            nameFront =  itemView.findViewById(R.id.nameFront);
            cityFront = itemView.findViewById(R.id.cityFront);
            phoneFront = itemView.findViewById(R.id.phoneFront);
            informationFront = itemView.findViewById(R.id.informationFront);
            ageFront = itemView.findViewById(R.id.ageFront);
            priceFront = itemView.findViewById(R.id.priceFront);
            linkFront = itemView.findViewById(R.id.linkFront);
        }
    }

}
