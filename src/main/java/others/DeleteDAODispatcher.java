package others;

import DAOs.*;

public class DeleteDAODispatcher {
    public static DAO getDAO(String type) {
        DAO dao = null;
        if ("question".equals(type)) {
            dao = new QuestionDAO();
        }

        return dao;
    }
}
