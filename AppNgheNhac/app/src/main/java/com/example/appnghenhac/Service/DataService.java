package com.example.appnghenhac.Service;

import com.example.appnghenhac.Model.Album;
import com.example.appnghenhac.Model.Baihat;
import com.example.appnghenhac.Model.ChuDe;
import com.example.appnghenhac.Model.Playlist;
import com.example.appnghenhac.Model.Quangcao;
import com.example.appnghenhac.Model.TheLoai;
import com.example.appnghenhac.Model.Theloaitrongngay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @FormUrlEncoded
    @POST("register.php")
    Call<String> UserRegister(
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );
    @FormUrlEncoded
    @POST("login.php")
    Call<String> UserLogin(

            @Field("username") String username,
            @Field("password") String password
    );

    @GET("banner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("chudevatheloai.php")
    Call<Theloaitrongngay> GetCategoryMusic();

    @GET("albumhot.php")
    Call<List<Album>> GetAlbumhot();

    @GET("baihatyeuthich.php")
    Call<List<Baihat>> GeBaiHatHot();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetListSongbyQuangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetListSongbyPlaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetDanhsachPlaylist();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetListSongbyTheLoai(@Field("idtheloai") String idtheloai);

    @GET("fullchude.php")
    Call<List<ChuDe>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheoChude(@Field("idchude") String idchude);

    @GET("fullalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("listsong.php")
    Call<List<Baihat>> GetListSongbyAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("luotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchsong.php")
    Call<List<Baihat>> GetSearchBaihat(@Field("keyword") String keyword);
}
