package com.example.demo.eatit.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.eatit.Interface.ItemClickListener;
import com.example.demo.eatit.R;

public class Menu_View_Holderr extends RecyclerView.ViewHolder implements View.OnClickListener {
    private ItemClickListener itemClickListener;
public TextView menu_name;
public ImageView menu_image;

    public Menu_View_Holderr(View itemView) {
        super(itemView);
        menu_name = (TextView)itemView.findViewById(R.id.menu_name);
        menu_image = (ImageView) itemView.findViewById(R.id.menu_image);
       // super(itemView);

    }

    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
