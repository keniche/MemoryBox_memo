package android.lifeistech.com.memo;

import io.realm.RealmObject;

public class Memo extends RealmObject {

    public byte picture1;
    public byte picture2;
    public byte picture3;
    public byte picture4;
    public byte picture5;

    public String updateDate;

    String title = "";
    String time = "";
    String folder = "";
    String episode = "";
}


