import com.github.stormwyrm.javawebsample.register_login_sys.dao.IUserDao;
import com.github.stormwyrm.javawebsample.register_login_sys.dao.UserDaoImp;
import com.github.stormwyrm.javawebsample.register_login_sys.domain.User;

public class UserDaoTest {
    public static void main(String[] args) {
        IUserDao userDao = new UserDaoImp();
        User liqingfeng = userDao.findUser("liqingfeng");

        User user = userDao.findUser("liqingfeng","fengfeng1314.");
    }
}
