package com.tokunaga.kensun.memory;

import io.realm.RealmObject;

public class Memo extends RealmObject {

    public byte[] pictures;
    public String updateDate;

    String title = "";
    String time = "";
    String folder = "";
    String episode = "";
}


