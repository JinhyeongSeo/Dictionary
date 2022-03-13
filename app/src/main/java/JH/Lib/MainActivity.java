package JH.Lib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
// import android.support.v7.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;

import JH.Lib.mRecyclerView.ListItem;
import JH.Lib.mRecyclerView.MyAdapter;

/*Intent intent = new Intent(Main.this, Password.class);

        intent.putExtra(Password.NEXT_ACTIVITY, "com.android.a.ViewProfile");

        intent.putExtra(Password.PASSWORD, "1234");

        intent.putExtra(Password.MODE, Password.MODE_CHECK_PASSWORD);

        startActivity(intent);*/


public class MainActivity extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private SearchAdapter adapter;
    private ArrayList<String> arraylist;
    private List<ListItem> listItems;

    TextView btn_sentence;
    TextView btn_word;
    TextView btn_search;
    Button button1;
    String filePassword = "1234";

/*
    Intent intent = new Intent(Main.this, Password.class);

        intent.putExtra(Password.NEXT_ACTIVITY, "JH.Lib");

        intent.putExtra(Password.PASSWORD, "1234");

        intent.putExtra(Password.MODE, Password.MODE_CHECK_PASSWORD);

    startActivity(intent);
*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V) {
                String password = ((EditText) findViewById(R.id.password)).getText().toString();
//                Toast.makeText(getApplicationContext(), password, Toast.LENGTH_SHORT).show();
                if (filePassword.equals(password)) {
                loadDictionary(password);
                }
                else{
                    Toast.makeText(getApplicationContext(),"다시 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void loadDictionary  (String password) {
        setContentView(R.layout.activity_main);
        btn_search = (TextView) findViewById(R.id.btn_search);
        btn_sentence = (TextView) findViewById(R.id.btn_sentence);
        btn_word = (TextView) findViewById(R.id.btn_word);


        btn_sentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        SentenceActivity.class);
                startActivity(intent);

            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        SearchActivity.class);
                startActivity(intent);
            }

        });
        btn_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        WordActivity.class);
                startActivity(intent);

            }
        });

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        list = new ArrayList<String>();
        if (null == listItems) {
            listItems = new ArrayList<ListItem>();
        }
        String json = null;
        try {
            InputStream is = getAssets().open("dictionary.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String cryptoString = new String(buffer, "UTF-8");

            AES256Util aesCrypto = new AES256Util(password);
            json = aesCrypto.decrypt(cryptoString);
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("alldic");

            for(int i = 0; i < array.length(); i++) {

                JSONObject o = array.getJSONObject(i);
                ListItem item = new ListItem(
                        o.getString("title"),
                        o.getString("desc")
                );
                listItems.add(item);
                list.add(item.getList_title());
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.printStackTrace(System.err);
        }


        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new SearchAdapter(list, this);

        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = editSearch.getText().toString();
                search(text);
            }
        });
    }

    public void search(String charText) {

        list.clear();

        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        else
        {
            for(int i = 0;i < arraylist.size(); i++)
            {
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    list.add(arraylist.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }


}
