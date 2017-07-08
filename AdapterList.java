package syaiful.finalpro.englishcourse.uitry;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import static android.provider.Settings.System.AIRPLANE_MODE_ON;
import java.util.ArrayList;
import java.util.HashMap;

import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.ui.Detail;

/**
 * Created by syaiful9508 on 04/07/17.
 */

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolder>{
    Context context;
    ArrayList<HashMap<String, String>> list_data;

    CustomItemClickListener listener;

    private Config config = new Config();

    public AdapterList(Context context, ArrayList<HashMap<String, String>> list_data, CustomItemClickListener listener) {
        this.context = context;
        this.list_data = list_data;
        this.listener = listener;
    }



    @Override
    public AdapterList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_card, parent, false);
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //07-07-2017
        final ViewHolder mViewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, mViewHolder.getAdapterPosition());

            }
        });

        //07-07-2017
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterList.ViewHolder holder, final int position) {
        holder.txt.setText(list_data.get(position).get(config.TAG_SUBJECTS));
        list_data.get(position).get(config.TAG_IMAGE);
        //holder.txt.setText(list.get(config.TAG_SUBJECTS));
        //binding
        //next tomorrow at Kam, jul 6 01:38
        //
        //Picasso.with(context).load(config.Image).centerCrop().fit().into(holder.img);
        /*Picasso.with(context)
                .load(list.get(config.Image)).centerCrop().fit()
                .error(R.drawable.ic_cloud_off_black_24dp)
                .into(holder.img);*/

        Glide.with(context)
                .load(config.Image + list_data.get(position).get(config.TAG_IMAGE))
                .placeholder(R.mipmap.ic_cloud)
                .into(holder.img);
        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, Detail.class);
                //in.putExtra("id",list_data.get(position).get(config.TAG_ID));

            }
        });

    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txt;
        ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            txt = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.image);
        }


        //Logic for OnItemClick
        //Date jum, jul 7


        //END OF CLASS VIEWHOLDER
    }

}
