package com.mycompany.apiprova.model;

import java.io.Serializable;
import java.util.ArrayList;
import lombok.Data;


@Data
public class SWModelList<T> implements Serializable {

    private int count;
    private String next;
    private String previous;
    private ArrayList<T> results;

    public boolean hasMore() {
        return (next != null && next.length() != 0);
    }

}
