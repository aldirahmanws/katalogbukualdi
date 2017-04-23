package com.example.rahma.katalogbukualdi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rahma.katalogbukualdi.R;
import com.example.rahma.katalogbukualdi.activity.Book;

import java.util.List;

/**
 * Created by rahma on 23/04/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {
    public TextView txtBookTitle; //Samakan dengan yang ada di Book_list_row.xml
    public TextView txtOtherInfo; //Samakan dengan yang ada di Book_list_row.xml
    private List<Book> books;
    private Context mContext;

    public BooksAdapter(Context context, List<Book> bookList) {
        this.books = bookList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Book book = books.get(position);
        txtBookTitle.setText(book.getBook_title());
        txtOtherInfo.setText(book.getBook_author());
    }

    @Override
    public int getItemCount() {
        return books == null ? 0 : books.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);

            txtBookTitle = (TextView) view.findViewById(R.id.txtBookTitle);
            txtOtherInfo = (TextView) view.findViewById(R.id.txtOtherInfo);
        }
    }

}
