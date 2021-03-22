package co.stormix.je.ui.components;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.stormix.je.R;
import co.stormix.je.data.model.Offer;
import co.stormix.je.ui.main.MainViewModel;

/**
 * A fragment representing a list of Items.
 */
public class OfferFragmentList extends Fragment {
  private MainViewModel mViewModel;
  private OfferRecyclerViewAdapter adapter;

  /**
   * Mandatory empty constructor for the fragment manager to instantiate the
   * fragment (e.g. upon screen orientation changes).
   */
  public OfferFragmentList() {
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    observerSetup();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_item_list, container, false);

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      adapter = new OfferRecyclerViewAdapter(R.layout.fragment_item_list);
      recyclerView.setLayoutManager(new LinearLayoutManager(context));
      recyclerView.setAdapter(adapter);
    }
    return view;
  }

  private void listenerSetup() {
    // TODO: setup listener for search button and call mViewModel.findOffer()
  }

  private void observerSetup() {
    mViewModel.getAllOffers().observe(getViewLifecycleOwner(), new Observer<List<Offer>>() {
      @Override
      public void onChanged(@Nullable final List<Offer> offers) {
        adapter.set0fferList(offers);
      }
    });

    mViewModel.getSearchResults().observe(getViewLifecycleOwner(), new Observer<List<Offer>>() {
      @Override
      public void onChanged(@Nullable final List<Offer> offers) {
        if (offers.size() > 0) {
          Log.d("Search", "Results found.");
        } else {
          Log.d("Search", "No results found.");
        }
      }
    });
  }

}