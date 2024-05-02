package com.kehuldroid.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateViewHolder> {

    private Context context;
    private List<StateModel> stateList;
    private OnItemClickListener listener;

    public StateAdapter(Context context, List<StateModel> stateList, OnItemClickListener listener) {
        this.context = context;
        this.stateList = stateList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_state, parent, false);
        return new StateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {
        StateModel state = stateList.get(position);
        holder.stateName.setText(state.getStateName());
    }

    @Override
    public int getItemCount() {
        return stateList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String stateName);
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {
        public TextView stateName;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);
            stateName = itemView.findViewById(R.id.stateName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        String stateName = stateList.get(position).getStateName();
                        listener.onItemClick(stateName);
                    }
                }
            });
        }
    }
}

