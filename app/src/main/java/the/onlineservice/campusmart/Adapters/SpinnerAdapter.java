package the.onlineservice.campusmart.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import the.onlineservice.campusmart.Models.SpinnerModel;
import the.onlineservice.campusmart.R;

public class SpinnerAdapter  extends ArrayAdapter<SpinnerModel> {
    private ArrayList<SpinnerModel> myarrayList;

    public SpinnerAdapter(Context context, int textViewResourceId, ArrayList<SpinnerModel> modelArrayList) {
        super(context, textViewResourceId, modelArrayList);
        this.myarrayList = modelArrayList;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    @Nullable
    @Override
    public SpinnerModel getItem(int position) {
        return myarrayList.get(position);
    }

    @Override
    public int getCount() {
        int count = myarrayList.size();
        //return count > 0 ? count - 1 : count;
        return count;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, parent);
    }

    private View getCustomView(int position, ViewGroup parent) {
        final SpinnerModel model = getItem(position);

        View spinnerRow = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

        TextView label = spinnerRow.findViewById(android.R.id.text1);
        label.setText(String.format("%s", model != null ? model.getSpinnerName() : ""));


//        label.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(),model.getSpinnerName(),Toast.LENGTH_SHORT).show();
//            }
//        });
        return spinnerRow;
    }
}

