package com.tdroid.tjah.sqlitepractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText mEditTextWord;
    EditText mEditTextDefinition;
    DictionaryDb mDB;
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new DictionaryDb(this);
        mEditTextWord =  findViewById(R.id.et_word);
        mEditTextDefinition =
                 findViewById(R.id.et_definition);
        Button buttonAddUpdate =
                 findViewById(R.id.button_add_update);
        buttonAddUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecord();
            }
        });

        mListView =  findViewById(R.id.listView);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View
                    view, int position, long id) {
                Toast.makeText(MainActivity.this, mDB.getDefinition(id), Toast.LENGTH_SHORT).show();
            }
        });
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Records deleted = " + mDB.deleteRecord(id), Toast.LENGTH_SHORT).show();
                updateWordList();
                return true;
            }
        });
        updateWordList();


    }

    private void saveRecord() {
        mDB.saveRecord(mEditTextWord.getText().toString(),
                mEditTextDefinition.getText().toString());
        mEditTextWord.setText("");
        mEditTextDefinition.setText("");
        updateWordList();
    }

    private void updateWordList() {
        SimpleCursorAdapter simpleCursorAdapter = new
                SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                mDB.getWordList(),
                new String[]{"word"},
                new int[]{android.R.id.text1},
                0);
        mListView.setAdapter(simpleCursorAdapter);
    }


}



