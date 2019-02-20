package d.lmao.libary.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import d.lmao.libary.DAO.HoaDonDAO;
import d.lmao.libary.R;
import d.lmao.libary.model.HoaDon;

public class HoaDonAdapter  extends BaseAdapter implements Filterable {
    List<HoaDon> listHoaDon;
    List<HoaDon> listSort;
    private Filter FFfilter;
    private Activity context;
    private HoaDonDAO hoaDonDAO;
    private HoaDon hoaDon;

    private final LayoutInflater inflater;

    public HoaDonAdapter(Activity context, List<HoaDon> listHoaDon) {
        super();
        this.listHoaDon = listHoaDon;
        this.listSort = listSort;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        hoaDonDAO = new HoaDonDAO(context);
    }


    @Override
    public int getCount() {
        return listHoaDon.size();
    }

    @Override
    public Object getItem(int position) {
        return listHoaDon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_loai_sach,parent,false);
            holder.tvInfoHoaDon=view.findViewById(R.id.tvInfoHoaDon);

            view.setTag(holder);




        }else
            holder = (ViewHolder) view.getTag();

        HoaDon hoaDon = listHoaDon.get(position);
        holder.tvInfoHoaDon.setText(hoaDon.getId()+"\n" +hoaDon.getDatetime());


        return view;
    }

    public static class ViewHolder {
        public TextView tvInfoHoaDon;
    }

    @Override
    public Filter getFilter() {
        return null;
    }


    public void changeDataset(ArrayList<HoaDon> items){
        this.listHoaDon=items;
        notifyDataSetChanged();
    }


}
