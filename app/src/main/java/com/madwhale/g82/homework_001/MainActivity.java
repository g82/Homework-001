package com.madwhale.g82.homework_001;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.madwhale.g82.homework_001.server.ModelIdol;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ModelIdol> idolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Datas From Server
        // MainList.php?
        idolList = getDummyDatas();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv_main);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

        MainListViewAdapter adapter = new MainListViewAdapter();
        rv.setAdapter(adapter);
    }


    private ArrayList<ModelIdol> getDummyDatas() {

        ArrayList<ModelIdol> arrayList = new ArrayList<ModelIdol>();

        arrayList.add(new ModelIdol("http://cfile4.uf.tistory.com/image/2616153D56C08D6005C29F", "설현", 10000000));
        arrayList.add(new ModelIdol("http://cfile5.uf.tistory.com/image/2271EB46530613D3309DC1", "초아", 10000000));
        arrayList.add(new ModelIdol("http://cfile4.uf.tistory.com/image/2616153D56C08D6005C29F", "설현", 10000000));
        arrayList.add(new ModelIdol("http://cfile5.uf.tistory.com/image/2271EB46530613D3309DC1", "초아", 10000000));
        arrayList.add(new ModelIdol("http://cfile4.uf.tistory.com/image/2616153D56C08D6005C29F", "설현", 10000000));
        arrayList.add(new ModelIdol("http://cfile5.uf.tistory.com/image/2271EB46530613D3309DC1", "초아", 10000000));
        arrayList.add(new ModelIdol("http://cfile4.uf.tistory.com/image/2616153D56C08D6005C29F", "설현", 10000000));
        arrayList.add(new ModelIdol("http://cfile5.uf.tistory.com/image/2271EB46530613D3309DC1", "초아", 10000000));
        arrayList.add(new ModelIdol("http://cfile4.uf.tistory.com/image/2616153D56C08D6005C29F", "설현", 10000000));
        arrayList.add(new ModelIdol("http://cfile5.uf.tistory.com/image/2271EB46530613D3309DC1", "초아", 10000000));

        return arrayList;

    }

    private class MainListViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView ivThumb;
        public TextView tvName;
        public Button btnLike;


        public MainListViewHolder(View itemView) {
            super(itemView);
            ivThumb = (ImageView) itemView.findViewById(R.id.iv_thumb);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            btnLike = (Button) itemView.findViewById(R.id.btn_like);
        }
    }

    private class MainListViewAdapter extends RecyclerView.Adapter<MainListViewHolder>
    {

        @Override
        public MainListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = View.inflate(MainActivity.this, R.layout.cell_item, null);
            MainListViewHolder holder = new MainListViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MainListViewHolder holder, int position) {

            ModelIdol idol = idolList.get(position);
            holder.tvName.setText(idol.name);

            Glide.with(MainActivity.this).load(idol.imgUrl).into(holder.ivThumb);
        }

        @Override
        public int getItemCount() {
            return idolList.size();
        }
    }


}
