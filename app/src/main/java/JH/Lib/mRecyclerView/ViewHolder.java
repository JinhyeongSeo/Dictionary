package JH.Lib.mRecyclerView;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import JH.Lib.R;



public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView list_title;
    public TextView list_desc;

    public ViewHolder(View itemView) {
        super(itemView);

        list_title = (TextView) itemView.findViewById(R.id.list_title);
        list_desc = (TextView) itemView.findViewById(R.id.list_desc);
    }

}
