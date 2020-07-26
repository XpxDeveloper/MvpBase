package com.xpxcoder.libbase.bean.wrap;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**************************************************************
 * @author: xpxCoder
 * CreateTime: 2020-07-26 10:55(星期日)
 * Description:
 * ************************************************************/
public class CommonIntentWrap<Data extends Parcelable> implements Parcelable {
    private int intValue;
    private long longValue;
    private boolean booleanValue;
    private String stringValue;
    private long[] longArray;
    private Data data;
    private List<Data> dataList = new ArrayList<>();

    public CommonIntentWrap() {
    }


    public long[] getLongArray() {
        return longArray;
    }

    public void setLongArray(long[] longArray) {
        this.longArray = longArray;
    }


    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public long getLongValue() {
        return longValue;
    }

    public void setLongValue(long longValue) {
        this.longValue = longValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.intValue);
        dest.writeLong(this.longValue);
        dest.writeByte(this.booleanValue ? (byte) 1 : (byte) 0);
        dest.writeString(this.stringValue);
        dest.writeLongArray(this.longArray);
        /*下面3行必须固定放在最下面*/
        dest.writeString(data.getClass().getName());
        dest.writeParcelable(this.data, flags);
        dest.writeTypedList(this.dataList);
    }


    protected CommonIntentWrap(Parcel in) {
        intValue = in.readInt();
        longValue = in.readLong();
        booleanValue = in.readByte() != 0;
        stringValue = in.readString();
        int length = in.readInt();
        if (length > 0) {
            longArray = new long[length];
            in.readLongArray(longArray);
        }
        String dataName = in.readString();
        if (!TextUtils.isEmpty(dataName)) {
            try {
                Class z = Class.forName(dataName);
                Parcelable.Creator<Data> creator = (Parcelable.Creator<Data>) z.getField("CREATOR").get(null);
                data = in.readParcelable(z.getClassLoader());
                dataList = in.createTypedArrayList(creator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static final Creator<CommonIntentWrap> CREATOR = new Creator<CommonIntentWrap>() {
        @Override
        public CommonIntentWrap createFromParcel(Parcel source) {
            return new CommonIntentWrap(source);
        }

        @Override
        public CommonIntentWrap[] newArray(int size) {
            return new CommonIntentWrap[size];
        }
    };
}
