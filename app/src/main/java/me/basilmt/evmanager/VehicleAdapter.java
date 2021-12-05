package me.basilmt.evmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder> {

    List<VehicleModel> peopleModelList;
    OnVehicleClickListen onVehicleClickListen;

    public VehicleAdapter(List<VehicleModel> peopleModelList, OnVehicleClickListen onVehicleClickListen) {
        this.peopleModelList = peopleModelList;
        this.onVehicleClickListen = onVehicleClickListen;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.vehicle_single_item, parent, false);
        return new ViewHolder(view, onVehicleClickListen);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(peopleModelList.get(position).getName());
        if(peopleModelList.get(position).getV_class() == 0){
//            holder.prp.setBackgroundResource(R.drawable.scooter);
            Glide.with(holder.prp)
                    .load(holder.prp.getContext().getDrawable(R.drawable.scooter))
                    .circleCrop()
                    .into(holder.prp);
        }
        else {
//            holder.prp.setBackgroundResource(R.drawable.car);
            Glide.with(holder.prp)
                    .load(holder.prp.getContext().getDrawable(R.drawable.car))
                    .circleCrop()
                    .into(holder.prp);
        }

    }


    @Override
    public int getItemCount() {
        return peopleModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnVehicleClickListen onClickListen;
        TextView name;
        ImageView prp;

        public ViewHolder(@NonNull View itemView, OnVehicleClickListen onVehicleClickListen) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            prp = itemView.findViewById(R.id.prp);
            this.onClickListen = onVehicleClickListen;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListen.onVehicleClick(getAdapterPosition());
        }
    }
    public interface OnVehicleClickListen {
        void onVehicleClick(int position);
    }
}
