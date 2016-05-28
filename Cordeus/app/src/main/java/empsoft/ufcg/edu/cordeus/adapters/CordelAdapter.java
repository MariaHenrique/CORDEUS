package empsoft.ufcg.edu.cordeus.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.models.Cordel;
import empsoft.ufcg.edu.cordeus.views.CordelViewHolder;
import empsoft.ufcg.edu.cordeus.views.OnItemClickListener;

public class CordelAdapter extends RecyclerView.Adapter<CordelViewHolder> {
    private List<Cordel> mCordels;
    private OnItemClickListener clickListener;
    private int type_item;

    public CordelAdapter(List<Cordel> cordels, int type_item, OnItemClickListener onItemClickListener) {
        mCordels = cordels;
        this.type_item = type_item;
        clickListener = onItemClickListener;
    }

    @Override
    public CordelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView;
        if (type_item == 1){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_new, parent, false);
        }
            return new CordelViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CordelViewHolder holder, int position) {
        holder.bind(mCordels.get(position), clickListener);
    }

    @Override
    public int getItemCount() {
        return mCordels.size();
    }
}
