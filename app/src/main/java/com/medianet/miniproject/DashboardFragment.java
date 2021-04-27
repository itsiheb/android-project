package com.medianet.miniproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.medianet.miniproject.R;
import com.medianet.miniproject.model.Game;
import com.medianet.miniproject.model.User;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    ArrayList<Game> games = new  ArrayList<>();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    GameAdapter adapter ;
    User user;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new GameAdapter(requireContext(),games);
        ((RecyclerView)view.findViewById(R.id.list) ).setAdapter(adapter);
        adapter.setClickListener(game -> {
            ArrayList<String> games = user.getList_games();
            if (game.getIsFavorie()){
                if (!games.contains(game.getId())){
                    games.add(game.getId());
                }
            }else {
                games.remove(game.getId());
            }

            reference
                    .child("users")
                    .child(((HomeActivity)requireActivity() ).user)
                    .child("list_games")
                    .setValue(games);
        });

        reference.child("users").child(((HomeActivity)requireActivity() ).user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                reference.child("games").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        games.clear();
                        for (DataSnapshot gameSnapshot: snapshot.getChildren()) {
                            Game game = gameSnapshot.getValue(Game.class);
                            game.setIsFavorie(user.getList_games().contains(game.getId()));
                            games.add(game);
                            // here you can access to name property like university.name
                        }
                        Log.e("sssss",games.toString());
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) { }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });


    }
}