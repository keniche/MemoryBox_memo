package android.lifeistech.com.memo;

import io.realm.RealmObject;

public class Memo extends RealmObject{

    public byte[] pictures;
    public int position;
    String title;
    String date;
    String folder;
    String memo;
}


