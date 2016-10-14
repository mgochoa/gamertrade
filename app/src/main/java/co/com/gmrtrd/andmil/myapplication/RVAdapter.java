package co.com.gmrtrd.andmil.myapplication;

/**
 * Created by estudiantelis on 13/10/16.
 */

        import android.content.Context;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.widget.CardView;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.squareup.picasso.Picasso;


        import java.util.List;


public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView placeName;
        TextView placeDescription;
        ImageView placePhoto;
        TextView placeRate;
        ImageButton placeGps,placeInfo;

        ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            placeName = (TextView)itemView.findViewById(R.id.place_name);
            placeDescription = (TextView)itemView.findViewById(R.id.place_description);
            placePhoto = (ImageView)itemView.findViewById(R.id.place_photo);
            placeRate =(TextView)itemView.findViewById(R.id.place_rate);
            placeGps=(ImageButton)itemView.findViewById(R.id.btn_placeGps);
            placeInfo=(ImageButton)itemView.findViewById(R.id.btn_info_place);
        }
    }

    List<ObjetoInfo> objetos;

    RVAdapter(List<ObjetoInfo> objetos){
        this.objetos = objetos;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder personViewHolder, int i) {
        personViewHolder.placeName.setText(objetos.get(i).name);
        personViewHolder.placeDescription.setText(objetos.get(i).from);
        //personViewHolder.placeRate.setText(String.valueOf(places.get(i)._rate));
        //personViewHolder.placePhoto.setImageResource(places.get(i)._image);
        Context context =personViewHolder.placePhoto.getContext();
        //Arregla el lag del recylerView

        Picasso.with(context).load(objetos.get(i).image).fit().centerCrop().into(personViewHolder.placePhoto);
        //boton para la geolocalizacion
        personViewHolder.placeGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context cont =personViewHolder.placeGps.getContext();
                int position= personViewHolder.getAdapterPosition();
              /* String uri = String.format(Locale.ENGLISH, "geo:%f,%f",places.get(position)._lat, places.get(position)._long);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                cont.startActivity(intent);*/
            }
        });
        //Boton para mostrar la informacion
        personViewHolder.placeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position= personViewHolder.getAdapterPosition();
                Context cont =personViewHolder.placeInfo.getContext();
                Intent infoActivity = new Intent(cont, objectDetail.class);
                Bundle b = new Bundle();
                b.putString("image",objetos.get(position).image);
                b.putString("name",objetos.get(position).name);
                //b.putDouble(lugar.Column.TEMP,places.get(position)._temp);
                b.putString("description",objetos.get(position).from);
                infoActivity.putExtras(b);
                cont.startActivity(infoActivity);

            }
        });
    }

    @Override
    public int getItemCount() {
        return objetos.size();
    }
}
