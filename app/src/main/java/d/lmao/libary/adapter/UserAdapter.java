package d.lmao.libary.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import d.lmao.libary.ChangePasswordActivity;
import d.lmao.libary.DAO.UserDAO;
import d.lmao.libary.R;
import d.lmao.libary.model.User;

public class UserAdapter extends BaseAdapter  implements Filterable {

    private List<User> userList;
    private List<User> listsort;
    private Filter FFfilter;
    private Activity context;
    private UserDAO userDAO;

    private final LayoutInflater inflater;

    public UserAdapter(Activity context, List<User> userList) {
        super();
        this.userList = userList;
        this.listsort = listsort;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        userDAO = new UserDAO(context);
    }




    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
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
            view = inflater.inflate(R.layout.item_user,viewGroup,false);
            holder.imgUser=view.findViewById(R.id.imgUser);
            holder.tvNameUser = view.findViewById(R.id.tvNameUser);



            view.setTag(holder);




        }else
            holder = (ViewHolder) view.getTag();

        User user = userList.get(position);
        holder.tvNameUser.setText(user.getName());


        byte[] img = userList.get(position).getAnh();
        Bitmap imgBitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
        holder.imgUser.setImageBitmap(imgBitmap);
        return view;


    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder {
        public TextView tvNameUser;

        public ImageView imgUser;


    }

    public void changeDataset(List<User> items) {
        this.userList = items;
        notifyDataSetChanged();
    }
}
