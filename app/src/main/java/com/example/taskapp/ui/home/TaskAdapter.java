package com.example.taskapp.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.interfaces.OnItemClickListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    ArrayList<Task> list = new ArrayList<>();
    private ItemClickListener itemClickListener;


    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setList(ArrayList<Task> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    public void addItems(List<Task> tasks) {
        list.addAll(tasks);
        notifyDataSetChanged();
    }

    public void deleteItems(List<Task> tasks) {
        list.removeAll(tasks);
        notifyDataSetChanged();
    }

    public void removeItem(Task task) {
        int pos = list.indexOf(task);
        this.list.remove(task);
        notifyItemRemoved(pos);
    }

    public Task getItem(int position) {
        return list.get(position);
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF03DAC5"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FF018786"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addItem(Task task) {
        list.add(task);
        notifyDataSetChanged();
    }
    public void setItem(int pos,Task task){
        list.set(pos, task);
        notifyItemChanged(pos);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView, data;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            data = itemView.findViewById(R.id.data_tv);
            textView = itemView.findViewById(R.id.task_tv);

        }

        public void bind(Task task) {
            textView.setText(task.getTitle());
            data.setText(task.getCreateAt());

            itemView.setOnClickListener(v -> {
                itemClickListener.onClick(getAdapterPosition());

            });

            itemView.setOnLongClickListener(v -> {
                itemClickListener.onLong(getAdapterPosition(), task);
                return true;
            });


        }
    }

   public interface ItemClickListener {
        void onClick(int position);

        void onLong(int position, Task task);
    }

}
