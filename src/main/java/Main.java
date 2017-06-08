import controllers.*;
import models.*;
import services.BootStrapServices;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by Jhon on 7/6/2017.
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        BootStrapServices.startDb();
//        UserDao.create(new User(1,"jhon","fernandez","1234",true,false));
//        UserDao.create(new User(2,"Marlon","Almanzar","2345",true,true));
//        UserDao.create(new User(3,"jaime","Cueto","1423",true,false));
//        UserDao.create(new User(4,"veronica","Ortega","3214",true,true));
//        TagDao.create(new Tag(1,"comida"));
//        TagDao.create(new Tag(2,"bebida"));
//        TagDao.create(new Tag(3,"comedia"));
//        TagDao.create(new Tag(4,"medicina"));
//        ArticleDao.create(new Article(1,"titulo 1",UserDao.find(1),"body 1", Date.valueOf(LocalDate.now())));
//        ArticleDao.create(new Article(2,"titulo 2",UserDao.find(3),"body 2", Date.valueOf(LocalDate.now())));
//        ArticleDao.create(new Article(3,"titulo 3",UserDao.find(2),"body 3", Date.valueOf(LocalDate.now())));
//        ArticleDao.create(new Article(4,"titulo 4",UserDao.find(1),"body 4", Date.valueOf(LocalDate.now())));
//        ArticleTagDao.create(new ArticleTag(1,1));
//        ArticleTagDao.create(new ArticleTag(1,2));
//        ArticleTagDao.create(new ArticleTag(1,3));
//        ArticleTagDao.create(new ArticleTag(2,2));
//        ArticleTagDao.create(new ArticleTag(2,3));
//        CommentDao.create(new Comment(1,ArticleDao.find(1),"C body 1",UserDao.find(1)));
//        CommentDao.create(new Comment(2,ArticleDao.find(1),"C body 2",UserDao.find(2)));
//        CommentDao.create(new Comment(3,ArticleDao.find(2),"C body 3",UserDao.find(3)));

        TagDao.findEntities().stream().forEach(System.out::println);
        UserDao.findEntities().stream().forEach(System.out::println);
        ArticleDao.findEntities().stream().forEach(System.out::println);

        System.out.println(CommentDao.findEntities());

        BootStrapServices.stopDb();
    }
}
