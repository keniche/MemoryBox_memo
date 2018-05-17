package com.tokunaga.kensun.memory;

public class Gallery {
    public Memo memo1;
    public Memo memo2;
    public Memo memo3;


    public Gallery(Memo memo1, Memo memo2, Memo memo3) {
        this.memo1 = memo1;
        this.memo2 = memo2;
        this.memo3 = memo3;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "memo1=" + memo1 +
                ", memo2=" + memo2 +
                ", memo3=" + memo3 +
                '}';
    }
}