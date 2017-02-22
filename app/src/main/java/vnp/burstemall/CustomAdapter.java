package vnp.burstemall;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pkapo8 on 10/6/2016.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ObjectInfo[] itemsData;
    private Context context;
    OnItemClick onClick;

    public CustomAdapter(ObjectInfo[] itemsData, Context context, OnItemClick onClick) {
        this.itemsData = itemsData;
        this.context = context;
        this.onClick = onClick;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.object_options, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView, context);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        //viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
        viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageUrl());
        viewHolder.imgViewIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClickListener(position, itemsData[position].getImageUrl() );
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(View itemLayoutView, Context context) {
            super(itemLayoutView);

           // Typeface typeHeading1 = Typeface.createFromAsset(context.getAssets(), "fonts/Montague.ttf");
            //txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.text);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.image1);

//            txtViewTitle.setTypeface(typeHeading1);
//            txtViewTitle.setTextColor(ContextCompat.getColor(context,R.color.text_red));
        }
    }

    @Override
    public int getItemCount() {
        return itemsData.length;
    }
}
