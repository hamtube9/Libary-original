package d.lmao.libary.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import d.lmao.libary.DAO.BookDAO;
import d.lmao.libary.ListSachActivity;
import d.lmao.libary.R;
import d.lmao.libary.model.Book;
import d.lmao.libary.model.User;

public class BookAdapter extends BaseAdapter implements Filterable {

    List<Book> list;
    List<Book> listSort;
    private Filter FFfilter;
    private Activity context;
    private BookDAO bookDAO;

    private final LayoutInflater inflater;

    public BookAdapter (Activity context, List<Book> FFList) {
        super();
        this.list = FFList;
        this.listSort = FFList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        bookDAO = new BookDAO(context);
    }





    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_sach,parent,false);
            holder.imgSach=convertView.findViewById(R.id.imgSach);
            holder.tvBookTenSach=convertView.findViewById(R.id.tvTenSach);
            holder.tvBookMaSach=convertView.findViewById(R.id.tvMaSach);
            holder.tvBookGia=convertView.findViewById(R.id.tvGiaSach);
            holder.tvBookSoLuong=convertView.findViewById(R.id.tvSoLuong);




            byte[] img = list.get(position).getAnh();
            Bitmap imgBitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
            holder.imgSach.setImageBitmap(imgBitmap);


        }else
            holder = (ViewHolder) convertView.getTag();

        Book book=list.get(position);
        holder.tvBookTenSach.setText(book.getTensach());
        holder.tvBookMaSach.setText(book.getMasach());
        holder.tvBookGia.setText(book.getGia());
        holder.tvBookSoLuong.setText(book.getSoluong());



        byte[] img = list.get(position).getAnh();
        Bitmap imgBitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        holder.imgSach.setImageBitmap(imgBitmap);
        return convertView;



    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder {
        private ImageView imgSach;
        private TextView tvBookTenSach,tvBookMaSach,tvBookGia,tvBookSoLuong;
    }



    public void changeDataset(List<Book> items){
        this.list=items;
        notifyDataSetChanged();
    }

}
