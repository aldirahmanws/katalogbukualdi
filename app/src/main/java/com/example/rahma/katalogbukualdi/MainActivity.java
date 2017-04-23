package com.example.rahma.katalogbukualdi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.rahma.katalogbukualdi.activity.Book;
import com.example.rahma.katalogbukualdi.adapter.BooksAdapter;
import com.example.rahma.katalogbukualdi.helper.HelperFunction;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    public int TO_FORM = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerBook)
    RecyclerView recyclerBook;
    @BindView(R.id.fab)
    FloatingActionButton btnAdd;
    private List<Book> bookList = new ArrayList<>();
    private BooksAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().setTitle("Books Catalog");

        mAdapter = new BooksAdapter(this, bookList);
        recyclerBook = (RecyclerView) findViewById(R.id.recyclerBook);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.scrollToPosition(0);
        recyclerBook.setLayoutManager(mLayoutManager);
        recyclerBook.setItemAnimator(new DefaultItemAnimator());
        recyclerBook.addItemDecoration(new DividerItemDecoration(this, 1));

        recyclerBook.setAdapter(mAdapter);
        recyclerBook.addOnItemTouchListener(new HelperFunction.RecyclerTouchListener(this, recyclerBook, new HelperFunction.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.d("APP", "Pressed");
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        recyclerBook.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        prepareBookData();


      /*  btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });*/
    }

    private void prepareBookData() {
        Book book = new Book("9780439064873", "Harry Potter And The Chamber Of Secrets", "J.K. Rowling", 2000, "Fantasy, Thriller, Mystery", "This is some synopsis", R.drawable.hp_chamber);
        bookList.add(book);

        book = new Book("9780316015844", "Twilight (The Twilight Saga, Book 1)", "Stephanie Meyer", 2006, "Fantasy, Drama, Romance", "This is some synopsis", R.drawable.twilight);
        bookList.add(book);

        book = new Book("9781484724989", "Journey to Star Wars: The Force Awakens Lost Stars", "Claudia Gray", 2015, "Action, Thriller, Science Fiction", "This is some synopsis", R.drawable.star_wars);
        bookList.add(book);

        book = new Book("9780439136365", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 2001, "Fantasy, Thriller, Mystery", "This is some synopsis", R.drawable.hp_azkaban);
        bookList.add(book);

        mAdapter.notifyDataSetChanged();
    }
}

