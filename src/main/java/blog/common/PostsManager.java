package blog.common;

import blog.model.Post;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostsManager extends SessionManager {

    private FileOperations<Post> fileOperations;

    public PostsManager() {
        fileOperations = FileOperations.getInstance();
    }

    public Post createNewPost(final Post post) {

        FileOperations.getInstance().writeToFile(Constants.POST_FILE_PREFIX, post, post.getTitle());

        return null;
    }

    public List<Post> readAllPosts() {
        Session session = openSession();
        List<Post> posts = session.createCriteria(Post.class).list();
        commitSession(session);
        return posts;
    }

    public List<Post> getThreePosts() {
        Session session = openSession();
        Criteria criteria = session.createCriteria(Post.class);
        criteria.setMaxResults(3);
        List<Post> posts = criteria.list();
        commitSession(session);
        return posts;
    }

    public long numberOfPosts() {
        Session session = openSession();
        Long noOfPosts = (Long) session.createCriteria(Post.class).setProjection(Projections.rowCount()).uniqueResult();
        commitSession(session);
        return noOfPosts;
    }

    @SuppressWarnings("unchecked")
    public Post readPost(final long postId) {
        Session session = openSession();
        Post post = (Post) session.get(Post.class, postId);
        commitSession(session);
        return post;
    }

    public void updatePost(final String body, final int postId) {
        Session session = openSession();
        Query query = session.createQuery("Update post set post body = \'" + body + "\' where id= :postId");
        query.setParameter("postId", postId);
        query.executeUpdate();
        commitSession(session);
    }

    public void deletePost(final Long postId) {
        Session session = openSession();
        Query query = session.createQuery("Delete from " + Post.class.getName() + "where id= :postId");
        query.setParameter("postId", postId);
        query.executeUpdate();
        commitSession(session);
    }

    public static void main(String[] args) {
        PostsManager postsManager = new PostsManager();
        postsManager.numberOfPosts();
        System.out.printf("");
    }

    public Post writeToFile(final Post post) {
        Session session = openSession();
        session.save(post);
        commitSession(session);
        return post;
    }

    public Post getPost(final String prefix) {
        return (Post) fileOperations.readFile(Constants.POST_FILE_PREFIX, prefix);
    }

}
