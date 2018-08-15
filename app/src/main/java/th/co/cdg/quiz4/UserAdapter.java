package th.co.cdg.quiz4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserModelViewHolder> {

    private Context context;
    private List<UserModel> dataList = new ArrayList<>();
    private UserAdapter.ItemClickListener mClickListener;
    private UserAdapter.ItemLongClickListener mLongClickListener;

    public UserAdapter(Context context) {
        this.context = context;
    }

    public void add(UserModel userModel) {
        dataList.add(userModel);
    }

    public void clear() {
        dataList.clear();
    }

    @NonNull
    @Override
    public UserAdapter.UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_user, parent, false);
        return new UserAdapter.UserModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserModelViewHolder holder, int position) {
        holder.tvName.setText(String.format("%s, %s",dataList.get(position).getName(),String.valueOf(dataList.get(position).getAge())));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder implements OnClickListener, View.OnLongClickListener{
        CardView cv;
        TextView tvName;

        UserModelViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            tvName = itemView.findViewById(R.id.tv_name);
            cv.setOnClickListener(this);
            cv.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View view) {
            if (mLongClickListener != null) mLongClickListener.onLongItemClick(view, getAdapterPosition());
            return true;
        }
    }

    // convenience method for getting data at click position
    public UserModel getItem(int id) {
        return dataList.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    // allows clicks events to be caught
    public void setLongClickListener(ItemLongClickListener itemLongClickListener) {
        this.mLongClickListener = itemLongClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemLongClickListener {
        void onLongItemClick(View view, int position);
    }

}
