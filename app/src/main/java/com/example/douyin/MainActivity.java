package com.example.douyin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView videoList;
    List<String> urls;
    ImageView record;
    LinearLayoutManager layoutManager;
    int curPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoList=findViewById(R.id.videoList);
        record=findViewById(R.id.record);
        initDate();
        initView();
    }

    private void initDate() {
        urls = new ArrayList<>();
        urls.add("http://www.liuyishou.site/video/1.mp4");
        urls.add("http://www.liuyishou.site/video/2.mp4");
        urls.add("http://www.liuyishou.site/video/3.mp4");
        urls.add("http://www.liuyishou.site/video/4.mp4");
        urls.add("http://www.liuyishou.site/video/5.mp4");
    }

    private void initView() {
        record.setOnClickListener(view->{
            startActivity(new Intent(MainActivity.this,RecordActivity.class));
        });
        final PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(videoList);
        layoutManager=new LinearLayoutManager(this);
        videoList.setLayoutManager(layoutManager);
        final VideoAdapter adapter=new VideoAdapter(urls,this);
        videoList.setAdapter(adapter);
        videoList.getItemAnimator().setChangeDuration(0);
        videoList.setItemAnimator(null);
        videoList.addOnScrollListener(new RecyclerViewPageChangeListenerHelper(pagerSnapHelper,
                new RecyclerViewPageChangeListenerHelper.OnPageChangeListener() {


                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                    }

                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        adapter.setPlay(position);
                        if(urls.size()-position<=3){
                            urls.add("http://www.liuyishou.site/video/"+String.valueOf(position+3)+".mp4");
                            adapter.notifyDataSetChanged();
                            curPosition=position;
                        }
                    }
                }));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
