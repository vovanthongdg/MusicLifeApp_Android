package com.example.appnghenhac.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Playlist implements Serializable {

@SerializedName("IdPlayList")
@Expose
private String idPlayList;
@SerializedName("TenPL")
@Expose
private String tenPL;
@SerializedName("HinhNen")
@Expose
private String hinhNen;
@SerializedName("HinhIcon")
@Expose
private String hinhIcon;

public String getIdPlayList() {
return idPlayList;
}

public void setIdPlayList(String idPlayList) {
this.idPlayList = idPlayList;
}

public String getTenPL() {
return tenPL;
}

public void setTenPL(String tenPL) {
this.tenPL = tenPL;
}

public String getHinhNen() {
return hinhNen;
}

public void setHinhNen(String hinhNen) {
this.hinhNen = hinhNen;
}

public String getHinhIcon() {
return hinhIcon;
}

public void setHinhIcon(String hinhIcon) {
this.hinhIcon = hinhIcon;
}

}