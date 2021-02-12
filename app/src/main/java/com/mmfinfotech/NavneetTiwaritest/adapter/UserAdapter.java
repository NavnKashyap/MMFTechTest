package com.mmfinfotech.NavneetTiwaritest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mmfinfotech.NavneetTiwaritest.R;
import com.mmfinfotech.NavneetTiwaritest.model.DataDTO;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {


    Context mContext;

    int pos=-1;
    private ArrayList<DataDTO> dataDTOS;
    public UserAdapter(Context mContext,ArrayList<DataDTO> dataDTOS) {
        this.mContext = mContext;
        this.dataDTOS = dataDTOS;
    }

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_user, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAdapter.MyViewHolder holder, final int position) {



    holder.tvName.setText(dataDTOS.get(position).getFirst_name()+" "+dataDTOS.get(position).getLast_name());
    holder.tvEmail.setText(dataDTOS.get(position).getEmail());
        Glide.with(mContext)
                .load(dataDTOS.get(position).getAvatar())
                .error(R.drawable.browse)
                .into(holder.civAvtar);
        holder.cbCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos=position;
                if(holder.cbCheck.isChecked()){
                    dataDTOS.get(pos).setChecked(true);
                    Log.e("TAG", "onClick: "+true );
                }else {
                    dataDTOS.get(pos).setChecked(false);
                    Log.e("TAG", "onClick: "+false );

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataDTOS.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName,tvEmail;
        CircleImageView civAvtar;
        CheckBox cbCheck;

        public MyViewHolder(View view) {
            super(view);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            cbCheck = (CheckBox) itemView.findViewById(R.id.cbCheck);
            tvEmail = (TextView) itemView.findViewById(R.id.tvEmail);
            civAvtar = (CircleImageView) itemView.findViewById(R.id.civAvtar);


        }
    }
}
