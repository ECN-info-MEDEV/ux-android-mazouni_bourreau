package co.stormix.je.ui.components;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.stormix.je.R;
import co.stormix.je.data.model.Offer;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Offer}.
 * TODO: Replace the implementation with code for your data type.
 */
public class OfferRecyclerViewAdapter extends RecyclerView.Adapter<OfferRecyclerViewAdapter.ViewHolder> {

    private final List<Offer> mValues;

    public OfferRecyclerViewAdapter(List<Offer> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mClientName.setText(mValues.get(position).getClient().getDisplayName());
        holder.mCompanyName.setText(mValues.get(position).getCompany().getDisplayName());
        holder.mCompanySite.setText(mValues.get(position).getCompany().getSite());
        holder.mCompanyType.setText(mValues.get(position).getCompany().getCompanyType());
        holder.mDuration.setText(mValues.get(position).getDuration());
        holder.mCreatedAt.setText(mValues.get(position).getHumanCreatedAt());
        holder.mDescription.setText(mValues.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mClientName;
        public final TextView mCompanyName;
        public final TextView mCompanySite;
        public final TextView mCompanyType;
        public final TextView mDuration;
        public final TextView mCreatedAt;
        public final TextView mDescription;
        public Offer mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mClientName = (TextView) view.findViewById(R.id.client_name);
            mCompanyName = (TextView) view.findViewById(R.id.company_name);
            mCompanySite = (TextView) view.findViewById(R.id.company_site);
            mCompanyType = (TextView) view.findViewById(R.id.company_type);
            mDuration = (TextView) view.findViewById(R.id.duration);
            mCreatedAt = (TextView) view.findViewById(R.id.created_at);
            mDescription = (TextView) view.findViewById(R.id.description);
        }

    }
}