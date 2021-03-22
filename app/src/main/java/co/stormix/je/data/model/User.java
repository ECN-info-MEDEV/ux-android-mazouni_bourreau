package co.stormix.je.data.model;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private String userId;
    private String displayName;

    public User(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static User createFakeUser(String id){
        Lorem lorem = LoremIpsum.getInstance();
        return new User(id, lorem.getName());
    }
}