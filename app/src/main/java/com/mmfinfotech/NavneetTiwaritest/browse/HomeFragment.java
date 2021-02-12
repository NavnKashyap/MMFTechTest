package com.mmfinfotech.NavneetTiwaritest.browse;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mmfinfotech.NavneetTiwaritest.R;
import com.mmfinfotech.NavneetTiwaritest.TabActivity;
import com.mmfinfotech.NavneetTiwaritest.adapter.PrimeNumAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class HomeFragment extends Fragment implements View.OnClickListener {


    TabActivity homeActivity;

    private static final String TAG = HomeFragment.class.getSimpleName();
    GridLayoutManager layoutManager;
    View view;
    PrimeNumAdapter primeNumAdapter;
    RecyclerView rvData;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        rvData = view.findViewById(R.id.rvData);

        setData();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    private void setData() {
        List<Integer> primes = new ArrayList<>();
        IntStream.range(2, 100)
                .filter(n -> primes.parallelStream().noneMatch(p -> n % p == 0))
                .forEach(primes::add);

        layoutManager = new GridLayoutManager(getActivity(), 3, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(layoutManager);
        primeNumAdapter = new PrimeNumAdapter(getActivity(), primes);
        rvData.setAdapter(primeNumAdapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        homeActivity = (TabActivity) activity;
    }


    @Override
    public void onClick(View v) {

    }


}
