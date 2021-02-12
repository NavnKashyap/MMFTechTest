package com.mmfinfotech.NavneetTiwaritest.browse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mmfinfotech.NavneetTiwaritest.MainActivity;
import com.mmfinfotech.NavneetTiwaritest.R;
import com.mmfinfotech.NavneetTiwaritest.SelectedProfile;
import com.mmfinfotech.NavneetTiwaritest.TabActivity;
import com.mmfinfotech.NavneetTiwaritest.adapter.UserAdapter;
import com.mmfinfotech.NavneetTiwaritest.https.HttpsRequest;
import com.mmfinfotech.NavneetTiwaritest.interfacess.Consts;
import com.mmfinfotech.NavneetTiwaritest.interfacess.Helper;
import com.mmfinfotech.NavneetTiwaritest.model.DataDTO;
import com.mmfinfotech.NavneetTiwaritest.model.UserDTO;
import com.mmfinfotech.NavneetTiwaritest.utils.EndlessRecyclerOnScrollListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrowseFragment extends Fragment implements  View.OnClickListener{


    TabActivity homeActivity;

    boolean request = false;
    private static final String TAG = BrowseFragment.class.getSimpleName();
    LinearLayoutManager mLayoutManager;
    View view;
    int page=1;
    UserDTO userDTO= new UserDTO();
    String per_page="",total="",total_pages="";
    RecyclerView rvUsers;
    UserAdapter userAdapter;
    private int currentVisibleItemCount = 0;
    ArrayList<DataDTO> dataDTOS=new ArrayList<>();
    ArrayList<DataDTO> tempList=new ArrayList<>();
    Button btnGetSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view= inflater.inflate( R.layout.fragment_browse, container, false);


        rvUsers = view.findViewById(R.id.rvUsers);
        btnGetSelected = view.findViewById(R.id.btnGetSelected);

        setData();
        return view;
    }

        private void setData() {


            tempList = new ArrayList<>();
            mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
            rvUsers.setLayoutManager(mLayoutManager);

            rvUsers.setOnScrollListener(new EndlessRecyclerOnScrollListener(mLayoutManager) {
                @Override
                public void onLoadMore(int current_page, int totalItemCount) {
                    currentVisibleItemCount = totalItemCount;
                    if (request) {
                        page = page + 1;
                        getData();

                    }else {
                        page = 1;

                    }

                }
            });
            getData();


        }





        private void getData() {
        new HttpsRequest(String.valueOf(page),getActivity()).stringGet(TAG, new Helper() {
            @Override
            public void backResponse(boolean flag, String msg, JSONObject response) {
                if(true){
                    int tot=0;
                    try {
//                        userDTO= new Gson().fromJson(response.getJSONObject("").toString(), UserDTO.class);

                        page= Integer.parseInt(new Gson().toJson(response.get("page")));
                        per_page=new Gson().toJson(response.get("per_page"));
                        total=new Gson().toJson(response.get("total"));
                        total_pages=new Gson().toJson(response.get("total_pages"));
                        tot= Integer.parseInt(total);
                        if(page<=tot){
                            request=true;
                        }else {
                            request=false;
                        }
                        dataDTOS = new ArrayList<>();
                        Type getpetDTO = new TypeToken<List<DataDTO>>() {
                        }.getType();
                        dataDTOS = (ArrayList<DataDTO>) new Gson().fromJson(response.getJSONArray("data").toString(), getpetDTO);


                        showData();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }
        });


        }


    public void showData() {
        tempList.addAll(dataDTOS);
        dataDTOS = tempList;

        userAdapter = new UserAdapter(getActivity(), dataDTOS);
        rvUsers.setAdapter(userAdapter);
        rvUsers.smoothScrollToPosition(currentVisibleItemCount);
        rvUsers.scrollToPosition(currentVisibleItemCount - 1);

        btnGetSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempList= new ArrayList<>();
                int j=0;
                for(int i=0;i<dataDTOS.size();i++){

                    if(dataDTOS.get(i).getChecked()){

                        tempList.add(j,dataDTOS.get(i));
                        j++;
                    }
                }


                Intent in =new Intent(getActivity(), SelectedProfile.class);
                in.putExtra(Consts.DTO,tempList);
                startActivity(in);

            }
        });
        ////////////////////////////////////
        ////////////////////////////////////

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
