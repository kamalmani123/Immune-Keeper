package com.example.immune_keeper2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadaptor extends RecyclerView.Adapter<myadaptor.myviewholder> {

    private Context context;
    private ArrayList<String> name;
    private ArrayList<String> phone;

    public myadaptor(Context context, ArrayList<String> name, ArrayList<String> phone) {
        this.context = context;
        this.name = name;
        this.phone = phone;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singlerow, parent, false);
        return new myviewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, final int position) {
        holder.name.setText(String.valueOf(name.get(position)));
        holder.phone.setText(String.valueOf(phone.get(position)));

        // Set OnClickListener for the report button
        holder.repbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the child's name and phone number at this position
                String childName = name.get(position);
                String childPhone = phone.get(position);

                // Create an Intent to start the ReportActivity
                Intent intent = new Intent(context, Report.class);

                // Pass the child's name and phone number to the ReportActivity
                intent.putExtra("childName", childName);
                intent.putExtra("childPhone", childPhone);

                // Start the ReportActivity
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {
        public ImageButton repbtn;
        TextView name, phone;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.displayname);
            phone = itemView.findViewById(R.id.displaymobile);
            repbtn = itemView.findViewById(R.id.repbtn); // Initialize repbtn
        }
    }
}
