package com.mumayi.fragmenttabtest;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.TabHost;

import com.mumayi.fragmenttabtest.fragment.CompositeFragment;
import com.mumayi.fragmenttabtest.fragment.TweetFragment;

public class MainActivity extends FragmentActivity implements CompositeFragment.OnFragmentInteractionListener, TweetFragment.OnFragmentInteractionListener {

    private FragmentTabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找控件
        tabHost = (FragmentTabHost) findViewById(R.id.la_tab);

        //初始化tabhost
        tabHost.setup(this, getSupportFragmentManager(), R.id.la_fl);//布局,必须是容器
//        tabHost.getTabWidget().setStripEnabled(false);//去下划线

        /**方式一**/
//        TabHost.TabSpec tab0 = tabHost.newTabSpec("tab0");
//        tab0.setIndicator("综合");
//
//        TabHost.TabSpec tab1 = tabHost.newTabSpec("tab1");
//        tab1.setIndicator("动弹");
//
//        //给每个fragment设置模拟数据
//        Bundle bundle0 = new Bundle();
//        bundle0.putString(CompositeFragment.ARG_PARAM1, "我是综合的内容");
//
//        Bundle bundle1 = new Bundle();
//        bundle1.putString(TweetFragment.ARG_PARAM2, "我是动弹的内容");
//
//        tabHost.addTab(tab0, CompositeFragment.class, bundle0);
//        tabHost.addTab(tab1, TweetFragment.class, bundle1);


        /**方式二 $$　使代码简洁**/
        MainTab[] mainTabs = MainTab.values();
        for (int i = 0; i < mainTabs.length; i++) {

            MainTab mainTab = mainTabs[i];//找每个枚举的fragment对象

            TabHost.TabSpec tabSpec = tabHost.newTabSpec(mainTab.getTitle());
            tabSpec.setIndicator(mainTab.getTitle());

            Bundle bundle = new Bundle();
            bundle.putString("param", mainTab.getTitle());

            tabHost.addTab(tabSpec, mainTab.getClazz(), bundle);

        }


    }

    /**
     * 使用for 循环,遍历的方式创建指示器
     */

    public enum MainTab {

        COMPOSITE(0, "综合", CompositeFragment.class, null),
        TWEET(1, "动弹", TweetFragment.class, null),
        EXPLORE(2, "发现", CompositeFragment.class, null),
        ME(3, "我", CompositeFragment.class, null);
        private int dex;
        private Class<?> clazz;
        private String title;
        private Bundle bundle;

        MainTab(int dex, String title, Class<?> clazz, Bundle bundle) {
            this.dex = dex;
            this.title = title;
            this.clazz = clazz;
            this.bundle = bundle;
        }

        public int getDex() {
            return dex;
        }

        public void setDex(int dex) {
            this.dex = dex;
        }

        public Class getClazz() {
            return clazz;
        }

        public void setClazz(Class clazz) {
            this.clazz = clazz;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Bundle getBundle() {
            return bundle;
        }

        public void setBundle(Bundle bundle) {
            this.bundle = bundle;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
