package empsoft.ufcg.edu.cordeus.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.models.Cordel;

public class ReflectionAdapter extends RecyclerView.Adapter<CordelViewHolder> {
    private List<Cordel> mCordels;
    private OnItemClickListener clickListener;

    public ReflectionAdapter(List<Cordel> cordels, OnItemClickListener onItemClickListener) {
        mCordels = cordels;
        clickListener = onItemClickListener;
    }

    @Override
    public CordelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
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
