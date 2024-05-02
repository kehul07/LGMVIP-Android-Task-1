package com.kehuldroid.covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.DistrictViewHolder> {

    private Context context;
    private List<DistrictModel> districtList;

    public DistrictAdapter(Context context, List<DistrictModel> districtList) {
        this.context = context;
        this.districtList = districtList;
    }

    @NonNull
    @Override
    public DistrictViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_district, parent, false);
        return new DistrictViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictViewHolder holder, int position) {
        DistrictModel district = districtList.get(position);
        holder.districtName.setText(district.getDistrictName());
        holder.activeCases.setText("Active : "+district.getActiveCases());
        holder.confirmedCases.setText("Confirmed : "+district.getConfirmedCases());
        holder.decreasedCases.setText("Decreased : "+district.getDecreasedCases());
        holder.recoveredCases.setText("Recovered : "+district.getRecoveredCases());
    }

    @Override
    public int getItemCount() {
        return districtList.size();
    }

    public static class DistrictViewHolder extends RecyclerView.ViewHolder {
        public TextView districtName, activeCases,confirmedCases,decreasedCases ,recoveredCases;

        public DistrictViewHolder(@NonNull View itemView) {
            super(itemView);
            districtName = itemView.findViewById(R.id.districtName);
            activeCases = itemView.findViewById(R.id.activeCases);
            confirmedCases=itemView.findViewById(R.id.confirmedCases);
            decreasedCases = itemView.findViewById(R.id.decreasedCases);
            recoveredCases = itemView.findViewById(R.id.recoveredCases);
        }
    }
}
