package JH.Lib.mRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import JH.Lib.R;

public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItem, Context context) {
        this.listItems = listItem;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ListItem listItem = listItems.get(position);


        holder.list_title.setText(listItem.getList_title());
        holder.list_desc.setText(listItem.getList_desc());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
