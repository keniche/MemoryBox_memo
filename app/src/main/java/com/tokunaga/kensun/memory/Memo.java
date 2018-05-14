package com.tokunaga.kensun.memory;

import io.realm.RealmObject;

public class Memo extends RealmObject {

    public byte[] pictures1;
    public byte[] pictures2;
    public byte[] pictures3;
    public byte[] pictures4;
    public byte[] pictures5;
    //

    public String updateDate = "";
    String title = "";
    String time = "";
    String folder = "";
    String episode = "";
}


