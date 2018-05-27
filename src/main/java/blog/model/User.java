package blog.model;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @Column
    private String username;

    @Column
    private String passwordHash;

    @Column
    private String fullName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProfilePhoto profilePhoto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts = new ArrayList<Post>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String password) {

        this.passwordHash = Hashing.sha256()
                .hashString(password, Charsets.UTF_8)
                .toString();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ProfilePhoto getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(ProfilePhoto profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
