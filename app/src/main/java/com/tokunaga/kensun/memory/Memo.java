package com.tokunaga.kensun.memory;

import java.util.Arrays;

import io.realm.RealmObject;

public class Memo extends RealmObject {

    public byte[] pictures1 = new byte[0];
    public byte[] pictures2 = new byte[0];
    public byte[] pictures3 = new byte[0];
    public byte[] pictures4 = new byte[0];
    public byte[] pictures5 = new byte[0];

    public String updateDate = "";
    String title = "";
    String time = "";
    String folder = "";
    String episode = "";


    @Override
    public String toString() {
        return "Memo{" +
                "pictures1=" + pictures1.length+
                ", pictures2=" + pictures2.length +
                ", pictures3=" + pictures3.length +
                ", pictures4=" + pictures4.length +
                ", pictures5=" + pictures5.length +
                ", updateDate='" + updateDate + '\'' +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", folder='" + folder + '\'' +
                ", episode='" + episode + '\'' +
                '}';
    }
}


