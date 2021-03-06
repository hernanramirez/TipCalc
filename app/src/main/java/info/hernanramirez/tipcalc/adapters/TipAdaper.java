package info.hernanramirez.tipcalc.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import info.hernanramirez.tipcalc.R;
import info.hernanramirez.tipcalc.models.TipRecord;

/**
 * Created by hernanr on 6/5/16.
 */
public class TipAdaper extends RecyclerView.Adapter<TipAdaper.ViewHolder> {

    private Context context;
    private List<TipRecord> dateset;
    private OnItemClickListener onItemClickListener;

    public TipAdaper(Context context, List<TipRecord> dateset, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dateset = dateset;
        this.onItemClickListener = onItemClickListener;
    }

    public TipAdaper(Context context,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dateset = new ArrayList<TipRecord>();
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TipRecord element = dateset.get(position);
        String strTip = String.format(context.getString(R.string.global_message_tip),
                element.getTip());
        holder.txtContext.setText(strTip);
        holder.txtDate.setText(element.getDateFormatted());
        holder.setOnItemClickListener(element, onItemClickListener);

    }

    public void add(TipRecord record){
        dateset.add(0, record);
        notifyDataSetChanged();
    }

    public void clear(){
        dateset.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return dateset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtContent)
        TextView txtContext;
        @BindView(R.id.txtDate)
        TextView txtDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnItemClickListener(final TipRecord element, final OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(element);
                }
            });

        }
    }
}
