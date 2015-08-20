package com.daimajia.slider.demo;

import java.util.HashMap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;


public class MainDemoActivity extends ActionBarActivity implements BaseSliderView.OnSliderClickListener{

    private SliderLayout mDemoSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_main);
        
        View headerView = LayoutInflater.from(this).inflate(R.layout.my_slider_layout, null);
        mDemoSlider = (SliderLayout)headerView.findViewById(R.id.my_slider);

        HashMap<String,String> http_url_maps = new HashMap<String, String>();
        http_url_maps.put("习近平接受八国新任驻华大使递交国书", "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg");
        http_url_maps.put("天津港总裁出席发布会", "http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg");
        http_url_maps.put("瑞海公司从消防鉴定到安评一路畅通无阻", "http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg");
        http_url_maps.put("Airbnb高调入华 命运将如Uber一样吗？", "http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg");

        for(String name : http_url_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(http_url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.getBundle().putString("extra",name);

           mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        
        
        
        ListView l = (ListView)findViewById(R.id.transformers);
        
        l.addHeaderView(headerView);
        
        l.setAdapter(new TransformerAdapter(this));
       
        
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mDemoSlider.setPresetTransformer(((TextView) view).getText().toString());
                Toast.makeText(MainDemoActivity.this, ((TextView) view).getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
   
}
