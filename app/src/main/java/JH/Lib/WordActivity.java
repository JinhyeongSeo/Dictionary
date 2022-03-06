package JH.Lib;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
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

        String json = null;

        try {
            InputStream is = getAssets().open("dictionary.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("word");

            for(int i = 0; i < array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                ListItem item = new ListItem(
                        o.getString("title"),
                        o.getString("desc")
                );

                listItems.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}