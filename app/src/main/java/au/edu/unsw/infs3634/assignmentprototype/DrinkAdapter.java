package au.edu.unsw.infs3634.assignmentprototype;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder> {

    private List<Drink> drinkToAdapt;

    public void setData(List<Drink> drinkToAdapt) {
        this.drinkToAdapt = drinkToAdapt;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.drink_summary, parent, false);

        DrinkViewHolder drinkViewHolder = new DrinkViewHolder(view);

        return drinkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkAdapter.DrinkViewHolder holder, int position) {
        final Drink drinkAtPosition = drinkToAdapt.get(position);

        holder.bind(drinkAtPosition);

        String url = drinkAtPosition.getImage();

        //github library that helps load SVG image files. These are not supported by androids default libraries
        GlideToVectorYou.justLoadImage((Activity) holder.itemView.getContext(), Uri.parse(url), holder.image);

    }

    @Override
    public int getItemCount() {
        return drinkToAdapt.size();
    }

    public static class DrinkViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public TextView name;
        public ImageView image;

        public DrinkViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.drinkName);
            image = view.findViewById(R.id.drinkImage);
        }

        public void bind(final Drink drink) {
            name.setText(drink.getName());

            //Onclick listener to bring in detailed view
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DrinkDetail.class);

                    Drink drinkSelected = drink;
                    intent.putExtra("drinkSelected", drinkSelected);

                    context.startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.slide_left, R.anim.slide_right);
                }
            });
        }

    }
}
