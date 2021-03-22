package co.stormix.je.ui.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.stormix.je.data.OfferRepository;
import co.stormix.je.data.model.Offer;

public class MainViewModel extends AndroidViewModel {
  private OfferRepository repository;
  private LiveData<List<Offer>> allOffers;
  private MutableLiveData<List<Offer>> searchResults;

  public MainViewModel(Application application) {
    super(application);
    repository = new OfferRepository();
    allOffers = repository.findAll();
    searchResults = repository.getSearchResults();
  }

  public LiveData<List<Offer>> getAllOffers() {
    return allOffers;
  }

  public OfferRepository getRepository() {
    return repository;
  }

  public MutableLiveData<List<Offer>> getSearchResults() {
    return searchResults;
  }

    public void findOffer(String keyword) {
        // TODO: update search results with values from the repository
    }
    public void findByID(String id) {
        // TODO: update search results with values from the repository
    }
}