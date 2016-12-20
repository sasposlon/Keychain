package hr.keychain.keychain.adapteri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hr.keychain.keychain.R;
import hr.keychain.keychain.klase.Izbornik;

/**
 * Created by anto_ on 20.12.2016..
 */

public class IzbornikAdapter extends RecyclerView.Adapter<IzbornikAdapter.IzbornikViewHolder>  {

    private List<Izbornik> izbornikList;

    public class IzbornikViewHolder extends RecyclerView.ViewHolder {

        public TextView text;
        public ImageView image;


        public IzbornikViewHolder(View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);

        }

    }

    public IzbornikAdapter(List<Izbornik> dataList) {
        this.izbornikList = dataList;
    }

    @Override
    public IzbornikViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_izbornik, parent, false);
        return new IzbornikViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IzbornikViewHolder holder, int position) {
        // popunjavanje
        Izbornik iz = izbornikList.get(position);
        holder.text.setText(iz.getNaziv());
        holder.image.setImageResource(iz.getImage());

    }

    @Override
    public int getItemCount() {
        return izbornikList.size();
    }
}


