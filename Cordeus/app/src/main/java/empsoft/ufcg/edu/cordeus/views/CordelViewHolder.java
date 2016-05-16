package empsoft.ufcg.edu.cordeus.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.models.Cordel;

public class CordelViewHolder extends RecyclerView.ViewHolder {
    protected ImageView imageView;

    public CordelViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.cordel_icon);
    }

    public void bind(final Cordel cordel, final OnItemClickListener listener) {
        imageView.setBackgroundColor(cordel.getIcon());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(cordel);
            }
        });
    }
}
