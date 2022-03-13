package JH.Lib.mRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import JH.Lib.R;
import JH.Lib.SearchAdapter;


public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView list_title;
    public TextView list_desc;

    ItemClickListener itemClickListener;



    public ViewHolder(View itemView) {
        super(itemView);

        list_title = (TextView) itemView.findViewById(R.id.list_title);
        list_desc = (TextView) itemView.findViewById(R.id.list_desc);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClick(this.getLayoutPosition());
    }

}
