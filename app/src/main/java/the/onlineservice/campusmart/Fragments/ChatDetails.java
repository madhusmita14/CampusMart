package the.onlineservice.campusmart.Fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import the.onlineservice.campusmart.R;

public class ChatDetails extends Fragment {
    ImageView backChat;

    public ChatDetails() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat_details, container, false);

        backChat = v.findViewById(R.id.backChat);
        backChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new productDetails()).commit();
            }
        });
        Toast.makeText(v.getContext(), "ChatDetails", Toast.LENGTH_SHORT).show();

        return v;
    }
}