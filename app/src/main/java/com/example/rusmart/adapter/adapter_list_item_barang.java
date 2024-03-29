package com.example.rusmart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.example.rusmart.Model.ModelBarang;
import com.example.rusmart.R;

import java.util.List;

public class adapter_list_item_barang extends RecyclerView.Adapter<adapter_list_item_barang.Holder> {

    private List<ModelBarang> arrayList;
    private Context context;

    public adapter_list_item_barang(Context context, List<ModelBarang> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_spa, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final ModelBarang data_item = arrayList.get(position);
        holder.textview_item_spa.setText(data_item.getNamabarang());
        holder.textview_description_item.setText("jumlah beli : "+data_item.getJumlah()+"");
        holder.textview_cost_item.setText("kode barang : "+data_item.getId());

    }

    @Override
    public int getItemCount() {
        return arrayList == null? 0 : arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView textview_item_spa,textview_description_item,textview_cost_item;

        public Holder(View itemView) {
            super(itemView);
            textview_item_spa = (TextView) itemView.findViewById(R.id.textview_name_item);
            textview_description_item = (TextView)itemView.findViewById(R.id.textview_description_item);
            textview_cost_item = (TextView)itemView.findViewById(R.id.textview_cost_item);

        }
    }
}
