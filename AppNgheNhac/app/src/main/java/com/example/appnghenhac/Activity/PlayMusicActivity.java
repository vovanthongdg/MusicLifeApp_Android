package com.example.appnghenhac.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.appnghenhac.Adapter.ViewPagerPlayListMusic;
import com.example.appnghenhac.Fragment.Fragment_Dia_Nhac;
import com.example.appnghenhac.Fragment.Fragment_Lyrics;
import com.example.appnghenhac.Fragment.Fragment_Play_ListSong;
import com.example.appnghenhac.Model.Baihat;
import com.example.appnghenhac.R;
import com.example.appnghenhac.Service.DataService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayMusicActivity extends AppCompatActivity {

    Toolbar toolbarPlaymusic;
    TextView txtTimesong, txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgPlay, imgRepeat, imgNext, imgPre, imgRandom;
    ViewPager viewPagerPlaymusic;
    public static ArrayList<Baihat> arrBaihat = new ArrayList<>();
    public static ViewPagerPlayListMusic adaperMuic;
    Fragment_Dia_Nhac fragmentDiaNhac;
    Fragment_Play_ListSong fragmentPlayListSong;
    Fragment_Lyrics fragmentLyrics;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adaperMuic.getItem(1) != null) {
                    if (arrBaihat.size() > 0) {
                        fragmentDiaNhac.PlayNhac(arrBaihat.get(0).getHinhBaiHat());
                        handler.removeCallbacks(this);
                    }else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        },500);
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgPlay.setImageResource(R.drawable.iconplay);
                    if (fragmentDiaNhac.objectAnimator!=null) {
                        fragmentDiaNhac.objectAnimator.pause();
                    }
                }else {
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.iconpause);
                        if (fragmentDiaNhac.objectAnimator!=null) {
                            fragmentDiaNhac.objectAnimator.resume();
                        }
                }
            }
        });
        imgRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgRepeat.setImageResource(R.drawable.iconsyned);
                        imgRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else {
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgRandom.setImageResource(R.drawable.iconshuffled);
                        imgRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    imgRandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else {
                    imgRandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrBaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < arrBaihat.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = arrBaihat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrBaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (arrBaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMusicMP().execute(arrBaihat.get(position).getPath());
                        fragmentDiaNhac.PlayNhac(arrBaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(arrBaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });
        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrBaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < arrBaihat.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = arrBaihat.size() -1;
                        }
                        if (repeat == true) {
                            position += 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrBaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new PlayMusicMP().execute(arrBaihat.get(position).getPath());
                        fragmentDiaNhac.PlayNhac(arrBaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(arrBaihat.get(position).getTenBaiHat());
                        UpdateTime();
                    }
                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        arrBaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                arrBaihat.add(baihat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<Baihat> baihatArrayList = intent.getParcelableArrayListExtra("cacbaihat");
                arrBaihat = baihatArrayList;
            }
        }
    }

    private void init() {
        toolbarPlaymusic = findViewById(R.id.toolbarplaymusic);
        txtTimesong = findViewById(R.id.tvtimesong);
        txtTotaltimesong = findViewById(R.id.tvtotaltimesong);
        imgPlay = findViewById(R.id.ibplay);
        imgNext = findViewById(R.id.ibnext);
        imgPre = findViewById(R.id.ibpreview);
        imgRandom = findViewById(R.id.ibsuffle);
        imgRepeat = findViewById(R.id.ibrepeat);
        sktime = findViewById(R.id.seekbarsong);
        viewPagerPlaymusic = findViewById(R.id.viewpagerplay);
        setSupportActionBar(toolbarPlaymusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarPlaymusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                arrBaihat.clear();
            }
        });
        toolbarPlaymusic.setTitleTextColor(Color.WHITE);
        fragmentDiaNhac = new Fragment_Dia_Nhac();
        fragmentLyrics = new Fragment_Lyrics();
        fragmentPlayListSong = new Fragment_Play_ListSong();
        adaperMuic = new ViewPagerPlayListMusic(getSupportFragmentManager());
        adaperMuic.AddFragment(fragmentPlayListSong);
        adaperMuic.AddFragment(fragmentDiaNhac);
        adaperMuic.AddFragment(fragmentLyrics);
        viewPagerPlaymusic.setAdapter(adaperMuic);
        viewPagerPlaymusic.setCurrentItem(1);
        if (arrBaihat.size() > 0) {
            getSupportActionBar().setTitle(arrBaihat.get(0).getTenBaiHat());
            new PlayMusicMP().execute(arrBaihat.get(0).getPath());
            imgPlay.setImageResource(R.drawable.iconpause);
        }
    }
    class PlayMusicMP extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];

        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null) {
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true) {
                    if (position < arrBaihat.size()) {
                        imgPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (position < 0) {
                            position = arrBaihat.size();
                        }
                        if (repeat == true) {
                            position -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrBaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (arrBaihat.size() - 1)) {
                            position = 0;
                        }
                        new PlayMusicMP().execute(arrBaihat.get(position).getPath());
                        fragmentDiaNhac.PlayNhac(arrBaihat.get(position).getHinhBaiHat());
                        getSupportActionBar().setTitle(arrBaihat.get(position).getTenBaiHat());

                }
                imgPre.setClickable(false);
                imgNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgPre.setClickable(true);
                        imgNext.setClickable(true);
                    }
                },5000);
                next = false;
                handler1.removeCallbacks(this,1000);
                }else {
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}