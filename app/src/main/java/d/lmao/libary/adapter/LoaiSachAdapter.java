package d.lmao.libary.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.DAO.TypeBookDAO;
import d.lmao.libary.R;
import d.lmao.libary.model.TypeBook;

public class LoaiSachAdapter extends BaseAdapter implements Filterable {

    List<TypeBook> listTypeBook;
    List<TypeBook> listSort;
    private Filter FFfilter;
    private Activity context;
    private TypeBookDAO typeBookDAO;

    private final LayoutInflater inflater;

    public LoaiSachAdapter(Activity context, List<TypeBook> listTypeBook) {
        super();
        this.listTypeBook = listTypeBook;
        this.listSort = listSort;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        typeBookDAO = new TypeBookDAO(context);
    }
    @Override
    public int getCount() {
        return listTypeBook.size();
    }

    @Override
    public Object getItem(int position) {
        return listTypeBook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_loai_sach,viewGroup,false);
            holder.tvMaSachLS=view.findViewById(R.id.tvMaSachLS);
            holder.tvTenLS = view.findViewById(R.id.tvTenLS);

            holder.tvTacGiaLS = view.findViewById(R.id.tvTacGiaLS);
            holder.tvVitriLS = view.findViewById(R.id.tvVitriLS);
            view.setTag(holder);




        }else
            holder = (ViewHolder) view.getTag();

        TypeBook typeBook = listTypeBook.get(position);
        holder.tvMaSachLS.setText(typeBook.getMaloai());
        holder.tvTenLS.setText(typeBook.getName());
        holder.tvTacGiaLS.setText(typeBook.getTacgia());
        holder.tvVitriLS.setText(typeBook.getVitri());

            return view;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder {
        private TextView  tvMaSachLS,tvTenLS,tvTacGiaLS,tvVitriLS;
    }



    public void changeDataset(List<TypeBook> items){
        this.listTypeBook=items;
        notifyDataSetChanged();
    }


}
