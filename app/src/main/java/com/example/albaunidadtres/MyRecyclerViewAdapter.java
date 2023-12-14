package com.example.albaunidadtres;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    private List<Pais> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    MyRecyclerViewAdapter(Context context, List<Pais> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pais pais = mData.get(position);
        holder.myTextView.setText(pais.getNombre());
        holder.myTextViewAlumno.setText(pais.getNumeroAlumno()+"Alumno/a");
        holder.imageView.setImageResource(pais.getImagen());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public Pais getItem(int position) {
        return mData.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView myTextViewAlumno;

        ImageView imageView;
        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.textViewPais);
            myTextViewAlumno=itemView.findViewById(R.id.textViewAlumno);
            itemView.setOnClickListener(this);
            imageView=itemView.findViewById(R.id.imageView1);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }


    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}