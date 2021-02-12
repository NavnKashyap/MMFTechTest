package com.mmfinfotech.NavneetTiwaritest.model;

import java.io.Serializable;
import java.util.ArrayList;

public class UserDTO implements Serializable {
    
      String   page="";
      String   per_page="";
      String   total="";
      String   total_pages="";
      ArrayList<DataDTO> data= new ArrayList<>();


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<DataDTO> getData() {
        return data;
    }

    public void setData(ArrayList<DataDTO> data) {
        this.data = data;
    }
}
