package co.stormix.je.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.stormix.je.data.model.Offer;

public class OfferRepository {
  private MutableLiveData<List<Offer>> searchResults = new MutableLiveData<>();
  private MutableLiveData<List<Offer>> offers;

  public OfferRepository(Application application) {
    offers = new MutableLiveData<>();
    offers.setValue(Offer.createFakeOfferList(20));
  }

  public OfferRepository() {
    offers = new MutableLiveData<>();
    offers.setValue(Offer.createFakeOfferList(20));
  }

    public LiveData<List<Offer>> findAll() {
        return offers;
    }

    public Offer findById(String id) {
        int idx = Integer.parseInt(id);
        return offers.getValue().get(idx);
    }

  public MutableLiveData<List<Offer>> getSearchResults() {
    return searchResults;
  }
}
