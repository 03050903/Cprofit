package com.chen.cprofit.operation;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;

public class OperationInfo {
    private ArrayList<DetailBean> buyInfo;
    private SparseArray<ArrayList<DetailBean>> sellInfo;

    public OperationInfo() {
        buyInfo = new ArrayList<>();
        sellInfo = new SparseArray<>();

    }

    public ArrayList<DetailBean> getBuyInfo() {
        return buyInfo;
    }

    public void setBuyInfo(ArrayList<DetailBean> buyInfo) {
        this.buyInfo = buyInfo;
    }

    public SparseArray<ArrayList<DetailBean>> getSellInfo() {
        return sellInfo;
    }

    public void setSellInfo(SparseArray<ArrayList<DetailBean>> sellInfo) {
        this.sellInfo = sellInfo;
    }

    public void add(DetailBean model) {
        if (model.getOrder().isBuy()){
            buyInfo.add(model);
        }else{
            int index=model.getOrder().getIndex();
            ArrayList<DetailBean> list = sellInfo.get(index);
            if (list==null){
                list=new ArrayList<>();
                sellInfo.put(index,list);
            }
            list.add(model);
        }

    }
}

