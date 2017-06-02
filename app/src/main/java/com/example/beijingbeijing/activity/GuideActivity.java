package com.example.beijingbeijing.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.beijingbeijing.R;
import com.example.beijingbeijing.utils.DensityUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.bt_start_main)
    Button btStartMain;
    @BindView(R.id.ll_point_group)
    LinearLayout llPointGroup;
    @BindView(R.id.activity_guide)
    RelativeLayout activityGuide;
    @BindView(R.id.iv_red_point)
    ImageView ivRedPoint;

    private  int leftMargin;

    private ArrayList<ImageView> imageViews;
    private int[] ids = {R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();

        viewpager.setAdapter(new MyPagerAdapter());

        viewpager.addOnPageChangeListener(new MyPageChangeListener());

        ivRedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ivRedPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                leftMargin = llPointGroup.getChildAt(1).getLeft() - llPointGroup.getChildAt(0).getLeft();
            }
        });

    }


    @OnClick(R.id.bt_start_main)
    public void onViewClicked() {
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        //滑动的时候回调
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            float left = leftMargin * (positionOffset + position);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ivRedPoint.getLayoutParams();
            layoutParams.leftMargin = (int) left;
            ivRedPoint.setLayoutParams(layoutParams);
        }

        //选中某个页面时回调
        @Override
        public void onPageSelected(int position) {
            if (position == imageViews.size() - 1) {
                btStartMain.setVisibility(View.VISIBLE);
            } else {
                btStartMain.setVisibility(View.GONE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }


    private void initData() {
        imageViews = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);
            imageViews.add(imageView);


            ImageView point = new ImageView(this);
            point.setImageResource(R.drawable.guide_point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(GuideActivity.this,10),DensityUtil.dip2px(GuideActivity.this,10));
            point.setLayoutParams(params);
            if(i != 0) {
                params.leftMargin = DensityUtil.dip2px(GuideActivity.this,10);
            }
            llPointGroup.addView(point);
        }
    }


    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }


}
