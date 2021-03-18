package co.stormix.je.data.model;

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
        User user = new User(id, "Anas Mazouni");
        return user;
    }
}