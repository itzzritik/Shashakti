package io.skyhack.skyhack;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.MyViewHolder> {
    private List<Schemes> schemes;
    private HomeActivity homeActivity;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView last_date;
        ImageView thumbnail;
        MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            title.setTypeface(Typeface.createFromAsset(homeActivity.getAssets(), "fonts/exo2.ttf"));
            thumbnail = view.findViewById(R.id.thumbnail);
            last_date = view.findViewById(R.id.desc);
            last_date.setTypeface(Typeface.createFromAsset(homeActivity.getAssets(), "fonts/exo2.ttf"));
        }
    }
    SchemeAdapter(HomeActivity homeActivity,List<Schemes> schemes) {
        this.schemes = schemes;
        this.homeActivity = homeActivity;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Schemes scheme = schemes.get(position);
        holder.title.setText(scheme.getTitle());
        holder.last_date.setText(daysLeft(scheme.getDate()));
        holder.thumbnail.setImageBitmap(scheme.getThumbnail());
    }
    @Override
    public int getItemCount() {
        return schemes.size();
    }
    private String daysLeft(String date)
    {
        long diffInMillies=0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Calendar c = Calendar.getInstance();
            Date firstDate = sdf.parse(sdf.format(c.getTime()));
            Date secondDate = sdf.parse(date);
            diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
        }
        catch (Exception ignored){}
        return((TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS)+1)+" Days\nRemaining");
    }
}