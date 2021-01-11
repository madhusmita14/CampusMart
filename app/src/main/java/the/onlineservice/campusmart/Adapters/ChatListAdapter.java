package the.onlineservice.campusmart.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import the.onlineservice.campusmart.Activities.Chat;
import the.onlineservice.campusmart.Fragments.ChatDetails;
import the.onlineservice.campusmart.Models.ChatModel;
import the.onlineservice.campusmart.R;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    List<ChatModel> chatList;
    Context context;
    Activity activity;

    public ChatListAdapter(List<ChatModel> chatList, Context context) {
        this.chatList = chatList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatListAdapter.ViewHolder holder, int position) {
        ChatModel model = chatList.get(position);

        holder.chat_tymTv.setText(model.getChat_tymTv());
        holder.chat_nameTv.setText(model.getChat_nameTv());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                FragmentTransaction transaction = ().beginTransaction();
//                transaction.replace(R.id.chatContainer,new ChatDetails()).commit();
                //holder.card.setVisibility(View.GONE);
                Toast.makeText(view.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                Chat.recyclerView.setVisibility(View.GONE);
                AppCompatActivity appCompatActivity = (AppCompatActivity)view.getContext();
                ChatDetails fragment = new ChatDetails();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.chatContainer,fragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView chat_tymTv,chat_nameTv;
        CardView card;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            chat_tymTv = itemView.findViewById(R.id.chat_tymTv);
            chat_nameTv = itemView.findViewById(R.id.chat_nameTv);
            card = itemView.findViewById(R.id.card);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(itemView.getContext(),""+getAdapterPosition(),Toast.LENGTH_SHORT).show();
//                    FragmentTransaction transaction = itemView.getContext().getS.beginTransaction();
//                    transaction.replace(R.id.chatContainer,new ChatDetails()).commit();
//                }
//            });
        }
    }
}
