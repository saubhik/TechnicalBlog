import blog.model.Post;
import blog.services.PostService;
import blog.services.PostServiceImp;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPostService {

    @Test
    public void testCreatePost() {

        PostService postService = new PostServiceImp();
        Post post = new Post();
        post.setTitle("Test1");
        post.setBody("This is part of unit test case");
        postService.create(post);

        Post postUsingGetOperation = postService.findByTitle("Test1");

        Assert.assertEquals(postUsingGetOperation.getTitle(), post.getTitle());
        Assert.assertEquals(postUsingGetOperation.getBody(), post.getBody());
    }
}
