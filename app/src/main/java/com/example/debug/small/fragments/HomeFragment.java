package com.example.debug.small.fragments;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bumptech.glide.Glide;
import com.example.debug.small.R;
import com.example.debug.small.adapter.Goodsadapter;
import com.example.debug.small.bean.Goodsbean;
import com.example.debug.small.myutils.HttpUtil;
import com.example.debug.small.myutils.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.xutils.x;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by lenovo on 2018/3/1.
 */

public class HomeFragment extends android.support.v4.app.Fragment{
    /*
    private Unbinder unbinder;
    @BindViews({ R.id.homeButton})
    public List<Button> buttons;
   @BindView(R.id.bindTxv)
   public TextView bindTxv;
   */
    private Banner banner;
    private Goodsadapter goodsadapter;
    String[] images= new String[] {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"};
    String[] titles=new String[]{"十大星级品牌联盟，全场2折起","全场2折起","十大星级品牌联盟","嗨购5折不要停","12趁现在","嗨购5折不要停，12.12趁现在","实打实大顶顶顶顶"};
    private SwipeMenuListView listView;
   private static ArrayList<Goodsbean> goodsbeans;
    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listView=view.findViewById(R.id.goodList);
        if(goodsbeans==null) {
            HttpUtil httpUtil = new HttpUtil("getnewused");
            httpUtil.get();
        }else{
            goodsadapter =new Goodsadapter(goodsbeans,getActivity());
            listView.setAdapter(goodsadapter);
           // Toast.makeText(getActivity(),"data"+goodsbeans,Toast.LENGTH_SHORT).show();
        }
        //unbinder = ButterKnife.bind(this,view);
        //final Button button2 =view.findViewById(R.id.homeButton);
        //button2.performClick();
        banner = (Banner) view.findViewById(R.id.banner);
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR	显示圆形指示器
        //2. Banner.NUM_INDICATOR	显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE	显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE	显示圆形指示器和标题
        banner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT	指示器居左
        //Banner.CENTER	指示器居中
        //Banner.RIGHT	指示器居右
        banner.setIndicatorGravity(Banner.CENTER);

        //设置轮播要显示的标题和图片对应（如果不传默认不显示标题）
        banner.setBannerTitle(titles);

        //设置是否自动轮播（不设置则默认自动）
        banner.isAutoPlay(true)	;

        //设置轮播图片间隔时间（不设置默认为2000）
        banner.setDelayTime(5000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        //banner.setImages(images);

        //自定义图片加载框架
        banner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                System.out.println("加载中");
                x.image().bind(view,url.toString());
               // Glide.with(getActivity()).load(url).into(view);
                System.out.println("加载完");
            }
        });
        //设置点击事件，下标是从1开始
        banner.setOnBannerClickListener(new Banner.OnBannerClickListener() {//设置点击事件
            @Override
            public void OnBannerClick(View view, int position) {
                Toast.makeText(getActivity(), "你点击了：" + position, Toast.LENGTH_LONG).show();
            }
        });
        RefreshLayout refreshLayout = view.findViewById(R.id.refresh);
        refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                HttpUtil httpUtil = new HttpUtil("getnewused");
                httpUtil.get();
                Toast.makeText(getActivity(),"已更新",Toast.LENGTH_SHORT).show();
                refreshlayout.finishRefresh(2000);
            }
        });
      refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
          @Override
          public void onLoadmore(RefreshLayout refreshlayout) {
              refreshlayout.finishLoadmore(2000);
          }
      });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                openItem.setTitle("打开");
                openItem.setTitleSize(18);
                openItem.setTitleColor(Color.WHITE);
                openItem.setWidth(dp2px(90));
                menu.addMenuItem(openItem);
                SwipeMenuItem delItem = new SwipeMenuItem(getActivity());
                delItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
                delItem.setTitle("删除");
                delItem.setTitleSize(18);
                delItem.setTitleColor(Color.WHITE);
                delItem.setWidth(dp2px(90));
                menu.addMenuItem(delItem);
            }
        };
        listView.setMenuCreator(creator);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        Toast.makeText(getActivity(),"open",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                      //  Toast.makeText(getActivity(),"del"+goodsbeans,Toast.LENGTH_SHORT).show();
                        goodsbeans.remove(position);
                        goodsadapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {

            }

            @Override
            public void onSwipeEnd(int position) {

            }
        });
        listView.setOnMenuStateChangeListener(new SwipeMenuListView.OnMenuStateChangeListener() {
            @Override
            public void onMenuOpen(int position) {

            }

            @Override
            public void onMenuClose(int position) {

            }
        });
        return view;
    }

    /*
    @OnClick (R.id.homeButton)
    void sendmessage(){
       HttpUtil httpUtil =new HttpUtil("getnewused");
       httpUtil.get();
    }*/
    /*
    @OnClick(R.id.bindTxv)
    void clear(){
        EventBus.getDefault().post(new Message("0"));
    }
    */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Message event) {

        if (event.getMsg() != null) {
            //System.out.println("onEventMainThread:"+event.getMsg()+" "+Thread.currentThread().getName());
            String json=event.getMsg();
            Gson gson = new Gson();
            Type type=new TypeToken<ArrayList<Goodsbean>>(){}.getType();
            goodsbeans = gson.fromJson(json, type);
            goodsadapter =new Goodsadapter(goodsbeans,getActivity());
            listView.setAdapter(goodsadapter);
          //  bindTxv.setText(event.getMsg());
        }else {
            System.out.println("event:"+event);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }
    private int dp2px(int dp){
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources()
                .getDisplayMetrics());
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}