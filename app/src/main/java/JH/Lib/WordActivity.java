package JH.Lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import JH.Lib.mRecyclerView.ListItem;
import JH.Lib.mRecyclerView.MyAdapter;

public class WordActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        recyclerView = (RecyclerView) findViewById(R.id.word_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        listItems.add(new ListItem("apple","과일의 한 종류이다. 달다"));
        listItems.add(new ListItem("kiwi","과일의 한 종류이다. 시다"));
        listItems.add(new ListItem("banana","과일의 한 종류이다. 물렁히다."));

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}