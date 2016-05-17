package empsoft.ufcg.edu.cordeus.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import empsoft.ufcg.edu.cordeus.R;
import empsoft.ufcg.edu.cordeus.models.Cordel;

public class CordelViewHolder extends RecyclerView.ViewHolder {
    private LinearLayout itemCordel;
    private ImageView imCordelIcon;
    private TextView tvTitleCordel;

    public CordelViewHolder(View itemView) {
        super(itemView);
        itemCordel = (LinearLayout) itemView.findViewById(R.id.layout_cordel);
        imCordelIcon = (ImageView) itemView.findViewById(R.id.cordel_icon);
        tvTitleCordel = (TextView) itemView.findViewById(R.id.tv_cordel_title);
    }

    public void bind(final Cordel cordel, final OnItemClickListener listener) {
        imCordelIcon.setBackgroundColor(cordel.getIcon());
        tvTitleCordel.setText(cordel.getTitle());

        itemCordel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(cordel);
            }
        });
    }
}
