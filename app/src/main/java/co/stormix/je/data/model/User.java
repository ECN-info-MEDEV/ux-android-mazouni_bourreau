package co.stormix.je.data.model;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class User {

    private String userId;
    private String displayName;
    private String email;

    public User(String userId, String displayName, String email) {
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    /**
     * Generates a random User
     * @param id User ID
     * @return User with random info
     */
    public static User createFakeUser(String id){
        Lorem lorem = LoremIpsum.getInstance();
        return new User(id, lorem.getName(), lorem.getEmail());
    }
}