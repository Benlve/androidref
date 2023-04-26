package com.ben.p_01_sqlite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ben.p_01_sqlite.R;
import com.ben.p_01_sqlite.bean.SQLMember;

import java.util.List;

/**
 * @author 廖新平
 * @version 1.0.0
 * @decription TODO
 * @create 2023-03-20 10:41
 */
public class SourceDataAdapter extends RecyclerView.Adapter<SourceDataAdapter.Holder> {

    private List<SQLMember> list;

    public SourceDataAdapter(List<SQLMember> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.id.setText(String.valueOf(list.get(position).getId()));
        holder.gender.setText(String.valueOf(list.get(position).getGender()));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView name;
        TextView id;
        TextView gender;

        public Holder(@NonNull View v) {
            super(v);
            name = v.findViewById(R.id.name);
            id = v.findViewById(R.id.id);
            gender = v.findViewById(R.id.gender);

        }

    }
}
