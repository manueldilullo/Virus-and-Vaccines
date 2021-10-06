package it.spaceapps21.latitude.covid19.virusvaccines.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import it.spaceapps21.latitude.covid19.virusvaccines.R;
import it.spaceapps21.latitude.covid19.virusvaccines.classes.ReccomendationElement;

public class ReccomendationListAdapter extends RecyclerView.Adapter<ReccomendationListAdapter.ReccomendationListViewHolder>{

    private ArrayList<ReccomendationElement> elements;
    private AppCompatActivity main;
    private Context context;
    private LayoutInflater inflater;

    public ReccomendationListAdapter(AppCompatActivity main){
        this.main = main;
        this.context = main;
        elements = new ArrayList<ReccomendationElement>();
        inflater = LayoutInflater.from(context);
    }

    public void add(ReccomendationElement element){
        elements.add(element);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ReccomendationListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.reccomendation_row, parent, false);
        return new ReccomendationListAdapter.ReccomendationListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReccomendationListAdapter.ReccomendationListViewHolder holder, int position) {
        holder.setData(elements.get(position));
    }

    @Override
    public int getItemCount() {
        return elements.size();
    }

    public class ReccomendationListViewHolder extends RecyclerView.ViewHolder{

        public ReccomendationElement element;
        public ImageView image;
        public TextView description;

        public ReccomendationListViewHolder(View itemView){
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.ivElement);
            description = (TextView) itemView.findViewById(R.id.tvDescrizione);
        }

        public void setData(ReccomendationElement reccomendationElement){
            image.setImageResource(reccomendationElement.getImageID());
            description.setText(reccomendationElement.getDescription());
        }
    }
}
