package com.mobile.myworkout.model.datarepository.api;

import com.mobile.myworkout.model.datarepository.networkutils.NetworkService;

public class CallInstance {


    private NetworkService networkService;

    public void setInstance(NetworkService networkService){
        this.networkService = networkService;
    }
    public NetworkService getInstance() {
        return this.networkService;
    }
}
