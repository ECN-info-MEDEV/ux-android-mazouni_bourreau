package co.stormix.je.data.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

import org.ocpsoft.prettytime.PrettyTime;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Offer {
  private User client;
  private Company company;
  private String description;
  private String logo;
  private Date createdAt;
  private Date expiresAt;
  private String id;

  // Client, Company, createdAt, expiresAt, description, logo
  public Offer(String id, User client, Company company, String description, String logo, Date createdAt, Date expiresAt) {
    this.id = id;
    this.client = client;
    this.company = company;
    this.description = description;
    this.logo = logo;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  public static Offer createFakeOffer(String id) {
    Lorem lorem = LoremIpsum.getInstance();
    long aDay = TimeUnit.DAYS.toMillis(1);
    long now = new Date().getTime();
    int days =  new Random().nextInt(10);
    return new Offer(
        id,
        User.createFakeUser(id),
        Company.createFakeCompany(id),
        lorem.getParagraphs(2, 4),
        "https://cdn2.iconfinder.com/data/icons/apple-tv-1/512/apple_logo-512.png",
        new Date(now - aDay * days),
        new Date(now + aDay * days * 2)
    );
  }

  public static ArrayList<Offer> createFakeOfferList(int count) {
    ArrayList<Offer> offers = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      offers.add(Offer.createFakeOffer(String.valueOf(i)));
    }

    return offers;
  }

  public User getClient() {
    return client;
  }

  public void setClient(User client) {
    this.client = client;
  }

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHumanCreatedAt() {
    PrettyTime p = new PrettyTime();
    return p.format(this.createdAt);
  }

  public String getDuration() {
    // TODO
    return "6 months";
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Date expiresAt) {
    this.expiresAt = expiresAt;
  }

  public String getId() {
    return id;
  }

  public String getExcerpt() {
    return this.description.substring(0, 80);
  }
}
