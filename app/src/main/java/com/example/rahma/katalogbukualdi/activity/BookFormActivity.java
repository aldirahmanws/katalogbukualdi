package com.example.rahma.katalogbukualdi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.rahma.katalogbukualdi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookFormActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edit_title)
    EditText edittitle;
    @BindView(R.id.edit_author)
    EditText editauthor;
    @BindView(R.id.edit_genre)
    EditText editgenre;
    @BindView(R.id.edit_isbn)
    EditText editisbn;
    @BindView(R.id.edit_year)
    EditText edityear;
    @BindView(R.id.edit_synopsis)
    EditText editsynopsis;
    @BindView(R.id.btn_submit)
    EditText btnsubmit;
    Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_form);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            book = (Book) bundle.getSerializable("bookEdit");
            editisbn.setText(book.getISBN());
            edityear.setText(book.getPublished_year());
            editauthor.setText(book.getBook_author());
            edittitle.setText(book.getBook_title());
            editgenre.setText(book.getBook_genre());
            editsynopsis.setText(book.getBook_synopsis());
            btnsubmit.setEnabled(false);
            getSupportActionBar().setTitle(book.getBook_title());
        } else {
            book = new Book();
        }

        btnsubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (validate()) {
                    book.setISBN(editisbn.getText().toString());
                    book.setBook_title(edittitle.getText().toString());
                    book.setBook_author(editauthor.getText().toString());
                    book.setBook_genre(Integer.parseInt(edityear.getText().toString()));
                    book.setBook_genre(editgenre.getText().toString());
                    book.setBook_synopsis(editsynopsis.getText().toString().equals("") ? "~" :
                            editsynopsis.getText().toString());

                    Intent i = new Intent();
                    i.putExtra("book", book);
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validate() {
        boolean valid = true;

        String isbn = editisbn.getText().toString();
        String booktitle = edittitle.getText().toString();
        String bookauthor = editauthor.getText().toString();
        String publishedyear = edityear.getText().toString();

        if (isbn.isEmpty()) {
            editisbn.setError("Enter ISBN");
            valid = false;
        } else {
            editisbn.setError(null);
        }

        if (booktitle.isEmpty()) {
            edittitle.setError("Enter Book Title");
            valid = false;
        } else {
            edittitle.setError(null);
        }

        if (bookauthor.isEmpty()) {
            editauthor.setError("Enter Book Author");
            valid = false;
        } else {
            editauthor.setError(null);
        }
        if (publishedyear.isEmpty() || publishedyear.length() < 4) {
            edityear.setError("Published Year Empty or must in YYYY format");
            valid = false;
        } else {
            edityear.setError(null);
        }
        return valid;
    }

}