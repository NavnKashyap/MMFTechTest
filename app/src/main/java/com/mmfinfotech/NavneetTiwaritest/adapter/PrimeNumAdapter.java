package com.mmfinfotech.NavneetTiwaritest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mmfinfotech.NavneetTiwaritest.R;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumAdapter extends RecyclerView.Adapter<PrimeNumAdapter.MyViewHolder> {


    Context mContext;
    List<Integer> primes ;


    public PrimeNumAdapter(Context mContext,   List<Integer> primes ) {
        this.mContext = mContext;
        this.primes = primes;
    }

    @NonNull
    @Override
    public PrimeNumAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_prime, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final PrimeNumAdapter.MyViewHolder holder, final int position) {



    holder.tvNum.setText(String.valueOf(primes.get(position)));
    }

    @Override
    public int getItemCount() {
        return primes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tvNum;

        public MyViewHolder(View view) {
            super(view);

            tvNum = (TextView) itemView.findViewById(R.id.tvNum);


        }
    }
}
